package com.shortthirdman.primekit.hackerearth;

import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UpgradeEnhancementOptimizer Tests")
class UpgradeEnhancementOptimizerTest {

    UpgradeEnhancementOptimizer app;

    @BeforeEach
    void setUp() {
        app = new UpgradeEnhancementOptimizer();
    }

    @AfterEach
    void tearDown() {
        app = null;
    }

    @Nested
    @DisplayName("Sample and Positive Functional Cases")
    class PositiveScenarios {

        @Test
        @DisplayName("Should solve the HackerRank example case correctly")
        void shouldSolveStandardExampleCase() {
            List<Integer> costs = List.of(10, 20, 14, 40, 50); // indices 0 to 4[cite: 1]
            int budget = 70; //[cite: 1]

            // Upgrade 4 (cost 50, val 16) + Upgrade 2 (cost 14, val 4) = 20[cite: 1]
            int result = app.optimizeInAppUpgrades(costs, budget);

            assertEquals(20, result); //[cite: 1]
        }

        @Test
        @DisplayName("Should solve Sample Case 0 where all upgrades can be afforded")
        void shouldSolveSampleCase0AffordingAllUpgrades() {
            List<Integer> costs = List.of(3, 4, 1); //[cite: 2]
            int budget = 8; //[cite: 2]

            // Total cost = 3 + 4 + 1 = 8 <= 8; total value = 2^0 + 2^1 + 2^2 = 1 + 2 + 4 = 7[cite: 2]
            int result = app.optimizeInAppUpgrades(costs, budget);

            assertEquals(7, result); //[cite: 2]
        }

        @Test
        @DisplayName("Should select only the single highest upgrade if budget permits nothing else")
        void shouldSelectSingleHighestUpgradeWhenRemainingBudgetIsInsufficient() {
            List<Integer> costs = List.of(15, 10, 100);
            int budget = 105;

            // Upgrade 2 (cost 100, val 4) leaves budget 5; cannot afford index 1 or 0
            int result = app.optimizeInAppUpgrades(costs, budget);

            assertEquals(4, result);
        }

        @Test
        @DisplayName("Should greedily skip expensive higher upgrade when cost exceeds budget")
        void shouldSkipExpensiveHigherUpgradeAndPickAffordableLowerUpgrades() {
            List<Integer> costs = List.of(5, 5, 200);
            int budget = 10;

            // Upgrade 2 (cost 200) skipped; Upgrade 1 (cost 5, val 2) + Upgrade 0 (cost 5, val 1) = 3
            int result = app.optimizeInAppUpgrades(costs, budget);

            assertEquals(3, result);
        }

        @Test
        @DisplayName("Should return correct value when exact budget equals single item cost")
        void shouldHandleSingleItemExactBudget() {
            List<Integer> costs = List.of(42);
            int budget = 42;

            // Upgrade 0 has value 2^0 = 1
            int result = app.optimizeInAppUpgrades(costs, budget);

            assertEquals(1, result);
        }

        @Test
        @DisplayName("Should correctly wrap modulo (10^9 + 7) for large power sum results")
        void shouldApplyModuloWhenTotalEnhancementExceedsModuloBoundary() {
            // 31 upgrades all with cost 0 -> sum = 2^31 - 1 = 2,147,483,647
            // Modulo 1,000,000,007 -> 2,147,483,647 % 1,000,000,007 = 147,483,633
            List<Integer> costs = Collections.nCopies(31, 0);
            int budget = 0;

            int result = app.optimizeInAppUpgrades(costs, budget);

            assertEquals(147_483_633, result);
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCases {

        @Test
        @DisplayName("Should return 0 when upgrade costs list is empty")
        void shouldReturnZeroWhenUpgradeCostsListIsEmpty() {
            int result = app.optimizeInAppUpgrades(List.of(), 500);

            assertEquals(0, result);
        }

        @Test
        @DisplayName("Should return 0 when budget is 0 and all items cost greater than 0")
        void shouldReturnZeroWhenBudgetIsZeroAndCostsPositive() {
            List<Integer> costs = List.of(10, 20, 30);
            int budget = 0;

            int result = app.optimizeInAppUpgrades(costs, budget);

            assertEquals(0, result);
        }

        @Test
        @DisplayName("Should return 0 when budget is strictly less than minimum item cost")
        void shouldReturnZeroWhenBudgetIsLessThanAnyCost() {
            List<Integer> costs = List.of(50, 100, 200);
            int budget = 25;

            int result = app.optimizeInAppUpgrades(costs, budget);

            assertEquals(0, result);
        }

        @Test
        @DisplayName("Should handle 0 cost upgrades when budget is 0")
        void shouldIncludeZeroCostUpgradesEvenWithZeroBudget() {
            List<Integer> costs = List.of(0, 10, 0); // index 0 and 2 are free
            int budget = 0;

            // index 2 (val 4) + index 0 (val 1) = 5
            int result = app.optimizeInAppUpgrades(costs, budget);

            assertEquals(5, result);
        }

        @Test
        @DisplayName("Should handle max budget constraint up to 10^9 without integer overflow")
        void shouldHandleMaximumAllowedBudget() {
            List<Integer> costs = List.of(100_000, 100_000); //[cite: 2]
            int budget = 1_000_000_000; // 10^9[cite: 2]

            // Both can be afforded: 2^0 + 2^1 = 3
            int result = app.optimizeInAppUpgrades(costs, budget);

            assertEquals(3, result);
        }
    }

    @Nested
    @DisplayName("Exception and Negative Validations")
    class ExceptionCases {

        @Test
        @DisplayName("Should throw NullPointerException when upgradeCosts list is null")
        void shouldThrowNullPointerExceptionWhenListIsNull() {
            NullPointerException exception = assertThrows(
                    NullPointerException.class,
                    () -> app.optimizeInAppUpgrades(null, 50)
            );

            assertEquals("upgradeCosts must not be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when budget is negative")
        void shouldThrowIllegalArgumentExceptionWhenBudgetIsNegative() {
            List<Integer> costs = List.of(10, 20);
            int invalidBudget = -1;

            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> app.optimizeInAppUpgrades(costs, invalidBudget)
            );

            assertEquals("budget cannot be negative: -1", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException with large negative budget")
        void shouldThrowIllegalArgumentExceptionForLargeNegativeBudget() {
            List<Integer> costs = List.of(1, 2, 3);
            int invalidBudget = Integer.MIN_VALUE;

            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> app.optimizeInAppUpgrades(costs, invalidBudget)
            );

            assertEquals("budget cannot be negative: " + Integer.MIN_VALUE, exception.getMessage());
        }
    }
}