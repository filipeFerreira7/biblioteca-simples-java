package com.br.biblioteca_java.exception;

import lombok.Getter;

@Getter
public class EmprestimoException extends RuntimeException {

    private String fieldName;

    public EmprestimoException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }


}
