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
  public ResponseEntity<Object> handleDNAException(DNAException exception) {
    return error(BAD_REQUEST, exception);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<Object> handleConstraintViolation(
      MethodArgumentNotValidException exception) {
    return error(BAD_REQUEST, exception);
  }

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<Object> handleRunTimeException(RuntimeException exception) {
    return error(INTERNAL_SERVER_ERROR, exception);
  }

  private ResponseEntity<Object> error(HttpStatus status, Exception exception) {
    return ResponseEntity.status(status)
        .body(new DNAErrorHandling(status, exception.getLocalizedMessage()));
  }
}
