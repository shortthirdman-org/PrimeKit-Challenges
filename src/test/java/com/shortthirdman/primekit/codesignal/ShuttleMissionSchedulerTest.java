package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShuttleMissionSchedulerTest {

    ShuttleMissionScheduler app;

    @BeforeEach
    void setUp() {
        app = new ShuttleMissionScheduler();
    }

    @Test
    public void testSingleMissionBasic() {
        int[] a2b = {0, 200, 500};
        int[] b2a = {99, 210, 450};
        int missions = 1;

        int result = app.scheduleShuttle(a2b, b2a, missions);
        assertEquals(310, result, "Expected return time is 310");
    }

    @Test
    public void testTwoMissionsWithBackToBackShuttles() {
        int[] a2b = {109, 200, 500};
        int[] b2a = {99, 210, 600};
        int missions = 2;

        int result = app.scheduleShuttle(a2b, b2a, missions);
        assertEquals(700, result, "Expected return time is 700");
    }

    @Test
    public void testMissionsUsingExactMatchTimes() {
        int[] a2b = {5, 206};
        int[] b2a = {105, 306};
        int missions = 2;

        int result = app.scheduleShuttle(a2b, b2a, missions);
        assertEquals(406, result);
    }

    @Test
    public void testShuttlesStartFromZeroTime() {
        int[] a2b = {0, 1, 2};
        int[] b2a = {100, 200, 300};
        int missions = 2;

        int result = app.scheduleShuttle(a2b, b2a, missions);
        assertEquals(200, result);
    }

    // ----------- EDGE CASES ------------

    @Test
    public void testShuttleAtLastPossibleTime() {
        int[] a2b = {1900, 1999, 2000};
        int[] b2a = {2000};
        int missions = 1;

        int result = app.scheduleShuttle(a2b, b2a, missions);
        assertEquals(2100, result);
    }

    @Test
    public void testMinimumInputLengthAndTime() {
        int[] a2b = {0};
        int[] b2a = {100};
        int missions = 1;

        int result = app.scheduleShuttle(a2b, b2a, missions);
        assertEquals(200, result);
    }

    @Test
    public void testMaxArrayLengthsWithinConstraint() {
        int[] a2b = new int[100];
        int[] b2a = new int[100];
        for (int i = 0; i < 100; i++) {
            a2b[i] = i * 10;
            b2a[i] = 100 + i * 10;
        }

        int result = app.scheduleShuttle(a2b, b2a, 1);
        assertEquals(200, result);
    }

    // ----------- NEGATIVE / CONSTRAINT VIOLATION CASES ------------

    @Test
    public void testAlpha2BetaEmptyThrows() {
        int[] a2b = {};
        int[] b2a = {100};
        assertEquals(-1, app.scheduleShuttle(a2b, b2a, 1));
    }

    @Test
    public void testBeta2AlphaEmptyThrows() {
        int[] a2b = {0};
        int[] b2a = {};
        assertEquals(-1, app.scheduleShuttle(a2b, b2a, 1));
    }

    @Test
    public void testInvalidAlpha2BetaLengthThrows() {
        int[] a2b = new int[101];
        int[] b2a = {100};
        assertThrows(IllegalArgumentException.class, () ->
                app.scheduleShuttle(a2b, b2a, 1));
    }

    @Test
    public void testInvalidBeta2AlphaLengthThrows() {
        int[] a2b = {0};
        int[] b2a = new int[101];
        assertThrows(IllegalArgumentException.class, () ->
                app.scheduleShuttle(a2b, b2a, 1));
    }

    @Test
    public void testAlpha2BetaContainsOutOfRangeTime() {
        int[] a2b = {0, 2001}; // Invalid
        int[] b2a = {100};

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                app.scheduleShuttle(a2b, b2a, 1));
        assertTrue(ex.getMessage().contains("between 0 and 2000"));
    }

    @Test
    public void testBeta2AlphaContainsOutOfRangeTime() {
        int[] a2b = {0};
        int[] b2a = {100, 2500}; // Invalid

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                app.scheduleShuttle(a2b, b2a, 1));
        assertTrue(ex.getMessage().contains("between 0 and 2000"));
    }

    @Test
    public void testInvalidMissionCountLow() {
        int[] a2b = {0};
        int[] b2a = {100};

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                app.scheduleShuttle(a2b, b2a, 0));
        assertTrue(ex.getMessage().contains("missions"));
    }

    @Test
    public void testInvalidMissionCountHigh() {
        int[] a2b = {0};
        int[] b2a = {100};

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                app.scheduleShuttle(a2b, b2a, 20));
        assertTrue(ex.getMessage().contains("missions"));
    }
}