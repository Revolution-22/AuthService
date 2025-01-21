package com.revolution.AuthService.api.port;

import com.revolution.AuthService.api.dto.RefreshTokenDto;

import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshTokenDto> findByEmail(String email);

    RefreshTokenDto save(RefreshTokenDto tokenVO);

    Optional<RefreshTokenDto> findByToken(String token);
}
