package com.revolution.AuthService.domain;

import com.revolution.AuthService.api.response.UserResponse;
import com.revolution.AuthService.api.dto.UserDto;

class UserMapper {

    User toModel(final UserDto userDto) {
        return User.builder()
                .id(userDto.id())
                .nickname(userDto.nickname())
                .email(userDto.email())
                .password(userDto.password())
                .roles(userDto.roles())
                .build();
    }

    UserResponse toResponse(final User user, final String token, final String refreshToken) {
        return new UserResponse(user.getEmail(), user.getRoles(), token, refreshToken);
    }

    UserDto toVO(final User user) {
        return new UserDto(user.getId(), user.getNickname(), user.getEmail(), user.getPassword(), user.getRoles());
    }
}
