package com.shortthirdman.primekit.codesignal;

/**
 * A non-empty array A consisting of N integers is given. A pair of integers (P, Q), such that <code>0 &le; P &le; Q &lt; N</code>,
 * is called a slice of array <code>A</code>.<br/>
 * The sum of a slice <code>(P, Q)</code> is the total of <code>A[P] + A[P+1] + ... + A[Q]</code>.
 * Write a function:
 * that, given an array A consisting of N integers, returns the maximum sum of any slice of A.
 * Find a maximum sum of a compact subsequence of array elements.
 *
 * @author ShortThirdMan
 */
public class MaxSliceSum {

    public int maxSumForArray(int[] A) {
        if (A == null || A.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int N = A.length;
        if (N == 1) {
            return A[0];
        }

        int maxCurrent = A[0];
        int maxGlobal = A[0];

        for (int i = 1; i < N; i++) {
            maxCurrent = Math.max(A[i], maxCurrent + A[i]);
            maxGlobal = Math.max(maxGlobal, maxCurrent);
        }

        return maxGlobal;
    }
}
