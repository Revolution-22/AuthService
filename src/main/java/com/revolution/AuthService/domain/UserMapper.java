package com.revolution.AuthService.domain;

import com.revolution.AuthService.api.response.UserResponse;
import com.revolution.AuthService.api.vo.UserVO;

class UserMapper {

    User toModel(final UserVO userVO) {
        return User.builder()
                .id(userVO.id())
                .nickname(userVO.nickname())
                .email(userVO.email())
                .password(userVO.password())
                .roles(userVO.roles())
                .build();
    }

    UserResponse toResponse(final User user, final String token, final String refreshToken) {
        return new UserResponse(user.getNickname(), user.getRoles(), token, refreshToken);
    }

    UserVO toVO(final User user) {
        return new UserVO(user.getId(), user.getNickname(), user.getEmail(), user.getPassword(), user.getRoles());
    }
}
