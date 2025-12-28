package com.estudos.api.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "pacientes")
@Entity(name = "pacientes")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String sexo;
    private String telefone;
    private LocalDate dataDeNascimento;
    private String cpf;
    private Double peso;
    private Double altura;
    private String diagnostico;
    private String tratamento;
}
