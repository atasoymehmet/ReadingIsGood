package com.getir.readingisgood.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "customer")
public class Customer {

    @Id
    public String id;

    public String firstName;
    public String lastName;
    @Indexed(unique = true)
    private String email;
    private String password;


    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    public String getName() {
        return String.format("%s  %s", firstName, lastName);
    }

}