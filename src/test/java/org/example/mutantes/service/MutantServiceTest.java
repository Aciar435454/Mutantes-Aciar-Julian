package org.example.mutantes.service;

import org.example.mutantes.entity.DnaRecord;
import org.example.mutantes.repository.DnaRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MutantServiceTest {

    private MutantDetector detector;
    private DnaRecordRepository repo;
    private MutantService service;

    @BeforeEach
    void setup() {
        detector = mock(MutantDetector.class);
        repo = mock(DnaRecordRepository.class);
        service = new MutantService(detector, repo);
    }

    // ----------------------------------------------------
    // 1) Guarda en BD si no existe y detector retorna true
    // ----------------------------------------------------
    @Test
    void storesResultIfNotExists() {

        List<String> dna = List.of("AAAA", "TTTT", "CCCC", "GGGG");

        when(repo.findByDnaHash(anyString())).thenReturn(Optional.empty());
        when(detector.isMutant(any())).thenReturn(true);

        boolean result = service.analyzeDna(dna);

        assertTrue(result);
        verify(repo, times(1)).save(any(DnaRecord.class));
    }

    // ----------------------------------------------------
    // 2) Devuelve valor cacheado sin llamar al detector
    // ----------------------------------------------------
    @Test
    void returnsCachedValue() {

        List<String> dna = List.of("ATGC", "CAGT", "TTAT", "AGAC");

        DnaRecord record = new DnaRecord();
        record.setMutant(false);

        when(repo.findByDnaHash(anyString())).thenReturn(Optional.of(record));

        boolean result = service.analyzeDna(dna);

        assertFalse(result);
        verify(detector, never()).isMutant(any());
        verify(repo, never()).save(any());
    }
}
