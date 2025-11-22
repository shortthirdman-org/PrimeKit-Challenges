package com.shortthirdman.primekit.leetcode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HireWorkersMinimumWageTest {

    HireWorkersMinimumWage app;

    @BeforeEach
    void setUp() {
        app = new HireWorkersMinimumWage();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testBasicScenario() {
        // Test basic positive case
        int[] quality = {10, 20, 5};
        int[] wage = {70, 50, 30};
        int K = 2;

        assertEquals(105.0, app.minCostToHireWorkers(quality, wage, K), 1e-6);
    }

    @Test
    public void testSingleWorker() {
        // Test case with only one worker
        int[] quality = {5};
        int[] wage = {50};
        int K = 1;

        assertEquals(50.0, app.minCostToHireWorkers(quality, wage, K), 1e-6);
    }

    @Test
    public void testAllWorkersRequired() {
        // Test case where K equals the number of workers
        int[] quality = {5, 10, 15};
        int[] wage = {50, 100, 150};
        int K = 3;

        assertEquals(300.0, app.minCostToHireWorkers(quality, wage, K), 1e-6);
    }

    @Test
    public void testHigherWageDoesNotAlwaysMeanBest() {
        // Test case where a higher wage does not mean the best choice
        int[] quality = {10, 5, 1};
        int[] wage = {70, 50, 30};
        int K = 2;

        assertEquals(150.0, app.minCostToHireWorkers(quality, wage, K), 1e-6);
    }

    @Test
    public void testMinimumKWorkers() {
        // Test minimum K workers, where K=1
        int[] quality = {10, 20};
        int[] wage = {70, 140};
        int K = 1;

        assertEquals(70.0, app.minCostToHireWorkers(quality, wage, K), 1e-6);
    }

    @Test
    public void testEqualQualityDifferentWage() {
        // Test where workers have equal quality but different wages
        int[] quality = {10, 10, 10};
        int[] wage = {60, 70, 80};
        int K = 2;

        assertEquals(140.0, app.minCostToHireWorkers(quality, wage, K), 1e-6);
    }

    @Test
    public void testZeroWage() {
        // Test case where a worker has zero wage
        int[] quality = {10, 20, 30};
        int[] wage = {0, 50, 70};
        int K = 2;

        assertEquals(75.0, app.minCostToHireWorkers(quality, wage, K), 1e-6);
    }

    @Test
    public void testExactNumberOfWorkers() {
        // Test case where the number of workers exactly equals K
        int[] quality = {5, 10, 20};
        int[] wage = {50, 100, 150};
        int K = 3;

        assertEquals(350.0, app.minCostToHireWorkers(quality, wage, K), 1e-6);
    }

    @Test
    public void testMoreWorkersThanK() {
        // Test where number of workers is more than K, and we need to select the optimal workers
        int[] quality = {10, 15, 20};
        int[] wage = {70, 60, 50};
        int K = 2;

        assertEquals(140.0, app.minCostToHireWorkers(quality, wage, K), 1e-6);
    }

    @Test
    public void testMinimumRatioSelected() {
        // Test case where minimum ratio of wage to quality is the optimal choice
        int[] quality = {10, 20, 5};
        int[] wage = {60, 120, 25};
        int K = 2;

        assertEquals(90.0, app.minCostToHireWorkers(quality, wage, K), 1e-6);
    }

    // Edge Cases

    @Test
    public void testKGreaterThanAvailableWorkers() {
        // Edge case where K is greater than the number of workers (should throw exception)
        int[] quality = {10, 20, 5};
        int[] wage = {60, 100, 30};
        int K = 5;

        assertThrows(IllegalArgumentException.class, () -> app.minCostToHireWorkers(quality, wage, K));
    }

    @Test
    public void testEmptyArrays() {
        // Edge case where quality and wage arrays are empty
        int[] quality = {};
        int[] wage = {};
        int K = 1;

        assertThrows(IllegalArgumentException.class, () -> app.minCostToHireWorkers(quality, wage, K));
    }

    @Test
    public void testNegativeQualityValues() {
        // Edge case with negative quality (invalid input, should throw an exception)
        int[] quality = {-5, 10, 15};
        int[] wage = {50, 100, 150};
        int K = 2;

        assertThrows(IllegalArgumentException.class, () -> app.minCostToHireWorkers(quality, wage, K));
    }

    @Test
    public void testNegativeWageValues() {
        // Edge case with negative wage (invalid input, should throw an exception)
        int[] quality = {5, 10, 15};
        int[] wage = {-50, 100, 150};
        int K = 2;

        assertThrows(IllegalArgumentException.class, () -> app.minCostToHireWorkers(quality, wage, K));
    }

    @Test
    public void testMismatchedQualityAndWageArrayLengths() {
        // Edge case with mismatched quality and wage array lengths (should throw exception)
        int[] quality = {5, 10};
        int[] wage = {50, 100, 150};
        int K = 2;

        assertThrows(IllegalArgumentException.class, () -> app.minCostToHireWorkers(quality, wage, K));
    }

    @Test
    public void testTypicalCase1() {
        int[] quality = {10, 20, 5};
        int[] wage = {70, 50, 30};
        int K = 2;
        double expected = 105.0; // Calculated based on the given workers and K
        assertEquals(expected, app.minCostToHireWorkers(quality, wage, K));
    }

    @Test
    public void testTypicalCase2() {
        int[] quality = {3, 6, 1, 8};
        int[] wage = {15, 30, 5, 40};
        int K = 3;
        assertEquals(50.0, app.minCostToHireWorkers(quality, wage, K));
    }

    @Test
    public void testEdgeCaseSingleWorker() {
        int[] quality = {10};
        int[] wage = {100};
        int K = 1;
        double expected = 100.0; // Only one worker, cost is their wage
        assertEquals(expected, app.minCostToHireWorkers(quality, wage, K));
    }

    @Test
    public void testEdgeCaseAllSame() {
        int[] quality = {5, 5, 5};
        int[] wage = {50, 50, 50};
        int K = 2;
        double expected = 100.0; // All workers are the same, so total cost is wage of one worker * K
        assertEquals(expected, app.minCostToHireWorkers(quality, wage, K));
    }

    @Test
    public void testEdgeCaseKGreaterThanWorkers() {
        int[] quality = {10, 20};
        int[] wage = {100, 200};
        int K = 3;
        // K is greater than the number of workers; should throw exception
        assertThrows(IllegalArgumentException.class, () -> app.minCostToHireWorkers(quality, wage, K));
    }

    @Test
    public void testEdgeCaseHighK() {
        int[] quality = {10, 20, 30};
        int[] wage = {100, 200, 300};
        int K = 5; // K is more than the available workers
        // Expectation: Since K is greater than number of workers, it should throw an exception
        assertThrows(IllegalArgumentException.class, () -> app.minCostToHireWorkers(quality, wage, K));
    }

    @Test
    public void testTypicalCaseWithLargeNumbers() {
        int[] quality = {100, 200, 300, 400, 500};
        int[] wage = {1000, 2000, 3000, 4000, 5000};
        int K = 3;
        assertEquals(6000.0, app.minCostToHireWorkers(quality, wage, K));
    }

    @Test
    public void testTypicalCaseWithFloatingPointPrecision() {
        int[] quality = {1, 2, 3};
        int[] wage = {1, 2, 3};
        int K = 2;
        assertEquals(3.0, app.minCostToHireWorkers(quality, wage, K), 1e-6); // Tolerance for floating point comparison
    }
}