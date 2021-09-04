package com.getir.readingisgood.api.command.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
public class UpdateBookStockCommand {

    @NotBlank(message = "id can not be blank")
    private String id;

    @PositiveOrZero
    private Long stock;
}
