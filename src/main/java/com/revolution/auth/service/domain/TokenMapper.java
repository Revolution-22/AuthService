package com.revolution.auth.service.domain;

import com.revolution.auth.service.api.dto.RefreshTokenDto;
import com.revolution.auth.service.api.dto.TokenDto;

class TokenMapper {

    Token toModel(final TokenDto tokenDto) {
        return Token.builder()
                .id(tokenDto.id())
                .email(tokenDto.email())
                .token(tokenDto.token())
                .expires(tokenDto.expires())
                .build();
    }

    TokenDto toDto(final Token token) {
        return new TokenDto(token.getId(), token.getEmail(), token.getToken(), token.getExpires());
    }

    RefreshToken toModel(final RefreshTokenDto tokenDto) {
        return RefreshToken.builder()
                .id(tokenDto.id())
                .email(tokenDto.email())
                .token(tokenDto.token())
                .expires(tokenDto.expires())
                .build();
    }

    RefreshTokenDto toDto(final RefreshToken token) {
        return new RefreshTokenDto(token.getId(), token.getEmail(), token.getToken(), token.getExpires());
    }
}
