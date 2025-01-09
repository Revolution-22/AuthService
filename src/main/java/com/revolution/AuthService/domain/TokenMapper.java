package com.revolution.AuthService.domain;

import com.revolution.AuthService.api.vo.TokenVO;

class TokenMapper {

    Token toModel(TokenVO tokenVO) {
        return Token.builder()
                .email(tokenVO.email())
                .token(tokenVO.token())
                .expires(tokenVO.expires())
                .build();
    }

    TokenVO toVO(Token token) {
        return new TokenVO(token.getEmail(), token.getToken(), token.getExpires());
    }
}
