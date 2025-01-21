package com.revolution.AuthService.api.dto;

import java.time.LocalDateTime;

public record RefreshTokenDto(
        String email,
        String token,
        LocalDateTime expires
) {
}
