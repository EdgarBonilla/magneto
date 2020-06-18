package com.meli.interview.edgar.api.dna.delegate;

import com.meli.interview.edgar.api.dna.domain.StatsDTO;
import com.meli.interview.edgar.api.dna.entity.StatsEntity;
import com.meli.interview.edgar.api.dna.mapper.StatsEntityToStatsDTOMapper;
import com.meli.interview.edgar.api.dna.processor.DNAProcessor;
import com.meli.interview.edgar.api.dna.repository.StatsRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DNADelegate {

  private final DNAProcessor dnaProcessor;
  private final StatsEntityToStatsDTOMapper statsEntityToStatsDTOMapper;
  private final StatsRepository statsRepository;

  public DNADelegate(DNAProcessor dnaProcessor, StatsEntityToStatsDTOMapper statsEntityToStatsDTOMapper,
      StatsRepository statsRepository) {
    this.dnaProcessor = dnaProcessor;
    this.statsEntityToStatsDTOMapper = statsEntityToStatsDTOMapper;
    this.statsRepository = statsRepository;
  }

  public boolean isMutantDNA(List<String> dna){
    boolean result = dnaProcessor.processListDNA(dna);
    return true;
  }

  public StatsDTO getStats() {
    StatsEntity statsEntity = new StatsEntity();
    statsEntity.setMutant_dna(20);
    statsEntity.setHuman_dna(80);

    return statsEntityToStatsDTOMapper.map(statsEntity);
  }
}
