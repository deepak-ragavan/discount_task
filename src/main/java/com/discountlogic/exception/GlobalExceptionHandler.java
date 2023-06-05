package com.discountlogic.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        // Handle the exception and return a meaningful response
        return ResponseEntity.badRequest().body(ex.getConstraintViolations().stream().findFirst().get().getPropertyPath()+":"+ex.getConstraintViolations().stream().findFirst().get().getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // Handle the exception and return a meaningful response
        return ResponseEntity.badRequest().body(ex.getFieldErrors().stream().findFirst().get().getField()+":"+ex.getFieldErrors().stream().findFirst().get().getDefaultMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // Handle the exception and return a meaningful response
        return ResponseEntity.badRequest().body(ex.getCause().getCause().getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFountException(UserNotFoundException ex) {
        // Handle the exception and return a meaningful response
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
