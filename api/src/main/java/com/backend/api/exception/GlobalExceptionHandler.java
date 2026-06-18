package com.backend.api.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e
                .getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());

        HttpStatus status = e.getMessage().contains("exists") ? HttpStatus.CONFLICT : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMissingEntity(EntityNotFoundException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGlobalFallback(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "An unexpected internal server error occurred.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
