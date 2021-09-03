package com.getir.readingisgood.converter;

import com.getir.readingisgood.api.command.response.OrderDTO;
import com.getir.readingisgood.domain.model.Order;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class OrderDTOConverter extends BaseConverter<Order, OrderDTO> {
    @Override
    public OrderDTO convert(@NonNull Order source) {
        return OrderDTO.builder()
                .id(source.getId())
                .customerEmail(source.getCustomerEmail())
                .createDate(source.getCreateDate())
                .bookId(source.getBookId())
                .purchasedBookCount(source.getPurchasedBookCount())
                .status(source.getStatus())
                .price(source.getPrice())
                .build();
    }
}