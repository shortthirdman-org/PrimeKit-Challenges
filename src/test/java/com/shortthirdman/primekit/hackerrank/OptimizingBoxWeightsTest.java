package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptimizingBoxWeightsTest {

    OptimizingBoxWeights app;

    @BeforeEach
    void setUp() {
        app = new OptimizingBoxWeights();
    }

    @Test
    void testEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> result = app.minimalHeaviestSetA(input);
        assertTrue(result.isEmpty(), "The result should be an empty list.");
    }

    // Test case when the input list has only one element
    @Test
    void testSingleElement() {
        List<Integer> input = new ArrayList<>(Collections.singletonList(5));
        List<Integer> result = app.minimalHeaviestSetA(input);
        assertEquals(input, result, "The result should be the same as the input.");
    }

    // Test case where all elements in the list are the same
    @Test
    void testAllElementsEqual() {
        List<Integer> input = new ArrayList<>(Arrays.asList(3, 3, 3, 3, 3));
        List<Integer> result = app.minimalHeaviestSetA(input);
        assertEquals(Arrays.asList(3, 3, 3), result, "The result should be a list of the first half of the elements.");
    }

    // Test case with multiple elements where Set A needs to be chosen carefully
    @Test
    void testNormalCase() {
        List<Integer> input = new ArrayList<>(Arrays.asList(10, 20, 15, 5, 30, 25));
        List<Integer> expected = Arrays.asList(25, 30);
        List<Integer> result = app.minimalHeaviestSetA(input);
        assertEquals(expected, result, "The minimal heaviest set A should be {25, 30}.");
    }

    // Test case with a list of increasing integers
    @Test
    void testIncreasingOrder() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> expected = Arrays.asList(6, 8, 9);
        List<Integer> result = app.minimalHeaviestSetA(input);
        assertEquals(expected, result, "The minimal heaviest set A should be {6, 8, 9}.");
    }

    // Test case with a list of decreasing integers
    @Test
    void testDecreasingOrder() {
        List<Integer> input = new ArrayList<>(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1));
        List<Integer> expected = Arrays.asList(6, 8, 9);
        List<Integer> result = app.minimalHeaviestSetA(input);
        assertEquals(expected, result, "The minimal heaviest set A should be {6, 8, 9}.");
    }

    // Test case with large numbers
    @Test
    void testLargeNumbers() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1000000, 2000000, 1500000, 500000, 3000000, 2500000));
        List<Integer> expected = Arrays.asList(2500000, 3000000);
        List<Integer> result = app.minimalHeaviestSetA(input);
        assertEquals(expected, result, "The minimal heaviest set A should be {2500000, 3000000}.");
    }

    // Test case with two elements
    @Test
    void testTwoElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(8, 6));
        List<Integer> expected = Collections.singletonList(8);
        List<Integer> result = app.minimalHeaviestSetA(input);
        assertEquals(expected, result, "The minimal heaviest set A should be {8}.");
    }

    // Test case where the set is split right at the middle of the list
    @Test
    void testSplitMiddle() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> expected = Arrays.asList(5, 6);
        List<Integer> result = app.minimalHeaviestSetA(input);
        assertEquals(expected, result, "The minimal heaviest set A should be {5, 6}.");
    }

    // Test case when all elements are larger than half of total sum
    @Test
    void testAllElementsLarge() {
        List<Integer> input = new ArrayList<>(Arrays.asList(50, 60, 70, 80, 90));
        List<Integer> expected = Arrays.asList(50, 80, 90);
        List<Integer> result = app.minimalHeaviestSetA(input);
        assertEquals(expected, result, "The minimal heaviest set A should be {50, 80, 90}.");
    }

    // Test case with larger input size (stress test case)
    @Test
    void testLargeInput() {
        List<Integer> input = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            input.add(i);
        }

        List<Integer> result = app.minimalHeaviestSetA(input);
        int sumA = result.stream().mapToInt(Integer::intValue).sum();
        int sumRemaining = input.stream().mapToInt(Integer::intValue).sum() - sumA;

        assertTrue(sumA > sumRemaining, "Sum of Set A should be greater than the sum of the remaining elements.");
    }
}