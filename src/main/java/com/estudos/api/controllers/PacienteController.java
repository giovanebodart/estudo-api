package com.estudos.api.controllers;

import com.estudos.api.domain.pacienteDTO.PacienteResponseDTO;
import com.estudos.api.domain.pacienteDTO.PacienteResquestDTO;
import com.estudos.api.services.paciente.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping("/visualizar")
    public ResponseEntity visualizarPacientes(@RequestParam String nome){
        List<PacienteResponseDTO> list = service.visualizar(nome);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarPacientes(@RequestBody PacienteResquestDTO dto){
        service.cadastrar(dto);
        return  ResponseEntity.ok().body("Cliente cadastrado com êxito");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity deletarPaciente(@RequestBody String cpf){

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/editar")
    public ResponseEntity editarPacientes(@RequestBody PacienteResquestDTO dto){

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
