package com.isaac.hexagonal.application.ports.out;

import java.util.Optional;

import com.isaac.hexagonal.application.core.domain.Customer;

public interface FindCustomerByIdOutputPort {

    Optional<Customer> find(String id);

}
