package com.revolution.auth.service.api.dto;

import java.time.LocalDateTime;

public record RefreshTokenDto(
        Long id,
        String email,
        String token,
        LocalDateTime expires
) {
}
