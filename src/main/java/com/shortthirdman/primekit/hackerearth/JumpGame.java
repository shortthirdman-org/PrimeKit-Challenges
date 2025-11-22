package com.shortthirdman.primekit.hackerearth;

import java.util.stream.IntStream;

/**
 * @author ShortThirdMan
 * @since 1.1.0
 */
public class JumpGame {

    /**
     * Calculates the minimum number of jumps required to reach the last index from index 0.
     * <p>
     * Each element in the array represents the maximum jump length from that position.
     * Uses a greedy approach by expanding the current jump range and counting jumps
     * whenever the current range is exhausted.
     * </p>
     *
     * @param nums an integer array where each element represents maximum jump length from that index
     * @return the minimum number of jumps needed to reach the last index
     * @throws IllegalArgumentException if:
     *                                  <ul>
     *                                    <li>{@code nums} is {@code null}</li>
     *                                    <li>length of {@code nums} is not in [1, 10^4]</li>
     *                                    <li>any element in {@code nums} is not in [0, 1000]</li>
     *                                  </ul>
     */
    public int jump(int[] nums) {
        // Constraint checks
        if (nums == null || nums.length < 1 || nums.length > 10_000) {
            throw new IllegalArgumentException("Array length must be between 1 and 10^4");
        }

        boolean invalidValue = IntStream.of(nums)
                .anyMatch(num -> num < 0 || num > 1_000);
        if (invalidValue) {
            throw new IllegalArgumentException("Array elements must be between 0 and 1000");
        }

        if (nums.length == 1) return 0; // already at last index

        int jumps = 0;
        int currentEnd = 0;   // boundary of the current jump
        int farthest = 0;     // farthest index we can reach

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) { // time to make another jump
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }

    /**
     * Determines if it is possible to reach the last index of the array starting from index 0.
     * <p>
     * Each element in the array represents the maximum jump length from that position.
     * Uses a greedy approach to keep track of the furthest reachable index at every step.
     * </p>
     *
     * @param nums an integer array where each element represents maximum jump length from that index
     * @return {@code true} if it is possible to reach the last index, otherwise {@code false}
     * @throws IllegalArgumentException if:
     *                                  <ul>
     *                                    <li>{@code nums} is {@code null}</li>
     *                                    <li>length of {@code nums} is not in [1, 10^4]</li>
     *                                    <li>any element in {@code nums} is not in [0, 10^5]</li>
     *                                  </ul>
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length < 1 || nums.length > 10_000) {
            throw new IllegalArgumentException("Array length must be between 1 and 10^4");
        }

        boolean invalidValue = IntStream.of(nums)
                .anyMatch(num -> num < 0 || num > 100_000);
        if (invalidValue) {
            throw new IllegalArgumentException("Array elements must be between 0 and 10^5");
        }

        // If there's only one element, we are already at the last index
        if (nums.length == 1) return true;

        int maxReach = 0;

        for (int i = 0; i < nums.length && i <= maxReach; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }

        return false;
    }
}
