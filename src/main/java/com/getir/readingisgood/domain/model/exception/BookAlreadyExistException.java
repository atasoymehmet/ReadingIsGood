package com.getir.readingisgood.domain.model.exception;

public class BookAlreadyExistException extends RuntimeException {
    public BookAlreadyExistException(String msg) {
        super(msg);
    }
}
