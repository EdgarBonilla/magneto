package com.meli.interview.edgar.api.dna.delegate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.interview.edgar.api.dna.domain.StatsDTO;
import com.meli.interview.edgar.api.dna.entity.DNAEntity;
import com.meli.interview.edgar.api.dna.entity.StatsEntity;
import com.meli.interview.edgar.api.dna.mapper.Mapper;
import com.meli.interview.edgar.api.dna.mapper.StatsEntityToStatsDTOMapper;
import com.meli.interview.edgar.api.dna.processor.DNAProcessor;
import com.meli.interview.edgar.api.dna.repository.DNARepository;
import com.meli.interview.edgar.api.dna.repository.StatsRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class DNADelegate {

  private final Integer ZERO = 0;
  private final Integer ONE = 1;
  private final DNAProcessor dnaProcessor;
  private final Mapper<StatsEntity, StatsDTO> statsEntityToStatsDTOMapper;
  private final JpaRepository<StatsEntity, Integer> statsRepository;
  private final JpaRepository<DNAEntity, Integer> dnaRepository;

  public DNADelegate(DNAProcessor dnaProcessor,
      StatsEntityToStatsDTOMapper statsEntityToStatsDTOMapper,
      StatsRepository statsRepository,
      DNARepository dnaRepository) {
    this.dnaProcessor = dnaProcessor;
    this.statsEntityToStatsDTOMapper = statsEntityToStatsDTOMapper;
    this.statsRepository = statsRepository;
    this.dnaRepository = dnaRepository;
  }

  public boolean isMutant(List<String> dnaList) {
    boolean isMutant = dnaProcessor.processDNA(dnaList);
    List<StatsEntity> statsEntity = statsRepository.findAll();
    if (!CollectionUtils.isEmpty(statsEntity)) {
      saveCurrentStats(isMutant, statsEntity.get(ZERO));
    } else {
      saveInitialStats(isMutant);
    }

    if (isMutant) {
      try {
        ObjectMapper object = new ObjectMapper();
        String jsonStr = object.writeValueAsString(dnaList.toString());
        DNAEntity dnaEntity = new DNAEntity();
        dnaEntity.setDnaString(jsonStr);
        dnaRepository.save(dnaEntity);
      } catch (JsonProcessingException exception) {
        exception.printStackTrace();
      }
    }

    return isMutant;
  }

  public StatsDTO getStats() {
    List<StatsEntity> statsEntityList = statsRepository.findAll();
    return !CollectionUtils.isEmpty(statsEntityList)
        ? statsEntityToStatsDTOMapper.map(statsRepository.findAll().get(ZERO))
        : null;
  }

  private void saveCurrentStats(boolean isMutant, StatsEntity statsEntity) {
    if (isMutant) {
      statsEntity.setMutant_dna(statsEntity.getMutant_dna() + ONE);
      statsRepository.saveAndFlush(statsEntity);
    } else {
      statsEntity.setHuman_dna(statsEntity.getHuman_dna() + ONE);
      statsRepository.saveAndFlush(statsEntity);
    }
  }

  private void saveInitialStats(boolean isMutant) {
    if (isMutant) {
      StatsEntity statsEntity = new StatsEntity();
      statsEntity.setMutant_dna(ONE);
      statsEntity.setHuman_dna(ZERO);
      statsRepository.save(statsEntity);
    } else {
      StatsEntity statsEntity = new StatsEntity();
      statsEntity.setMutant_dna(ZERO);
      statsEntity.setHuman_dna(ONE);
      statsRepository.save(statsEntity);
    }
  }
}
