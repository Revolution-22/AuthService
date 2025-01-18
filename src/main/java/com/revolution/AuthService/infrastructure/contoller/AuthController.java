package com.revolution.AuthService.infrastructure.contoller;

import com.revolution.AuthService.api.AuthService;
import com.revolution.AuthService.api.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    UserResponse login(@RequestParam final String email, @RequestParam final String password) {
        return authService.login(email, password);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse register(@RequestParam final String nickname, @RequestParam final String email, @RequestParam final String password) {
        return authService.register(nickname, email, password);
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
