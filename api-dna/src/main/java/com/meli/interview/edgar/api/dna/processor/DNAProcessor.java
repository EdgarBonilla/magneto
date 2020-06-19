package com.meli.interview.edgar.api.dna.processor;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DNAProcessor {

  private int indexRow = 0;

  public boolean processDNA(List<String> dna) {
    int dnaInputs = dna.size();
    int dnaLength = dna.get(0).length();
    char[][] wordBoard = new char[dnaInputs][dnaLength];

    for (String dnaString : dna) {
      initWorkBoard(dnaString, wordBoard);
    }

    return true;
  }

  private void initWorkBoard(String dnaString, char[][] charBoard) {
    for (int index = 0; index < dnaString.length(); index++) {
      char letter = dnaString.charAt(index);
      charBoard[indexRow][index] = letter;
    }

    indexRow++;
  }
}
