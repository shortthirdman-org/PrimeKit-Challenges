package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AutoCorrectAnagramTest {

    AutoCorrectAnagram app;

    @BeforeEach
    void setUp() {
        app = new AutoCorrectAnagram();
    }

    // ---------------------------------------------------------
    // Positive Test Cases
    // ---------------------------------------------------------

    @Test
    @DisplayName("Basic functionality — simple anagram matches")
    void testBasicAnagrams() {
        List<String> words = List.of("duel", "speed", "dule", "cars");
        List<String> queries = List.of("spede", "deul");

        List<List<String>> result = app.getSearchResults(words, queries);

        assertEquals(List.of(
                List.of("speed"),
                List.of("duel", "dule")
        ), result);
    }

    @Test
    @DisplayName("Multiple queries hitting same anagram group")
    void testMultipleQueriesSameGroup() {
        List<String> words = List.of("listen", "silent", "enlist", "tinsel");
        List<String> queries = List.of("litsen", "inlets");

        List<List<String>> result = app.getSearchResults(words, queries);

        assertEquals(
                List.of(
                        List.of("enlist", "listen", "silent", "tinsel"),
                        List.of("enlist", "listen", "silent", "tinsel")
                ),
                result
        );
    }

    @Test
    @DisplayName("Words that are not anagrams of any query should be ignored")
    void testNonMatchingWordsIgnored() {
        List<String> words = List.of("aa", "bb", "cc", "abc");
        List<String> queries = List.of("cba");

        List<List<String>> result = app.getSearchResults(words, queries);

        assertEquals(List.of(List.of("abc")), result);
    }

    // ---------------------------------------------------------
    // Edge Test Cases
    // ---------------------------------------------------------

    @Test
    @DisplayName("Empty words list but queries present — should return empty results")
    void testEmptyWordsList() {
        List<String> words = List.of();
        List<String> queries = List.of("abc");

        List<List<String>> result = app.getSearchResults(words, queries);

        assertEquals(List.of(List.of()), result);
    }

    @Test
    @DisplayName("Empty queries list should return empty outer list")
    void testEmptyQueriesList() {
        List<String> words = List.of("abc", "bca");
        List<String> queries = List.of();

        List<List<String>> result = app.getSearchResults(words, queries);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Duplicate words handled correctly in mapping")
    void testDuplicateWords() {
        List<String> words = List.of("rat", "tar", "art", "rat");
        List<String> queries = List.of("tar");

        List<List<String>> res = app.getSearchResults(words, queries);

        assertEquals(List.of(
                List.of("art", "rat", "rat", "tar")
        ), res);
    }

    @Test
    @DisplayName("Longest allowed word sizes (100 chars)")
    void testMaxWordLength() {
        String w1 = "a".repeat(100);
        String w2 = "a".repeat(100);
        List<String> words = List.of(w1, w2);
        List<String> queries = List.of("a".repeat(100));

        List<List<String>> res = app.getSearchResults(words, queries);

        assertEquals(List.of(List.of(w1, w2)), res);
    }

    // ---------------------------------------------------------
    // Negative Test Cases
    // ---------------------------------------------------------

    @Test
    @DisplayName("Words list contains null — should throw exception")
    void testWordsContainNull() {
        List<String> words = Arrays.asList("abc", null, "cba"); // Arrays.asList to allow null
        List<String> queries = List.of("abc");

        assertThrows(IllegalArgumentException.class,
                () -> app.getSearchResults(words, queries));
    }

    @Test
    @DisplayName("Queries list contains null — should throw exception")
    void testQueriesContainNull() {
        List<String> words = List.of("abc", "bca");
        List<String> queries = Arrays.asList("abc", null);

        assertThrows(IllegalArgumentException.class,
                () -> app.getSearchResults(words, queries));
    }

    @Test
    @DisplayName("Words list contains empty string — should return empty match")
    void testWordsContainEmptyString() {
        List<String> words = List.of("", "a", "abc");
        List<String> queries = List.of("");

        List<List<String>> res = app.getSearchResults(words, queries);

        assertEquals(List.of(List.of("")), res);
    }

    @Test
    @DisplayName("Queries contain empty string — find empty-string anagrams only")
    void testQueryEmptyString() {
        List<String> words = List.of("", "", "abc");
        List<String> queries = List.of("");

        List<List<String>> res = app.getSearchResults(words, queries);

        assertEquals(List.of(List.of("", "")), res);
    }

    // ---------------------------------------------------------
    // Exception Test Cases (method-level input validation)
    // ---------------------------------------------------------

    @Test
    @DisplayName("Null words list should throw exception")
    void testNullWordsList() {
        assertThrows(IllegalArgumentException.class,
                () -> app.getSearchResults(null, List.of("abc")));
    }

    @Test
    @DisplayName("Null queries list should throw exception")
    void testNullQueriesList() {
        assertThrows(IllegalArgumentException.class,
                () -> app.getSearchResults(List.of("abc"), null));
    }

    @Test
    @DisplayName("Both arguments null should throw exception")
    void testBothNull() {
        assertThrows(IllegalArgumentException.class,
                () -> app.getSearchResults(null, null));
    }
}