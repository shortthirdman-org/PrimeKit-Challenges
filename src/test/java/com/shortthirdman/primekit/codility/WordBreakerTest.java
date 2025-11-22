package com.shortthirdman.primekit.codility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordBreakerTest {

    WordBreaker app;
    List<String> wordDict;

    @BeforeEach
    void setUp() {
        app = new WordBreaker();
    }

    @AfterEach
    void tearDown() {
        if (wordDict != null && !wordDict.isEmpty()) {
            wordDict = new ArrayList<>();
        }
    }

    @Test
    public void testSingleWordMatch() {
        String s = "microsoft";
        wordDict = Arrays.asList("micro", "soft");
        assertTrue(app.wordBreak(s, wordDict));
    }

    @Test
    public void testMultipleWordsMatch() {
        String s = "appletenapple";
        wordDict = Arrays.asList("apple", "ten");
        assertTrue(app.wordBreak(s, wordDict));
    }

    @Test
    public void testNoMatch() {
        String s = "catsandog";
        wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        assertFalse(app.wordBreak(s, wordDict));
    }

    // Edge Cases

    @Test
    public void testEmptyString() {
        String s = "";
        wordDict = Arrays.asList("apple", "banana");
        assertTrue(app.wordBreak(s, wordDict)); // Empty string should return true
    }

    @Test
    public void testNoDictionaryWords() {
        String s = "hello";
        wordDict = List.of();
        assertFalse(app.wordBreak(s, wordDict)); // No words to match
    }

    @Test
    public void testSingleWordNoMatch() {
        String s = "hello";
        wordDict = List.of("world");
        assertFalse(app.wordBreak(s, wordDict)); // Dictionary word does not match
    }

    @Test
    public void testSingleWordMatchInDict() {
        String s = "hello";
        wordDict = List.of("hello");
        assertTrue(app.wordBreak(s, wordDict)); // Single word matches the string
    }

    @Test
    public void testLargeStringWithRepetition() {
        String s = "a".repeat(300);
        wordDict = List.of("a");
        assertTrue(app.wordBreak(s, wordDict)); // Large string with repetitive single character
    }

    // Exception Cases

    @Test
    public void testNullString() {
        wordDict = List.of("word");
        assertThrows(NullPointerException.class, () -> app.wordBreak(null, wordDict));
    }

    @Test
    public void testNullDictionary() {
        String s = "test";
        assertThrows(NullPointerException.class, () -> app.wordBreak(s, null));
    }

    @Test
    public void testNullStringAndDictionary() {
        assertThrows(NullPointerException.class, () -> app.wordBreak(null, null));
    }
}