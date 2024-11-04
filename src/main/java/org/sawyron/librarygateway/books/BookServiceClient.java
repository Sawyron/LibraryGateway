package org.sawyron.librarygateway.books;

import org.sawyron.librarygateway.books.dtos.BookResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookServiceClient {
    Optional<BookResponse> getById(UUID id);

    List<BookResponse> getBookPage(int page, int pageSize);
}
