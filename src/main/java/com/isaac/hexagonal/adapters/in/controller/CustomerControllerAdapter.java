package com.isaac.hexagonal.adapters.in.controller;

import com.isaac.hexagonal.adapters.in.controller.api.CustomerControllerAPI;
import com.isaac.hexagonal.adapters.in.controller.mapper.CustomerMapper;
import com.isaac.hexagonal.adapters.in.controller.request.CustomerRequest;
import com.isaac.hexagonal.adapters.in.controller.response.CustomerResponse;
import com.isaac.hexagonal.application.core.domain.Customer;
import com.isaac.hexagonal.application.ports.in.CustomerInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerControllerAdapter implements CustomerControllerAPI {

    private CustomerInputPort customerInputPort;
    private CustomerMapper customerMapper;

    @Override
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest customerRequest) {
        Customer customer = customerMapper.toCustomer(customerRequest);
        customerInputPort.insert(customer, customerRequest.zipCode());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CustomerResponse> findById(@PathVariable String id) {
        Customer customer = customerInputPort.find(id);
        CustomerResponse customerResponse = customerMapper.toCustomerResponse(customer);
        return ResponseEntity.ok().body(customerResponse);
    }

    @Override
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody CustomerRequest customerRequest) {
        Customer customer = customerMapper.toCustomer(customerRequest);
        customer.setId(id);
        customerInputPort.update(customer, customerRequest.zipCode());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable String id) {
        customerInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
