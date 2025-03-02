package com.revolution.auth.service.api.dto;


import java.time.LocalDateTime;

public record TokenDto(
        String email,
        String token,
        LocalDateTime expires
) {
}
