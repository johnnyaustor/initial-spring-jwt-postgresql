package com.jap.initial.springjwt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserCreateException extends RuntimeException {
    public UserCreateException(String message) {
        super(message);
    }
}
