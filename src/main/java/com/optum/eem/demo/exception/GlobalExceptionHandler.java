package com.optum.eem.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MyCustomException.class)
  public ResponseEntity<?> resourceNotFoundException(MyCustomException ex, WebRequest request) {
    return new ResponseEntity<>(request.getSessionId() + ex.getMessage(), HttpStatus.NOT_IMPLEMENTED);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> resourceNotFoundException(IllegalArgumentException ex, WebRequest request) {
    return new ResponseEntity<>(request.getSessionId() + ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
    return new ResponseEntity<>( request.getSessionId() + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
