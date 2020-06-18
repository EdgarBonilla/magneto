package com.meli.interview.edgar.api.dna.delegate;

import com.meli.interview.edgar.api.dna.domain.StatsDTO;
import com.meli.interview.edgar.api.dna.entity.StatsEntity;
import com.meli.interview.edgar.api.dna.mapper.StatsEntityToStatsDTOMapper;
import com.meli.interview.edgar.api.dna.processor.DNAProcessor;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DNADelegate {

  private final DNAProcessor dnaProcessor;
  private final StatsEntityToStatsDTOMapper statsEntityToStatsDTOMapper;

  public DNADelegate(DNAProcessor dnaProcessor, StatsEntityToStatsDTOMapper statsEntityToStatsDTOMapper) {
    this.dnaProcessor = dnaProcessor;
    this.statsEntityToStatsDTOMapper = statsEntityToStatsDTOMapper;
  }

  public boolean isMutantDNA(List<String> dna){
    boolean result = dnaProcessor.processListDNA(dna);
    return true;
  }

  public StatsDTO getStats() {
    StatsEntity statsEntity = new StatsEntity();
    return statsEntityToStatsDTOMapper.map(statsEntity);
  }
}
