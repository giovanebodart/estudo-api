package com.estudos.api.execptions;

public class ValorInvalido extends RuntimeException{

    public ValorInvalido(String message) {
        super(message);
    }
}
