package com.revolution.AuthService.infrastructure.database;

import com.revolution.AuthService.api.vo.UserVO;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
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
}
