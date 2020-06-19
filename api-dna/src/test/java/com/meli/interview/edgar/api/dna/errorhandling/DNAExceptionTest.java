package com.meli.interview.edgar.api.dna.errorhandling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DNAExceptionTest {

  private static final String MESSAGE = "MOCKED_MESSAGE";

  @Test
  public void shouldRetunrMessageDNAExceptionTest() {
    DNAException dnaException = new DNAException(MESSAGE);
    Assertions.assertEquals(MESSAGE, dnaException.getMessage());
  }
}
