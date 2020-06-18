package com.meli.interview.edgar.api.dna.mapper;

import com.meli.interview.edgar.api.dna.domain.StatsDTO;
import com.meli.interview.edgar.api.dna.entity.StatsEntity;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class StatsEntityToStatsDTOMapper implements Mapper<StatsEntity, StatsDTO> {

  @Override
  public StatsDTO map(StatsEntity entity) {
    return Objects.nonNull(entity)
        ? new StatsDTO(entity.getMutant_dna(), entity.getHuman_dna(),
        Double.valueOf(entity.getMutant_dna() / entity.getHuman_dna()))
        : null;
  }
}
