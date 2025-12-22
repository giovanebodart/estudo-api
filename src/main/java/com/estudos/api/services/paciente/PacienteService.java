package com.estudos.api.services.paciente;

import com.estudos.api.domain.paciente.Paciente;
import com.estudos.api.domain.pacienteDTO.PacienteResponseDTO;
import com.estudos.api.domain.pacienteDTO.PacienteResquestDTO;
import com.estudos.api.execptions.EntidadePreExistente;
import com.estudos.api.execptions.ValorInvalido;
import com.estudos.api.execptions.ValorNaoEncontrado;
import com.estudos.api.repository.PacienteRepository;
import com.estudos.api.services.date.getIdade;
import com.estudos.api.services.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService{
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private getIdade getIdade;
    @Autowired
    private ValidationService validate;

    public boolean buscarPorTelefone (String telefone){
        Paciente paciente = pacienteRepository.findByTelefone(telefone);
        return paciente.getTelefone().isEmpty();
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
        if(!(buscarPorTelefone(dto.telefone()))) throw new EntidadePreExistente("Ja existe um paciente com esse telefone cadastrado no sistema");
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


}
