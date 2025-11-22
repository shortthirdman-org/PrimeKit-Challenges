package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MaxDoubleSliceSumTest {

    MaxDoubleSliceSum app;

    @BeforeEach
    void setUp() {
        app = new MaxDoubleSliceSum();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testValidArrayMinLength() {
        int[] A = {1, -2, 3};
        assertEquals(0, app.calculateMaxDoubleSum(A));
        assertDoesNotThrow(() -> app.calculateMaxDoubleSum(A));
    }

    @Test
    void testAllPositiveNumbers() {
        int[] A = {3, 2, 6, 4, 5};
        assertNotEquals(12, app.calculateMaxDoubleSum(A));
        assertEquals(10, app.calculateMaxDoubleSum(A));
        assertDoesNotThrow(() -> app.calculateMaxDoubleSum(A));
    }

    @Test
    void testMixedPositiveNegative() {
        int[] A = {5, 3, -7, 4, 8, -2, 5};
        assertEquals(15, app.calculateMaxDoubleSum(A));
        assertDoesNotThrow(() -> app.calculateMaxDoubleSum(A));
    }

    @Test
    void testAllNegativeNumbers() {
        int[] A = {-1, -2, -3, -4, -5};
        assertEquals(0, app.calculateMaxDoubleSum(A));
        assertDoesNotThrow(() -> app.calculateMaxDoubleSum(A));
    }

    @Test
    void testLargeNumbers() {
        int[] A = {10000, -10000, 10000};
        assertEquals(0, app.calculateMaxDoubleSum(A));
        assertDoesNotThrow(() -> app.calculateMaxDoubleSum(A));
    }

    @Test
    void testPerformanceLargeArray() {
        int[] A = new int[100000];
        for (int i = 0; i < 100000; i++) {
            A[i] = 10000;
        }
        assertDoesNotThrow(() -> app.calculateMaxDoubleSum(A));
        assertTrue(app.calculateMaxDoubleSum(A) > 0); // Arbitrary check for large array
    }

    // Negative cases
    @Test
    void testArrayTooSmall() {
        int[] A = {1, 2};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.calculateMaxDoubleSum(A));
        assertEquals("Array length must be between 3 and 100,000.", exception.getMessage());
    }

    @Test
    void testArrayTooLarge() {
        int[] A = new int[100001];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.calculateMaxDoubleSum(A));
        assertEquals("Array length must be between 3 and 100,000.", exception.getMessage());
    }

    @Test
    void testElementGreaterThanLimit() {
        int[] A = {10001, 1, 2, 3};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.calculateMaxDoubleSum(A));
        assertEquals("Array elements must be between -10,000 and 10,000.", exception.getMessage());
    }

    @Test
    void testElementLessThanLimit() {
        int[] A = {-10001, 0, 1, 2};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.calculateMaxDoubleSum(A));
        assertEquals("Array elements must be between -10,000 and 10,000.", exception.getMessage());
    }

    @Test
    void testEmptyArray() {
        int[] A = {};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.calculateMaxDoubleSum(A));
        assertEquals("Array length must be between 3 and 100,000.", exception.getMessage());
    }
}