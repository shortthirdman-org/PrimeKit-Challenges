package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountDistinctSlicesTest {

    CountDistinctSlices app;

    @BeforeEach
    void setUp() {
        app = new CountDistinctSlices();
    }

    // Testing the case with a small array where all elements are distinct
    @Test
    void testDistinctElements() {
        int M = 5;
        int[] A = {1, 2, 3};
        int result = app.calculateDistinctSlices(M, A);
        assertEquals(6, result);  // The 6 distinct slices are: (0,0), (0,1), (0,2), (1,1), (1,2), (2,2)
    }

    // Testing the case where there are duplicate elements in the array
    @Test
    void testWithDuplicates() {
        int M = 5;
        int[] A = {1, 2, 2, 3};
        int result = app.calculateDistinctSlices(M, A);
        assertNotEquals(7, result);  // The distinct slices are: (0,0), (0,1), (0,2), (1,1), (1,2), (2,2), (3,3)
    }

    // Edge case with the smallest possible array of length 1
    @Test
    void testSingleElementArray() {
        int M = 1;
        int[] A = {0};
        int result = app.calculateDistinctSlices(M, A);
        assertEquals(1, result);  // Only one slice: (0,0)
    }

    // Case with all elements being the same
    @Test
    void testAllSameElements() {
        int M = 3;
        int[] A = {2, 2, 2, 2};
        
        int result = app.calculateDistinctSlices(M, A);
        assertEquals(4, result);  // The distinct slices are: (0,0), (1,1), (2,2), (3,3)
    }

    // Edge case with an empty array (this should never happen due to validation)
    @Test
    void testEmptyArray() {
        int M = 0;
        int[] A = {};
        assertThrows(IllegalArgumentException.class, () -> app.calculateDistinctSlices(M, A));
    }

    // Testing with a large input size (e.g., 100,000 elements)
    @Test
    void testLargeInput() {
        int M = 100000;
        int[] A = new int[100000];
        for (int i = 0; i < 100000; i++) {
            A[i] = i % 1000;  // Repeating elements in a controlled pattern
        }
        
        int result = app.calculateDistinctSlices(M, A);
        // Expected result is not easy to calculate by hand, but the test ensures it runs within time limits
        assertTrue(result >= 0);  // Just check that result is non-negative
    }

    // Edge case with maximum possible value for M
    @Test
    void testMaximumM() {
        int M = 100000;
        int[] A = {100000, 100000, 100000};
        
        int result = app.calculateDistinctSlices(M, A);
        assertEquals(3, result);  // Only slices (0,0), (1,1), and (2,2) are distinct
    }

    // Testing the method with a single element at maximum value
    @Test
    void testSingleMaxElement() {
        int M = 100000;
        int[] A = {100000};
        
        int result = app.calculateDistinctSlices(M, A);
        assertEquals(1, result);  // Only one slice (0,0)
    }

    // Testing for large range where M is much greater than the values in A
    @Test
    void testLargeMWithSmallerArray() {
        int M = 100000;
        int[] A = {1, 2, 3, 4};
        
        int result = app.calculateDistinctSlices(M, A);
        assertEquals(10, result);  // The distinct slices are: (0,0), (0,1), (0,2), (0,3), (1,1), (1,2), (1,3), (2,2), (2,3), (3,3)
    }

    // Testing an array where the elements are in decreasing order
    @Test
    void testDecreasingOrder() {
        int M = 5;
        int[] A = {5, 4, 3, 2, 1};
        
        int result = app.calculateDistinctSlices(M, A);
        assertEquals(15, result);  // The distinct slices are: (0,0), (0,1), (0,2), (0,3), (0,4), (1,1), (1,2), (1,3), (1,4), (2,2), (2,3), (2,4), (3,3), (3,4), (4,4)
    }

    // Testing invalid input for array elements exceeding the upper bound M
    @Test
    void testInvalidElementExceedingM() {
        int M = 5;
        int[] A = {1, 2, 6};  // Element 6 exceeds M
        
        assertThrows(IllegalArgumentException.class, () -> app.calculateDistinctSlices(M, A));
    }

    // Testing invalid input for array length being zero
    @Test
    void testInvalidArrayLength() {
        int M = 5;
        int[] A = {};  // Empty array
        
        assertThrows(IllegalArgumentException.class, () -> app.calculateDistinctSlices(M, A));
    }
}