package com.isaac.hexagonal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.isaac.hexagonal.adapters.out.database.CustomerDataBaseAdapter;
import com.isaac.hexagonal.adapters.out.client.FindAddressByZipCodeClientAdapter;
import com.isaac.hexagonal.adapters.out.SendCpfForValidationKafkaAdapter;
import com.isaac.hexagonal.application.core.usecase.CustomerUseCase;

@Configuration
public class CustomerConfig {

    @Bean
    public CustomerUseCase customerUseCase(
            FindAddressByZipCodeClientAdapter findAddressByZipCodeClientAdapter,
            CustomerDataBaseAdapter customerDataBaseAdapter,
            SendCpfForValidationKafkaAdapter sendCpfForValidationAdapter) {
        return new CustomerUseCase(findAddressByZipCodeClientAdapter, customerDataBaseAdapter, sendCpfForValidationAdapter);
    }
}