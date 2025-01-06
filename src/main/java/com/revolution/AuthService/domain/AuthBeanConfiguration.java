package com.revolution.AuthService.domain;

import com.revolution.AuthService.api.AuthService;
import com.revolution.AuthService.api.port.Encoder;
import com.revolution.AuthService.api.port.TokenService;
import com.revolution.AuthService.api.port.UserRepository;

public class AuthBeanConfiguration {

    public AuthService getAuthService(UserRepository userRepository, Encoder encoder, TokenService tokenService) {
        return new CoreAuthService(userRepository, encoder, new UserMapper(), tokenService);
    }
}
