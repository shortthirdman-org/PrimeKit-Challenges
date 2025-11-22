package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TripleThresholdFinderTest {

    TripleThresholdFinder app;

    @BeforeEach
    void setUp() {
        app = new TripleThresholdFinder();
    }

    @Test
    void testTypicalCasePositive() {
        int[] numbers = {0, 1, 4, 3, 2, 5};
        int threshold = 1;
        assertEquals(2, app.findThreshold(numbers, threshold));
    }

    @Test
    void testNoTripleAboveThreshold() {
        int[] numbers = {-9, 95, 94, 4, 51};
        int threshold = 42;
        assertEquals(-1, app.findThreshold(numbers, threshold));
    }

    @Test
    void testTripleAtBeginning() {
        int[] numbers = {10, 11, 12, 1, 2, 3};
        int threshold = 5;
        assertEquals(0, app.findThreshold(numbers, threshold));
    }

    @Test
    void testTripleAtEnd() {
        int[] numbers = {1, 2, 3, 10, 20, 30};
        int threshold = 5;
        assertEquals(3, app.findThreshold(numbers, threshold));
    }

    @Test
    void testTripleWithExactThreshold() {
        int[] numbers = {5, 5, 5, 6, 6, 6};
        int threshold = 5;
        assertEquals(3, app.findThreshold(numbers, threshold)); // since elements must be > threshold
    }

    @Test
    void testTripleWithJustEnoughElements() {
        int[] numbers = {100, 200, 300};
        int threshold = 50;
        assertEquals(0, app.findThreshold(numbers, threshold));
    }

    @Test
    void testArrayTooShort() {
        int[] numbers = {10, 20}; // Less than 3 elements
        int threshold = 5;
        assertEquals(-1, app.findThreshold(numbers, threshold));
    }

    @Test
    void testAllBelowThreshold() {
        int[] numbers = {1, 1, 1, 1, 1, 1};
        int threshold = 10;
        assertEquals(-1, app.findThreshold(numbers, threshold));
    }

    @Test
    void testNegativeThresholdAndNegativesInArray() {
        int[] numbers = {-10, -5, -3, -2, -1};
        int threshold = -6;
        assertEquals(1, app.findThreshold(numbers, threshold)); // All > -6 from index 1
    }

    @Test
    void testAllEqualAboveThreshold() {
        int[] numbers = {9, 9, 9, 9, 9};
        int threshold = 5;
        assertEquals(0, app.findThreshold(numbers, threshold));
    }

    @Test
    void testLargeThreshold() {
        int[] numbers = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        int threshold = Integer.MAX_VALUE - 1;
        assertEquals(0, app.findThreshold(numbers, threshold));
    }

    @Test
    void testThresholdIsIntegerMinValue() {
        int[] numbers = {1, 2, 3, 4, 5};
        int threshold = Integer.MIN_VALUE;
        assertEquals(0, app.findThreshold(numbers, threshold));
    }
}