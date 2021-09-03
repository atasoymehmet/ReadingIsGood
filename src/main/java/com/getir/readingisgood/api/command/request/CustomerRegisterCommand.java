package com.getir.readingisgood.api.command.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CustomerRegisterCommand {

    @NotNull
    @NotEmpty
    public String firstName;
    @NotNull
    @NotEmpty
    public String lastName;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String password;
}
