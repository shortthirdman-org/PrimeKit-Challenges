package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatasetMatcherTest {

    DatasetMatcher app;

    @BeforeEach
    void setUp() {
        app = new DatasetMatcher();
    }

    @Test
    void testExampleCase1() {
        int[] primary = {1, 2, 3};
        int[] secondary = {3, 4};
        int[][] operations = {
                {1, 5}, {0, 0, 1}, {1, 5}
        };

        int[] expected = {2, 1};
        assertArrayEquals(expected, app.computePairMatchCounts(primary, secondary, operations));
    }

    @Test
    void testExampleCase2() {
        int[] primary = {1, 2, 2};
        int[] secondary = {2, 3};
        int[][] operations = {
                {1, 4}, {0, 0, 3}, {1, 5}
        };

        int[] expected = {3, 4};
        assertArrayEquals(expected, app.computePairMatchCounts(primary, secondary, operations));
    }

    @Test
    void testMultipleUpdatesAndCounts() {
        int[] primary = {1, 2};
        int[] secondary = {2, 2};
        int[][] operations = {
                {1, 4},         // 2
                {0, 0, 3},      // [3, 2]
                {1, 5},         // 1 + 2
                {0, 1, 3},      // [3, 3]
                {1, 5},         // 2
        };

        int[] expected = {2, 1, 2};
        assertArrayEquals(expected, app.computePairMatchCounts(primary, secondary, operations));
    }

    // ========== EDGE CASES ==========

    @Test
    void testEmptyArrays() {
        int[] primary = {};
        int[] secondary = {};
        int[][] operations = {
                {1, 5}, {0, 0, 1}
        };

        assertThrows(IndexOutOfBoundsException.class, () -> app.computePairMatchCounts(primary, secondary, operations));

        int[][] operations2 = new int[][]{
            {1, 5}
        };
        int[] expected = {0};
        assertArrayEquals(expected, app.computePairMatchCounts(primary, secondary, operations2));
    }

    @Test
    void testSingleElementArrays() {
        int[] primary = {2};
        int[] secondary = {3};
        int[][] operations = {
                {1, 5}, // 2+3 = 5 -> 1 match
                {0, 0, 1},
                {1, 5} // 2+1 = 3 -> no match
        };

        int[] expected = {1, 0};
        assertArrayEquals(expected, app.computePairMatchCounts(primary, secondary, operations));
    }

    @Test
    void testNoMatchingSums() {
        int[] primary = {1, 2};
        int[] secondary = {10, 20};
        int[][] operations = {{1, 5}};
        int[] expected = {0};
        assertArrayEquals(expected, app.computePairMatchCounts(primary, secondary, operations));
    }

    @Test
    void testDuplicateValues() {
        int[] primary = {1, 1};
        int[] secondary = {2, 2};
        int[][] operations = {{1, 3}}; // 4 matches: 1+2 (x2) from primary, x2 from secondary
        int[] expected = {4};
        assertArrayEquals(expected, app.computePairMatchCounts(primary, secondary, operations));
    }

    @Test
    void testUpdateIndexOutOfBounds() {
        int[] primary = {1, 2};
        int[] secondary = {2};
        int[][] operations = {
                {0, 1, 5} // secondary has only index 0
        };

        assertThrows(IndexOutOfBoundsException.class, () ->
                app.computePairMatchCounts(primary, secondary, operations)
        );
    }

    @Test
    void testMalformedOperation() {
        int[] primary = {1, 2};
        int[] secondary = {2, 3};
        int[][] operations = {
                {2, 5},   // Invalid operation code
                {1, 5}
        };

        assertThrows(IllegalArgumentException.class, () ->
                app.computePairMatchCounts(primary, secondary, operations)
        );
    }

    @Test
    void testNullInputs() {
        assertThrows(NullPointerException.class, () ->
                app.computePairMatchCounts(null, new int[]{1}, new int[][]{{1, 2}})
        );

        assertThrows(NullPointerException.class, () ->
                app.computePairMatchCounts(new int[]{1}, null, new int[][]{{1, 2}})
        );

        assertThrows(NullPointerException.class, () ->
                app.computePairMatchCounts(new int[]{1}, new int[]{2}, null)
        );
    }
}