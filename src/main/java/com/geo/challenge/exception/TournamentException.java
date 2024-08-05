package com.geo.challenge.exception;

public class TournamentException extends RuntimeException {

    private final String code;
    private final String message;

    public TournamentException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
