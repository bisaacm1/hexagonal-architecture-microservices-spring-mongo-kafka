package com.isaac.hexagonal.adapters.out.database;

import com.isaac.hexagonal.adapters.out.database.document.CustomerDocument;
import com.isaac.hexagonal.adapters.out.database.mapper.CustomerEntityMapper;
import com.isaac.hexagonal.adapters.out.database.repository.CustomerRepository;
import com.isaac.hexagonal.application.core.domain.Address;
import com.isaac.hexagonal.application.core.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerDataBaseAdapterTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerEntityMapper customerEntityMapper;

    @InjectMocks
    private CustomerDataBaseAdapter customerDataBaseAdapter;

    private Customer customer;
    private CustomerDocument customerDocument;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId("1");
        customer.setName("John Doe");
        customer.setCpf("12345678901");
        customer.setAddress(new Address("Main St", "New York", "NY"));
        customer.setIsValidCpf(false);

        customerDocument = new CustomerDocument(customer.getId(),customer.getName(),null,customer.getCpf(),customer.getIsValidCpf() );

    }

    @Test
    void insert_ShouldSaveCustomerToDatabase() {
        // Given
        when(customerEntityMapper.toCustomerEntity(any(Customer.class))).thenReturn(customerDocument);
        when(customerRepository.save(any(CustomerDocument.class))).thenReturn(customerDocument);

        // When
        customerDataBaseAdapter.insert(customer);

        // Then
        verify(customerEntityMapper).toCustomerEntity(customer);
        verify(customerRepository).save(customerDocument);
    }

    @Test
    void update_ShouldUpdateExistingCustomer() {
        // Given
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(customerDocument));
        when(customerEntityMapper.toCustomerEntity(any(Customer.class))).thenReturn(customerDocument);
        when(customerRepository.save(any(CustomerDocument.class))).thenReturn(customerDocument);

        // When
        customerDataBaseAdapter.update(customer);

        // Then
        verify(customerRepository).findById("1");
        verify(customerEntityMapper).toCustomerEntity(customer);
        verify(customerRepository).save(customerDocument);
    }

    @Test
    void update_ShouldNotUpdateWhenCustomerNotFound() {
        // Given
        when(customerRepository.findById(anyString())).thenReturn(Optional.empty());

        // When
        customerDataBaseAdapter.update(customer);

        // Then
        verify(customerRepository).findById("1");
        verifyNoMoreInteractions(customerEntityMapper);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void find_ShouldReturnCustomerWhenExists() {
        // Given
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(customerDocument));
        when(customerEntityMapper.toCustomer(any(CustomerDocument.class))).thenReturn(customer);

        // When
        Optional<Customer> result = customerDataBaseAdapter.find("1");

        // Then
        assertTrue(result.isPresent());
        assertEquals(customer, result.get());
        verify(customerRepository).findById("1");
        verify(customerEntityMapper).toCustomer(customerDocument);
    }

    @Test
    void find_ShouldReturnEmptyWhenCustomerNotFound() {
        // Given
        when(customerRepository.findById(anyString())).thenReturn(Optional.empty());

        // When
        Optional<Customer> result = customerDataBaseAdapter.find("1");

        // Then
        assertFalse(result.isPresent());
        verify(customerRepository).findById("1");
        verifyNoInteractions(customerEntityMapper);
    }

    @Test
    void delete_ShouldDeleteCustomer() {
        // Given
        doNothing().when(customerRepository).deleteById(anyString());

        // When
        customerDataBaseAdapter.delete("1");

        // Then
        verify(customerRepository).deleteById("1");
    }
}
