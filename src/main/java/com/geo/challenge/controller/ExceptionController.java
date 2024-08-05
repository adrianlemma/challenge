package com.geo.challenge.controller;

import com.geo.challenge.dto.response.ErrorResponse;
import com.geo.challenge.exception.GenericException;
import com.geo.challenge.exception.PlayerException;
import com.geo.challenge.exception.TournamentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(PlayerException.class)
    public ResponseEntity<ErrorResponse> handlePlayerException(PlayerException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(TournamentException.class)
    public ResponseEntity<ErrorResponse> handleTournamentException(TournamentException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(GenericException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorResponse(ex.getCode(), ex.getMessage()));
    }
}
