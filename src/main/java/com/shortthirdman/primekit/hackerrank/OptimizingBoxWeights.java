package com.shortthirdman.primekit.hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * An Amazon Fulfillment Associate has a set of items that need to be packed into two boxes.
 * Given an integer array of the item weights (<code>arr</code>) to be packed, divide the item weights into two subsets,
 * <code>A</code> and <code>B</code>, for packing into the associated boxes, while respecting the following conditions:
 * <ul>
 *     <li>The intersection of A and B is null.</li>
 *     <li>The union A and B is equal to the original array.</li>
 *     <li>The number of elements in subset A is minimal.</li>
 *     <li>The sum of A's weights is greater than the sum of B's weights.</li>
 * </ul>
 *
 * Return the subset A in increasing order where the sum of A's weights is greater than the sum of B's weights.
 * If more than one subset A exists, return the one with the maximal total weight.
 *
 * @author ShortThirdMan
 */
public class OptimizingBoxWeights {

    /**
     * @param weights the input array
     * @return the list of
     */
    public List<Integer> minimalHeaviestSetA(List<Integer> weights) {
        Collections.sort(weights);
        Stack<Integer> stack  = new Stack<>();
        int totalRemainingSum = 0;

        for (Integer integer : weights) {
            stack.push(integer);
            totalRemainingSum += integer;
        }

        List<Integer> setA =  new ArrayList<>();
        int setASum  = 0;
        while (!stack.isEmpty()) {
            int next = stack.pop();
            setA.addFirst(next);
            setASum+= next;
            totalRemainingSum-= next;

            // Stop When Sum of List A is greater than remaining
            if (setASum > totalRemainingSum) {
                while (!stack.isEmpty() && setASum - setA.getFirst() + stack.peek() > totalRemainingSum + setA.getFirst() - stack.peek()) {
                    int lastItem = setA.getFirst();
                    Integer newMin = stack.pop();
                    setA.removeFirst();
                    setA.addFirst(newMin);

                    setASum += (newMin - lastItem);
                    totalRemainingSum += (lastItem - newMin);
                }
                break;
            }
        }

        Collections.sort(setA);

        return setA;
    }
}
