package com.getir.readingisgood.domain.service;

import com.getir.readingisgood.api.command.response.OrderDTO;

import java.util.List;
import java.util.Map;

public interface StatisticsService {

    Long getTotalOrderCount();

    Map<String, List<OrderDTO>> getCustomerOrderSummaryPerMonth(String email);
}
