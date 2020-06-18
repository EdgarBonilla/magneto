package com.meli.interview.edgar.api.dna.entity;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "passenger")
public class StatsEntity {

  private Integer mutant_dna;
  private Integer human_dna;
}
