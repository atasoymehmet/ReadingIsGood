package com.getir.readingisgood.api.command.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerMonthlyStatCommand {

    @NotBlank(message = "customerEmail can not be blank")
    private String customerEmail;
}
