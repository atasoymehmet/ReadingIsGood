package com.getir.readingisgood.order;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.domain.model.Order;
import com.getir.readingisgood.domain.model.OrderStatus;
import com.getir.readingisgood.domain.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void findOrder_givenOrder_thenReturn() throws Exception {

        Order order = Order.builder()
                .id("123asd")
                .createDate(LocalDate.now())
                .bookId("asd123")
                .status(OrderStatus.COMPLETED)
                .price(10.0)
                .customerEmail("test@test.com")
                .purchasedBookCount(10L)
                .build();

        given(orderRepository.findById("123asd")).willReturn(Optional.of(order));

        ResponseEntity<Order> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/order/get/123asd",
                order, Order.class);

        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

    }

    private String getRootUrl() {
        return "http://localhost:8080";
    }
}