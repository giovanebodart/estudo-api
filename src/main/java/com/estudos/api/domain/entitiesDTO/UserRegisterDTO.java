package com.estudos.api.domain.entitiesDTO;

import com.estudos.api.domain.entities.UserRole;

public record UserRegisterDTO(String login, String password, UserRole role) {
}
