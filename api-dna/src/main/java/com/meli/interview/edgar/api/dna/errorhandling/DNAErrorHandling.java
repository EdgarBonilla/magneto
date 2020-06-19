package com.meli.interview.edgar.api.dna.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DNAErrorHandling {

  private HttpStatus status;
  private String message;
}
