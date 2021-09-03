package com.getir.readingisgood.api.command.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CustomerOrdersCommand {

    @NotNull
    @NotEmpty
    private String customerEmail;
    private Integer pageNumber;
    private Integer pageSize;
}
