package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestMaximizerTest {

    RequestMaximizer app;

    @BeforeEach
    void setUp() {
        app = new RequestMaximizer();
    }

    @AfterEach
    void tearDown() {
    }

    // --- ✅ Positive Tests ---

    @Test
    void testBasicCase() {
        List<Integer> cap = List.of(10, 4, 3, 7);
        List<Integer> traffic = List.of(3, 10, 4, 5);
        int k = 2;
        assertEquals(20, app.getMaxRequests(cap, traffic, k));
    }

    @Test
    void testKIsZero() {
        List<Integer> cap = List.of(5, 10);
        List<Integer> traffic = List.of(10, 10);
        int k = 0;
        assertEquals(15, app.getMaxRequests(cap, traffic, k));
    }

    @Test
    void testKMoreThanN() {
        List<Integer> cap = List.of(2, 3);
        List<Integer> traffic = List.of(6, 7);
        int k = 5;
        assertEquals(10, app.getMaxRequests(cap, traffic, k));
    }

    @Test
    void testZeroTraffic() {
        List<Integer> cap = List.of(5, 10, 8);
        List<Integer> traffic = List.of(0, 0, 0);
        int k = 2;
        assertEquals(0, app.getMaxRequests(cap, traffic, k));
    }

    @Test
    void testAllTrafficLessThanCapacity() {
        List<Integer> cap = List.of(10, 10, 10);
        List<Integer> traffic = List.of(1, 2, 3);
        int k = 2;
        assertEquals(6, app.getMaxRequests(cap, traffic, k));
    }

    @Test
    void testAllTrafficMoreThanCapacity() {
        List<Integer> cap = List.of(2, 2, 2);
        List<Integer> traffic = List.of(10, 10, 10);
        int k = 1;
        assertEquals(8, app.getMaxRequests(cap, traffic, k));
    }

    // --- ❌ Negative Tests ---

    @Test
    void testNullCapacityList() {
        assertThrows(IllegalArgumentException.class, () -> app.getMaxRequests(null, List.of(1, 2, 3), 2));
    }

    @Test
    void testNullTrafficList() {
        assertThrows(IllegalArgumentException.class, () -> app.getMaxRequests(List.of(1, 2, 3), null, 2));
    }

    @Test
    void testMismatchedListSize() {
        List<Integer> cap = List.of(1, 2);
        List<Integer> traffic = List.of(5, 6, 7);
        assertThrows(IllegalArgumentException.class, () -> app.getMaxRequests(cap, traffic, 1));
    }

    @Test
    void testNegativeK() {
        List<Integer> cap = List.of(5, 5);
        List<Integer> traffic = List.of(6, 6);
        assertThrows(IllegalArgumentException.class, () -> app.getMaxRequests(cap, traffic, -1));
    }

    // --- 🧨 Edge Cases ---

    @Test
    void testEmptyLists() {
        List<Integer> cap = List.of();
        List<Integer> traffic = List.of();
        int k = 0;
        assertEquals(0, app.getMaxRequests(cap, traffic, k));
    }

    @Test
    void testSingleEndpoint() {
        List<Integer> cap = List.of(2);
        List<Integer> traffic = List.of(5);
        int k = 1;
        assertEquals(4, app.getMaxRequests(cap, traffic, k));
    }

    @Test
    void testAllCapacityZero() {
        List<Integer> cap = List.of(0, 0, 0);
        List<Integer> traffic = List.of(5, 5, 5);
        int k = 2;
        assertEquals(0, app.getMaxRequests(cap, traffic, k));
    }

    @Test
    void testDoublingDoesNotHelp() {
        List<Integer> cap = List.of(10, 10, 10);
        List<Integer> traffic = List.of(5, 5, 5);
        int k = 3;
        assertEquals(15, app.getMaxRequests(cap, traffic, k));
    }

    @Test
    void testLargeNumbers() {
        List<Integer> cap = List.of(1_000_000_000, 1_000_000_000);
        List<Integer> traffic = List.of(2_000_000_000, 2_000_000_000);
        int k = 1;
        assertEquals(3_000_000_000L, app.getMaxRequests(cap, traffic, k));
    }
}