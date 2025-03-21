package com.Libri.dtos;

import com.Libri.models.enums.BookStatus;

import java.util.UUID;

public record BookDto(String googleBookId, UUID libraryId) {
}
