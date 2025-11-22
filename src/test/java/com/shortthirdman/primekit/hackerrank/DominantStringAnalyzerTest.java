package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DominantStringAnalyzerTest {

    DominantStringAnalyzer app;

    @BeforeEach
    void setUp() {
        app = new DominantStringAnalyzer();
    }

    @Test
    void testEmptyString() {
        assertEquals(0, app.getDominantStringCount(""));
    }

    @Test
    void testSingleCharacter() {
        assertEquals(0, app.getDominantStringCount("a"));
    }

    @Test
    void testTwoSameCharacters() {
        assertEquals(0, app.getDominantStringCount("aa"));
    }

    @Test
    void testTwoDifferentCharacters() {
        assertEquals(1, app.getDominantStringCount("ab"));
    }

    @Test
    void testFourSameCharacters() {
        assertEquals(0, app.getDominantStringCount("aaaa"));
    }

    @Test
    void testTwoPairsDominantSubstrings() {
        assertEquals(2, app.getDominantStringCount("aabb"));
    }

    @Test
    void testMixedCaseInput() {
        assertEquals(2, app.getDominantStringCount("AaBb"));
    }

    @Test
    void testNonLetterCharactersIncluded() {
        assertEquals(4, app.getDominantStringCount("a1a1"));
    }

    @Test
    void testRepeatingPattern() {
        assertEquals(9, app.getDominantStringCount("ababab"));
    }

    @Test
    void testOddLengthInput() {
        assertEquals(2, app.getDominantStringCount("abc"));
    }

    @Test
    void testPalindromeEvenLength() {
        assertEquals(3, app.getDominantStringCount("abba"));
    }

    @Test
    void testAllUniqueCharactersEvenLength() {
        assertEquals(3, app.getDominantStringCount("abcd"));
    }

    @Test
    void testLongerStringWithMultipleDominantSubstrings() {
        assertEquals(10, app.getDominantStringCount("aabbaabb"));
    }

    @Test
    void testExactHalfMultipleCharacters() {
        assertNotEquals(1, app.getDominantStringCount("aaabbb"));
        assertEquals(3, app.getDominantStringCount("aaabbb"));
    }
}