package com.meli.interview.edgar.api.dna.processor;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DNAProcessor {

  private static final Integer FOUR = 4;
  private char[][] wordBoard = null;
  private int indexRow;
  private int dnaInputs;

  public boolean processDNA(List<String> dna) {
    indexRow = 0;
    dnaInputs = dna.size();
    int dnaLength = dna.get(0).length();
    wordBoard = new char[dnaInputs][dnaLength];

    for (String dnaString : dna) {
      if (initWorkBoard(dnaString, wordBoard)) {
        return Boolean.TRUE;
      }
      indexRow++;
    }

    return verticalSearchCoincidence(wordBoard, dnaInputs);
  }

  private Boolean initWorkBoard(String dnaString, char[][] charBoard) {
    Boolean isMutant = horizontalSearchCoincidence(dnaString);
    if (!isMutant) {
      for (int index = 0; index < dnaString.length(); index++) {
        char letter = dnaString.charAt(index);
        charBoard[indexRow][index] = letter;
      }
    }

    return isMutant;
  }

  private Boolean horizontalSearchCoincidence(String dnaString) {
    char[] letters = dnaString.toCharArray();
    char currentLetter = letters[0];
    int countCoincidences = 0;
    for (int row = 0; row < letters.length; row++) {
      if (letters[row] == currentLetter) {
        countCoincidences = countCoincidences + 1;
      } else {
        currentLetter = letters[row];
        countCoincidences = 1;
      }

      if (countCoincidences == FOUR) {
        return Boolean.TRUE;
      }
    }

    return Boolean.FALSE;
  }

  private Boolean verticalSearchCoincidence(char[][] wordBoard, int dnaInputs) {
    for (int i = 0; i < dnaInputs; i++) {
      int axisY = i;
      char currentLetter = wordBoard[0][axisY];
      int countCoincidences = 0;
      for (int axisX = 0; axisX < dnaInputs; axisX++) {
        if (wordBoard[axisX][axisY] == currentLetter) {
          countCoincidences = countCoincidences + 1;
        } else {
          currentLetter = wordBoard[axisX][axisY];
          countCoincidences = 1;
        }
        if (countCoincidences == FOUR) {
          return Boolean.TRUE;
        }
      }
    }

    return Boolean.FALSE;
  }
}
