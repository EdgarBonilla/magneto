package com.meli.interview.edgar.api.dna.mapper;

import com.meli.interview.edgar.api.dna.domain.StatsDTO;
import com.meli.interview.edgar.api.dna.entity.StatsEntity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class StatsEntityToStatsDTOMapper implements Mapper<StatsEntity, StatsDTO> {

  private static final Integer ZERO = 0;
  private static final Double ONE = 1.0;

  @Override
  public StatsDTO map(StatsEntity entity) {
    return Objects.nonNull(entity)
        ? new StatsDTO(entity.getMutant_dna(), entity.getHuman_dna(),
        BigDecimal.valueOf(calculateRatio(entity)).setScale(2, RoundingMode.HALF_UP).doubleValue())
        : null;
  }

  private Double calculateRatio(StatsEntity entity) {
    return (entity.getHuman_dna() == ZERO) ? ONE
        : Double
            .valueOf(entity.getMutant_dna().doubleValue() / entity.getHuman_dna().doubleValue());
  }
}
