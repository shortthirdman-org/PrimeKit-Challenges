package com.shortthirdman.primekit.codility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecentRequestsTrackerTest {

    RecentRequestsTracker app;

    @BeforeEach
    void setUp() {
        app = new RecentRequestsTracker();
    }

    @Test
    @DisplayName("Test with typical duplicates and k=3")
    void testTypicalCase() {
        List<String> input = Arrays.asList("item1", "item2", "item3", "item1", "item3");
        List<String> expected = Arrays.asList("item3", "item1", "item2");

        List<String> result = app.getLatestKRequests(input, 3);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test with all distinct requests and k=2")
    void testAllDistinct() {
        List<String> input = Arrays.asList("item1", "item2", "item3");
        List<String> expected = Arrays.asList("item3", "item2", "item1");

        List<String> result = app.getLatestKRequests(input, 2);
        assertEquals(expected, result);

        expected = Arrays.asList("item3", "item2");
        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Test with fewer unique items than k")
    void testFewerUniqueThanK() {
        List<String> input = Arrays.asList("item1", "item1", "item2");
        List<String> expected = Arrays.asList("item2", "item1");

        List<String> result = app.getLatestKRequests(input, 5);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test with exactly k unique items")
    void testExactlyKUnique() {
        List<String> input = Arrays.asList("a1", "b2", "c3");
        List<String> expected = Arrays.asList("c3", "b2", "a1");
        List<String> matchingExpected = List.of("c3");

        List<String> result = app.getLatestKRequests(input, 3);
        assertEquals(matchingExpected, result);
        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Test with invalid input: uppercase letters")
    void testInvalidUppercase() {
        List<String> input = Arrays.asList("Item1", "item2");

        Executable executable = () -> app.getLatestKRequests(input, 2);
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    @DisplayName("Test with invalid input: special characters")
    void testInvalidSpecialChars() {
        List<String> input = Arrays.asList("item@", "item#");

        assertThrows(IllegalArgumentException.class, () -> app.getLatestKRequests(input, 1));
    }

    @Test
    @DisplayName("Test with empty input list")
    void testEmptyInput() {
        List<String> input = Collections.emptyList();
        assertThrows(IllegalArgumentException.class, () -> app.getLatestKRequests(input, 5));
    }

    @Test
    @DisplayName("Test with k=0")
    void testZeroK() {
        List<String> input = Arrays.asList("item1", "item2", "item3");
        assertThrows(IllegalArgumentException.class, () -> app.getLatestKRequests(input, 0));
    }

    @Test
    @DisplayName("Test with k > total items")
    void testKGreaterThanTotal() {
        List<String> input = Arrays.asList("item1", "item2");
        List<String> expected = Arrays.asList("item2", "item1");

        List<String> result = app.getLatestKRequests(input, 10);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test with repeated item at end")
    void testRepeatedAtEnd() {
        List<String> input = Arrays.asList("a", "b", "c", "a");
        List<String> expected = Arrays.asList("a", "c", "b");

        List<String> result = app.getLatestKRequests(input, 3);
        assertEquals(expected, result);
    }
}