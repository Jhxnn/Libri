package com.Libri.dtos;

import java.sql.Timestamp;

public record ChatDto(String sender, String content, String timestamp) {
}
