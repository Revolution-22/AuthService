package com.revolution.AuthService.infrastructure.database;

import com.revolution.AuthService.api.vo.RefreshTokenVO;
import com.revolution.AuthService.api.vo.TokenVO;
import com.revolution.AuthService.api.vo.UserVO;

import java.util.Set;

public class EntityMapper {

    UserVO toVO(final UserEntity userEntity) {
        return new UserVO(userEntity.getId(), userEntity.getNickname(), userEntity.getEmail(), userEntity.getPassword(), Set.of(userEntity.getRoles().split(",")));
    }

    UserEntity toEntity(final UserVO userVO) {
        return UserEntity.builder()
                .id(userVO.id())
                .nickname(userVO.nickname())
                .email(userVO.email())
                .password(userVO.password())
                .roles(String.join(",", userVO.roles()))
                .build();
    }

    TokenVO toVO(final TokenEntity tokenEntity) {
        return new TokenVO(tokenEntity.getEmail(), tokenEntity.getToken(), tokenEntity.getExpires());
    }

    TokenEntity toEntity(final TokenVO tokenVO) {
        return TokenEntity.builder()
                .email(tokenVO.email())
                .token(tokenVO.token())
                .expires(tokenVO.expires())
                .build();
    }

    RefreshTokenVO toVO(final RefreshTokenEntity tokenEntity) {
        return new RefreshTokenVO(tokenEntity.getEmail(), tokenEntity.getToken(), tokenEntity.getExpires());
    }

    RefreshTokenEntity toEntity(final RefreshTokenVO tokenVO) {
        return RefreshTokenEntity.builder()
                .email(tokenVO.email())
                .token(tokenVO.token())
                .expires(tokenVO.expires())
                .build();
    }
}
