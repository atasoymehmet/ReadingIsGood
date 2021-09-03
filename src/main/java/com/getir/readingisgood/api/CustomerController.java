package com.getir.readingisgood.api;

import com.getir.readingisgood.api.command.request.CustomerOrdersCommand;
import com.getir.readingisgood.api.command.request.CustomerRegisterCommand;
import com.getir.readingisgood.api.command.response.CustomerDTO;
import com.getir.readingisgood.api.command.response.OrderDTO;
import com.getir.readingisgood.domain.service.CustomerService;
import com.getir.readingisgood.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerRegisterCommand command) {
        return ResponseEntity.ok(customerService.register(command));
    }

    @GetMapping(path = "/get/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable final String customerId) {
        return ResponseEntity.ok(customerService.getCustomerDetailById(customerId));
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping(path = "/getorders", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> getOrdersOfCustomer(@RequestBody @Valid CustomerOrdersCommand command) {
        return ResponseEntity.ok(orderService.findOrderByCustomerEmail(command));
    }
}
