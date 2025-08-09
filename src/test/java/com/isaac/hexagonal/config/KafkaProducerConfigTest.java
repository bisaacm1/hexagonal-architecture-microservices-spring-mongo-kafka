package com.isaac.hexagonal.config;

import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class KafkaProducerConfigTest {

    @Test
    void producerFactory_ShouldCreateProducerFactorySuccessfully() {
        KafkaProducerConfig kafkaProducerConfig = new KafkaProducerConfig();
        ProducerFactory<String, String> producerFactory = kafkaProducerConfig.producerFactory();
        assertNotNull(producerFactory);
    }

    @Test
    void kafkaTemplate_ShouldCreateKafkaTemplateSuccessfully() {
        KafkaProducerConfig kafkaProducerConfig = new KafkaProducerConfig();
        KafkaTemplate<String, String> kafkaTemplate = kafkaProducerConfig.kafkaTemplate();
        assertNotNull(kafkaTemplate);
    }
}
