package com.revolution.auth.service.domain;

import com.revolution.auth.service.api.port.AuthService;
import com.revolution.auth.service.api.port.BrokerService;
import com.revolution.auth.service.api.port.Encoder;
import com.revolution.auth.service.api.port.RefreshTokenRepository;
import com.revolution.auth.service.api.port.TokenRepository;
import com.revolution.auth.service.api.port.TokenService;
import com.revolution.auth.service.api.port.UserRepository;

public class AuthBeanConfiguration {

    public TokenService getTokenService(TokenRepository tokenRepository, RefreshTokenRepository refreshTokenRepository) {
        return new CoreTokenService(tokenRepository, refreshTokenRepository, new TokenMapper());
    }

    public AuthService getAuthService(UserRepository userRepository, Encoder encoder, TokenService tokenService, BrokerService brokerService) {
        return new CoreAuthService(userRepository, encoder, new UserMapper(), tokenService, brokerService);
    }
}
