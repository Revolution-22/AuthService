package com.revolution.AuthService.api.dto;

public record TokensRefreshDto(
        String email,
        String token,
        String refreshToken
) {
}
