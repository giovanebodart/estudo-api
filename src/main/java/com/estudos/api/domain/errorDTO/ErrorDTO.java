package com.estudos.api.domain.errorDTO;

import java.time.LocalDateTime;

public record ErrorDTO(LocalDateTime timestamp, String description) {
}
