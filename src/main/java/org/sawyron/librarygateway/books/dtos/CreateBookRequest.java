package org.sawyron.librarygateway.books.dtos;

import java.time.LocalDate;

public record CreateBookRequest(String title, String author, LocalDate publishedDate) {
}
