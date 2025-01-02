package com.isaac.hexagonal.application.ports.in;

import com.isaac.hexagonal.application.core.domain.Customer;

public interface FindCustomerByIdInputPort {

    Customer find(String id);

}
