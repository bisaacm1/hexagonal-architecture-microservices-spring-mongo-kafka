package com.isaac.hexagonal.application.ports.out;

import com.isaac.hexagonal.application.core.domain.Customer;

public interface InsertCustomerOutputPort {

    void insert(Customer customer);

}
