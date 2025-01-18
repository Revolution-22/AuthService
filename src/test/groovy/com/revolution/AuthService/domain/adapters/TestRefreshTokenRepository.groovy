package com.revolution.AuthService.domain.adapters

import com.revolution.AuthService.api.port.RefreshTokenRepository
import com.revolution.AuthService.api.vo.RefreshTokenVO

class TestRefreshTokenRepository implements RefreshTokenRepository {

    protected Map<String, RefreshTokenVO> database = new HashMap<>()

    @Override
    Optional<RefreshTokenVO> findByEmail(String email) {
        Optional.ofNullable(database.get(email))
    }

    @Override
    RefreshTokenVO save(RefreshTokenVO tokenVO) {
        database.put(tokenVO.email(), tokenVO)
        tokenVO
    }

    @Override
    Optional<RefreshTokenVO> findByToken(String token) {
        database.values().stream()
            .filter { Objects.equals(it.token(), token)}
            .findFirst()
    }
}
