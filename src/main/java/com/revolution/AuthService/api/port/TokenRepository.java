package com.revolution.AuthService.api.port;

import com.revolution.AuthService.api.dto.TokenDto;

import java.util.Optional;

public interface TokenRepository {
    Optional<TokenDto> findByEmail(String email);

    TokenDto save(TokenDto tokenDto);

    Optional<TokenDto> findByToken(String token);
}
