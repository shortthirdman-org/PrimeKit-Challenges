package com.shortthirdman.primekit.codesignal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Monitoring energy usage in a smart grid system and need to identify specific data patterns in the readings.
 * Given an array of integers readings representing the energy readings over a period and an integer k, count the readings that are powers of k.
 * In this context, a power of k refers to numbers that can be expressed as k raised to an integer power (e.g., k0, k1, k2, etc.).
 * <br/><br/>
 * For readings = [2, 4, 7, 8, 16, 32, 120] and k = 2, the output should be solution(readings, k) = 5.<br/>
 * Explanation: The numbers in the readings list that are powers of 2 are 2 (21), 4 (22), 8 (23), 16 (24), and 32 (25). There are 5 such numbers.
 * <br/><br/>
 * For readings = [10201, 101, 1030301, 101, 101] and k = 101, the output should be solution(readings, k) = 5.<br/>
 * Explanation: The numbers in the readings list that are powers of 10123 are 101 (1011), 10201 (1012), and 1030301 (1013). There are 5 such numbers.
 *
 * @author ShortThirdMan
 */
public class PowerCounter {

    /**
     * Count Powers of K
     * @param readings the list of readings
     * @param k the power to check
     * @return count of integers matching the power of k
     */
    public int countPowersOfK(int[] readings, int k) {
        if (k <= 1) {
            return (int) Arrays.stream(readings)
                    .filter(n -> (k == 0 && n == 1) || (k == 1 && n == 1) || n == 0 || n == 1)
                    .count();
        }

        Set<Integer> powers = new HashSet<>();
        long value = 1;

        while (value <= Integer.MAX_VALUE) {
            // if (value != 1)
            powers.add((int) value);
            if (value > Integer.MAX_VALUE / k) break; // Prevent overflow
            value *= k;
        }

        return (int) Arrays.stream(readings)
                .filter(powers::contains)
                .count();
    }

}
