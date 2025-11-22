package com.shortthirdman.primekit.codility;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given a string consisting of lowercase letters of the English alphabet.
 * You must split this string into a minimal number of substrings in such a way that no letter
 * occurs more than once in each substring.
 * For example, here are some correct splits of the string <pre>"abacdec"</pre>
 * <pre>
 *     ('a', 'bac', 'dec'), ('a', bacd', 'ec') and ('ab', 'ac', 'dec')
 * </pre>
 *
 * @author ShortThirdMan
 * @since 1.0.0
 */
public class SubstringSplitter {

    public int splitSubstrings(String S) {
        if (S == null) {
            throw new NullPointerException("String input can not be null or empty");
        }

        if (S.isEmpty()) {
            return 0;
        }

        Set<Character> seenCharacters = new HashSet<>();
        int substringsCount = 1;  // We start with one substring

        for (char c : S.toCharArray()) {
            // If the character is already in the current substring, start a new substring
            if (seenCharacters.contains(c)) {
                substringsCount++;  // Start a new substring
                seenCharacters.clear();  // Clear the set for the new substring
            }
            // Add the current character to the set
            seenCharacters.add(c);
        }

        return substringsCount;
    }

    @Deprecated
    public int splitString(String S) {
        int n = S.length();
        if (n == 0) return 0;

        // Step 1: Find the last occurrence of each character
        int[] lastOccurrence = new int[26];
        for (int i = 0; i < n; i++) {
            lastOccurrence[S.charAt(i) - 'a'] = i;
        }

        // Step 2: Split the string into minimal substrings
//        int start = 0;
        int end = 0; // The end of the current substring
        int substringsCount = 0;

        for (int i = 0; i < n; i++) {
            // Update the end of the current substring to be the farthest
            // we need to go to include all characters seen so far
            end = Math.max(end, lastOccurrence[S.charAt(i) - 'a']);

            // When we reach the end of the current substring
            if (i == end) {
                substringsCount++;
//                start = i + 1;
                end = i + 1; // Update end to start a new substring
            }
        }

        return substringsCount;
    }
}
