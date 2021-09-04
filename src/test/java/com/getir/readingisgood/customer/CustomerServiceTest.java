package com.getir.readingisgood.customer;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.api.command.request.CustomerRegisterCommand;
import com.getir.readingisgood.api.command.response.CustomerDTO;
import com.getir.readingisgood.converter.CustomerDTOConverter;
import com.getir.readingisgood.domain.model.Customer;
import com.getir.readingisgood.domain.repository.CustomerRepository;
import com.getir.readingisgood.domain.service.CustomerService;
import com.getir.readingisgood.domain.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceTest {

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerDTOConverter customerDTOConverter;

    @Mock
    private List<Customer> allCustomers;

    @BeforeEach
    public void initBefore() {
        customerService = new CustomerServiceImpl(customerRepository, customerDTOConverter);
        createCustomer();
    }

    @Test
    public void customerRegisterWhenEverythingIsOkay() {
        CustomerDTO register = customerService.register(createCustomer());
        assertNull(register);
    }

    @Test
    public void getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        assertEquals(Mockito.anyList(), customers);
    }

    private CustomerRegisterCommand createCustomer() {
        CustomerRegisterCommand registerCommand = new CustomerRegisterCommand();
        registerCommand.setFirstName("Mr");
        registerCommand.setLastName("Yes");
        registerCommand.setEmail("test@test.com");
        registerCommand.setPassword("password");

        return registerCommand;
    }
    private void createCustomerList() {
        Customer customer1 = Customer.builder()
                .firstName("Mr")
                .lastName("Yes")
                .email("test@test.com")
                .id("123")
                .password("password")
                .build();

        Customer customer2 = Customer.builder()
                .firstName("Mr")
                .lastName("No")
                .email("test@test.com")
                .id("1234")
                .password("password")
                .build();

        Customer customer3 = Customer.builder()
                .firstName("Mr")
                .lastName("Maybe")
                .email("test@test.com")
                .id("12345")
                .password("password")
                .build();

        allCustomers.add(customer1);
        allCustomers.add(customer2);
        allCustomers.add(customer3);
    }
}
