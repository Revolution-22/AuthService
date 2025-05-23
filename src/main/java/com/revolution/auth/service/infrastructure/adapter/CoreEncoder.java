package com.revolution.auth.service.infrastructure.adapter;

import com.revolution.auth.service.api.port.Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoreEncoder implements Encoder {

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean matches(final String rawPassword, final String password) {
        return passwordEncoder.matches(rawPassword, password);
    }

    @Override
    public String encode(final String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
