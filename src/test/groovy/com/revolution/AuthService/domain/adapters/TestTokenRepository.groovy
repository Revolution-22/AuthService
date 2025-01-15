package com.revolution.AuthService.domain.adapters

import com.revolution.AuthService.api.port.TokenRepository
import com.revolution.AuthService.api.vo.TokenVO

class TestTokenRepository implements TokenRepository {

    protected Map<String, TokenVO> database = new HashMap<>()

    @Override
    Optional<TokenVO> findByEmail(String email) {
        Optional.ofNullable(database.get(email))
    }

    @Override
    TokenVO save(TokenVO tokenVO) {
        database.put(tokenVO.email(), tokenVO)
        tokenVO
    }

    @Override
    Optional<TokenVO> findByToken(String token) {
        database.values().stream()
            .filter { Objects.equals(it.token(), token)}
            .findFirst()
    }
}
