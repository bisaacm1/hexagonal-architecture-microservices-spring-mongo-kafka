package com.isaac.hexagonal.adapters.out.database;

import java.util.Optional;

import com.isaac.hexagonal.adapters.out.database.document.CustomerDocument;
import com.isaac.hexagonal.adapters.out.database.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import com.isaac.hexagonal.adapters.out.database.mapper.CustomerEntityMapper;
import com.isaac.hexagonal.application.core.domain.Customer;
import com.isaac.hexagonal.application.ports.out.CustomerOutputPort;

@Component
public class CustomerDataBaseAdapter implements CustomerOutputPort {

    private CustomerRepository customerRepository;
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public void insert(Customer customer) {
        CustomerDocument customerEntity = customerEntityMapper.toCustomerEntity(customer);
        customerRepository.save(customerEntity);
    }

    @Override
    public void update(Customer customer) {
        Optional<CustomerDocument> existingCustomer = customerRepository.findById(customer.getId());
        existingCustomer.ifPresent(existing -> {
            CustomerDocument customerEntity = customerEntityMapper.toCustomerEntity(customer);
            customerRepository.save(customerEntity);
        });
    }

    @Override
    public Optional<Customer> find(String id) {
        Optional<CustomerDocument> customerEntity = customerRepository.findById(id);
        return customerEntity.map(entity -> customerEntityMapper.toCustomer(entity));
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

}
