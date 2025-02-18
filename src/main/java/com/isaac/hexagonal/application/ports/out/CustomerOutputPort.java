package com.isaac.hexagonal.application.ports.out;

import com.isaac.hexagonal.application.core.domain.Customer;

import java.util.Optional;

public interface CustomerOutputPort {
    void insert(Customer customer);

    void update(Customer customer);

    Optional<Customer> find(String id);

    void delete(String id);

}
