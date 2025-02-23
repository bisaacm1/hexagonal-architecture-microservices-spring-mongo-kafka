package com.isaac.hexagonal.adapters.out.database.mapper;

import com.isaac.hexagonal.adapters.out.database.document.CustomerDocument;
import com.isaac.hexagonal.application.core.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustomerDocument toCustomerEntity(Customer customer);

    Customer toCustomer(CustomerDocument customerDocument);

}
