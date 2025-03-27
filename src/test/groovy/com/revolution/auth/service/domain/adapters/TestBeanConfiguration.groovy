package com.revolution.auth.service.domain.adapters

import com.revolution.auth.service.api.port.AuthService
import com.revolution.auth.service.api.port.BrokerService
import com.revolution.auth.service.api.port.RefreshTokenRepository
import com.revolution.auth.service.api.port.TokenRepository
import com.revolution.auth.service.api.port.TokenService
import com.revolution.auth.service.api.port.UserRepository
import com.revolution.auth.service.domain.AuthBeanConfiguration

class TestBeanConfiguration {

    private final AuthBeanConfiguration authBeanConfiguration = new AuthBeanConfiguration()

    private final TokenRepository tokenRepository = new TestTokenRepository()
    private final RefreshTokenRepository refreshTokenRepository = new TestRefreshTokenRepository()
    private final UserRepository userRepository = new TestUserRepository()

    TokenService getTokenService() {
        authBeanConfiguration.getTokenService(tokenRepository, refreshTokenRepository)
    }

    AuthService getAuthService() {
        authBeanConfiguration.getAuthService(userRepository, new TestEncoder(), getTokenService(), new TestBrokerService())
    }

    def clear() {
        tokenRepository.database.clear()
        userRepository.database.clear()
        refreshTokenRepository.database.clear()
    }
}
