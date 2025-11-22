package com.shortthirdman.primekit.codesignal;

/**
 * @author ShortThirdMan
 */
public class FrameGenerator {

    /**
     * Generates a square frame of size {@code n} as an array of strings.
     * @param n the size of the square frame; must be at least 2
     * @return an array of {@code n} strings, each of length {@code n}, representing the frame
     * @throws IllegalArgumentException if {@code n < 2}
     */
    public String[] generate(int n) {
        if (n < 2) {
            throw new IllegalArgumentException("Frame size must be at least 2.");
        }

        String[] frame = new String[n];

        // Top and bottom rows are completely filled with '*'
        String borderRow = "*".repeat(n);

        // Middle rows have '*' at start and end, spaces in between
        String middleRow = "*" + " ".repeat(n - 2) + "*";

        for (int i = 0; i < n; i++) {
            if (i == 0 || i == n - 1) {
                frame[i] = borderRow;
            } else {
                frame[i] = middleRow;
            }
        }

        return frame;
    }
}
