package com.revolution.AuthService.api;

import com.revolution.AuthService.api.response.UserResponse;

public interface AuthService {

    UserResponse login(String email, String password);
    UserResponse register(String nickname, String email, String password);
    UserResponse validateToken(String token);
    UserResponse refreshToken(String token);
}
