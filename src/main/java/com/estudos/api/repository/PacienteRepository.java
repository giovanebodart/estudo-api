package com.estudos.api.repository;

import com.estudos.api.domain.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String> {
    List<Paciente> findByNome(String nome);
    Paciente findByCpf(String cpf);
}
