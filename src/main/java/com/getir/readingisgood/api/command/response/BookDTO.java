package com.getir.readingisgood.api.command.response;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class BookDTO {

    private String id;
    private String isbnNumber;

    @NotBlank
    private String bookName;
    private Long stockCount;
    private double price;
}
