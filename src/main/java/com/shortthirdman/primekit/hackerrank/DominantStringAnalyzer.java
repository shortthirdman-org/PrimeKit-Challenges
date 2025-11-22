package com.shortthirdman.primekit.hackerrank;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * A team of data analysts at Amazon is working to identify data patterns.
 * During their analysis, they found a category of string they call dominant string:
 * <br><br/>
 * - It has an even length.
 * <br><br/>
 * - The string contains at least one character with a frequency that matches exactly half of the length of the string.
 * <br><br/>
 * Given a string <code>s</code> of length <code>n</code>, determine the number of its distinct substrings that are dominant strings.
 *
 * @author ShortThirdMan
 */
public class DominantStringAnalyzer {

    /**
     * @param s the input text
     * @return the count or number of dominant string
     */
    public long getDominantStringCount(String s) {
        long count = 0;
        int n = s.length();
        var lowercaseText = s.toLowerCase();

        count = IntStream.range(2, n + 1)
                .filter(len -> len % 2 == 0)  // Only consider even lengths
                .mapToLong(len ->
                        IntStream.range(0, n - len + 1)
                                .mapToObj(i -> lowercaseText.substring(i, i + len))
                                .filter(substring -> isDominant(substring, len))
                                .count()
                ).sum();

        return count;
    }

    private boolean isDominant(String substring, int len) {
        int[] frequency = new int[10];
        substring.chars()
                .forEach(c -> {
                    // Ensure the character is between 'a' and 'z' before updating frequency
                    if (c >= 'a' && c <= 'z') {
                        frequency[c - 'a']++;
                    }
                });

        int halfLength = len / 2;
        return Arrays.stream(frequency)
                .anyMatch(f -> f == halfLength);
    }
}
