package org.sawyron.librarygateway.books.impl;

import org.sawyron.librarygateway.books.BookService;
import org.sawyron.librarygateway.books.BookServiceClient;
import org.sawyron.librarygateway.books.dtos.BookResponse;
import org.sawyron.librarygateway.books.dtos.CreateBookRequest;
import org.sawyron.librarygateway.books.dtos.UpdateBookMessage;
import org.sawyron.librarygateway.books.dtos.UpdateBookRequest;
import org.sawyron.librarygateway.books.properties.BookRabbitMqRouteProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final RabbitTemplate template;
    private final BookServiceClient bookServiceClient;
    private final BookRabbitMqRouteProperties properties;

    public BookServiceImpl(
            RabbitTemplate template,
            BookServiceClient bookServiceClient,
            BookRabbitMqRouteProperties properties
    ) {
        this.template = template;
        this.bookServiceClient = bookServiceClient;
        this.properties = properties;
    }

    @Override
    public void createBook(CreateBookRequest request) {
        template.convertAndSend(properties.exchange(), properties.createRoute(), request);
        logger.info("Book create message send. {}", request);
    }

    @Override
    public BookResponse findBookById(UUID id) {
        return bookServiceClient.getById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Book with id %s was not found".formatted(id)));
    }

    @Override
    public List<BookResponse> findBooks(int page, int pageSize) {
        return bookServiceClient.getBookPage(page, pageSize);
    }

    @Override
    public void updateBook(UUID id, UpdateBookRequest request) {
        var message = new UpdateBookMessage(id, request.title(), request.author(), request.publishedDate());
        template.convertAndSend(properties.exchange(), properties.updateRoute(), message);
        logger.info("Book update message send. {}", message);
    }

    @Override
    public void deleteBookById(UUID id) {
        template.convertAndSend(properties.exchange(), properties.deleteRoute(), id);
        logger.info("Book delete message send. {}", id);
    }
}
