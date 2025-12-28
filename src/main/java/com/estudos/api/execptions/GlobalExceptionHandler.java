package com.estudos.api.execptions;

import com.estudos.api.domain.statusDTO.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ValorInvalido.class)
    public ResponseEntity<ErrorDTO> handleValorInvalido(ValorInvalido ex){
        return ResponseEntity
                .ofNullable(new ErrorDTO("403", "BAD_REQUEST", ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(ValorNaoEncontrado.class)
    public ResponseEntity<ErrorDTO> handleValorNaoEncontrado(ValorNaoEncontrado ex) {
        return ResponseEntity
                .ofNullable(new ErrorDTO("404", "NOT_FOUND", ex.getMessage(), LocalDateTime.now()));
    }
}
