package com.isaac.hexagonal.adapters.in.controller;

import com.isaac.hexagonal.adapters.in.controller.mapper.CustomerMapper;
import com.isaac.hexagonal.adapters.in.controller.request.CustomerRequest;
import com.isaac.hexagonal.adapters.in.controller.response.CustomerResponse;
import com.isaac.hexagonal.application.core.domain.Address;
import com.isaac.hexagonal.application.core.domain.Customer;
import com.isaac.hexagonal.application.ports.in.CustomerInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerAdapterTest {

    @Mock
    private CustomerInputPort customerInputPort;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerControllerAdapter customerControllerAdapter;

    private CustomerRequest customerRequest;
    private Customer customer;
    private CustomerResponse customerResponse;

    @BeforeEach
    void setUp() {
        customerRequest = new CustomerRequest(
                "John Doe",
                "12345678901",
                "12345678",
                null,
                false
        );

        customer = new Customer();
        customer.setId("1");
        customer.setName("John Doe");
        customer.setCpf("12345678901");
        customer.setAddress(new Address("Main St", "New York", "NY"));
        customer.setIsValidCpf(false);

        customerResponse = new CustomerResponse("John Doe", null, "12345678901", false);
    }

    @Test
    void insert_ShouldReturnOkResponse() {
        when(customerMapper.toCustomer(any(CustomerRequest.class))).thenReturn(customer);
        doNothing().when(customerInputPort).insert(any(Customer.class), anyString());
        ResponseEntity<Void> response = customerControllerAdapter.insert(customerRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerInputPort).insert(customer, customerRequest.zipCode());
    }

    @Test
    void findById_ShouldReturnCustomerResponse() {
        when(customerInputPort.find(anyString())).thenReturn(customer);
        when(customerMapper.toCustomerResponse(any(Customer.class))).thenReturn(customerResponse);
        ResponseEntity<CustomerResponse> response = customerControllerAdapter.findById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(customerResponse, response.getBody());
        verify(customerInputPort).find("1");
    }

    @Test
    void update_ShouldReturnNoContentResponse() {
        when(customerMapper.toCustomer(any(CustomerRequest.class))).thenReturn(customer);
        doNothing().when(customerInputPort).update(any(Customer.class), anyString());
        ResponseEntity<Void> response = customerControllerAdapter.update("1", customerRequest);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerInputPort).update(customer, customerRequest.zipCode());
        assertEquals("1", customer.getId());
    }

    @Test
    void delete_ShouldReturnNoContentResponse() {
        doNothing().when(customerInputPort).delete(anyString());
        ResponseEntity<Void> response = customerControllerAdapter.delete("1");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerInputPort).delete("1");
    }
}
