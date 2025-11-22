package com.shortthirdman.primekit.leetcode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryGapTests {

    protected BinaryGap app;

    @BeforeEach
    public void setUp() {
        app = new BinaryGap();
    }

    @AfterEach
    public void tearDown() {
        app = null;
    }

    @Test
    public void testZeroGap() {
        assertEquals(0, app.findLongestSequence(1));
        assertEquals(0, app.findLongestSequence(32));
        assertEquals(0, app.findLongestSequence(15));
    }

    @Test
    public void testSingleGap() {
        assertEquals(1, app.findLongestSequence(20));
        assertEquals(2, app.findLongestSequence(9));
    }

    @Test
    public void testMultipleGaps() {
        assertEquals(4, app.findLongestSequence(529));
        assertEquals(5, app.findLongestSequence(1041));
    }
}
