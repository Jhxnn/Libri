package com.Libri.dtos;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

public record FineDto(UUID userId, LocalDate date) {
}
