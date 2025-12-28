package com.estudos.api.domain.statusDTO;

import java.time.LocalDateTime;

public record ErrorDTO(String status, String error, String message, LocalDateTime timestamp) {
}
