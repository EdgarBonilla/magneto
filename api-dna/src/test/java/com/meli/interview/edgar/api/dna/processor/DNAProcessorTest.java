package com.meli.interview.edgar.api.dna.processor;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DNAProcessorTest {

  private DNAProcessor dnaProcessor;

  @BeforeEach
  public void setUp() {
    dnaProcessor = new DNAProcessor();
  }

  @Test
  public void shouldProcessDNAHorizontalSearchAsTrueTest() {
    Boolean isMutantTrue = dnaProcessor.processDNA(List.of("AAAAC","AGTAC","AGAAC","TAACC","TCAAG"));
    Assertions.assertEquals(Boolean.TRUE, isMutantTrue);
  }

  @Test
  public void shouldProcessDNAHorizontalSearchAsFalseTest() {
    Boolean isMutantTrue = dnaProcessor.processDNA(List.of("AAATC","AGTAC","AGAAC","TAACT","TCAAG"));
    Assertions.assertEquals(Boolean.FALSE, isMutantTrue);
  }

  @Test
  public void shouldProcessDNAVerticalSearchAsTrueTest() {
    Boolean isMutantTrue = dnaProcessor.processDNA(List.of("AAACC","AGTAC","AGAAC","AAACC","TCAAG"));
    Assertions.assertEquals(Boolean.TRUE, isMutantTrue);
  }

  @Test
  public void shouldProcessDNAVerticalSearchAsFalseTest() {
    Boolean isMutantTrue = dnaProcessor.processDNA(List.of("ACACT","AGTAC","GGAAG","AACCC","TCAGG"));
    Assertions.assertEquals(Boolean.FALSE, isMutantTrue);
  }


  @Test
  public void shouldProcessDNAVerticalSearchTest() {

  }
}
