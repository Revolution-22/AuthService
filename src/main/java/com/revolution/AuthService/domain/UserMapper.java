package com.revolution.AuthService.domain;

import com.revolution.AuthService.api.response.UserResponse;
import com.revolution.AuthService.api.vo.UserVO;
import org.springframework.stereotype.Component;

class UserMapper {

    User toModel(UserVO userVO) {
        return User.builder()
                .id(userVO.id())
                .nickname(userVO.nickname())
                .email(userVO.email())
                .password(userVO.password())
                .roles(userVO.roles())
                .build();
    }

    UserResponse toResponse(User user) {
        return new UserResponse(user.getNickname(), user.getRoles());
    }

    UserVO toVO(User user) {
        return new UserVO(user.getId(), user.getNickname(), user.getEmail(), user.getPassword(), user.getRoles());
    }
}
