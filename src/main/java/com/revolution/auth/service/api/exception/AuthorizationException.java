package com.revolution.auth.service.api.exception;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException(String data) {
        super("Can not authorize user with data: %s".formatted(data) );
    }
}
