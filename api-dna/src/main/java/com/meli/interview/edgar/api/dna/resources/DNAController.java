package com.meli.interview.edgar.api.dna.resources;

import com.meli.interview.edgar.api.dna.delegate.DNADelegate;
import com.meli.interview.edgar.api.dna.domain.DNARequest;
import com.meli.interview.edgar.api.dna.domain.StatsDTO;
import com.meli.interview.edgar.api.dna.errorhandling.DNAException;
import com.meli.interview.edgar.api.dna.resources.interfaces.DNAOperations;
import com.meli.interview.edgar.api.dna.validator.APIValidator;
import com.meli.interview.edgar.api.dna.validator.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class DNAController implements DNAOperations {

  private static final String DNA_PROCESSED = "DNA Processed";
  private final DNADelegate dnaDelegate;
  private final Validator<DNARequest> validator;

  public DNAController(DNADelegate dnaDelegate, APIValidator validator) {
    this.dnaDelegate = dnaDelegate;
    this.validator = validator;
  }

  @Override
  public ResponseEntity<String> validateMutantDNA(DNARequest dnaRequest) {
    validator.validate(dnaRequest);
    return dnaDelegate.isMutant(dnaRequest.getDna())
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
