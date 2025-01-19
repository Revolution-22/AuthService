package com.revolution.AuthService.api.request;

import jakarta.validation.constraints.Email;

public record UserLoginRequest(
        @Email
        String email,
        @Password
        String password
) {
}
