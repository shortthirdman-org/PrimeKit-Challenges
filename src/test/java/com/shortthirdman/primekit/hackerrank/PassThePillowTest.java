package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassThePillowTest {

    PassThePillow app;

    @BeforeEach
    void setUp() {
        app = new PassThePillow();
    }

    @Test
    public void testNormalCases() {
        assertEquals(2, app.pillowPass(4, 5));  // Example 1
        assertEquals(3, app.pillowPass(3, 2));  // Example 2
        assertEquals(2, app.pillowPass(2, 1));  // Simple Case
        assertEquals(1, app.pillowPass(2, 2));  // Back to start
        assertEquals(5, app.pillowPass(5, 4));  // Mid Line
    }

    @Test
    public void testEdgeCases() {
        assertNotEquals(2, app.pillowPass(1000, 999));  // Large input edge case
        assertEquals(1, app.pillowPass(2, 1000));    // Min `n`, Max `time`
        assertEquals(501, app.pillowPass(1000, 500)); // Halfway through forward
        assertThrows(IllegalArgumentException.class, () -> app.pillowPass(1000, 1998)); // Just before the end of cycle
        assertThrows(IllegalArgumentException.class, () -> app.pillowPass(1000, 2000));  // Start of new cycle
    }

    @Test
    public void testInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> app.pillowPass(1, 100));
        assertThrows(IllegalArgumentException.class, () -> app.pillowPass(1001, 100));
        assertThrows(IllegalArgumentException.class, () -> app.pillowPass(100, 0));
        assertThrows(IllegalArgumentException.class, () -> app.pillowPass(100, 1001));
    }
}