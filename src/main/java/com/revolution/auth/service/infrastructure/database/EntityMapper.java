package com.revolution.auth.service.infrastructure.database;

import com.revolution.auth.service.api.dto.RefreshTokenDto;
import com.revolution.auth.service.api.dto.TokenDto;
import com.revolution.auth.service.api.dto.UserDto;

import java.util.Set;

public class EntityMapper {

    UserDto toDto(final UserEntity userEntity) {
        return new UserDto(userEntity.getId(), userEntity.getNickname(), userEntity.getEmail(), userEntity.getPassword(), Set.of(userEntity.getRoles().split(",")));
    }

    UserEntity toEntity(final UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.id())
                .nickname(userDto.nickname())
                .email(userDto.email())
                .password(userDto.password())
                .roles(String.join(",", userDto.roles()))
                .build();
    }

    TokenDto toDto(final TokenEntity tokenEntity) {
        return new TokenDto(tokenEntity.getId(), tokenEntity.getEmail(), tokenEntity.getToken(), tokenEntity.getExpires());
    }

    TokenEntity toEntity(final TokenDto tokenDto) {
        return TokenEntity.builder()
                .id(tokenDto.id())
                .email(tokenDto.email())
                .token(tokenDto.token())
                .expires(tokenDto.expires())
                .build();
    }

    RefreshTokenDto toDto(final RefreshTokenEntity tokenEntity) {
        return new RefreshTokenDto(tokenEntity.getId(), tokenEntity.getEmail(), tokenEntity.getToken(), tokenEntity.getExpires());
    }

    RefreshTokenEntity toEntity(final RefreshTokenDto tokenDto) {
        return RefreshTokenEntity.builder()
                .id(tokenDto.id())
                .email(tokenDto.email())
                .token(tokenDto.token())
                .expires(tokenDto.expires())
                .build();
    }
}
