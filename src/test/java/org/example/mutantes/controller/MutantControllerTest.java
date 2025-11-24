package org.example.mutantes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.mutantes.dto.DnaRequest;
import org.example.mutantes.dto.StatsResponse;
import org.example.mutantes.dto.StrictStringDeserializer;
import org.example.mutantes.service.MutantService;
import org.example.mutantes.service.StatsService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MutantController.class)
@Import(StrictStringDeserializer.class)
class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutantService mutantService;

    @MockBean
    private StatsService statsService;

    @Autowired
    private ObjectMapper mapper;

    // ----------------------------------------------------
    // 200 OK – MUTANTE
    // ----------------------------------------------------
    @Test
    void mutantReturns200() throws Exception {
        List<String> dna = List.of("AAAA", "CCCC", "TTTT", "GGGG");
        DnaRequest req = new DnaRequest(dna);

        when(mutantService.analyzeDna(dna)).thenReturn(true);

        mockMvc.perform(post("/mutant")
                        .content(mapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // ----------------------------------------------------
    // 403 – HUMANO
    // ----------------------------------------------------
    @Test
    void humanReturns403() throws Exception {
        List<String> dna = List.of("ATGC", "CAGT", "TTAT", "AGAC");
        DnaRequest req = new DnaRequest(dna);

        when(mutantService.analyzeDna(dna)).thenReturn(false);

        mockMvc.perform(post("/mutant")
                        .content(mapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    // ----------------------------------------------------
    // 400 – JSON MAL FORMADO
    // ----------------------------------------------------
    @Test
    void invalidJsonReturns400() throws Exception {
        String invalidJson = "{ \"dna\": [ \"ATGC\", 123 ] }"; // Número inválido

        mockMvc.perform(post("/mutant")
                        .content(invalidJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    // ----------------------------------------------------
    // 400 – ADN INVALIDO (ControllerAdvice)
    // ----------------------------------------------------
    @Test
    void invalidDnaReturns400() throws Exception {
        List<String> dna = List.of("ATGC", "CAGT", "TTAT", "AGXT"); // Letra inválida
        DnaRequest req = new DnaRequest(dna);

        when(mutantService.analyzeDna(dna))
                .thenThrow(new RuntimeException("ADN inválido"));

        mockMvc.perform(post("/mutant")
                        .content(mapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    // ----------------------------------------------------
    // /stats – 200 OK
    // ----------------------------------------------------
    @Test
    void statsReturns200() throws Exception {
        StatsResponse stats = new StatsResponse(40, 100, 0.4);

        when(statsService.getStats()).thenReturn(stats);

        mockMvc.perform(get("/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutant_dna").value(40))
                .andExpect(jsonPath("$.count_human_dna").value(100))
                .andExpect(jsonPath("$.ratio").value(0.4));
    }
}



