package com.revolution.AuthService.infrastructure.configuration;

import com.revolution.AuthService.api.AuthService;
import com.revolution.AuthService.api.port.Encoder;
import com.revolution.AuthService.api.port.TokenService;
import com.revolution.AuthService.api.port.UserRepository;
import com.revolution.AuthService.domain.AuthBeanConfiguration;
import com.revolution.AuthService.infrastructure.adapter.CoreEncoder;
import com.revolution.AuthService.infrastructure.adapter.CoreTokenService;
import com.revolution.AuthService.infrastructure.database.EntityMapper;
import com.revolution.AuthService.infrastructure.database.UserJpaRepository;
import com.revolution.AuthService.infrastructure.database.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaRepositories(basePackages = "com.revolution.AuthService.infrastructure.*")
public class BeanConfiguration {

    private final AuthBeanConfiguration authConfiguration = new AuthBeanConfiguration();

    @Bean
    public TokenService tokenService() {
        return new CoreTokenService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public Encoder encoder(PasswordEncoder passwordEncoder) {
        return new CoreEncoder(passwordEncoder);
    }

    @Bean
    public EntityMapper entityMapper() {
        return new EntityMapper();
    }

    @Bean
    public UserRepository userRepository(UserJpaRepository userJpaRepository, EntityMapper entityMapper) {
        return new UserRepositoryImpl(userJpaRepository, entityMapper);
    }

    @Bean
    public AuthService authService(UserRepository userRepository, Encoder passwordEncoder, TokenService tokenService) {
        return authConfiguration.getAuthService(userRepository, passwordEncoder, tokenService);
    }

}
