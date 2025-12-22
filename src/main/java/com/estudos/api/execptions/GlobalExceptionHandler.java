package com.estudos.api.execptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ValorInvalido.class)
    public ResponseEntity<String> handleNomeInvalido(ValorInvalido ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ValorNaoEncontrado.class)
    public ResponseEntity<String> handleNomeNaoEncontrado(ValorNaoEncontrado ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
