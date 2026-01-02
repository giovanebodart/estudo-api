package com.estudos.api.domain.entitiesDTO;

public record PacienteResponseDTO(String nome, Integer idade, Double peso, Double altura, String diagnostico, String tratamento) {
}