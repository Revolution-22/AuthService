package com.revolution.AuthService.domain.adapters

import com.revolution.AuthService.api.port.RefreshTokenRepository
import com.revolution.AuthService.api.dto.RefreshTokenDto

class TestRefreshTokenRepository implements RefreshTokenRepository {

    protected Map<String, RefreshTokenDto> database = new HashMap<>()

    @Override
    Optional<RefreshTokenDto> findByEmail(String email) {
        Optional.ofNullable(database.get(email))
    }

    @Override
    RefreshTokenDto save(RefreshTokenDto tokenVO) {
        database.put(tokenVO.email(), tokenVO)
        tokenVO
    }

    @Override
    Optional<RefreshTokenDto> findByToken(String token) {
        database.values().stream()
            .filter { Objects.equals(it.token(), token)}
            .findFirst()
    }
}
