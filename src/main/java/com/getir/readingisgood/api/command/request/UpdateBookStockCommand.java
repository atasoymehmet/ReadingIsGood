package com.getir.readingisgood.api.command.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
public class UpdateBookStockCommand {

    @NotNull
    @NotEmpty
    private String id;

    @PositiveOrZero
    private Long stock;
}
