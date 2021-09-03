package com.getir.readingisgood.domain.service;

import com.getir.readingisgood.api.command.request.CustomerOrdersCommand;
import com.getir.readingisgood.api.command.response.OrderDTO;

import java.time.LocalDate;
import java.util.List;


public interface OrderService {

    OrderDTO createOrder(String bookIsbnNumber, Long count, String customerName);

    List<OrderDTO> findOrderByCustomerEmail(CustomerOrdersCommand command);

    List<OrderDTO> findAllByDate(LocalDate startDate, LocalDate endDate);

    List<OrderDTO> getCustomerAllOrders(String email);

    OrderDTO findOrderById(String id);

    List<OrderDTO> findAll();
}
