package com.meli.interview.edgar.api.dna.errorhandling;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DNAServiceErrorAdvice {

  @ExceptionHandler({DNAException.class})
  public ResponseEntity<String> handleDNAException(DNAException exception) {
    return error(BAD_REQUEST, exception);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<String> handleConstraintViolation(
      MethodArgumentNotValidException exception) {
    return error(BAD_REQUEST, exception);
  }

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<String> handleRunTimeException(RuntimeException exception) {
    return error(INTERNAL_SERVER_ERROR, exception);
  }

  private ResponseEntity<String> error(HttpStatus status, Exception e) {
    return ResponseEntity.status(status).body(e.getMessage());
  }
}
