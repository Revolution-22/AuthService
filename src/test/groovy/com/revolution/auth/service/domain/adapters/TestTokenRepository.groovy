package com.revolution.auth.service.domain.adapters

import com.revolution.auth.service.api.port.TokenRepository
import com.revolution.auth.service.api.dto.TokenDto

class TestTokenRepository implements TokenRepository {

    protected Map<String, TokenDto> database = new HashMap<>()

    @Override
    Optional<TokenDto> findByEmail(String email) {
        Optional.ofNullable(database.get(email))
    }

    @Override
    TokenDto save(TokenDto tokenDto) {
        database.put(tokenDto.email(), tokenDto)
        tokenDto
    }

    @Override
    Optional<TokenDto> findByToken(String token) {
        database.values().stream()
            .filter { Objects.equals(it.token(), token)}
            .findFirst()
    }
}
