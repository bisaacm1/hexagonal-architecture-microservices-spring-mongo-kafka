package com.isaac.hexagonal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.isaac.hexagonal.adapters.out.CustomerAdapter;
import com.isaac.hexagonal.adapters.out.FindAddressByZipCodeAdapter;
import com.isaac.hexagonal.adapters.out.SendCpfForValidationAdapter;
import com.isaac.hexagonal.application.core.usecase.CustomerUseCase;

@Configuration
public class CustomerConfig {

    @Bean
    public CustomerUseCase customerUseCase(
            FindAddressByZipCodeAdapter findAddressByZipCodeAdapter,
            CustomerAdapter customerAdapter,
            SendCpfForValidationAdapter sendCpfForValidationAdapter) {
        return new CustomerUseCase(findAddressByZipCodeAdapter, customerAdapter, sendCpfForValidationAdapter);
    }
}