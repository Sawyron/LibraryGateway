package org.sawyron.librarygateway.books;

import org.sawyron.librarygateway.books.dtos.BookResponse;
import org.sawyron.librarygateway.books.dtos.CreateBookRequest;
import org.sawyron.librarygateway.books.dtos.UpdateBookRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/books")
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody CreateBookRequest request) {
        bookService.createBook(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBookById(@PathVariable UUID id, @RequestBody UpdateBookRequest request) {
        bookService.updateBook(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable UUID id) {
        BookResponse bookResponse = bookService.findBookById(id);
        return ResponseEntity.ok(bookResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable UUID id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }
}
