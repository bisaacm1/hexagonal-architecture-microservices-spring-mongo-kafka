package com.isaac.hexagonal.adapters.in.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.isaac.hexagonal.adapters.in.controller.request.CustomerRequest;
import com.isaac.hexagonal.adapters.in.controller.response.CustomerResponse;
import com.isaac.hexagonal.application.core.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "isValidCpf", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "cpf", source = "cpf")
    Customer toCustomer(CustomerRequest customerRequest);

    CustomerResponse toCustomerResponse(Customer customer);
}
