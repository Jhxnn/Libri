package com.Libri.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record LoanDto(LocalDate startDate, LocalDate endDate, UUID userId, UUID bookId) {
}