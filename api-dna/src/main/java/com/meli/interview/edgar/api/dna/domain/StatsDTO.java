package com.meli.interview.edgar.api.dna.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatsDTO {

  private Integer count_mutant_dna;
  private Integer count_human_dna;
  private Double ratio;
}
