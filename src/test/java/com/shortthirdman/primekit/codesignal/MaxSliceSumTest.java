package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxSliceSumTest {

    MaxSliceSum app;

    @BeforeEach
    void setUp() {
        app = new MaxSliceSum();
    }

    @AfterEach
    void tearDown() {
    }

    // Positive test cases
    @Test
    public void testMixedNumbers() {
        int[] array = {3, 2, -6, 4, 0};
        assertEquals(5, app.maxSumForArray(array), "Should be 5");
    }

    @Test
    public void testAllPositiveNumbers() {
        int[] array = {1, 2, 3, 4, 5};
        assertEquals(15, app.maxSumForArray(array), "Should be 15");
    }

    @Test
    public void testAllNegativeNumbers() {
        int[] array = {-1, -2, -3, -4, -5};
        assertEquals(-1, app.maxSumForArray(array), "Should be -1");
    }

    // Negative test cases
    @Test
    public void testSingleElementArray() {
        int[] array = {5};
        assertEquals(5, app.maxSumForArray(array), "Should be 5");
    }

    @Test
    public void testSingleNegativeElementArray() {
        int[] array = {-5};
        assertEquals(-5, app.maxSumForArray(array), "Should be -5");
    }

    @Test
    public void testLargeArray() {
        int[] array = new int[1_000_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i % 2 == 0 ? 1 : -1; // Alternating 1 and -1
        }
        assertEquals(1, app.maxSumForArray(array), "Should be 1");
    }

    @Test
    public void testArrayWithAllZeros() {
        int[] array = {0, 0, 0, 0};
        assertEquals(0, app.maxSumForArray(array), "Should be 0");
    }

    // Edge cases
    @Test
    public void testNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.maxSumForArray(null);
        }, "Should throw IllegalArgumentException for null array");
    }

    @Test
    public void testEmptyArray() {
        // Although the problem guarantees non-empty arrays, if we handle it, this is how it would look:
        assertThrows(IllegalArgumentException.class, () -> {
            app.maxSumForArray(new int[0]);
        }, "Should throw IllegalArgumentException for empty array");
    }

    @Test
    public void testArrayWithOneElement() {
        int[] array = {1};
        assertEquals(1, app.maxSumForArray(array), "Should be 1");
    }
}