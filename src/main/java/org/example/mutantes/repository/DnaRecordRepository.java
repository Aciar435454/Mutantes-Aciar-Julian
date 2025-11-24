package org.example.mutantes.repository;

import org.example.mutantes.entity.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {

    Optional<DnaRecord> findByDnaHash(String hash);

    long countByIsMutant(boolean isMutant);
}

