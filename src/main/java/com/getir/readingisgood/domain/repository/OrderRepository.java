package com.getir.readingisgood.domain.repository;

import com.getir.readingisgood.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Page<Order> findOrderByCustomerEmailIgnoreCase(String customerEmail, Pageable page);

    List<Order> findByCreateDateBetween(LocalDate startDate, LocalDate endDate);

    List<Order> findAllByCustomerEmail(String email);

}
