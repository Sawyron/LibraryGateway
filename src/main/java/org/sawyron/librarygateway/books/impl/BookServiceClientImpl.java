package org.sawyron.librarygateway.books.impl;

import org.sawyron.librarygateway.books.BookServiceClient;
import org.sawyron.librarygateway.books.dtos.BookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookServiceClientImpl implements BookServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(BookServiceClientImpl.class);

    private final RestClient restClient;

    public BookServiceClientImpl(RestClient restClient) {
        this.restClient = restClient;
    }


    @Override
    public Optional<BookResponse> getById(UUID id) {
        ResponseEntity<BookResponse> bookEntity = restClient.get()
                .uri("/{id}", id)
                .retrieve()
                .toEntity(BookResponse.class);
        logger.debug("Retrieved book entity: {}", bookEntity);
        if (bookEntity.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
            return Optional.ofNullable(bookEntity.getBody());
        }
        return Optional.empty();
    }

    @Override
    public List<BookResponse> getBookPage(int page, int pageSize) {
        ResponseEntity<List<BookResponse>> bookPageEntity = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("page", page)
                        .queryParam("pageSize", pageSize)
                        .build())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {
                });
        logger.debug("Retrieved book page entity {}", bookPageEntity);
        if (bookPageEntity.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
            return bookPageEntity.getBody();
        }
        return List.of();
    }
}
