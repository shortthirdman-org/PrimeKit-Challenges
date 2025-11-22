package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MaxMinimaTest {

    MaxMinima app;

    @BeforeEach
    void setUp() {
        app = new MaxMinima();
    }

    @Test
    void testTypicalCase() {
        List<Integer> arr = Arrays.asList(1, 3, 2, 5, 4);
        int result = app.maxMin(arr, 2);
        // Windows: [1,3] -> 1; [3,2] -> 2; [2,5] -> 2; [5,4] -> 4 => max = 4
        assertEquals(4, result);
    }

    @Test
    void testKEquals1() {
        List<Integer> arr = Arrays.asList(4, 9, 2, 7);
        int result = app.maxMin(arr, 1);
        // Min of each window = the element itself → max = 9
        assertEquals(9, result);
    }

    @Test
    void testKEqualsN() {
        List<Integer> arr = Arrays.asList(8, 3, 6, 9);
        int result = app.maxMin(arr, 4);
        // Only 1 window → min = 3
        assertEquals(3, result);
    }

    @Test
    void testAllElementsSame() {
        List<Integer> arr = Arrays.asList(5, 5, 5, 5, 5);
        int result = app.maxMin(arr, 3);
        assertEquals(5, result);
    }

    @Test
    void testStrictlyIncreasingList() {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5);
        int result = app.maxMin(arr, 3);
        // Windows min → [1,2,3]=1, [2,3,4]=2, [3,4,5]=3 → max=3
        assertEquals(3, result);
    }

    @Test
    void testStrictlyDecreasingList() {
        List<Integer> arr = Arrays.asList(9, 7, 5, 3, 1);
        int result = app.maxMin(arr, 2);
        // Windows min: [9,7]=7, [7,5]=5, [5,3]=3, [3,1]=1 → max=7
        assertEquals(7, result);
    }

    @Test
    void testLargeValues() {
        List<Integer> arr = Arrays.asList(1_000_000_000, 999_999_999, 1_000_000_000);
        int result = app.maxMin(arr, 2);
        assertEquals(999_999_999, result);
    }

    @Test
    void testListSizeOne() {
        List<Integer> arr = Collections.singletonList(7);
        int result = app.maxMin(arr, 1);
        assertEquals(7, result);
    }


    // ---------- NEGATIVE / EXCEPTIONAL TEST CASES ----------

    @Test
    void testKLessThan1ThrowsException() {
        List<Integer> arr = Arrays.asList(1, 2, 3);
        assertThrows(IllegalArgumentException.class,
                () -> app.maxMin(arr, 0));
    }

    @Test
    void testKGreaterThanNThrowsException() {
        List<Integer> arr = Arrays.asList(1, 2, 3);
        assertThrows(IllegalArgumentException.class,
                () -> app.maxMin(arr, 4));
    }

    @Test
    void testElementEqualToZeroThrowsException() {
        List<Integer> arr = Arrays.asList(2, 0, 5);
        assertThrows(IllegalArgumentException.class,
                () -> app.maxMin(arr, 2));
    }

    @Test
    void testMultipleZerosThrowsException() {
        List<Integer> arr = Arrays.asList(0, 0, 0);
        assertThrows(IllegalArgumentException.class,
                () -> app.maxMin(arr, 1));
    }

    @Test
    void testNullListThrowsException() {
        assertThrows(NullPointerException.class,
                () -> app.maxMin(null, 1));
    }

    @Test
    void testEmptyListThrowsException() {
        List<Integer> arr = Collections.emptyList();
        // k cannot be between 1 and n (0), so throws
        assertThrows(IllegalArgumentException.class,
                () -> app.maxMin(arr, 1));
    }


    // ---------- EDGE CASE TESTS ----------

    @Test
    void testLargestPossibleWindowNMinusOne() {
        List<Integer> arr = Arrays.asList(10, 20, 30, 40);
        int result = app.maxMin(arr, 3);
        // Windows min: [10,20,30]=10, [20,30,40]=20 → max=20
        assertEquals(20, result);
    }

    @Test
    void testSmallRandomMixedValues() {
        List<Integer> arr = Arrays.asList(9, 1, 8, 2, 7, 3);
        int result = app.maxMin(arr, 2);
        // mins: 1,1,2,2,3 → max = 3
        assertEquals(3, result);
    }

    @Test
    void testAlternatingHighLow() {
        List<Integer> arr = Arrays.asList(100, 1, 100, 1, 100);
        int result = app.maxMin(arr, 2);
        // mins: 1,1,1,1 → max = 1
        assertEquals(1, result);
    }

    @Test
    void testVeryLargeListSmallK() {
        List<Integer> arr = Arrays.asList(5, 4, 3, 2, 1, 6, 7);
        int result = app.maxMin(arr, 1);
        // max = max element = 7
        assertEquals(7, result);
    }
}