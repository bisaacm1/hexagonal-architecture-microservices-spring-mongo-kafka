package com.isaac.hexagonal.application.ports.out;

import com.isaac.hexagonal.application.core.domain.Address;

public interface FindAddressByZipCodeOutputPort {

    Address find(String zipcode);

}
