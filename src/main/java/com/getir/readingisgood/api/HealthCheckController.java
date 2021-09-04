package com.getir.readingisgood.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/healthcheck")
    public String healthCheck()
    {
        return "Reading Is Good is up and running.";
    }
}
