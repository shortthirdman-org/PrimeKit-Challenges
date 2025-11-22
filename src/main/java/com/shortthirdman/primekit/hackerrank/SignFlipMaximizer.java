package com.shortthirdman.primekit.hackerrank;

import java.util.List;

/**
 * Given an array <code>arr</code> of size <code>n</code> and an integer <code>k</code> representing the number of sign-flip
 * operations to perform.
 * A sign-flip operation changes the sign of a single element in the array (from positive to negative or vice versa).
 * Implement a function that calculates the maximum possible sum of the array after performing exactly k sign-flip operations.
 *
 * @author shortthirdman
 */
public class SignFlipMaximizer {

    /**
     * @param arr the input array
     * @param k the total number of sign-flip operations performed
     * @return the maximum possible sum of the array after performing exactly <code>k</code> sign-flip operations
     */
    public long maximizeSum(List<Integer> arr, int k) {
        // Step 1: Sort the array by absolute value in descending order
        arr.sort((a, b) -> Integer.compare(Math.abs(b), Math.abs(a)));

        // Step 2: Flip as many negative numbers as possible
        for (int i = 0; i < arr.size() && k > 0; i++) {
            if (arr.get(i) < 0) {
                arr.set(i, -arr.get(i));
                k--;
            }
        }

        // Step 3: If k is still odd, flip the smallest (absolute) value
        if (k % 2 != 0) {
            // Find the smallest absolute value element and flip it
            int minAbsIndex = 0;
            for (int i = 1; i < arr.size(); i++) {
                if (Math.abs(arr.get(i)) < Math.abs(arr.get(minAbsIndex))) {
                    minAbsIndex = i;
                }
            }
            arr.set(minAbsIndex, -arr.get(minAbsIndex));
        }

        // Step 4: Calculate the final sum
        long totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        return totalSum;
    }
}
