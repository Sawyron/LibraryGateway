package org.sawyron.librarygateway.books.impl;

import org.sawyron.librarygateway.books.BookService;
import org.sawyron.librarygateway.books.dtos.BookResponse;
import org.sawyron.librarygateway.books.dtos.CreateBookRequest;
import org.sawyron.librarygateway.books.dtos.UpdateBookMessage;
import org.sawyron.librarygateway.books.dtos.UpdateBookRequest;
import org.sawyron.librarygateway.books.properties.BookRabbitMqRouteProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final RabbitTemplate template;
    private final BookRabbitMqRouteProperties properties;

    public BookServiceImpl(RabbitTemplate template, BookRabbitMqRouteProperties properties) {
        this.template = template;
        this.properties = properties;
    }

    @Override
    public void createBook(CreateBookRequest request) {
        template.convertAndSend(properties.exchange(), properties.createRoute(), request);
        logger.info("Book create message send. {}", request);
    }

    @Override
    public BookResponse findBookById(UUID id) {
        return new BookResponse(id, "title", "author", LocalDate.now());
    }

    @Override
    public List<BookResponse> findBooks(int limit, long offset) {
        return List.of();
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
