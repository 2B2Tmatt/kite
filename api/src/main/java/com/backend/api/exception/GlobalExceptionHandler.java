package com.backend.api.exception;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleConflict(){
       return new ResponseEntity<>("Data conflict: Item already exists", HttpStatus.CONFLICT);
   }

    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<String> handleDbDowntime(){
        return new ResponseEntity<>("Database currently unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
