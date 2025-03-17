package com.revolution.auth.service.infrastructure.configuration;

import com.revolution.auth.service.api.port.AuthService;
import com.revolution.auth.service.api.port.BrokerService;
import com.revolution.auth.service.api.port.Encoder;
import com.revolution.auth.service.api.port.RefreshTokenRepository;
import com.revolution.auth.service.api.port.TokenRepository;
import com.revolution.auth.service.api.port.TokenService;
import com.revolution.auth.service.api.port.UserRepository;
import com.revolution.auth.service.domain.AuthBeanConfiguration;
import com.revolution.auth.service.infrastructure.database.EntityMapper;
import com.revolution.auth.service.infrastructure.database.RefreshTokenJpaRepository;
import com.revolution.auth.service.infrastructure.database.RefreshTokenRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaRepositories(basePackages = "com.revolution.auth.service.infrastructure.*")
class BeanConfiguration {

    private final AuthBeanConfiguration authConfiguration = new AuthBeanConfiguration();

    @Bean
    RefreshTokenRepository refreshTokenRepository(RefreshTokenJpaRepository tokenJpaRepository, EntityMapper entityMapper) {
        return new RefreshTokenRepositoryAdapter(tokenJpaRepository, entityMapper);
    }

    @Bean
    TokenService tokenService(TokenRepository tokenRepository, RefreshTokenRepository refreshTokenRepository) {
        return authConfiguration.getTokenService(tokenRepository, refreshTokenRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    AuthService authService(UserRepository userRepository, Encoder passwordEncoder, TokenService tokenService, BrokerService brokerService) {
        return authConfiguration.getAuthService(userRepository, passwordEncoder, tokenService, brokerService);
    }

}
