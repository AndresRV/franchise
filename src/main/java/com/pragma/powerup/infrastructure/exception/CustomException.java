package com.pragma.powerup.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

    private final HttpStatus status;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
