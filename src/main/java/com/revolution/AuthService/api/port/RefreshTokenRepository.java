package com.revolution.AuthService.api.port;

import com.revolution.AuthService.api.vo.RefreshTokenVO;

import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshTokenVO> findByEmail(String email);

    RefreshTokenVO save(RefreshTokenVO tokenVO);

    Optional<RefreshTokenVO> findByToken(String token);
}
