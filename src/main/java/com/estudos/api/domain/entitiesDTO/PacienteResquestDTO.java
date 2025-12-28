package com.estudos.api.domain.entitiesDTO;

import java.time.LocalDate;

public record PacienteResquestDTO(String nome, LocalDate dataDeNascimento, String sexo, String cpf, String telefone, Double altura, Double peso, String diagnostico, String tratamento) {
}
