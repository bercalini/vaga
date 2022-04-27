package com.bercalini.vaga.exception;

public class CandidatoNaoEncontradaException extends RuntimeException{
    public CandidatoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
