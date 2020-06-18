package com.meli.interview.edgar.api.dna.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsDTO {

  private Integer count_mutant_dna;
  private Integer count_human_dna;
  private Double ratio;
}
