package com.shortthirdman.primekit.codility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptimalCarUtilizationTest {

    OptimalCarUtilization app;

    @BeforeEach
    void setUp() {
        app = new OptimalCarUtilization();
    }

    @AfterEach
    void tearDown() {
    }

    // Test with minimum input size
    @Test
    void testMinimumInputSize() {
        assertEquals(1, app.optimizeCarsUsed(new int[]{1}, new int[]{1}));
    }

    // Test where all people can fit in one car
    @Test
    void testAllPeopleInOneCar() {
        assertEquals(1, app.optimizeCarsUsed(new int[]{2, 1}, new int[]{4, 2}));
    }

    // Test where each car is exactly full
    @Test
    void testEachCarExactlyFull() {
        assertEquals(3, app.optimizeCarsUsed(new int[]{2, 3, 4}, new int[]{2, 3, 4}));
    }

    // Test where cars have varying capacities and some are unused
    @Test
    void testVaryingCapacities() {
        assertEquals(2, app.optimizeCarsUsed(new int[]{1, 4, 2}, new int[]{4, 4, 4}));
    }

    // Test where some cars have more seats than people
    @Test
    void testExcessSeats() {
        assertNotEquals(2, app.optimizeCarsUsed(new int[]{1, 2, 2}, new int[]{3, 5, 3}));
        assertEquals(1, app.optimizeCarsUsed(new int[]{1, 2, 2}, new int[]{3, 5, 3}));
    }

    // Test with no people but with cars (should return 0 cars needed)
    @Test
    void testNoPeople() {
        assertEquals(0, app.optimizeCarsUsed(new int[]{0, 0, 0}, new int[]{3, 5, 3}));
    }

    // Test where no cars are available (empty input arrays)
    @Test
    void testNoCars() {
        assertEquals(0, app.optimizeCarsUsed(new int[]{}, new int[]{}));
    }

    // Test with more people than total seats available (should throw exception)
    @Test
    void testMorePeopleThanSeats() {
        assertThrows(IllegalStateException.class, () -> app.optimizeCarsUsed(new int[]{3, 4, 5}, new int[]{2, 2, 3}));
    }

    // Test with all zero seats and people
    @Test
    void testAllZeroSeatsAndPeople() {
        assertEquals(0, app.optimizeCarsUsed(new int[]{0, 0, 0}, new int[]{0, 0, 0}));
    }

    // Test with single person and multiple cars with 1 seat
    @Test
    void testSinglePersonMultipleCars() {
        assertEquals(1, app.optimizeCarsUsed(new int[]{1, 0, 0}, new int[]{1, 1, 1}));
    }

    // Test with cars having more seats than necessary
    @Test
    void testCarsWithExcessSeats() {
        assertNotEquals(2, app.optimizeCarsUsed(new int[]{5, 4}, new int[]{10, 5}));
        assertEquals(1, app.optimizeCarsUsed(new int[]{5, 4}, new int[]{10, 5}));
    }

    // Test with mixed scenarios
    @Test
    void testMixedScenarios() {
        assertEquals(2, app.optimizeCarsUsed(new int[]{1, 2, 3, 4}, new int[]{3, 4, 5, 6}));
    }

    // Test with empty seats
    @Test
    void testEmptySeats() {
        assertEquals(0, app.optimizeCarsUsed(new int[]{0, 0, 0}, new int[]{0, 0, 0}));
    }

    // Test with very large numbers of people and seats (stress test)
    @Test
    void testLargeNumbers() {
        int[] people = new int[1000];
        int[] seats = new int[1000];
        for (int i = 0; i < 1000; i++) {
            people[i] = 1;
            seats[i] = 2;
        }
        assertEquals(500, app.optimizeCarsUsed(people, seats));
    }
}