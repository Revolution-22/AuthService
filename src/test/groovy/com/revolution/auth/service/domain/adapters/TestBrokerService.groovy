package com.revolution.auth.service.domain.adapters;

import com.revolution.auth.service.api.port.BrokerService;

class TestBrokerService implements BrokerService {

    @Override
    void publishMessage(String topic, Object message) {

    }
}
