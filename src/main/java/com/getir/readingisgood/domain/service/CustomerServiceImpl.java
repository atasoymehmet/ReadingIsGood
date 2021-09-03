package com.getir.readingisgood.domain.service;

import com.getir.readingisgood.api.command.request.CustomerRegisterCommand;
import com.getir.readingisgood.api.command.response.CustomerDTO;
import com.getir.readingisgood.converter.CustomerDTOConverter;
import com.getir.readingisgood.domain.model.Customer;
import com.getir.readingisgood.domain.model.CustomerAlreadyExistException;
import com.getir.readingisgood.domain.repository.CustomerRepository;
import com.getir.readingisgood.security.config.WebSecurityConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDTOConverter customerDTOConverter;

    @Override
    public CustomerDTO register(CustomerRegisterCommand command) {
        log.debug("Customer register started: {} ", command);
        customerRepository.findByEmail(command.getEmail())
                .ifPresent(x -> {
                    throw new CustomerAlreadyExistException();
                });

        Customer persistRIGCustomer = customerRepository.save(prepareCustomer(command));
        CustomerDTO customerDTO = customerDTOConverter.convert(persistRIGCustomer);
        log.debug("Customer register completed: {} ", customerDTO);
        return customerDTO;
    }

    @Override
    public CustomerDTO getCustomerDetailById(String id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        CustomerDTO customerDTO = customerOptional.map(customerDTOConverter::convert).orElse(null);
        log.debug("Customer getCustomerDetailById completed: {} ", customerDTO);
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = customerDTOConverter.convert(customerList);
        log.debug("Customer getAllCustomers completed: {} ", customerDTOList);
        return customerDTOList;
    }

    @Override
    public CustomerDTO getCustomerDetailByEmail(String email) {
        log.debug("Customer getCustomerDetailByEmail started: {} ", email);
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        CustomerDTO customerDTO = customerOptional.isPresent() ? customerDTOConverter.convert(customerOptional.get())
                : CustomerDTO.builder().build();
        log.debug("Customer getCustomerDetailByEmail completed: {} ", customerDTO);
        return customerDTO;

    }

    private Customer prepareCustomer(CustomerRegisterCommand command) {
        return Customer.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(command.getEmail())
                .password(new WebSecurityConfig().passwordEncoder().encode(command.getPassword()))
                .build();
    }
}
