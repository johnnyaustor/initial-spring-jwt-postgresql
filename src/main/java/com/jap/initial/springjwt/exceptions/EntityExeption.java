package com.jap.initial.springjwt.exceptions;

public class EntityExeption extends RuntimeException {
    public EntityExeption(String message) {
        super(message);
    }

    public EntityExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
