package com.revolution.AuthService.api.response;

public record TokenRefreshResponse(
        String email,
        String token,
        String refreshToken
) {
}
