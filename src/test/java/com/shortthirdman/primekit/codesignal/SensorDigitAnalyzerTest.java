package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SensorDigitAnalyzerTest {

    SensorDigitAnalyzer app;

    @BeforeEach
    void setUp() {
        app =  new SensorDigitAnalyzer();
    }

    // Positive Test: Basic example (classic and stream)
    @Test
    void testBasicExample() {
        int[] readingsArray = {123, 456, 789, 101};
        List<Integer> readingsList = Arrays.asList(123, 456, 789, 101);

        assertEquals(6, app.findMostFrequentSingleDigit(readingsArray));
        assertEquals(6, app.findMostFrequentSingleDigit(readingsList));
    }

    // Positive Test: Tie-breaking case (highest digit wins)
    @Test
    void testTieBreaker() {
        int[] readingsArray = {19, 28, 37, 46}; // Reduces to [1,1,1,1] => Only '1' appears
        List<Integer> readingsList = Arrays.asList(9, 18, 27, 36);    // Reduces to [9,9,9,9] => Only '9' appears

        assertEquals(1, app.findMostFrequentSingleDigit(readingsArray));
        assertEquals(9, app.findMostFrequentSingleDigit(readingsList));
    }

    // Positive Test: Mixed digits with tie (choose higher)
    @Test
    void testTieWithHigherDigitWinning() {
        int[] readingsArray = {10, 100, 55, 46}; // Reduces to [1,1,1,5] => 1(3x), 5(1x)
        List<Integer> readingsList = Arrays.asList(11, 20, 2, 29);    // Reduces to [2,2,2,2] => Only 2

        assertEquals(1, app.findMostFrequentSingleDigit(readingsArray));
        assertEquals(2, app.findMostFrequentSingleDigit(readingsList));
    }

    // Edge Test: Empty array/list
    @Test
    void testEmptyInput() {
        int[] emptyArray = {};
        List<Integer> emptyList = Collections.emptyList();

        assertEquals(-1, app.findMostFrequentSingleDigit(emptyArray));
        assertEquals(-1, app.findMostFrequentSingleDigit(emptyList));
    }

    // Edge Test: All elements reduce to same digit
    @Test
    void testAllSameReducedDigit() {
        int[] readingsArray = {111, 12, 21, 3};   // All reduce to 3
        List<Integer> readingsList = Arrays.asList(222, 6, 33); // All reduce to 6

        assertEquals(3, app.findMostFrequentSingleDigit(readingsArray));
        assertEquals(6, app.findMostFrequentSingleDigit(readingsList));
    }

    // Edge Test: Single element array/list
    @Test
    void testSingleElement() {
        int[] singleArray = {9999}; // Reduces to 9
        List<Integer> singleList = Collections.singletonList(123456); // Reduces to 3

        assertEquals(9, app.findMostFrequentSingleDigit(singleArray));
        assertEquals(3, app.findMostFrequentSingleDigit(singleList));
    }

    // Edge Test: All zeroes
    @Test
    void testAllZeroes() {
        int[] zeroArray = {0, 0, 0};
        List<Integer> zeroList = Arrays.asList(0, 0, 0);

        assertEquals(0, app.findMostFrequentSingleDigit(zeroArray));
        assertEquals(0, app.findMostFrequentSingleDigit(zeroList));
    }

    // Edge Test: Large values
    @Test
    void testLargeValues() {
        int[] largeArray = {999999999, 888888888, 777777777}; // All reduce to 9
        List<Integer> largeList = Arrays.asList(1000000000, 1234567890); // Reduce to 1 and 9 -> 9 wins

        assertEquals(9, app.findMostFrequentSingleDigit(largeArray));
        assertEquals(9, app.findMostFrequentSingleDigit(largeList));
    }
}