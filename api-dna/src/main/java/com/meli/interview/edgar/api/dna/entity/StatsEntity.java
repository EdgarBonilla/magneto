package com.meli.interview.edgar.api.dna.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "passenger")
public class StatsEntity {

  @Id
  private Integer index;
  private Integer mutant_dna;
  private Integer human_dna;
}
