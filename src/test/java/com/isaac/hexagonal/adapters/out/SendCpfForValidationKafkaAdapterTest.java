package com.isaac.hexagonal.adapters.out;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SendCpfForValidationKafkaAdapterTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private ListenableFuture listenableFuture;

    @InjectMocks
    private SendCpfForValidationKafkaAdapter sendCpfForValidationKafkaAdapter;

    @Test
    void send_ShouldSendCpfToKafkaTopic() {
        // Given
        String cpf = "12345678901";
        when(kafkaTemplate.send(anyString(), anyString())).thenReturn(listenableFuture);

        // When
        sendCpfForValidationKafkaAdapter.send(cpf);

        // Then
        verify(kafkaTemplate).send(eq("tp-cpf-validation"), eq(cpf));
    }
}
