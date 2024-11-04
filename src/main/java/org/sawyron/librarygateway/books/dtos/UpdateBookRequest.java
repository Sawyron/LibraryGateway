package org.sawyron.librarygateway.books.dtos;

import java.time.LocalDate;

public record UpdateBookRequest(String title, String author, LocalDate publishedDate) {
}
