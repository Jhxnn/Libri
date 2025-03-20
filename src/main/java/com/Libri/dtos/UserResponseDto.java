package com.Libri.dtos;

import java.util.UUID;

public record UserResponseDto(UUID userId, String name, String email) {
}
