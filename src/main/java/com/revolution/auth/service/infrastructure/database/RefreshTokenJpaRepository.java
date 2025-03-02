package com.revolution.auth.service.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByEmail(String email);

    Optional<RefreshTokenEntity> findByToken(String token);
}
