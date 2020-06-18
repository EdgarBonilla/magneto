package com.meli.interview.edgar.api.dna.errorhandling;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DNAException  extends RuntimeException {

  public DNAException(String message) {
    super(message);
  }
}
