package com.estudos.api.services.paciente;

import com.estudos.api.domain.entities.Paciente;
import com.estudos.api.domain.entitiesDTO.PacienteResponseDTO;
import com.estudos.api.domain.entitiesDTO.PacienteResquestDTO;
import com.estudos.api.execptions.ValorInvalido;
import com.estudos.api.execptions.ValorNaoEncontrado;
import com.estudos.api.repository.PacienteRepository;
import com.estudos.api.services.date.getIdade;
import com.estudos.api.services.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void atualizar(String Id, PacienteResquestDTO pacienteAtualizar){
        Paciente paciente = buscarPorId(Id).get();
        String nome = paciente.getNome().equals(pacienteAtualizar.nome()) || pacienteAtualizar.nome() == null
                ? paciente.getNome() : pacienteAtualizar.nome();
        Double peso = paciente.getPeso().equals(pacienteAtualizar.peso()) || pacienteAtualizar.peso() == null
                ? paciente.getPeso() : pacienteAtualizar.peso();
        Double altura = paciente.getAltura().equals(pacienteAtualizar.altura()) || pacienteAtualizar.altura() == null
                ? paciente.getAltura() : pacienteAtualizar.altura();
        String telefone = paciente.getTelefone().equals(pacienteAtualizar.telefone()) || pacienteAtualizar.telefone() == null
                ? paciente.getTelefone() : pacienteAtualizar.telefone();
        String diagnostico = paciente.getDiagnostico().equals(pacienteAtualizar.diagnostico()) || pacienteAtualizar.diagnostico() == null
                ? paciente.getDiagnostico() : pacienteAtualizar.diagnostico();
        String tratamento = paciente.getTratamento().equals(pacienteAtualizar.tratamento()) || pacienteAtualizar.tratamento() == null
                ? paciente.getTratamento() : pacienteAtualizar.tratamento();
        LocalDate dateDeNascimento = paciente.getDataDeNascimento().equals(pacienteAtualizar.dataDeNascimento()) || pacienteAtualizar.dataDeNascimento() == null
                ? paciente.getDataDeNascimento() : pacienteAtualizar.dataDeNascimento();
        String sexo = paciente.getSexo().equals(pacienteAtualizar.sexo()) || pacienteAtualizar.sexo() == null
                ? paciente.getSexo() : pacienteAtualizar.sexo();
        String cpf = paciente.getCpf().equals(pacienteAtualizar.cpf()) || pacienteAtualizar.cpf() == null
                ? paciente.getCpf() : pacienteAtualizar.cpf();
        paciente.setNome(nome);
        paciente.setTelefone(telefone);
        paciente.setPeso(peso);
        paciente.setAltura(altura);
        paciente.setTratamento(tratamento);
        paciente.setCpf(cpf);
        paciente.setSexo(sexo);
        paciente.setDataDeNascimento(dateDeNascimento);
        paciente.setDiagnostico(diagnostico);
        pacienteRepository.save(paciente);
    }
}
