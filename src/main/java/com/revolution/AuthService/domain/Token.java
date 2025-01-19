package com.revolution.AuthService.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
class Token {

    private String email;
    private String token;
    private LocalDateTime expires;

    void refresh() {
        expires = LocalDateTime.now().plusMinutes(15);
        token = UUID.randomUUID().toString();
    }

    boolean isExpired() {
        return expires.isBefore(LocalDateTime.now());
    }

    static Token of(String email) {
        return new Token(email, UUID.randomUUID().toString(), LocalDateTime.now().plusHours(1));
    }
}
