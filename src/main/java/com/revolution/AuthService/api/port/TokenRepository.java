package com.revolution.AuthService.api.port;

import com.revolution.AuthService.api.vo.TokenVO;

import java.util.Optional;

public interface TokenRepository {
    Optional<TokenVO> findByEmail(String email);

    TokenVO save(TokenVO tokenVO);

    Optional<TokenVO> findByToken(String token);
}
