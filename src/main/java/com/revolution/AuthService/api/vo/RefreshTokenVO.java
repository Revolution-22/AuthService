package com.revolution.AuthService.api.vo;

import java.time.LocalDateTime;

public record RefreshTokenVO (
        String email,
        String token,
        LocalDateTime expires
) {
}
