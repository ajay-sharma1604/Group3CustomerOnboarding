package com.oracle.customer.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//This annotation makes the class a global exception handler for all REST controllers
@RestControllerAdvice
public class GlobalExceptionHandler {

    //Handles validation errors (e.g., missing or invalid input fields)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        //Create a map to store field-specific error messages
        Map<String, String> errors = new HashMap<>();

        //Loop through each validation error and collect field name and message
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage()); // e.g., "email": "Email should be valid"
        });

        //Return error map with HTTP status 400 (Bad Request)
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //Catches all other unhandled exceptions (like NullPointerException, DB errors, etc.)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalExceptions(Exception ex) {
        // Return a simple message with HTTP status 500 (Internal Server Error)
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCustomerNotFound(CustomerNotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("timestamp", new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
