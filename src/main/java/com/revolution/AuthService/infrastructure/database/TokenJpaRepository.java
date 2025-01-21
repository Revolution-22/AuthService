package com.revolution.AuthService.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByEmail(String email);

    Optional<TokenEntity> findByToken(String token);
}
