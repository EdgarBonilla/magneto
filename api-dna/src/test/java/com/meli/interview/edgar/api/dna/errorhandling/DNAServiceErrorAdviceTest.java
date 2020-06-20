package com.meli.interview.edgar.api.dna.errorhandling;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.interview.edgar.api.dna.domain.DNARequest;
import com.meli.interview.edgar.api.dna.resources.DNAController;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class DNAServiceErrorAdviceTest {

  private MockMvc mockMvc;

  @Mock
  private DNAController mockDNAController;

  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @BeforeEach
  public void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new DNAServiceErrorAdvice(), mockDNAController)
        .build();
  }

  @Test
  public void shouldThrowDNAExceptionExceptionTest() throws Exception {
    when(mockDNAController.validateMutantDNA(any())).thenThrow(new DNAException("Exception"));

    mockMvc.perform(post("/mutant/").contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString((new DNARequest(List.of("ABCD", "ABCD", "ABCD", "ABCD"))))))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldThrowMethodArgumentNotValidExceptionTest() throws Exception {
    mockMvc.perform(post("/mutant/").contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString((new DNARequest(List.of("ABCD", "ABCD"))))))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldThrowRuntimeExceptionTest() throws Exception {
    when(mockDNAController.retrieveStats()).thenThrow(new RuntimeException("Unexpected Exception"));

    mockMvc.perform(get("/stats/"))
        .andDo(print())
        .andExpect(status().isInternalServerError());
  }
}
