package com.isaac.hexagonal.adapters.out.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.isaac.hexagonal.adapters.out.client.response.AddressResponse;

@FeignClient(name = "FindAddressByZipCodeClient", url = "${isaac.client.address.url}")
public interface FindAddressByZipCodeClient {

    @GetMapping("/{zipCode}")
    AddressResponse find(@PathVariable("zipCode") String zipCode);

}
