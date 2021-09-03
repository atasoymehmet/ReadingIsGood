package com.getir.readingisgood.api.command.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginResponse {
    private String token;
    private Integer timeoutInMinutes;
}
