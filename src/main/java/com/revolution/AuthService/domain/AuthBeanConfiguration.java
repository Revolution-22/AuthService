package com.revolution.AuthService.domain;

import com.revolution.AuthService.api.AuthService;
import com.revolution.AuthService.api.port.Encoder;
import com.revolution.AuthService.api.port.UserRepository;

public class AuthBeanConfiguration {

    public AuthService getAuthService(UserRepository userRepository, Encoder encoder) {
        return new CoreAuthService(userRepository, encoder, new UserMapper());
    }
}
