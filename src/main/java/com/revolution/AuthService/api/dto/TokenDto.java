package com.revolution.AuthService.api.dto;


import java.time.LocalDateTime;

public record TokenDto(
        String email,
        String token,
        LocalDateTime expires
) {
}
