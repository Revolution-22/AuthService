package com.revolution.auth.service.api.dto;

public record TokensRefreshDto(
        String email,
        String token,
        String refreshToken
) {
}
