package com.revolution.auth.service.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(
        @Size(min = 5)
        String nickname,
        @Email
        String email,
        @Password
        String password) {
}
