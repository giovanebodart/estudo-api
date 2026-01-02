package com.estudos.api.controllers;

import com.estudos.api.domain.entitiesDTO.PacienteResponseDTO;
import com.estudos.api.domain.entitiesDTO.PacienteResquestDTO;
import com.estudos.api.domain.entitiesDTO.PacienteUpdateDTO;
import com.estudos.api.domain.statusDTO.okDTO;
import com.estudos.api.services.paciente.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity visualizarPacientes(@RequestParam String nome){
        List<PacienteResponseDTO> list = service.visualizar(nome);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity cadastrarPacientes(@RequestBody PacienteResquestDTO dto){
        service.cadastrar(dto);
        return  ResponseEntity.ofNullable(new okDTO("200", "Cadastro bem sucedido", LocalDateTime.now()));
    }

    @DeleteMapping
    public ResponseEntity deletarPaciente(@RequestParam String Id){
        service.deletar(Id);
        return ResponseEntity.ofNullable(new okDTO("200", "Exclusão bem sucedida", LocalDateTime.now()));
    }

    @PutMapping
    public ResponseEntity editarPacientes(@RequestParam String Id, @RequestBody PacienteResquestDTO dto){
        service.atualizar(Id, dto);
        return ResponseEntity.ofNullable(new okDTO("200", "Edição bem sucedida", LocalDateTime.now()));
    }
}
