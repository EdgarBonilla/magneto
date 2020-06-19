package com.meli.interview.edgar.api.dna.processor;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DNAProcessor {

  private char[][] wordBoard = null;
  private int indexRow;

  public boolean processDNA(List<String> dna) {
    indexRow = 0;
    int dnaInputs = dna.size();
    int dnaLength = dna.get(0).length();
    wordBoard = new char[dnaInputs][dnaLength];

    for (String dnaString : dna) {
      initWorkBoard(dnaString, wordBoard);
      indexRow++;
    }

    //imprimir(wordBoard);

    return false;
  }

  private void initWorkBoard(String dnaString, char[][] charBoard) {
    for (int index = 0; index < dnaString.length(); index++) {
      char letter = dnaString.charAt(index);
      charBoard[indexRow][index] = letter;
    }
  }

  private void imprimir(char[][] wordBoard) {
    for (int x = 0; x < wordBoard.length; x++) {
      System.out.print("|");
      for (int y = 0; y < wordBoard[x].length; y++) {
        System.out.print(wordBoard[x][y]);
        if (y != wordBoard[x].length - 1) {
          System.out.print("\t");
        }
      }
      System.out.println("|");
    }
  }
}
