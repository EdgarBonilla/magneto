package com.meli.interview.edgar.api.dna.domain;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DNARequest {

  @NotNull
  @NotEmpty
  @Size(min = 4, message = "FOUR_ELEMENTS_MINIMUM_FOR_PROCESSING")
  private List<String> dna;
}
