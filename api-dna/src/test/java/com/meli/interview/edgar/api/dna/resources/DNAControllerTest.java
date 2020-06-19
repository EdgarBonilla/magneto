package com.meli.interview.edgar.api.dna.resources;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.when;

import com.meli.interview.edgar.api.dna.delegate.DNADelegate;
import com.meli.interview.edgar.api.dna.domain.DNARequest;
import com.meli.interview.edgar.api.dna.domain.StatsDTO;
import com.meli.interview.edgar.api.dna.validator.APIValidator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class DNAControllerTest {

  @Mock
  private DNADelegate mockDnaDelegate;
  @Mock
  private APIValidator mockValidator;

  private DNAController dnaController;
  private List<String> dnaList;

  @BeforeEach
  public void setUp() {
    dnaList = List.of("ATGAA", "AGTAC", "AGAAC", "TAACC", "TCAAG");
    dnaController = new DNAController(mockDnaDelegate, mockValidator);
  }

  @Test
  public void shouldValidateOkMutantDNAControllerTest() {
    DNARequest dnaRequest = new DNARequest();
    dnaRequest.setDna(dnaList);
    when(mockValidator.validate(dnaRequest)).thenReturn(true);
    when(mockDnaDelegate.isMutant(dnaRequest.getDna())).thenReturn(true);

    ResponseEntity<String> response = dnaController.validateMutantDNA(dnaRequest);

    assertThat(response, notNullValue());
    assertThat(response.getStatusCode(), is(HttpStatus.OK));
  }

  @Test
  public void shouldValidateForbiddenMutantDNAControllerTest() {
    DNARequest dnaRequest = new DNARequest();
    dnaRequest.setDna(dnaList);
    when(mockValidator.validate(dnaRequest)).thenReturn(true);
    when(mockDnaDelegate.isMutant(dnaRequest.getDna())).thenReturn(false);

    ResponseEntity<String> response = dnaController.validateMutantDNA(dnaRequest);

    assertThat(response, notNullValue());
    assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));
  }

  @Test
  public void shouldRetrieveStatsOkDNAControllerTest() {
    when(mockDnaDelegate.getStats()).thenReturn(new StatsDTO());

    ResponseEntity<StatsDTO> response = dnaController.retrieveStats();

    assertThat(response, notNullValue());
    assertThat(response.getStatusCode(), is(HttpStatus.OK));
  }

  @Test
  public void shouldRetrieveStatsNoContentDNAControllerTest() {
    when(mockDnaDelegate.getStats()).thenReturn(null);

    ResponseEntity<StatsDTO> response = dnaController.retrieveStats();

    assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
  }

}
