package com.meli.interview.edgar.api.dna.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DNAErrorHandling {

  private HttpStatus status;
  private String message;
}
