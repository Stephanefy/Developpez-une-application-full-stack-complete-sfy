package com.openclassrooms.mddapi.exceptions;


import com.openclassrooms.mddapi.responses.ApiErrorResponse;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity notFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<String>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity <Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        System.out.println("reached");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ApiErrorResponse> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        return ResponseEntity.status(UNAUTHORIZED).body(new ApiErrorResponse(UNAUTHORIZED.value(), e.getMessage()));
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiErrorResponse> handleUnknownException(Exception e) {
//        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiErrorResponse(INTERNAL_SERVER_ERROR.value(), e.getMessage()));
//    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleInvalidTokenException(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return new ResponseEntity<Map<String, String>>(errors, BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> handleInvalidTokenException(InvalidTokenException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token provided.");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> handleInvalidTokenException(NoSuchElementException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return new ResponseEntity<Map<String, String>>(errors, NOT_FOUND);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<String> handleInvalidTokenException(MalformedJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token provided.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleInvalidTokenException(AccessDeniedException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return new ResponseEntity<Map<String, String>>(errors, UNAUTHORIZED);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<String> handleInvalidTokenException(MissingRequestHeaderException ex) {
        return ResponseEntity.status(UNAUTHORIZED).body("No Authorization header in the request.");
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, String>> handleDataAccessException(DataAccessException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return new ResponseEntity<Map<String, String>>(errors, BAD_REQUEST);
    }

}