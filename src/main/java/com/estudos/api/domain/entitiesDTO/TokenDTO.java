package com.estudos.api.domain.entitiesDTO;

import java.time.LocalDateTime;

public record TokenDTO(String token, LocalDateTime timestamp) {
}
