package com.revolution.AuthService.domain;

import com.revolution.AuthService.api.dto.RefreshTokenDto;
import com.revolution.AuthService.api.dto.TokenDto;

class TokenMapper {

    Token toModel(final TokenDto tokenDto) {
        return Token.builder()
                .email(tokenDto.email())
                .token(tokenDto.token())
                .expires(tokenDto.expires())
                .build();
    }

    TokenDto toVO(final Token token) {
        return new TokenDto(token.getEmail(), token.getToken(), token.getExpires());
    }

    RefreshToken toModel(final RefreshTokenDto tokenVO) {
        return RefreshToken.builder()
                .email(tokenVO.email())
                .token(tokenVO.token())
                .expires(tokenVO.expires())
                .build();
    }

    RefreshTokenDto toVO(final RefreshToken token) {
        return new RefreshTokenDto(token.getEmail(), token.getToken(), token.getExpires());
    }
}
