package com.isaac.hexagonal.application.core.usecase;

import com.isaac.hexagonal.application.core.domain.Address;
import com.isaac.hexagonal.application.core.domain.Customer;
import com.isaac.hexagonal.application.ports.out.CustomerOutputPort;
import com.isaac.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import com.isaac.hexagonal.application.ports.out.SendCpfForValidationOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerUseCaseTest {

    @Mock
    private FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort;

    @Mock
    private CustomerOutputPort customerOutputPort;

    @Mock
    private SendCpfForValidationOutputPort sendCpfForValidationOutputPort;

    @InjectMocks
    private CustomerUseCase customerUseCase;

    private Customer customer;
    private Address address;
    private final String ZIP_CODE = "12345678";

    @BeforeEach
    void setUp() {
        customer = new Customer("1", "John Doe", null, "12345678901", false);
        address = new Address("Main St", "New York", "NY");
    }

    @Test
    void insert_ShouldSaveCustomerAndSendCpfForValidation() {
        when(findAddressByZipCodeOutputPort.find(anyString())).thenReturn(address);
        customerUseCase.insert(customer, ZIP_CODE);
        assertEquals(address, customer.getAddress());
        verify(customerOutputPort, times(1)).insert(customer);
        verify(sendCpfForValidationOutputPort, times(1)).send(customer.getCpf());
    }

    @Test
    void update_ShouldUpdateExistingCustomer() {
        when(findAddressByZipCodeOutputPort.find(anyString())).thenReturn(address);
        when(customerOutputPort.find(anyString())).thenReturn(Optional.of(customer));
        customerUseCase.update(customer, ZIP_CODE);
        assertEquals(address, customer.getAddress());
        verify(customerOutputPort, times(1)).update(customer);
    }

    @Test
    void find_ShouldReturnCustomerWhenExists() {
        when(customerOutputPort.find(anyString())).thenReturn(Optional.of(customer));
        Customer result = customerUseCase.find("1");
        assertEquals(customer, result);
    }

    @Test
    void find_ShouldThrowExceptionWhenCustomerNotFound() {
        when(customerOutputPort.find(anyString())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> customerUseCase.find("1"));
    }

    @Test
    void delete_ShouldDeleteExistingCustomer() {
        when(customerOutputPort.find(anyString())).thenReturn(Optional.of(customer));
        customerUseCase.delete("1");
        verify(customerOutputPort, times(1)).delete("1");
    }
}
