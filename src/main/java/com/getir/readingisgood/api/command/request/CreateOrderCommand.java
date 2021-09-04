package com.getir.readingisgood.api.command.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CreateOrderCommand {

    @NotBlank(message = "bookIsbnNumber can not be blank")
    private String bookIsbnNumber;

    @Positive(message = "Count must be bigger than zero")
    private Long count;

    @Email(message = "Please provide valid email address")
    private String customerEmail;
}
