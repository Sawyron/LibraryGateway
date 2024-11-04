package org.sawyron.librarygateway.books;

import org.sawyron.librarygateway.books.dtos.BookResponse;
import org.sawyron.librarygateway.books.dtos.CreateBookRequest;
import org.sawyron.librarygateway.books.dtos.UpdateBookRequest;

import java.util.List;
import java.util.UUID;

public interface BookService {
    void createBook(CreateBookRequest request);

    BookResponse findBookById(UUID id);

    List<BookResponse> findBooks(int page, int pageSize);

    void updateBook(UUID id, UpdateBookRequest request);

    void deleteBookById(UUID id);
}
