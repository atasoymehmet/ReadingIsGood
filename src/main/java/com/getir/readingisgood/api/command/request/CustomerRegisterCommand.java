package com.getir.readingisgood.api.command.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerRegisterCommand {

    @NotBlank(message = "customerEmail can not be blank")
    public String firstName;
    @NotBlank(message = "customerEmail can not be blank")
    public String lastName;
    @NotBlank(message = "customerEmail can not be blank")
    private String email;
    @NotBlank(message = "customerEmail can not be blank")
    private String password;
}
