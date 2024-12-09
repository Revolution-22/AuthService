package com.revolution.AuthService.infrastructure.adapter;

import com.revolution.AuthService.api.port.Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EncoderImpl implements Encoder {

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean matches(String rawPassword, String password) {
        return passwordEncoder.matches(rawPassword, password);
    }

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
