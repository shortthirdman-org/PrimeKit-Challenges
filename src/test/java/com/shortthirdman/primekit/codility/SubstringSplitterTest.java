package com.shortthirdman.primekit.codility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubstringSplitterTest {

    SubstringSplitter app;

    @BeforeEach
    void setUp() {
        app = new SubstringSplitter();
    }

    // Test with an empty string
    @Test
    void testEmptyString() {
        assertEquals(0, app.splitSubstrings(""));
    }

    // Test with a string containing all unique characters
    @Test
    void testAllUniqueCharacters() {
        assertEquals(1, app.splitSubstrings("abcdef"));
    }

    // Test with a string with all same characters
    @Test
    void testAllSameCharacters() {
        assertEquals(4, app.splitSubstrings("dddd"));
    }

    // Test with a string with characters repeating immediately
    @Test
    void testImmediateRepetition() {
        assertEquals(2, app.splitSubstrings("abba"));
    }

    // Test with a string with characters repeating non-adjacently
    @Test
    void testNonAdjacentRepetition() {
        assertEquals(3, app.splitSubstrings("abacdec"));
    }

    // Test with a string where the last character repeats the first character
    @Test
    void testLastCharacterRepetition() {
        assertEquals(2, app.splitSubstrings("cycle"));
    }

    // Test with a string containing mixed character repetitions
    @Test
    void testMixedRepetition() {
        assertEquals(4, app.splitSubstrings("abacabad"));
    }

    // Test with a single character string
    @Test
    void testSingleCharacterString() {
        assertEquals(1, app.splitSubstrings("a"));
    }

    // Test with a string of two different characters
    @Test
    void testTwoDifferentCharacters() {
        assertEquals(1, app.splitSubstrings("ab"));
    }

    // Test with a string of two identical characters
    @Test
    void testTwoIdenticalCharacters() {
        assertEquals(2, app.splitSubstrings("aa"));
    }

    // Test with null input to check for exceptions
    @Test
    void testNullInput() {
        assertThrows(NullPointerException.class, () -> app.splitSubstrings(null));
    }

    // Test with longer string with multiple repetitions
    @Test
    void testLongerStringWithMultipleRepetitions() {
        assertEquals(3, app.splitSubstrings("abcadefagh"));
        assertNotEquals(4, app.splitSubstrings("abcadefagh"));
    }

    // Test with non-repeating characters throughout the string
    @Test
    void testNonRepeatingCharacters() {
        assertEquals(1, app.splitSubstrings("abcdefghijklmnopqrstuvwxyz"));
    }

    // Test with repetitive pattern string
    @Test
    void testRepetitivePatternString() {
        assertNotEquals(6, app.splitSubstrings("abcabcabc"));
        assertEquals(3, app.splitSubstrings("abcabcabc"));
    }

    // Test with special case string where each character repeats after every alternate character
    @Test
    void testAlternateRepeatingCharacters() {
        assertEquals(4, app.splitSubstrings("abababab"));
    }

    // Test with long string where every character repeats after some distance
    @Test
    void testLongStringWithRepeats() {
        assertEquals(4, app.splitSubstrings("abacdebfgbhijbk"));
    }
}