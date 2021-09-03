package com.getir.readingisgood.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
public class Order {

    @Id
    @Indexed(unique = true)
    private String id;

    @NotBlank
    private String customerEmail;

    private LocalDate createDate;

    @NotBlank
    private String bookId;

    @PositiveOrZero(message = "Count must be bigger than zero")
    private Long purchasedBookCount;

    private OrderStatus status;

    @Positive
    private double price;

}
