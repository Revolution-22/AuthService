package com.revolution.auth.service.api.dto;


import java.time.LocalDateTime;

public record TokenDto(
        Long id,
        String email,
        String token,
        LocalDateTime expires
) {
}
