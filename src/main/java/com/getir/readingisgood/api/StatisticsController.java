package com.getir.readingisgood.api;

import com.getir.readingisgood.api.command.request.CustomerMonthlyStatCommand;
import com.getir.readingisgood.api.command.response.CustomerOrderStatDTO;
import com.getir.readingisgood.domain.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class StatisticsController {

    private final StatisticsService statisticsService;

    @PostMapping("/customer-monthly")
    public ResponseEntity<List<CustomerOrderStatDTO>> getCustomerMonthlyStat(@Valid @RequestBody CustomerMonthlyStatCommand command) {
        return ResponseEntity
                .ok(statisticsService
                        .getCustomerOrderSummaryPerMonth(command.getCustomerEmail())
                        .entrySet()
                        .stream()
                        .map(CustomerOrderStatDTO::customerOrderStatMap)
                        .collect(Collectors.toList()));
    }
}
