package com.getir.readingisgood.domain.service;

import com.getir.readingisgood.api.command.request.CustomerOrdersCommand;
import com.getir.readingisgood.api.command.request.UpdateBookStockCommand;
import com.getir.readingisgood.api.command.response.BookDTO;
import com.getir.readingisgood.api.command.response.CustomerDTO;
import com.getir.readingisgood.api.command.response.OrderDTO;
import com.getir.readingisgood.converter.OrderDTOConverter;
import com.getir.readingisgood.domain.model.Order;
import com.getir.readingisgood.domain.model.OrderStatus;
import com.getir.readingisgood.domain.model.exception.BookIsOutOfStockException;
import com.getir.readingisgood.domain.model.exception.BookStockUpdateException;
import com.getir.readingisgood.domain.model.exception.NoBookFoundException;
import com.getir.readingisgood.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class OrderServiceImpl implements OrderService {

    private final CustomerService customerService;
    private final BookService bookService;
    private final OrderRepository orderRepository;
    private final OrderDTOConverter orderDTOConverter;
    private final ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized OrderDTO createOrder(String bookIsbnNumber, Long count, String customerMail) {
        try {
            reentrantLock.lock();
            BookDTO book = bookService.getBook(bookIsbnNumber);
            if (!Objects.nonNull(book)) {
                log.error("Order createOrder error: No book found. bookIsbnNumber: {}", bookIsbnNumber);

                throw new NoBookFoundException("No book found");
            }
            CustomerDTO customerDTO = customerService.getCustomerDetailByEmail(customerMail);
            if (book.getStockCount() < count) {
                log.error("Order createOrder error: Book is out of stock. Book StockCount(): {} requestedCount(): {}",
                        book.getStockCount(), count);
                throw new BookIsOutOfStockException("Book is out of stock.");
            }

            Long newStock = book.getStockCount() - count;
            bookService.updateStockInfo(UpdateBookStockCommand.builder().id(book.getId()).stock(newStock).build());
            OrderDTO orderDTO = orderDTOConverter.convert(orderRepository.save(Order.builder()
                    .purchasedBookCount(count)
                    .bookId(book.getId())
                    .createDate(LocalDate.now())
                    .status(OrderStatus.COMPLETED)
                    .customerEmail(customerDTO.getEmail())
                    .price(book.getPrice() * count)
                    .build()));
            log.debug("OrderService::createOrder completed: {}", orderDTO);

            return orderDTO;
        } catch (Exception e) {
            log.error("OrderService::createOrder error: {}", e.getMessage());
            throw new BookStockUpdateException("Stock update exception");
        } finally {
            if (reentrantLock.isHeldByCurrentThread()) {
                reentrantLock.unlock();
            }
        }
    }

    @Override
    public List<OrderDTO> findOrderByCustomerEmail(CustomerOrdersCommand command) {
        List<OrderDTO> orderDTOList = orderRepository.findOrderByCustomerEmailIgnoreCase(command.getCustomerEmail(),
                        PageRequest.of(command.getPageNumber(), command.getPageSize()))
                .stream()
                .map(orderDTOConverter::convert)
                .collect(Collectors.toList());
        log.debug("OrderService::findOrderByCustomerEmail completed: {}", orderDTOList);
        return orderDTOList;
    }

    @Override
    public List<OrderDTO> findAllByDate(LocalDate startDate, LocalDate endDate) {
        log.debug("OrderService::findAllByDate started: startDate: {} endDate: {}", startDate, endDate);
        List<OrderDTO> orderDTOList = orderRepository.findByCreateDateBetween(startDate, endDate)
                .stream()
                .map(orderDTOConverter::convert)
                .collect(Collectors.toList());
        log.debug("OrderService::findAllByDate completed: orders: {} ", orderDTOList);
        return orderDTOList;
    }

    @Override
    public List<OrderDTO> getCustomerAllOrders(String email) {
        return orderDTOConverter.convert(orderRepository.findAllByCustomerEmail(email));
    }

    @Override
    public OrderDTO findOrderById(String id) {
        OrderDTO orderDTO = orderDTOConverter.convert(orderRepository.findById(id).orElse(new Order()));
        log.debug("OrderService::findOrderById completed: id: {} order: {} ", id, orderDTO);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findAll() {
        List<OrderDTO> orderDTOList = orderRepository.findAll()
                .stream()
                .map(orderDTOConverter::convert)
                .collect(Collectors.toList());
        log.debug("OrderService::findAll completed: orders: {} ", orderDTOList);
        return orderDTOList;
    }
}
