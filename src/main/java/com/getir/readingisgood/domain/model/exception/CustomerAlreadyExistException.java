package com.getir.readingisgood.domain.model.exception;

public class CustomerAlreadyExistException extends RuntimeException {
    public CustomerAlreadyExistException(String msg) {
        super(msg);
    }
}
