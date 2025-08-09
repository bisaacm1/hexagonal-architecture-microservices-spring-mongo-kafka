package com.isaac.hexagonal.adapters.in.consumer;

import com.isaac.hexagonal.adapters.in.consumer.mapper.CustomerMessageMapper;
import com.isaac.hexagonal.adapters.in.consumer.message.CustomerMessage;
import com.isaac.hexagonal.application.core.domain.Customer;
import com.isaac.hexagonal.application.ports.in.CustomerInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReceiveValidatedCpfConsumerTest {

    @Mock
    private CustomerInputPort customerInputPort;

    @Mock
    private CustomerMessageMapper customerMessageMapper;

    @InjectMocks
    private ReceiveValidatedCpfConsumer consumer;

    private CustomerMessage customerMessage;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId("1");
        customer.setName("John Doe");
        customer.setCpf("12345678901");
        customer.setIsValidCpf(true);

        customerMessage = new CustomerMessage("1", "John Doe", "12345678901", "12345678", true);
    }

    @Test
    void receive_ShouldProcessMessageAndUpdateCustomer() {
        when(customerMessageMapper.toCustomer(any(CustomerMessage.class))).thenReturn(customer);
        consumer.receive(customerMessage);
        verify(customerMessageMapper).toCustomer(customerMessage);
        verify(customerInputPort).update(customer, customerMessage.zipCode());
    }
}
