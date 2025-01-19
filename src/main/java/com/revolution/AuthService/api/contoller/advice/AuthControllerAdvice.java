package com.revolution.AuthService.api.contoller.advice;

import com.revolution.AuthService.api.exception.AuthorizationException;
import com.revolution.AuthService.api.exception.UserAlreadyExistsException;
import com.revolution.Common.exception.ErrorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ErrorEntity handleAuthorizationException(AuthorizationException exception ) {
        return new ErrorEntity(exception.getMessage(), 1401, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorEntity handleUserAlreadyExistsException(UserAlreadyExistsException exception ) {
        return new ErrorEntity(exception.getMessage(), 1400, HttpStatus.BAD_REQUEST);
    }
}
