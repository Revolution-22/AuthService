package com.revolution.AuthService.api.exception;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException(String email) {
        super("Can not authorize user with email: %s".formatted(email) );
    }
}
