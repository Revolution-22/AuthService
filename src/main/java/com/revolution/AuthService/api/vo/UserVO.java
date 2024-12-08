package com.revolution.AuthService.api.vo;

import java.util.Set;

public record UserVO(
        Long id,
        String nickname,
        String email,
        String password,
        Set<String> roles
) {
}
