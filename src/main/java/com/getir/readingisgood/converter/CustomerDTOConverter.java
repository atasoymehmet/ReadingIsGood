package com.getir.readingisgood.converter;

import com.getir.readingisgood.api.command.response.CustomerDTO;
import com.getir.readingisgood.domain.model.Customer;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOConverter extends BaseConverter<Customer, CustomerDTO> {

    @Override
    public CustomerDTO convert(@NonNull Customer source) {
        return CustomerDTO.builder()
                .id(source.getId())
                .name((source.getName()))
                .email(source.getEmail())
                .build();
    }
}
