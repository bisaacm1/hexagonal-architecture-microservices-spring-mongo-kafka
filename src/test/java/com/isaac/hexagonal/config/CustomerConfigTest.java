package com.isaac.hexagonal.config;

import com.isaac.hexagonal.adapters.out.SendCpfForValidationKafkaAdapter;
import com.isaac.hexagonal.adapters.out.client.FindAddressByZipCodeClientAdapter;
import com.isaac.hexagonal.adapters.out.database.CustomerDataBaseAdapter;
import com.isaac.hexagonal.application.core.usecase.CustomerUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CustomerConfigTest {

    @Mock
    private FindAddressByZipCodeClientAdapter findAddressByZipCodeClientAdapter;

    @Mock
    private CustomerDataBaseAdapter customerDataBaseAdapter;

    @Mock
    private SendCpfForValidationKafkaAdapter sendCpfForValidationAdapter;

    @Test
    void customerUseCase_ShouldCreateBeanSuccessfully() {
        CustomerConfig customerConfig = new CustomerConfig();
        CustomerUseCase customerUseCase = customerConfig.customerUseCase(
                findAddressByZipCodeClientAdapter,
                customerDataBaseAdapter,
                sendCpfForValidationAdapter
        );
        assertNotNull(customerUseCase);
    }
}
