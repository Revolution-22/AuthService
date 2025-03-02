package com.revolution.auth.service.api.response;

import java.util.Set;

public record UserResponse(
        String email,
        Set<String> roles,
        String token,
        String refreshToken) {
}
