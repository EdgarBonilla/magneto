package com.meli.interview.edgar.api.dna.validator;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import com.meli.interview.edgar.api.dna.domain.DNARequest;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class APIValidator implements Validator<DNARequest> {

  private static final String MSG_NUMERIC_LIST = "NOT_ALLOWED_NUMERIC_LIST";
  private static final String MSG_NOT_VALID_LETTERS = "NOT_VALID_LETTERS";
  private static final List<String> VALID_LETTER = Arrays.asList("A", "T", "C", "G");

  @Override
  public Boolean validate(DNARequest request) {
    if(isNumericList(request.getDna())){
      throw new BadRequestException(Response.status(BAD_REQUEST).entity(MSG_NUMERIC_LIST).build());
    } else if (hasInvalidLetters(request.getDna())){
      throw new BadRequestException(Response.status(BAD_REQUEST).entity(MSG_NOT_VALID_LETTERS).build());
    }

    return true;
  }

  private boolean isNumericList(List<String> dna) {
    boolean flag = false;
    for(String input : dna){
      if(StringUtils.isNumeric(input)){
        flag = Boolean.TRUE;
        break;
      }
    }

    return flag;
  }

  private boolean hasInvalidLetters(List<String> dna) {
    boolean flag = false;
    for(String input : dna){
       for(int index =0; index<input.length(); index++){
         if(!VALID_LETTER.contains(input.charAt(index))){
           flag = true;
           break;
         }
       }
    }

    return flag;
  }
}
