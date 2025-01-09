package com.revolution.AuthService.infrastructure.configuration;

import com.revolution.AuthService.api.AuthService;
import com.revolution.AuthService.api.port.Encoder;
import com.revolution.AuthService.api.port.TokenRepository;
import com.revolution.AuthService.api.port.TokenService;
import com.revolution.AuthService.api.port.UserRepository;
import com.revolution.AuthService.domain.AuthBeanConfiguration;
import com.revolution.AuthService.infrastructure.adapter.CoreEncoder;
import com.revolution.AuthService.infrastructure.database.EntityMapper;
import com.revolution.AuthService.infrastructure.database.TokeRepositoryAdapter;
import com.revolution.AuthService.infrastructure.database.TokenJpaRepository;
import com.revolution.AuthService.infrastructure.database.UserJpaRepository;
import com.revolution.AuthService.infrastructure.database.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaRepositories(basePackages = "com.revolution.AuthService.infrastructure.*")
class BeanConfiguration {

    private final AuthBeanConfiguration authConfiguration = new AuthBeanConfiguration();

    @Bean
    TokenRepository tokenRepository(TokenJpaRepository tokenJpaRepository, EntityMapper entityMapper) {
        return new TokeRepositoryAdapter(tokenJpaRepository, entityMapper);
    }

    @Bean
    TokenService tokenService(TokenRepository tokenRepository) {
        return authConfiguration.getTokenService(tokenRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    Encoder encoder(PasswordEncoder passwordEncoder) {
        return new CoreEncoder(passwordEncoder);
    }

    @Bean
    EntityMapper entityMapper() {
        return new EntityMapper();
    }

    @Bean
    UserRepository userRepository(UserJpaRepository userJpaRepository, EntityMapper entityMapper) {
        return new UserRepositoryAdapter(userJpaRepository, entityMapper);
    }

    @Bean
    AuthService authService(UserRepository userRepository, Encoder passwordEncoder, TokenService tokenService) {
        return authConfiguration.getAuthService(userRepository, passwordEncoder, tokenService);
    }

}
