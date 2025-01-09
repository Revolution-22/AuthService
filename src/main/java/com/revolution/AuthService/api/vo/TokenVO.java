package com.revolution.AuthService.api.vo;


import java.time.LocalDateTime;

public record TokenVO (
        String email,
        String token,
        LocalDateTime expires
) {
}
