package com.meli.interview.edgar.api.dna.mapper;

public interface Mapper<I, O> {

  O map(final I input);
}
