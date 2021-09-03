package com.getir.readingisgood.domain.repository;

import com.getir.readingisgood.domain.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findByEmail(String email);

    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);
}
