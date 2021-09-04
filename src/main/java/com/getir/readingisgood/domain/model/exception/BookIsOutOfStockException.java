package com.getir.readingisgood.domain.model.exception;

public class BookIsOutOfStockException extends RuntimeException{
    public BookIsOutOfStockException(String msg) {
        super(msg);
    }
}
