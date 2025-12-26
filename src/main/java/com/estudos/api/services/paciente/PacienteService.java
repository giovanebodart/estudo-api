package com.estudos.api.services.paciente;

import com.estudos.api.domain.paciente.Paciente;
import com.estudos.api.domain.pacienteDTO.PacienteResponseDTO;
import com.estudos.api.domain.pacienteDTO.PacienteResquestDTO;
import com.estudos.api.domain.pacienteDTO.PacienteUpdateDTO;
import com.estudos.api.execptions.EntidadePreExistente;
import com.estudos.api.execptions.ValorInvalido;
import com.estudos.api.execptions.ValorNaoEncontrado;
import com.estudos.api.repository.PacienteRepository;
import com.estudos.api.services.date.getIdade;
import com.estudos.api.services.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService{
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private getIdade getIdade;
    @Autowired
    private ValidationService validate;

    public Optional<Paciente> buscarPorId(String id){
        return pacienteRepository.findById(id);
    }

    public List<PacienteResponseDTO> visualizar(String nome){
        if(!(validate.nameValidation(nome))) throw new ValorInvalido("Nome inválido");
        List<Paciente> pacienteList = pacienteRepository.findByNome(nome);
        if(pacienteList.isEmpty()) throw new ValorNaoEncontrado("Não foi possivel encontrar o nome solicitado");
        List<PacienteResponseDTO> pacienteListDTO = new ArrayList<>();
        for (Paciente paciente: pacienteList){
            PacienteResponseDTO pacienteDTO = new PacienteResponseDTO(
                    paciente.getNome(),
                    getIdade.idade(paciente.getDataDeNascimento()),
                    paciente.getPeso(),
                    paciente.getAltura(),
                    paciente.getDiagnostico(),
                    paciente.getTratamento());
            pacienteListDTO.add(pacienteDTO);
        }
        return pacienteListDTO;
    }

    public void cadastrar(PacienteResquestDTO dto){
        validate.validateAll(dto);
        Paciente paciente = new Paciente();
        paciente.setNome(dto.nome());
        paciente.setDataDeNascimento(dto.dataDeNascimento());
        paciente.setSexo(dto.sexo());
        paciente.setTelefone(dto.telefone());
        paciente.setCpf(dto.cpf());
        paciente.setAltura(dto.altura());
        paciente.setPeso(dto.peso());
        paciente.setDiagnostico(dto.diagnostico());
        paciente.setTratamento(dto.tratamento());
        pacienteRepository.save(paciente);
    }

    public void deletar(String Id) {
        Paciente paciente = buscarPorId(Id).orElseThrow(() -> new ValorNaoEncontrado("Não foi encontrado nenhum registro"));
        pacienteRepository.delete(paciente);
    }

    public void atualizar(String Id, PacienteUpdateDTO pacienteAtualizar){
        Paciente paciente = buscarPorId(Id).get();
        paciente.setNome(pacienteAtualizar.nome());
        paciente.setTelefone(pacienteAtualizar.telefone());
        paciente.setPeso(pacienteAtualizar.peso());
        paciente.setAltura(pacienteAtualizar.altura());
        pacienteRepository.save(paciente);
    }
}
