package org.example.mutantes.service;

import lombok.RequiredArgsConstructor;
import org.example.mutantes.entity.DnaRecord;
import org.example.mutantes.exception.InvalidDnaException;
import org.example.mutantes.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MutantService {

    private final MutantDetector detector;
    private final DnaRecordRepository repo;

    // ============================================================
    // MÉTODO PRINCIPAL USADO POR EL CONTROLLER
    // ============================================================
    public boolean analyzeDna(List<String> dnaList) {

        if (dnaList == null || dnaList.isEmpty()) {
            throw new InvalidDnaException("El ADN no puede estar vacío.");
        }

        // Convertimos la lista a arreglo
        String[] dna = dnaList.toArray(new String[0]);

        // Validar contenido (si falla → 400 BAD REQUEST)
        validateDna(dna);

        // Crear hash para evitar recalcular y guardar duplicados
        String hash = hashDna(dna);

        // Buscar en cache (base de datos)
        Optional<DnaRecord> existing = repo.findByDnaHash(hash);

        if (existing.isPresent()) {
            return existing.get().isMutant();
        }

        // Analizar realmente si es mutante
        boolean result = detector.isMutant(dna);

        // Guardar en BD
        DnaRecord record = new DnaRecord();
        record.setDnaHash(hash);
        record.setMutant(result);
        repo.save(record);

        return result;
    }

    // ============================================================
    // VALIDACIÓN DEL ADN (400 BAD REQUEST)
    // ============================================================
    private void validateDna(String[] dna) {

        int n = dna.length;

        for (String row : dna) {

            if (row == null) {
                throw new InvalidDnaException("Una fila del ADN es null.");
            }

            if (row.length() != n) {
                throw new InvalidDnaException("El ADN debe ser una matriz NxN.");
            }

            if (!row.matches("[ATCG]+")) {
                throw new InvalidDnaException("El ADN solo puede contener A, T, C o G.");
            }
        }
    }

    // ============================================================
    // HASH SHA-256 PARA CACHE Y BD
    // ============================================================
    private String hashDna(String[] dna) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            for (String row : dna) {
                md.update(row.getBytes());
            }

            StringBuilder sb = new StringBuilder();
            for (byte b : md.digest()) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error generando hash de ADN.", e);
        }
    }
}



