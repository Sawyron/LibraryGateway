package org.sawyron.librarygateway.books.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateBookRequest(
        @NotBlank @Size(max = 150) String title,
        @NotBlank @Size(max = 150) String author,
        LocalDate publishedDate
) {
}
