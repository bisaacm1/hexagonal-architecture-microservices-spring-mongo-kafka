package com.isaac.hexagonal.config;

import com.isaac.hexagonal.adapters.in.consumer.message.CustomerMessage;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class KafkaConsumerConfigTest {

    @Test
    void consumerFactory_ShouldCreateConsumerFactorySuccessfully() {
        KafkaConsumerConfig kafkaConsumerConfig = new KafkaConsumerConfig();
        ConsumerFactory<String, CustomerMessage> consumerFactory = kafkaConsumerConfig.consumerFactory();
        assertNotNull(consumerFactory);
    }

    @Test
    void kafkaListenerContainerFactory_ShouldCreateFactorySuccessfully() {
        KafkaConsumerConfig kafkaConsumerConfig = new KafkaConsumerConfig();
        ConcurrentKafkaListenerContainerFactory<String, CustomerMessage> factory =
                kafkaConsumerConfig.kafkaListenerContainerFactory();
        assertNotNull(factory);
    }
}
