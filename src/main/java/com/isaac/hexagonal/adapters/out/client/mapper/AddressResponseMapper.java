package com.isaac.hexagonal.adapters.out.client.mapper;

import org.mapstruct.Mapper;

import com.isaac.hexagonal.adapters.out.client.response.AddressResponse;
import com.isaac.hexagonal.application.core.domain.Address;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {

    Address toAddress(AddressResponse addressResponse);

}
