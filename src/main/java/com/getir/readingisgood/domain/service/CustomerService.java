package com.getir.readingisgood.domain.service;

import com.getir.readingisgood.api.command.request.CustomerRegisterCommand;
import com.getir.readingisgood.api.command.response.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO register(CustomerRegisterCommand command);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerDetailById(final String id);

    CustomerDTO getCustomerDetailByEmail(String email);
}
