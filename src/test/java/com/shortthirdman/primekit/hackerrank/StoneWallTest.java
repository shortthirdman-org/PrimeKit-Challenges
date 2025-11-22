package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoneWallTest {

    StoneWall app;

    @BeforeEach
    void setUp() {
        app = new StoneWall();
    }

    // Positive Test Cases
    @Test
    public void testSingleHeight() {
        int[] heights = {5};
        assertEquals(5, app.coverManhattanSkyline(heights));
    }

    @Test
    public void testUniformHeight() {
        int[] heights = {3, 3, 3};
        assertEquals(9, app.coverManhattanSkyline(heights));
    }

    @Test
    public void testIncreasingHeights() {
        int[] heights = {1, 2, 3, 4};
        assertEquals(10, app.coverManhattanSkyline(heights));
    }

    @Test
    public void testDecreasingHeights() {
        int[] heights = {4, 3, 2, 1};
        assertEquals(10, app.coverManhattanSkyline(heights));
    }

    @Test
    public void testVaryingHeights() {
        int[] heights = {2, 1, 4, 3};
        assertEquals(10, app.coverManhattanSkyline(heights));
    }

    @Test
    public void testLargeHeights() {
        int[] heights = {1000, 2000, 3000};
        assertEquals(6000, app.coverManhattanSkyline(heights));
    }

    // Edge Test Cases
    @Test
    public void testEmptyInput() {
        int[] heights = {};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.coverManhattanSkyline(heights));
        assertEquals("Number of height-blocks should be in range [1,100000]", exception.getMessage());
    }

    // Negative Test Cases
    @Test
    public void testNegativeHeights() {
        int[] heights = {-1, -2, -3};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.coverManhattanSkyline(heights));
        assertEquals("Height values must be positive", exception.getMessage());
    }

    @Test
    public void testZeroHeight() {
        int[] heights = {0, 1, 2};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.coverManhattanSkyline(heights));
        assertEquals("Height values must be positive", exception.getMessage());
    }
}