package com.getir.readingisgood.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "book")
public class Book {

    @Id
    @Indexed(unique = true)
    private String id;

    @Indexed(unique = true)
    private String isbnNumber;

    @NotBlank
    private String bookName;

    @PositiveOrZero
    private Long stockCount;

    @Positive
    private double price;
}
