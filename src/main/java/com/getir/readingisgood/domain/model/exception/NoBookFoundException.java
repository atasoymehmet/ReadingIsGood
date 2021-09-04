package com.getir.readingisgood.domain.model.exception;

public class NoBookFoundException extends RuntimeException {
    public NoBookFoundException(String msg) {
        super(msg);
    }
}
