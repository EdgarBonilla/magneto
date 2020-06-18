package com.meli.interview.edgar.api.dna.validator;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import com.meli.interview.edgar.api.dna.domain.DNARequest;
import com.meli.interview.edgar.api.dna.errorhandling.DNAException;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class APIValidator implements Validator<DNARequest> {

  private static final String MSG_NUMERIC_LIST = "NOT_ALLOWED_NUMERIC_LIST";
  private static final String MSG_NOT_VALID_LETTERS = "NOT_VALID_LETTERS";
  private static final String MSG_INVALID_SIZE = "NOT_VALID_SIZE_OF_STRINGS";
  private static final List<Character> VALID_LETTER = Arrays.asList('A', 'T', 'C', 'G');

  @Override
  public Boolean validate(DNARequest request) {
    if (numericValidationList(request.getDna())) {
      throw new DNAException(MSG_NUMERIC_LIST);
    } else if (sizeValidationList(request.getDna())) {
      throw new DNAException(MSG_INVALID_SIZE);
    } else if (hasInvalidLetters(request.getDna())) {
      throw new DNAException(MSG_NOT_VALID_LETTERS);
    }

    return Boolean.TRUE;
  }

  private Boolean numericValidationList(List<String> dna) {
    Boolean flag = Boolean.FALSE;
    for (String input : dna) {
      if (!StringUtils.isAlpha(input)) {
        flag = Boolean.TRUE;
        break;
      }
    }

    return flag;
  }

  private Boolean sizeValidationList(List<String> dna) {
    int initialStringSize = 0;
    Boolean firstIteration = Boolean.TRUE;
    Boolean flag = Boolean.FALSE;
    for (String input : dna) {
      if (firstIteration) {
        initialStringSize = input.length();
        firstIteration = Boolean.FALSE;
      }
      if (initialStringSize != input.length()) {
        flag = Boolean.TRUE;
        break;
      }
    }

    return flag;
  }

  private boolean hasInvalidLetters(List<String> dna) {
    Boolean flag = Boolean.FALSE;
    for (String input : dna) {
      for (int index = 0; index < input.length(); index++) {
        char letter = input.charAt(index);
        if (!VALID_LETTER.contains(letter)) {
          flag = Boolean.TRUE;
          break;
        }
      }
    }

    return flag;
  }
}
