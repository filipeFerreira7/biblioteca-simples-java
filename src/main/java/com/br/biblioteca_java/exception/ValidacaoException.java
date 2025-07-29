package com.br.biblioteca_java.exception;

import lombok.Getter;

@Getter
public class ValidacaoException extends RuntimeException {

    private String fieldName;

    public ValidacaoException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }


}
