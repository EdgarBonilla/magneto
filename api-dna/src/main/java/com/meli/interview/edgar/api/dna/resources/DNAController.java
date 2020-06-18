package com.meli.interview.edgar.api.dna.resources;

import com.meli.interview.edgar.api.dna.delegate.DNADelegate;
import com.meli.interview.edgar.api.dna.domain.DNARequest;
import com.meli.interview.edgar.api.dna.domain.StatsDTO;
import com.meli.interview.edgar.api.dna.resources.interfaces.DNAOperations;
import com.meli.interview.edgar.api.dna.validator.APIValidator;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DNAController implements DNAOperations {

  private static final String DNA_PROCESSED = "DNA Processed";
  private final DNADelegate dnaDelegate;
  private final APIValidator validator;

  public DNAController(DNADelegate dnaDelegate, APIValidator validator) {
    this.dnaDelegate = dnaDelegate;
    this.validator = validator;
  }

  @Override
  public ResponseEntity<String> validateMutantDNA(DNARequest dnaRequest) {
    validator.validate(dnaRequest);
    return dnaDelegate.isMutantDNA(dnaRequest.getDna())
        ? ResponseEntity.status(HttpStatus.OK).body(DNA_PROCESSED)
        : ResponseEntity.status(HttpStatus.FORBIDDEN).body(DNA_PROCESSED);
  }

  @Override
  public ResponseEntity<StatsDTO> retrieveStats() {
    return Objects.nonNull(dnaDelegate.getStats())
        ? ResponseEntity.ok(dnaDelegate.getStats())
        : ResponseEntity.noContent().build();
  }
}
