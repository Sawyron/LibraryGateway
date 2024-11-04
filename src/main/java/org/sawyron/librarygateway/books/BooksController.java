package org.sawyron.librarygateway.books;

import org.sawyron.librarygateway.books.dtos.BookResponse;
import org.sawyron.librarygateway.books.dtos.CreateBookRequest;
import org.sawyron.librarygateway.books.dtos.UpdateBookRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable UUID id) {
        BookResponse bookResponse = bookService.findBookById(id);
        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getBookPage(@RequestParam int page, @RequestParam int pageSize) {
        List<BookResponse> books = bookService.findBooks(page, pageSize);
        return ResponseEntity.ok(books);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBookById(@PathVariable UUID id, @RequestBody UpdateBookRequest request) {
        bookService.updateBook(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable UUID id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
