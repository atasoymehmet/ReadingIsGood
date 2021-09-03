package com.getir.readingisgood.api.command.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public class AddBookCommand {

    @NotNull
    @NotEmpty
    private String isbnNumber;
    @NotNull
    @NotEmpty
    private String bookName;
    @PositiveOrZero
    private Long stockCount;
    @Positive
    private double price;

}