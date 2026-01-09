package com.estudos.api.controllers;

import com.estudos.api.domain.entitiesDTO.PacienteResponseDTO;
import com.estudos.api.domain.entitiesDTO.PacienteResquestDTO;
import com.estudos.api.domain.statusDTO.okDTO;
import com.estudos.api.services.paciente.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(
            summary = "Buscar paciente por ID",
            description = "Retorna os dados de um paciente cadastrado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })

    @GetMapping
    public ResponseEntity visualizarPacientes(@RequestParam String nome){
        List<PacienteResponseDTO> list = service.visualizar(nome);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Cadastrar paciente",
            description = "Cria um novo paciente no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })

    @PostMapping
    public ResponseEntity cadastrarPacientes(@RequestBody PacienteResquestDTO dto){
        service.cadastrar(dto);
        return  ResponseEntity.ofNullable(new okDTO("200", "Cadastro bem sucedido", LocalDateTime.now()));
    }

    @Operation(
            summary = "Excluir paciente",
            description = "Remove um paciente do sistema pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })

    @DeleteMapping
    public ResponseEntity deletarPaciente(@RequestParam String Id){
        service.deletar(Id);
        return ResponseEntity.ofNullable(new okDTO("200", "Exclusão bem sucedida", LocalDateTime.now()));
    }

    @Operation(
            summary = "Atualizar paciente",
            description = "Atualiza os dados de um paciente existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })

    @PutMapping
    public ResponseEntity editarPacientes(@RequestParam String Id, @RequestBody PacienteResquestDTO dto){
        service.atualizar(Id, dto);
        return ResponseEntity.ofNullable(new okDTO("200", "Edição bem sucedida", LocalDateTime.now()));
    }
}
