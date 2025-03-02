package com.revolution.auth.service.api.port;

import com.revolution.auth.service.api.dto.TokenDto;

import java.util.Optional;

public interface TokenRepository {
    Optional<TokenDto> findByEmail(String email);

    TokenDto save(TokenDto tokenDto);

    Optional<TokenDto> findByToken(String token);
}
