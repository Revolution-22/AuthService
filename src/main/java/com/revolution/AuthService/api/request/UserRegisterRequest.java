package com.revolution.AuthService.api.request;

public record UserRegisterRequest(
        String nickname,
        String email,
        String password) {
}
