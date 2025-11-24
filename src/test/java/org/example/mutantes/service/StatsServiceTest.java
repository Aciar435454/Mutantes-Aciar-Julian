package org.example.mutantes.service;

import org.example.mutantes.dto.StatsResponse;
import org.example.mutantes.repository.DnaRecordRepository;
import org.example.mutantes.service.StatsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class StatsServiceTest {

    @Test
    void statsCalculation() {
        DnaRecordRepository repo = Mockito.mock(DnaRecordRepository.class);

        Mockito.when(repo.countByIsMutant(true)).thenReturn(40L);
        Mockito.when(repo.countByIsMutant(false)).thenReturn(100L);

        StatsService service = new StatsService(repo);

        StatsResponse stats = service.getStats();

        assertEquals(40, stats.getCount_mutant_dna());
        assertEquals(100, stats.getCount_human_dna());
        assertEquals(0.4, stats.getRatio());
    }
}

