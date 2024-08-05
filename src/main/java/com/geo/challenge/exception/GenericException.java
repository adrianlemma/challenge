package com.geo.challenge.exception;

import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException {

    private final HttpStatus status;
    private final String code;
    private final String message;

    public GenericException(HttpStatus status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
