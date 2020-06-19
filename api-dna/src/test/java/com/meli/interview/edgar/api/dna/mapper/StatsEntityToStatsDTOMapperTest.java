package com.meli.interview.edgar.api.dna.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.meli.interview.edgar.api.dna.domain.StatsDTO;
import com.meli.interview.edgar.api.dna.entity.StatsEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StatsEntityToStatsDTOMapperTest {

  private StatsEntityToStatsDTOMapper statsEntityToStatsDTOMapper;

  @BeforeEach
  public void setUp() {
    statsEntityToStatsDTOMapper = new StatsEntityToStatsDTOMapper();
  }

  @Test
  public void shouldMapNullStatsEntityToStatsDTOTest() {
    Assertions.assertNull(statsEntityToStatsDTOMapper.map(null));
  }

  @Test
  public void shouldMapStatsEntityToStatsDTOTest() {
    StatsEntity statsEntity = new StatsEntity();
    statsEntity.setHuman_dna(4);
    statsEntity.setMutant_dna(2);

    StatsDTO statsDTO = statsEntityToStatsDTOMapper.map(statsEntity);

    assertEquals(4, statsDTO.getCount_human_dna());
    assertEquals(2, statsDTO.getCount_mutant_dna());
    assertEquals(0.5, statsDTO.getRatio());
  }

  @Test
  public void shouldMapCalculateRationTest() {
    StatsEntity statsEntity = new StatsEntity();
    statsEntity.setHuman_dna(0);
    statsEntity.setMutant_dna(2);

    StatsDTO statsDTO = statsEntityToStatsDTOMapper.map(statsEntity);

    assertEquals(0, statsDTO.getCount_human_dna());
    assertEquals(2, statsDTO.getCount_mutant_dna());
    assertEquals(1, statsDTO.getRatio());
  }
}
