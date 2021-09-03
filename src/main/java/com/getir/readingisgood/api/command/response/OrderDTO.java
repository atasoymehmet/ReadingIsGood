package com.getir.readingisgood.api.command.response;

import com.getir.readingisgood.domain.model.OrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@Builder
@Getter
public class OrderDTO {

    private String id;
    private String customerEmail;
    private LocalDate createDate;
    private String bookId;
    private Long purchasedBookCount;
    private OrderStatus status;
    private double price;
}
