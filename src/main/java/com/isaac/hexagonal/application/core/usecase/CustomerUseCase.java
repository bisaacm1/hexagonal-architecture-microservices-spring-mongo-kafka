package com.isaac.hexagonal.application.core.usecase;

import com.isaac.hexagonal.application.core.domain.Address;
import com.isaac.hexagonal.application.core.domain.Customer;
import com.isaac.hexagonal.application.ports.in.CustomerInputPort;
import com.isaac.hexagonal.application.ports.out.CustomerOutputPort;
import com.isaac.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import com.isaac.hexagonal.application.ports.out.SendCpfForValidationOutputPort;

public class CustomerUseCase implements CustomerInputPort {

    private final FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort;
    private final CustomerOutputPort customerOutputPort;
    private final SendCpfForValidationOutputPort sendCpfForValidationOutputPort;

    public CustomerUseCase(FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort,
                           CustomerOutputPort customerOutputPort,
                           SendCpfForValidationOutputPort sendCpfForValidationOutputPort) {
        this.findAddressByZipCodeOutputPort = findAddressByZipCodeOutputPort;
        this.customerOutputPort = customerOutputPort;
        this.sendCpfForValidationOutputPort = sendCpfForValidationOutputPort;
    }

    @Override
    public void insert(Customer customer, String zipCode) {
        Address address = findAddressByZipCodeOutputPort.find(zipCode);
        customer.setAddress(address);
        customerOutputPort.insert(customer);
        sendCpfForValidationOutputPort.send(customer.getCpf());
    }

    @Override
    public void update(Customer customer, String zipCode) {
        customerOutputPort.find(customer.getId());
        Address address = findAddressByZipCodeOutputPort.find(zipCode);
        customer.setAddress(address);
        customerOutputPort.update(customer);
    }

    @Override
    public Customer find(String id) {
        return customerOutputPort.find(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public void delete(final String id) {
        customerOutputPort.find(id);
        customerOutputPort.delete(id);
    }
}
