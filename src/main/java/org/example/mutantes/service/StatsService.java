package org.example.mutantes.service;

import org.example.mutantes.dto.StatsResponse;
import org.example.mutantes.repository.DnaRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final DnaRecordRepository repo;

    public StatsResponse getStats() {
        long mutants = repo.countByIsMutant(true);
        long humans = repo.countByIsMutant(false);
        double ratio = humans == 0 ? 0 : (double) mutants / humans;

        return new StatsResponse(mutants, humans, ratio);
    }
}
