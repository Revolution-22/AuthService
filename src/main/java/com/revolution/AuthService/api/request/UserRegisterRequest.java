package com.revolution.AuthService.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public record UserRegisterRequest(
        @Min(5)
        String nickname,
        @Email
        String email,
        @Password
        String password) {
}
