package com.revolution.auth.service.api.contoller;

import com.revolution.auth.service.api.port.AuthService;
import com.revolution.auth.service.api.request.UserLoginRequest;
import com.revolution.auth.service.api.request.UserRegisterRequest;
import com.revolution.auth.service.api.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    UserResponse login(@RequestBody @Valid UserLoginRequest request) {
        return authService.login(request.email(), request.password());
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse register(@RequestBody @Valid UserRegisterRequest request) {
        return authService.register(request.nickname(), request.email(), request.password());
    }

    @PutMapping("/validate")
    UserResponse validate(@RequestParam final String token) {
        return authService.validateToken(token);
    }

    @PutMapping("/refresh")
    UserResponse refresh(@RequestParam final String token) {
        return authService.refreshToken(token);
    }
}
