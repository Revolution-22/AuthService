package com.revolution.AuthService.domain.adapters

import com.revolution.AuthService.api.AuthService
import com.revolution.AuthService.api.port.RefreshTokenRepository
import com.revolution.AuthService.api.port.TokenRepository
import com.revolution.AuthService.api.port.TokenService
import com.revolution.AuthService.api.port.UserRepository
import com.revolution.AuthService.domain.AuthBeanConfiguration

class TestBeanConfiguration {

    private final AuthBeanConfiguration authBeanConfiguration = new AuthBeanConfiguration()

    private final TokenRepository tokenRepository = new TestTokenRepository()
    private final RefreshTokenRepository refreshTokenRepository = new TestRefreshTokenRepository()
    private final UserRepository userRepository = new TestUserRepository()

    TokenService getTokenService() {
        return authBeanConfiguration.getTokenService(tokenRepository, refreshTokenRepository)
    }

    AuthService getAuthService() {
        return authBeanConfiguration.getAuthService(userRepository, new TestEncoder(), getTokenService())
    }

    def clear() {
        tokenRepository.database.clear()
        userRepository.database.clear()
        refreshTokenRepository.database.clear()
    }
}
