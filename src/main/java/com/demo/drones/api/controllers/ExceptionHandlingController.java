package com.demo.drones.api.controllers;

import com.demo.drones.api.dto.ErrorDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(value = EntityExistsException.class)
    public ResponseEntity<ErrorDto> handleNotFoundException(EntityExistsException e, WebRequest request) {
        return generateResponse(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException e, WebRequest request) {
        return generateResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<ErrorDto> handleIllegalStateException(IllegalStateException e, WebRequest request) {
        return generateResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
        return generateResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e, WebRequest request) {
        return generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<ErrorDto> generateResponse(HttpStatus status, String message) {

        var error = new ErrorDto();
        error.setTimestamp(new Date());
        error.setMessage(message);
        error.setCode(status.value());
        error.setStatus(status.getReasonPhrase());

        return ResponseEntity.status(status.value()).body(error);
    }
}
