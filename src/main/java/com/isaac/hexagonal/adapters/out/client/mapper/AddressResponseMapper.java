package com.isaac.hexagonal.adapters.out.client.mapper;

import com.isaac.hexagonal.adapters.out.client.response.AddressResponse;
import com.isaac.hexagonal.application.core.domain.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {

    Address toAddress(AddressResponse addressResponse);

}
