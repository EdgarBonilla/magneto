package com.meli.interview.edgar.api.dna.delegate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.meli.interview.edgar.api.dna.domain.StatsDTO;
import com.meli.interview.edgar.api.dna.entity.StatsEntity;
import com.meli.interview.edgar.api.dna.mapper.StatsEntityToStatsDTOMapper;
import com.meli.interview.edgar.api.dna.processor.DNAProcessor;
import com.meli.interview.edgar.api.dna.repository.DNARepository;
import com.meli.interview.edgar.api.dna.repository.StatsRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DNADelegateTest {

  @Mock
  private DNAProcessor mockDnaProcessor;

  @Mock
  private StatsEntityToStatsDTOMapper mockStatsEntityToStatsDTOMapper;

  @Mock
  private StatsRepository mockStatsRepository;

  @Mock
  private DNARepository mockDnaRepository;

  private DNADelegate dnaDelegate;

  @BeforeEach
  public void setUp() {
    dnaDelegate = new DNADelegate(mockDnaProcessor, mockStatsEntityToStatsDTOMapper
        , mockStatsRepository, mockDnaRepository);
  }

  @Test
  public void shouldDelegateIsMutantTest() {
    StatsEntity statsEntity = new StatsEntity();
    statsEntity.setMutant_dna(1);
    statsEntity.setHuman_dna(0);
    when(mockDnaProcessor.processDNA(any())).thenReturn(true);
    when(mockStatsRepository.findAll()).thenReturn(List.of(statsEntity));

    Boolean result = dnaDelegate.isMutant(List.of("AAAAC", "AGTAC", "AGAAC", "TAACC", "TCAAG"));

    Assertions.assertEquals(Boolean.TRUE, result);
  }

  @Test
  public void shouldDelegateIsMutantFalseTest() {
    StatsEntity statsEntity = new StatsEntity();
    statsEntity.setMutant_dna(0);
    statsEntity.setHuman_dna(1);
    when(mockDnaProcessor.processDNA(any())).thenReturn(false);
    when(mockStatsRepository.findAll()).thenReturn(List.of(statsEntity));

    Boolean result = dnaDelegate.isMutant(List.of("ACAAC", "AGTAC", "AGAAC", "TAACC", "TCAAG"));

    Assertions.assertEquals(Boolean.TRUE, result);
  }

  @Test
  public void shouldSaveInformationIsMutantTrueFirstTimeTest() {
    when(mockDnaProcessor.processDNA(any())).thenReturn(true);
    when(mockStatsRepository.findAll()).thenReturn(null);

    Boolean result = dnaDelegate.isMutant(List.of("AAAAC", "AGTAC", "AGAAC", "TAACC", "TCAAG"));

    Assertions.assertEquals(Boolean.TRUE, result);
  }

  @Test
  public void shouldSaveInformationIsMutantFalseFirstTimeTest() {
    when(mockDnaProcessor.processDNA(any())).thenReturn(false);
    when(mockStatsRepository.findAll()).thenReturn(null);

    Boolean result = dnaDelegate.isMutant(List.of("AACAC", "AGTAC", "AGAAC", "TAACC", "TCAAG"));

    Assertions.assertEquals(Boolean.TRUE, result);
  }

  @Test
  public void shouldReturnStatsDTOWhenDelegateGetStatsTest() {
    StatsEntity statsEntity = new StatsEntity();
    statsEntity.setMutant_dna(1);
    statsEntity.setHuman_dna(1);

    StatsDTO statsMapperDTO = new StatsDTO();
    statsMapperDTO.setCount_mutant_dna(1);
    statsMapperDTO.setCount_human_dna(1);
    statsMapperDTO.setRatio(Double.valueOf(1));
    when(mockStatsRepository.findAll()).thenReturn(List.of(statsEntity));
    when(mockStatsEntityToStatsDTOMapper.map(statsEntity)).thenReturn(statsMapperDTO);

    StatsDTO statsDTO = dnaDelegate.getStats();

    Assertions.assertEquals(Integer.valueOf(1), statsDTO.getCount_mutant_dna());
    Assertions.assertEquals(Integer.valueOf(1), statsDTO.getCount_human_dna());
    Assertions.assertEquals(Double.valueOf("1.0"), statsDTO.getRatio());
  }

  @Test
  public void shouldReturnNullWhenDelegateGetStatsIsNullTest() {
    when(mockStatsRepository.findAll()).thenReturn(null);

    StatsDTO statsDTO = dnaDelegate.getStats();

    Assertions.assertNull(statsDTO);
  }
}
