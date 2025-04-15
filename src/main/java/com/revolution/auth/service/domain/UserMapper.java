package com.revolution.auth.service.domain;

import com.revolution.common.response.UserResponse;
import com.revolution.auth.service.api.dto.UserDto;

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

    UserDto toDto(final User user) {
        return new UserDto(user.getId(), user.getNickname(), user.getEmail(), user.getPassword(), user.getRoles());
    }
}
