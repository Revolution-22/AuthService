package com.revolution.AuthService.infrastructure.contoller;

import com.revolution.AuthService.api.AuthService;
import com.revolution.AuthService.api.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    UserResponse login(String email, String password) {
        return authService.login(email, password);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse register(String nickname, String email, String password) {
        return authService.register(nickname, email, password);
    }

    @GetMapping("/validate")
    UserResponse validate(@RequestParam String token) {
        return authService.validateToken(token);
    }
}
