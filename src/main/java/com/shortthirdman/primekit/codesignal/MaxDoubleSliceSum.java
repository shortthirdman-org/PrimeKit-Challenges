package com.shortthirdman.primekit.codesignal;

import java.util.Arrays;

/**
 *
 * Find the maximal sum of any double slice.
 * <br><br/>
 * A non-empty array A consisting of N integers is given.
 * <br><br/>
 * <pre>
 * A triplet (X, Y, Z), such that 0 &le; X &lt; Y &lt; Z &lt; N, is called a double slice.
 * </pre>
 * A double slice sum is the sum of the elements of the array A between indices
 * X and Y (exclusive) and between indices Y and Z (exclusive):
 * <br><br/>
 * The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].
 *
 * @author ShortThirdMan
 */
public class MaxDoubleSliceSum {

    public int calculateMaxDoubleSum(int[] A) {
        int N = A.length;
        if (N < 3 || N > 100000) {
            throw new IllegalArgumentException("Array length must be between 3 and 100,000.");
        }

        boolean flag = Arrays.stream(A).boxed().allMatch(num -> num >= -10000 && num <= 10000);
        if (!flag) {
            throw new IllegalArgumentException("Array elements must be between -10,000 and 10,000.");
        }

        // Step 1: Calculate left max slice sums
        int[] leftMax = new int[N];
        int maxEndingHere = 0;
        for (int i = 1; i < N; i++) {
            maxEndingHere = Math.max(0, maxEndingHere + A[i]);
            leftMax[i] = maxEndingHere;
        }

        // Step 2: Calculate right max slice sums
        int[] rightMax = new int[N];
        maxEndingHere = 0;
        for (int i = N - 2; i >= 0; i--) {
            maxEndingHere = Math.max(0, maxEndingHere + A[i]);
            rightMax[i] = maxEndingHere;
        }

        // Step 3: Find the maximum sum of any double slice
        int maxDoubleSliceSum = 0;
        for (int i = 1; i < N - 1; i++) {
            maxDoubleSliceSum = Math.max(maxDoubleSliceSum, leftMax[i - 1] + rightMax[i + 1]);
        }

        return maxDoubleSliceSum;
    }
}
