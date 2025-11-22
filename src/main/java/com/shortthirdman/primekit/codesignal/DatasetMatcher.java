package com.shortthirdman.primekit.codesignal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You're building a data analysis tool that needs to track relationships between two separate datasets.
 * Your tool needs to support two operations:
 * <br/>
 * <pre>
 *     Update a value in the second dataset<br/>
 *     Count matching pairs that satisfy a specific sum condition<br/>
 * </pre>
 * Given two integer datasets primary and secondary, you need to implement a system that can:
 * <pre>
 *     If the operation is of the form [0, index, newValue], then secondary[index] should be assigned the value of newValue.<br/>
 *     If the operation is of the form [1, targetSum], then find the total number of pairs of indices i and j such that
 *     primary[i] + secondary[j] = targetSum.<br/>
 * </pre>
 * You will be given the arrays of integers primary and secondary, as well as operations,
 * an array of operations in either of the forms described above. Your task is to implement this system,
 * perform the given operations and return an array of the results of the operations of the type [1, targetSum].
 *
 * @author ShortThirdMan
 */
public class DatasetMatcher {

    /**
     * Compute pair match counts
     * @param primary the primary dataset
     * @param secondary the secondary dataset
     * @param operations the operations
     * @return
     */
    public int[] computePairMatchCounts(int[] primary, int[] secondary, int[][] operations) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> secondaryFreq = new HashMap<>();

        // Initialize frequency map for the secondary array
        for (int num : secondary) {
            secondaryFreq.put(num, secondaryFreq.getOrDefault(num, 0) + 1);
        }

        for (int[] op : operations) {
            if (op[0] == 0) {
                // Update operation: [0, index, newValue]
                int index = op[1];
                int newVal = op[2];

                if (index < 0 || index >= secondary.length) {
                    throw new IndexOutOfBoundsException("Update index out of bounds: " + index);
                }

                int oldVal = secondary[index];

                // Update frequency map
                secondaryFreq.put(oldVal, secondaryFreq.get(oldVal) - 1);
                if (secondaryFreq.get(oldVal) == 0) {
                    secondaryFreq.remove(oldVal);
                }

                secondary[index] = newVal;
                secondaryFreq.put(newVal, secondaryFreq.getOrDefault(newVal, 0) + 1);
            } else if (op[0] == 1) {
                // Count operation: [1, targetSum]
                int target = op[1];
                int count = 0;

                for (int p : primary) {
                    int needed = target - p;
                    count += secondaryFreq.getOrDefault(needed, 0);
                }

                result.add(count);
            } else {
                throw new IllegalArgumentException("Invalid operation type: " + op[0]);
            }
        }

        // Convert result list to int array
        return result.stream().mapToInt(i -> i).toArray();
    }
}
