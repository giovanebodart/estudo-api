package com.estudos.api.execptions;

public class EntidadePreExistente extends RuntimeException{
    public EntidadePreExistente(String message) {
        super(message);
    }
}
