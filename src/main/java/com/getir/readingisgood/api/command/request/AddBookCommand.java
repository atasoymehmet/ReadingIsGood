package com.getir.readingisgood.api.command.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AddBookCommand {

    @NotBlank(message = "isbnNumber can not be blank")
    private String isbnNumber;

    @NotBlank(message = "bookName can not be blank")
    private String bookName;

    @PositiveOrZero
    private Long stockCount;

    @Positive
    private double price;

}