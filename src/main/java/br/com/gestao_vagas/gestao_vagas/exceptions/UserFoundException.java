package br.com.gestao_vagas.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException() {
        super("Username já existe");
    }
}
