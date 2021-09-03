package com.getir.readingisgood.api.command.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class CustomerOrderStatDTO {

    private String month;
    private Long totalOrderCount;
    private Long totalBookCount;
    private Double totalPurchasedAmount;

    public static CustomerOrderStatDTO customerOrderStatMap(Map.Entry<String, List<OrderDTO>> entry) {
        return CustomerOrderStatDTO.builder()
                .month(entry.getKey())
                .totalOrderCount((long) entry.getValue().size())
                .totalBookCount(entry.getValue().stream().mapToLong(OrderDTO::getPurchasedBookCount).sum())
                .totalPurchasedAmount(entry.getValue().stream().mapToDouble(OrderDTO::getPrice).sum())
                .build();
    }
}
