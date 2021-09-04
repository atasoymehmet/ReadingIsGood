package com.getir.readingisgood.api.command.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerOrdersCommand {

    @NotBlank(message = "customerEmail can not be blank")
    private String customerEmail;
    private Integer pageNumber;
    private Integer pageSize;
}
