package com.revolution.AuthService.domain.adapters

import com.revolution.AuthService.api.port.Encoder

class TestEncoder implements Encoder{

    @Override
    boolean matches(String rawPassword, String password) {
        Objects.equals(rawPassword, password)
    }

    @Override
    String encode(String rawPassword) {
        rawPassword
    }
}
