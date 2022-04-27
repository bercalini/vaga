package com.bercalini.vaga.exception;

public class VagaNaoEncontradaException extends RuntimeException{
    public VagaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
