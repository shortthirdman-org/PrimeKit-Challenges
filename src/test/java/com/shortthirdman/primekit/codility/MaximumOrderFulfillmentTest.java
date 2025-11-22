package com.shortthirdman.primekit.codility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaximumOrderFulfillmentTest {

    MaximumOrderFulfillment app;

    @BeforeEach
    void setUp() {
        app = new MaximumOrderFulfillment();
    }

    @Test
    void testBasicCase() {
        int[] D = {1, 2, 3};
        int[] C = {2, 2, 2};
        int P = 6;
        assertEquals(3, app.maxOrdersFulfilled(D, C, P));
    }

    @Test
    void testPartialFulfillment() {
        int[] D = {1, 2, 3};
        int[] C = {2, 4, 2};
        int P = 5;
        assertNotEquals(2, app.maxOrdersFulfilled(D, C, P));
        assertEquals(1, app.maxOrdersFulfilled(D, C, P));
    }

    @Test
    void testAllOrdersSameDistance() {
        int[] D = {1, 1, 1};
        int[] C = {1, 2, 1};
        int P = 4;
        assertEquals(3, app.maxOrdersFulfilled(D, C, P));
    }

    @Test
    void testExactMatch() {
        int[] D = {2, 3, 1};
        int[] C = {2, 1, 3};
        int P = 6;
        assertEquals(3, app.maxOrdersFulfilled(D, C, P));
    }

    @Test
    void testNoOrders() {
        int[] D = {};
        int[] C = {};
        int P = 10;
        assertEquals(0, app.maxOrdersFulfilled(D, C, P));
    }

    @Test
    void testZeroMonitorsAvailable() {
        int[] D = {1, 2, 3};
        int[] C = {2, 1, 3};
        int P = 0;
        assertEquals(0, app.maxOrdersFulfilled(D, C, P));
    }

    @Test
    void testSingleOrder() {
        int[] D = {1};
        int[] C = {2};
        int P = 5;
        assertEquals(1, app.maxOrdersFulfilled(D, C, P));
    }

    @Test
    void testInsufficientMonitorsForAnyOrder() {
        int[] D = {1, 2, 3};
        int[] C = {4, 5, 6};
        int P = 3;
        assertEquals(0, app.maxOrdersFulfilled(D, C, P));
    }

    @Test
    void testNegativeInputs() {
        int[] D = {-1, -2, -3};
        int[] C = {4, 5, 6};
        int P = 10;
        assertThrows(IllegalArgumentException.class, () -> app.maxOrdersFulfilled(D, C, P));
    }

    @Test
    void testNegativeMonitors() {
        int[] D = {1, 2, 3};
        int[] C = {4, 5, 6};
        int P = -5;
        assertThrows(IllegalArgumentException.class, () -> app.maxOrdersFulfilled(D, C, P));
    }
}