package com.getir.readingisgood.customer;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.domain.model.Customer;
import com.getir.readingisgood.domain.repository.CustomerRepository;
import com.getir.readingisgood.domain.service.CustomerService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void initBefore() {
        ReflectionTestUtils.setField(customerService, "customerRepository", customerRepository);
    }

    @Test
    public void testRegisterCustomer() {
        Customer customer = Customer.builder()
                .firstName("Mr")
                .lastName("Yes")
                .email("test@test.com")
                .password("password")
                .build();

        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/customer/register",
                customer, Customer.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    private String getRootUrl() {
        return "http://localhost:8080";
    }
}
