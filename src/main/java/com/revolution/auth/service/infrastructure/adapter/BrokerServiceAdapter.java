package com.revolution.auth.service.infrastructure.adapter;

import com.revolution.auth.service.api.port.BrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrokerServiceAdapter implements BrokerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishMessage(final String topic, final Object message) {
        kafkaTemplate.send(topic, message);
    }
}
