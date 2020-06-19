package com.meli.interview.edgar.api.dna.validator;

import com.meli.interview.edgar.api.dna.domain.DNARequest;
import com.meli.interview.edgar.api.dna.errorhandling.DNAException;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class APIValidator implements Validator<DNARequest> {

  private static final String MSG_INVALID_SIZE = "THE ELEMENTS OF LIST SHOULD HAVE THE SAME SIZE OF"
      + " THE FIRST DNA LENGTH IN ORDER TO MEET THE NxN CONDITION ALLOWED";
  private static final String MSG_INVALID_WORD_SIZE =
      "THE ELEMENTS OF LIST %s DOES NOT MEET THE SAME "
          + "SIZE OF THE FIRST DNA STRING";
  private static final String MSG_UPPER_CASE_LETTER = "THE CHARACTER %c IS NOT VALID VALUE WITHIN "
      + "THE DNA %s. REVIEW IF THE VALUE IS WITHIN THE ALLOWED VALUE: A, T, C AND G "
      + "(SENSITIVE UPPERCASE AND NOT ALLOWED NUMBERS)";
  private static final List<Character> VALID_LETTER = Arrays.asList('A', 'T', 'C', 'G');

  @Override
  public Boolean validate(DNARequest request) {
    sizeWordsValidation(request.getDna());
    hasInvalidValues(request.getDna());
    return Boolean.TRUE;
  }

  private void sizeWordsValidation(List<String> dnaList) {
    int initialStringSize = dnaList.get(0).length();

    if (initialStringSize != dnaList.size()) {
      throw new DNAException(MSG_INVALID_SIZE);
    }

    for (String input : dnaList) {
      if (initialStringSize != input.length()) {
        throw new DNAException(String.format(MSG_INVALID_WORD_SIZE, input));
      }
    }
  }

  private void hasInvalidValues(List<String> dnaList) {
    for (String dnaString : dnaList) {
      for (int index = 0; index < dnaString.length(); index++) {
        char letter = dnaString.charAt(index);
        if (!VALID_LETTER.contains(letter)) {
          throw new DNAException(String.format(MSG_UPPER_CASE_LETTER, letter, dnaString));
        }
      }
    }
  }
}
