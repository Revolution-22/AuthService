package com.revolution.auth.service.domain.adapters

import com.revolution.auth.service.api.port.RefreshTokenRepository
import com.revolution.auth.service.api.dto.RefreshTokenDto

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
