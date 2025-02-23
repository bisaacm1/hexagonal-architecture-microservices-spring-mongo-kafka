package com.isaac.hexagonal.adapters.out.client;

import com.isaac.hexagonal.adapters.out.client.feign.FindAddressByZipCodeClient;
import com.isaac.hexagonal.adapters.out.client.mapper.AddressResponseMapper;
import com.isaac.hexagonal.application.core.domain.Address;
import com.isaac.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import org.springframework.stereotype.Component;

@Component
public class FindAddressByZipCodeClientAdapter implements FindAddressByZipCodeOutputPort {

    private FindAddressByZipCodeClient findAddressByZipCodeClient;
    private AddressResponseMapper addressResponseMapper;

    @Override
    public Address find(String zipcode) {
        var addressResponse = findAddressByZipCodeClient.find(zipcode);
        return addressResponseMapper.toAddress(addressResponse);
    }

}
