package com.revolution.auth.service.api.port;

public interface BrokerService {

    void publishMessage(String topic, Object message);
}
