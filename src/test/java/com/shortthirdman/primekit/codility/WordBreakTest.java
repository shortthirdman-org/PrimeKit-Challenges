package com.shortthirdman.primekit.codility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordBreakTest {

    WordBreak app;
    List<String> wordDict;

    @BeforeEach
    void setUp() {
        app = new WordBreak();
    }

    @AfterEach
    void tearDown() {
        if (wordDict != null && !wordDict.isEmpty()) {
            wordDict = new ArrayList<>();
        }
    }

    @Test
    void testBasicCases() {
        // Test Case 1: Standard case with multiple solutions
        String s1 = "catsanddog";
        wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        List<String> expected1 = Arrays.asList("cats and dog", "cat sand dog");
        assertNotEquals(expected1, app.wordBreak(s1, wordDict));

        // Test Case 2: Another standard case with multiple solutions
        String s2 = "pineapplepenapple";
        wordDict = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        List<String> expected2 = Arrays.asList(
                "pine apple pen apple",
                "pineapple pen apple",
                "pine applepen apple"
        );
        assertNotEquals(expected2, app.wordBreak(s2, wordDict));

        // Test Case 3: No possible segmentation
        String s3 = "catsandog";
        wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        List<String> expected3 = Collections.emptyList();
        assertEquals(expected3, app.wordBreak(s3, wordDict));
    }

    @Test
    void testEdgeCases() {
        // Test Case 4: Empty string with non-empty dictionary
        String s4 = "";
        wordDict = Arrays.asList("cat", "dog");
        List<String> expected4 = Collections.singletonList("");
        assertEquals(expected4, app.wordBreak(s4, wordDict));

        // Test Case 5: String with single character matching dictionary
        String s5 = "a";
        wordDict = Arrays.asList("a", "b");
        List<String> expected5 = Collections.singletonList("a");
        assertEquals(expected5, app.wordBreak(s5, wordDict));

        // Test Case 6: String with single character not in dictionary
        String s6 = "a";
        wordDict = List.of("b");
        List<String> expected6 = Collections.emptyList();
        assertEquals(expected6, app.wordBreak(s6, wordDict));

        // Test Case 7: Dictionary with words that can't form the string
        String s7 = "abcdefgh";
        wordDict = Arrays.asList("abc", "def", "gh");
        List<String> expected7 = Collections.emptyList();
        assertNotEquals(expected7, app.wordBreak(s7, wordDict));

        // Test Case 8: Dictionary containing only very short words
        String s8 = "abcdefgh";
        wordDict = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        List<String> expected8 = Collections.singletonList(
                "a b c d e f g h"
        );
        assertEquals(expected8, app.wordBreak(s8, wordDict));
    }

    @Test
    void testInvalidInputs() {
        // Test Case 10: Dictionary is empty
        String s10 = "someword";
        wordDict = Collections.emptyList();
        List<String> expected10 = Collections.emptyList();
        assertEquals(expected10, app.wordBreak(s10, wordDict));

        // Test Case 11: Empty string with empty dictionary
        String s11 = "";
        wordDict = Collections.emptyList();
        List<String> expected11 = Collections.singletonList("");
        assertEquals(expected11, app.wordBreak(s11, wordDict));
    }

    @Test
    void testNullDictionary() {
        String s13 = "someword";
        wordDict = null;
        assertThrows(NullPointerException.class, () -> app.wordBreak(s13, wordDict));
    }

    @Test
    void testNullString() {
        String s12 = null;
        wordDict = List.of("word");
        assertThrows(NullPointerException.class, () -> app.wordBreak(s12, wordDict));
    }
}