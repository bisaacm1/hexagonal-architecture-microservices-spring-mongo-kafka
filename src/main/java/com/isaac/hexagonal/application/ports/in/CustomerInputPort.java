package com.isaac.hexagonal.application.ports.in;

import com.isaac.hexagonal.application.core.domain.Customer;

public interface CustomerInputPort {
    void insert(Customer customer, String zipCode);

    void update(Customer customer, String zipCode);

    Customer find(String id);

    void delete(String id);
}
