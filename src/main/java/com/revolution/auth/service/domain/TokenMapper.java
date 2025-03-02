package com.revolution.auth.service.domain;

import com.revolution.auth.service.api.dto.RefreshTokenDto;
import com.revolution.auth.service.api.dto.TokenDto;

class TokenMapper {

    Token toModel(final TokenDto tokenDto) {
        return Token.builder()
                .email(tokenDto.email())
                .token(tokenDto.token())
                .expires(tokenDto.expires())
                .build();
    }

    TokenDto toDto(final Token token) {
        return new TokenDto(token.getEmail(), token.getToken(), token.getExpires());
    }

    RefreshToken toModel(final RefreshTokenDto tokenVO) {
        return RefreshToken.builder()
                .email(tokenVO.email())
                .token(tokenVO.token())
                .expires(tokenVO.expires())
                .build();
    }

    RefreshTokenDto toDto(final RefreshToken token) {
        return new RefreshTokenDto(token.getEmail(), token.getToken(), token.getExpires());
    }
}
