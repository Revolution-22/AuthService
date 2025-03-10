package com.revolution.auth.service.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
class RefreshToken {

    private Long id;
    private String email;
    private String token;
    private LocalDateTime expires;

    void refresh() {
        expires = LocalDateTime.now().plusHours(1);
        token = UUID.randomUUID().toString();
    }

    boolean isExpired() {
        return expires.isBefore(LocalDateTime.now());
    }

    static RefreshToken of(String email) {
        return new RefreshToken(null, email, UUID.randomUUID().toString(), LocalDateTime.now().plusDays(1));
    }
}