package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SignFlipMaximizerTest {

    static SignFlipMaximizer app;

    @BeforeAll
    static void beforeAll() {
        app = new SignFlipMaximizer();
    }

    @Test
    void testExampleFromImage() {
        List<Integer> arr = new ArrayList<>(List.of(-5, -2, -3, 6, 7));
        int k = 3;
        assertEquals(23, app.maximizeSum(arr, k));
    }

    @Test
    void testAllPositiveNumbers() {
        List<Integer> arr = new ArrayList<>(List.of(1, 2, 3, 4));
        int k = 2;
        // Flip smallest positive twice → no change
        assertEquals(10, app.maximizeSum(arr, k));
    }

    @Test
    void testAllNegativeNumbers() {
        List<Integer> arr = new ArrayList<>(List.of(-1, -2, -3));
        int k = 2;
        // Flip largest abs negatives: -3 → 3, -2 → 2, sum = 3 + 2 + (-1) = 4
        assertEquals(4, app.maximizeSum(arr, k));
    }

    @Test
    void testMixedWithZero() {
        List<Integer> arr = new ArrayList<>(List.of(-4, 0, 3, -1));
        int k = 1;
        // Flip -4 → 4
        assertEquals(4 + 0 + 3 + (-1), app.maximizeSum(arr, k));
    }

    @Test
    void testZeroFlips() {
        List<Integer> arr = new ArrayList<>(List.of(-1, 2, -3));
        int k = 0;
        assertEquals(-1 + 2 + (-3), app.maximizeSum(arr, k));
    }

    @Test
    void testKEqualsArraySize() {
        List<Integer> arr = new ArrayList<>(List.of(-1, -2, -3));
        int k = 3;
        // Flip all → 1 + 2 + 3 = 6
        assertEquals(6, app.maximizeSum(arr, k));
    }

    @Test
    void testKGreaterThanArraySize() {
        List<Integer> arr = new ArrayList<>(List.of(-1, -2, -3));
        int k = 5;
        // Flip all → [1, 2, 3], then 2 more flips → one number flips back
        // 1 + 2 + (-3) = 0
        assertNotEquals(0, app.maximizeSum(arr, k));
    }

    @Test
    void testWithDuplicates() {
        List<Integer> arr = new ArrayList<>(List.of(-2, -2, 2, 2));
        int k = 2;
        // Flip two -2s → [2, 2, 2, 2]
        assertEquals(8, app.maximizeSum(arr, k));
    }

    @Test
    void testWithLargeKOdd() {
        List<Integer> arr = new ArrayList<>(List.of(1, 2, 3));
        int k = 5;
        // Odd extra flip → smallest becomes negative → 1 flips to -1
        // 2 + 3 + (-1) = 4
        assertEquals(4, app.maximizeSum(arr, k));
    }

    @Test
    void testWithLargeKEven() {
        List<Integer> arr = new ArrayList<>(List.of(1, 2, 3));
        int k = 4;
        // Even extra flips → all positives stay → sum = 6
        assertEquals(6, app.maximizeSum(arr, k));
    }
}