package com.estudos.api.services.validation;

import com.estudos.api.domain.paciente.Paciente;
import com.estudos.api.domain.pacienteDTO.PacienteResquestDTO;
import com.estudos.api.execptions.ValorInvalido;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidationService {

    public boolean nameValidation(String nome){
        return (nome.matches("([a-zA-Z](\s)?)+"));
    }

    public boolean dateValidation(LocalDate date){
        return date.toString().matches("(\\d{4})[-/.](\\d{2})[-/.](\\d{2})");
    }

    public boolean cpfValidation(String cpf){
        return cpf.matches("([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})");
    }

    public boolean telefoneValidation(String telefone){
        return telefone.matches("^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$");
    }

    public boolean alturaValidation(Double altura){
        return altura.toString().matches("[1-2][,\\.][0-9]{2,}");
    }

    public boolean pesoValidation(Double peso){
        return peso.toString().matches("\\d+(\\.\\d{1,2})?");
    }

    public boolean diagnosticoValidation(String diagnostico){
        return diagnostico.matches("([a-zA-Z0-9](\s)?)+");
    }

    public boolean tratamentoValidation(String diagnostico){
        return diagnostico.matches("([a-zA-Z0-9](\s)?)+");
    }

    public void validateAll(PacienteResquestDTO dto){
        if(!(nameValidation(dto.nome()))) throw new ValorInvalido("Nome inválido");
        if(!(dateValidation(dto.dataDeNascimento()))) throw new ValorInvalido("Data de nascimento invalida");
        if(!(cpfValidation(dto.cpf()))) throw new ValorInvalido("CPF invalido");
        if(!(telefoneValidation(dto.telefone()))) throw new ValorInvalido("Telefone invalido");
        if(!(alturaValidation(dto.altura()))) throw new ValorInvalido("Altura invalida");
        if(!(pesoValidation(dto.peso()))) throw new ValorInvalido("Peso invalido");
        if(!(diagnosticoValidation(dto.diagnostico()))) throw new ValorInvalido("Formato de diagnostico invalido");
        if(!(tratamentoValidation(dto.tratamento()))) throw new ValorInvalido("Formato de tratamento invalido");
    }
}
