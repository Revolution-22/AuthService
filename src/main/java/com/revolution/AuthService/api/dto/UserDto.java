package com.revolution.AuthService.api.dto;

import java.util.Set;

public record UserDto(
        Long id,
        String nickname,
        String email,
        String password,
        Set<String> roles
) {
}
