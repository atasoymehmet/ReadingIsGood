package com.getir.readingisgood.api.command.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreateOrderCommand {

    @NotNull
    @NotEmpty
    private String bookIsbnNumber;

    @Positive(message = "Count must be bigger than zero")
    private Long count;

    @Email(message = "Please provide valid email address")
    private String customerEmail;
}
