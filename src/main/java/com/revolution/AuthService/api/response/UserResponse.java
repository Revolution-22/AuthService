package com.revolution.AuthService.api.response;

import java.util.Set;

public record UserResponse(
        String nickname,
        Set<String> roles,
        String token) {
}
