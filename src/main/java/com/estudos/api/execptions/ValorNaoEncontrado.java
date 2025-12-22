package com.estudos.api.execptions;

public class ValorNaoEncontrado extends RuntimeException{

    public ValorNaoEncontrado(String message) {
        super(message);
    }
}
