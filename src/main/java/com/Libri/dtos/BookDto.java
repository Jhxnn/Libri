package com.Libri.dtos;

import com.Libri.models.enums.BookStatus;

import java.util.UUID;

public record BookDto(String title, String description, String author, UUID libraryId, BookStatus status) {
}
