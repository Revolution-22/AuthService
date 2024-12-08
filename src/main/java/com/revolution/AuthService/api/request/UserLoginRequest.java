package com.revolution.AuthService.api.request;

public record UserLoginRequest(
        String email,
        String password
) {
}
