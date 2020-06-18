package com.meli.interview.edgar.api.dna.validator;

public interface Validator<T>  {

  Boolean validate(final T input);
}
