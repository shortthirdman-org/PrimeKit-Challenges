package com.shortthirdman.primekit.hackerearth;

import java.util.List;
import java.util.Objects;

/**
 * Solves the optimal upgrade selection problem under a fixed currency constraint.
 *
 * <p>Each available upgrade index {@code i} incurs a given cost and yields an
 * enhancement gain equal to {@code 2^i}[cite: 1]. Because every power of two is strictly greater
 * than the sum of all preceding powers of two (i.e., {@code \sum_{j=0}^{i-1} 2^j = 2^i - 1 < 2^i}),
 * the problem exhibits the greedy-choice property. This optimizer selects upgrades
 * greedily starting from the highest available power down to zero.
 *
 * <p><strong>Thread Safety:</strong> This class is stateless, immutable, and thread-safe.
 *
 * @since 1.2.1
 * @author ShortThirdMan
 */
public class UpgradeEnhancementOptimizer {

    private static final int MOD = 1_000_000_007;

    /**
     * Calculates the maximum enhancement obtainable without exceeding the provided budget.
     *
     * <p>Enhancement values grow exponentially such that the item at index {@code i}
     * contributes {@code 2^i} enhancement[cite: 1]. The returned sum is reduced modulo {@code 10^9 + 7}[cite: 1].
     *
     * <h4>Algorithm Details</h4>
     * <ul>
     *   <li><strong>Precomputes</strong> powers of two modulo {@code 1,000,000,007}.</li>
     *   <li><strong>Greedy Selection:</strong> Evaluates items in descending index order
     *       ({@code n - 1} down to {@code 0}), deducting the item cost whenever sufficient
     *       budget remains.</li>
     * </ul>
     *
     * <h4>Complexity</h4>
     * <ul>
     *   <li>Time Complexity: {@code O(n)}, where {@code n} is the size of {@code upgradeCosts}.</li>
     *   <li>Space Complexity: {@code O(n)} auxiliary memory for modular exponent precomputations.</li>
     * </ul>
     *
     * @param upgradeCosts non-null list of non-negative integers representing upgrade costs,
     *                     where element at index {@code i} yields enhancement {@code 2^i}[cite: 1]
     * @param budget       maximum spendable currency, must be non-negative
     * @return the maximum enhancement score achievable within budget, modulo {@code 10^9 + 7}[cite: 1]
     * @throws NullPointerException     if {@code upgradeCosts} is {@code null}
     * @throws IllegalArgumentException if {@code budget} is negative
     */
    public int optimizeInAppUpgrades(List<Integer> upgradeCosts, int budget) {
        Objects.requireNonNull(upgradeCosts, "upgradeCosts must not be null");
        if (budget < 0) {
            throw new IllegalArgumentException("budget cannot be negative: " + budget);
        }

        int n = upgradeCosts.size();
        if (n == 0) {
            return 0;
        }

        // Precompute powers of 2: 2^0, 2^1, ..., 2^(n-1) modulo 10^9 + 7
        int[] pow2 = new int[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        long remainingBudget = budget;
        int totalEnhancement = 0;

        for (int i = n - 1; i >= 0; i--) {
            int cost = upgradeCosts.get(i);
            if (remainingBudget >= cost) {
                remainingBudget -= cost;
                totalEnhancement += pow2[i];
                if (totalEnhancement >= MOD) {
                    totalEnhancement -= MOD;
                }
            }
        }

        return totalEnhancement;
    }
}
