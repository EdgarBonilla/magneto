package com.meli.interview.edgar.api.dna.repository;

import com.meli.interview.edgar.api.dna.entity.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends JpaRepository<StatsEntity, Integer> {
}
