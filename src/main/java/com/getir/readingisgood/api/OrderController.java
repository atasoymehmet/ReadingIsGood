package com.getir.readingisgood.api;

import com.getir.readingisgood.api.command.request.CreateOrderCommand;
import com.getir.readingisgood.api.command.response.OrderDTO;
import com.getir.readingisgood.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody CreateOrderCommand command) {
        return ResponseEntity.ok(orderService.createOrder(command.getBookIsbnNumber(), command.getCount(),
                command.getCustomerEmail()));
    }

    @GetMapping("/between")
    public ResponseEntity<List<OrderDTO>> findOrdersByDate(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ResponseEntity.ok(orderService.findAllByDate(startDate, endDate));
    }

    @GetMapping(path = "/get/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable final String orderId) {
        return ResponseEntity.ok(orderService.findOrderById(orderId));
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
