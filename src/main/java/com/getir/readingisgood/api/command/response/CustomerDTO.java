package com.getir.readingisgood.api.command.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {

    private String id;
    private String name;
    private String email;
}
