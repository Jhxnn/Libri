package com.Libri.dtos;

import java.sql.Timestamp;
import java.util.UUID;

public record FineDto(UUID userId, double value, Timestamp time) {
}
