package com.meli.interview.edgar.api.dna.mapper;

import com.meli.interview.edgar.api.dna.domain.StatsDTO;
import com.meli.interview.edgar.api.dna.entity.StatsEntity;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class StatsEntityToStatsDTOMapper implements Mapper<StatsEntity, StatsDTO> {

  private final Integer ZERO = 0;
  private final Double ONE = 1.0;

  @Override
  public StatsDTO map(StatsEntity entity) {
    return Objects.nonNull(entity)
        ? new StatsDTO(entity.getMutant_dna(), entity.getHuman_dna(), calculateRatio(entity))
        : null;
  }

  private Double calculateRatio(StatsEntity entity) {
    return (entity.getHuman_dna() != ZERO)
        ? Double.valueOf(entity.getMutant_dna() / entity.getHuman_dna())
        : ONE;
  }
}
