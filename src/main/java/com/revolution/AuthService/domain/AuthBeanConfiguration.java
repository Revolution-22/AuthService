package com.revolution.AuthService.domain;

import com.revolution.AuthService.api.port.AuthService;
import com.revolution.AuthService.api.port.Encoder;
import com.revolution.AuthService.api.port.RefreshTokenRepository;
import com.revolution.AuthService.api.port.TokenRepository;
import com.revolution.AuthService.api.port.TokenService;
import com.revolution.AuthService.api.port.UserRepository;

public class AuthBeanConfiguration {

    public TokenService getTokenService(TokenRepository tokenRepository, RefreshTokenRepository refreshTokenRepository) {
        return new CoreTokenService(tokenRepository, refreshTokenRepository, new TokenMapper());
    }

    public AuthService getAuthService(UserRepository userRepository, Encoder encoder, TokenService tokenService) {
        return new CoreAuthService(userRepository, encoder, new UserMapper(), tokenService);
    }
}
