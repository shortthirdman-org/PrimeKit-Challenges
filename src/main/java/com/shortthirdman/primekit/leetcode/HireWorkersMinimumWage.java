package com.shortthirdman.primekit.leetcode;

import com.shortthirdman.primekit.common.Worker;

import java.util.*;

/**
 * <b>Minimum Cost to Hire K Workers</b><br/>
 * There are N workers. The i-th worker has a <code>quality[i]</code> and a minimum wage expectation <code>wage[i]</code>.<br/>
 *
 * Now we want to hire exactly K workers to form a paid group. When hiring a group of K workers, we must pay them according to the following rules:<br/>
 * 1. Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.<br/>
 * 2. Every worker in the paid group must be paid at least their minimum wage expectation.<br/>
 *
 * Return the least amount of money needed to form a paid group satisfying the above conditions.<br/>
 *
 * @author ShortThirdMan
 */
public class HireWorkersMinimumWage {

	public double minCostToHireWorkers(int[] quality, int[] wage, int K) {
        if (quality.length == 0 || wage.length == 0) {
            throw new IllegalArgumentException("Wages and qualities can not be empty");
        }

        if (K > quality.length) {
            throw new IllegalArgumentException("K cannot be greater than the number of available workers");
        }

        if (quality.length != wage.length) {
            throw new IllegalArgumentException("Mismatched quality and wage for workers");
        }

        boolean isNegativeWage = Arrays.stream(wage).boxed().anyMatch(w -> w < 0);
        boolean isNegativeQuality = Arrays.stream(quality).boxed().anyMatch(q -> q < 0);
        if (isNegativeQuality || isNegativeWage) {
            throw new IllegalArgumentException("Wage or quality for worker can not be negative");
        }

        int N = quality.length;

        // Create an array of Worker objects
        Worker[] workers = new Worker[N];
        for (int i = 0; i < N; i++) {
            workers[i] = new Worker(quality[i], wage[i]);
        }

        // Sort workers based on the ratio (wage/quality)
        Arrays.sort(workers, Comparator.comparingDouble(Worker::getRatio));

        // Priority queue to maintain the largest K qualities
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int qualitySum = 0;
        double minCost = Double.MAX_VALUE;

        for (Worker worker : workers) {
            int q = worker.getQuality();
            qualitySum += q;
            pq.add(q);

            // If we exceed the number of workers, remove the smallest quality
            if (pq.size() > K) {
                Integer removedQuality = pq.poll();  // Safely unbox
                if (removedQuality != null) {
                    qualitySum -= removedQuality;
                }
            }

            // If we have exactly K workers, calculate the cost
            if (pq.size() == K) {
                minCost = Math.min(minCost, worker.getRatio() * qualitySum);
            }
        }

        return minCost;
    }
}
