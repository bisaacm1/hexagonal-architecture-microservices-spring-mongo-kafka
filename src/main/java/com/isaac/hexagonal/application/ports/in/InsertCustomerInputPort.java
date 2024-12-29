package com.isaac.hexagonal.application.ports.in;

import com.isaac.hexagonal.application.core.domain.Customer;

public interface InsertCustomerInputPort {

    void insert(Customer customer, String zipCode);

}
