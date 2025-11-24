package org.example.mutantes.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MutantDetectorTest {

    private MutantDetector detector;

    @BeforeEach
    void setUp() {
        detector = new MutantDetector();
    }

    // --------------------------------------------------
    //  MUTANTE HORIZONTAL (2 secuencias horizontales)
    // --------------------------------------------------
    @Test
    void mutantHorizontal() {
        String[] dna = {
                "AAAA", // Secuencia 1
                "CCCC", // Secuencia 2
                "TGCT",
                "CTGA"
        };
        assertTrue(detector.isMutant(dna));
    }

    // --------------------------------------------------
    //  MUTANTE VERTICAL (2 secuencias verticales)
    // --------------------------------------------------
    @Test
    void mutantVertical() {
        String[] dna = {
                "ATGC",
                "ATGC",
                "ATGC",
                "ATGC"
        };
        // Esta matriz tiene 4 columnas con verticales.
        // Tu detector va a cortar apenas encuentre 2.
        assertTrue(detector.isMutant(dna));
    }

    // --------------------------------------------------
    //  MUTANTE DIAGONAL (2 diagonales)
    // --------------------------------------------------
    @Test
    void mutantDiagonal() {
        String[] dna = {
                "ATGCA",
                "CAGTA",
                "CTATA",
                "CGTAA",
                "AAAAA"  // Segunda diagonal clara
        };
        assertTrue(detector.isMutant(dna));
    }

    // --------------------------------------------------
    //  HUMANO (0 secuencias)
    // --------------------------------------------------
    @Test
    void nonMutant() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TTAT",
                "AGAC"
        };
        assertFalse(detector.isMutant(dna));
    }
}

