package com.estudos.api.repository;

import com.estudos.api.domain.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String> {
    List<Paciente> findByNome(String nome);
    Optional<Paciente> findById(String Id);
}
