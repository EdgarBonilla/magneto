package com.meli.interview.edgar.api.dna.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.meli.interview.edgar.api.dna.domain.DNARequest;
import com.meli.interview.edgar.api.dna.errorhandling.DNAException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class APIValidatorTest {

  private static final String MSG_INVALID_SIZE = "THE ELEMENTS OF LIST SHOULD HAVE THE SAME SIZE OF"
      + " THE FIRST DNA LENGTH IN ORDER TO MEET THE NxN CONDITION ALLOWED";
  private static final String MSG_INVALID_WORD_SIZE =
      "THE ELEMENTS OF LIST %s DOES NOT MEET THE SAME SIZE OF THE FIRST DNA STRING";
  private static final String MSG_UPPER_CASE_LETTER = "THE CHARACTER %c IS NOT VALID VALUE WITHIN "
      + "THE DNA %s. REVIEW IF THE VALUE IS WITHIN THE ALLOWED VALUE: A, T, C AND G "
      + "(SENSITIVE UPPERCASE AND NOT ALLOWED NUMBERS)";

  private APIValidator apiValidator;

  @BeforeEach
  public void setUp() {
    apiValidator = new APIValidator();
  }

  @Test
  public void shouldValidateOkValidationMessageTest() {
    List<String> adnList = List.of("ATGAA", "AGTAC", "AGAAC", "TAACC", "TAACC");
    Boolean response = apiValidator.validate(new DNARequest(adnList));

    assertEquals(Boolean.TRUE, response);
  }

  @Test
  public void shouldThrowInvalidSizeValidationMessageTest() {
    List<String> adnList = List.of("ATGAA", "AGTAC", "AGAAC", "TAACC");
    DNAException thrown =
        Assertions.assertThrows(
            DNAException.class,
            () ->
                apiValidator.validate(new DNARequest(adnList)), MSG_INVALID_SIZE);
    Assertions.assertTrue(MSG_INVALID_SIZE.equals(thrown.getMessage()));
  }

  @Test
  public void shouldThrowInvalidWordSizeValidationMessageTest() {
    List<String> adnList = List.of("ATGA", "AGT", "AGAC", "TAAC");
    DNAException thrown =
        Assertions.assertThrows(
            DNAException.class,
            () ->
                apiValidator.validate(new DNARequest(adnList)), MSG_INVALID_WORD_SIZE);
    Assertions.assertTrue(String.format(MSG_INVALID_WORD_SIZE, "AGT").equals(thrown.getMessage()));
  }

  @Test
  public void shouldThrowInvalidCharactersValidationMessageTest() {
    List<String> adnList = List.of("ATGA", "A2TG", "AGAC", "TAAC");
    DNAException thrown =
        Assertions.assertThrows(
            DNAException.class,
            () ->
                apiValidator.validate(new DNARequest(adnList)), MSG_UPPER_CASE_LETTER);
    Assertions
        .assertTrue(String.format(MSG_UPPER_CASE_LETTER, '2', "A2TG").equals(thrown.getMessage()));
  }

}
