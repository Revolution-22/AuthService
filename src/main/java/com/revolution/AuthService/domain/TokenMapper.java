package com.revolution.AuthService.domain;

import com.revolution.AuthService.api.vo.RefreshTokenVO;
import com.revolution.AuthService.api.vo.TokenVO;

class TokenMapper {

    Token toModel(final TokenVO tokenVO) {
        return Token.builder()
                .email(tokenVO.email())
                .token(tokenVO.token())
                .expires(tokenVO.expires())
                .build();
    }

    TokenVO toVO(final Token token) {
        return new TokenVO(token.getEmail(), token.getToken(), token.getExpires());
    }

    RefreshToken toModel(final RefreshTokenVO tokenVO) {
        return RefreshToken.builder()
                .email(tokenVO.email())
                .token(tokenVO.token())
                .expires(tokenVO.expires())
                .build();
    }

    RefreshTokenVO toVO(final RefreshToken token) {
        return new RefreshTokenVO(token.getEmail(), token.getToken(), token.getExpires());
    }
}
