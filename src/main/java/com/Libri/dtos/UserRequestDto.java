package com.Libri.dtos;

import com.Libri.models.enums.UserRole;

public record UserRequestDto(String name, String email, String password, String cpf, UserRole role) {
}
