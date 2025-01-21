package com.revolution.AuthService.domain.adapters

import com.revolution.AuthService.api.port.TokenRepository
import com.revolution.AuthService.api.dto.TokenDto

class TestTokenRepository implements TokenRepository {

    protected Map<String, TokenDto> database = new HashMap<>()

    @Override
    Optional<TokenDto> findByEmail(String email) {
        Optional.ofNullable(database.get(email))
    }

    @Override
    TokenDto save(TokenDto tokenVO) {
        database.put(tokenVO.email(), tokenVO)
        tokenVO
    }

    @Override
    Optional<TokenDto> findByToken(String token) {
        database.values().stream()
            .filter { Objects.equals(it.token(), token)}
            .findFirst()
    }
}
