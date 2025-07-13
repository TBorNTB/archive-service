package com.sejong.archiveservice.application.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handle(IllegalArgumentException e) {
        ErrorResponse errorResponse = ErrorResponse.badRequest(e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
