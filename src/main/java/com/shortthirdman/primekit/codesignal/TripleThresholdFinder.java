package com.shortthirdman.primekit.codesignal;

import java.util.stream.IntStream;

/**
 * Given an array of integers numbers, find the first index i for which three consecutive elements numbers[i], numbers[i + 1],
 * and numbers[i + 2] are all greater than a given threshold. If there is no such index, return -1.
 *
 * @author ShortThirdMan
 */
public class TripleThresholdFinder {

    public int findThreshold(int[] numbers, int threshold) {
        return IntStream.range(0, numbers.length - 2)
                .filter(i -> numbers[i] > threshold &&
                        numbers[i + 1] > threshold &&
                        numbers[i + 2] > threshold)
                .findFirst()
                .orElse(-1);
    }
}
