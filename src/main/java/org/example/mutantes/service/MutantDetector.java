package org.example.mutantes.service;

import org.springframework.stereotype.Component;

@Component
public class MutantDetector {

    private static final int SEQ = 4;

    public boolean isMutant(String[] dna) {
        int n = dna.length;
        char[][] m = new char[n][n];

        for (int i = 0; i < n; i++) m[i] = dna[i].toCharArray();

        int count = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {

                if (c <= n - SEQ && checkHorizontal(m, r, c)) count++;
                if (r <= n - SEQ && checkVertical(m, r, c)) count++;
                if (r <= n - SEQ && c <= n - SEQ && checkDiagDown(m, r, c)) count++;
                if (r >= SEQ - 1 && c <= n - SEQ && checkDiagUp(m, r, c)) count++;

                if (count > 1) return true; // EARLY TERMINATION
            }
        }
        return false;
    }

    private boolean checkHorizontal(char[][] m, int r, int c) {
        char b = m[r][c];
        return m[r][c + 1] == b && m[r][c + 2] == b && m[r][c + 3] == b;
    }

    private boolean checkVertical(char[][] m, int r, int c) {
        char b = m[r][c];
        return m[r + 1][c] == b && m[r + 2][c] == b && m[r + 3][c] == b;
    }

    private boolean checkDiagDown(char[][] m, int r, int c) {
        char b = m[r][c];
        return m[r + 1][c + 1] == b && m[r + 2][c + 2] == b && m[r + 3][c + 3] == b;
    }

    private boolean checkDiagUp(char[][] m, int r, int c) {
        char b = m[r][c];
        return m[r - 1][c + 1] == b && m[r - 2][c + 2] == b && m[r - 3][c + 3] == b;
    }
}

