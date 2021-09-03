package com.getir.readingisgood.domain.service;

import com.getir.readingisgood.api.command.response.OrderDTO;
import com.getir.readingisgood.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class StatisticsServiceImpl implements StatisticsService{

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    public Long getTotalOrderCount() {
        return orderRepository.count();
    }

    @Override
    public Map<String, List<OrderDTO>> getCustomerOrderSummaryPerMonth(String email) {
        return orderService.getCustomerAllOrders(email)
                .stream()
                .collect(Collectors.groupingBy(x -> x.getCreateDate().getMonth().toString()));
    }
}
