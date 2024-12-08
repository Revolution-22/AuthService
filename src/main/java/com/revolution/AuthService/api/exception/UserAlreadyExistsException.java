package com.revolution.AuthService.api.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String email, String nickname) {
        super("User with email %s or nickname %s already exists".formatted(email, nickname));
    }
}
