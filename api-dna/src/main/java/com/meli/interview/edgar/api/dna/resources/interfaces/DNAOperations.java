package com.meli.interview.edgar.api.dna.resources.interfaces;

import com.meli.interview.edgar.api.dna.domain.DNARequest;
import com.meli.interview.edgar.api.dna.domain.StatsDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface DNAOperations {

  @PostMapping(value = "/mutant/", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<String> validateMutantDNA(@RequestBody DNARequest dnaRequest);

  @GetMapping(value = "/stats/", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<StatsDTO> retrieveStats();
}
