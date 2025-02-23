package com.isaac.hexagonal.adapters.in.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.isaac.hexagonal.adapters.in.consumer.mapper.CustomerMessageMapper;
import com.isaac.hexagonal.adapters.in.consumer.message.CustomerMessage;
import com.isaac.hexagonal.application.ports.in.CustomerInputPort;

@Component
public class ReceiveValidatedCpfConsumer {

    private CustomerInputPort ucustomerInputPort;

    private CustomerMessageMapper customerMessageMapper;

    @KafkaListener(topics = "tp-cpf-validated", groupId = "isaac")
    public void receive(CustomerMessage customerMessage) {
        var customer = customerMessageMapper.toCustomer(customerMessage);
        ucustomerInputPort.update(customer, customerMessage.zipCode());
    }

}
