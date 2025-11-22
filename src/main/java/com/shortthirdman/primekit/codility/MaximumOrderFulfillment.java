package com.shortthirdman.primekit.codility;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * A technology company announced that a new supply of P monitors would soon be available at their store.
 * There were N orders (numbered from 0 to N-1) placed by customers who wanted to buy those monitors.
 * The K-th order has to be delivered to a location at distance D[K] from the store and is for exactly C[K] monitors.
 * Now the time has come for the monitors to be delivered. The orders will be fulfilled one by one. To minimize the shipping time,
 * it has been decided that the deliveries will be made in order of increasing distance from the store.
 * If there are many customers at the same distance, they can be processed in any order.
 * Monitors to more distant customers will be delivered only once all orders to customers closer to the store have already been fulfilled.
 * What is the maximum total number of orders that can be fulfilled?
 *
 * @author ShortThirdMan
 * @since 1.0.0
 */
public class MaximumOrderFulfillment {

    Predicate<Integer> isGreaterThanZero = n -> n > 0;

    public int maxOrdersFulfilled(int[] D, int[] C, int P) {

        if (P < 0) {
            throw new IllegalArgumentException("P can not be negative or less than zero");
        }
        boolean allDistancesArePositive = Arrays.stream(D)
                .boxed()
                .allMatch(isGreaterThanZero);
        if (!allDistancesArePositive) {
            throw new IllegalArgumentException("Distance can not be negative or less than zero");
        }

        boolean allMonitorCountArePositive = Arrays.stream(C)
                .boxed()
                .allMatch(isGreaterThanZero);
        if (!allMonitorCountArePositive) {
            throw new IllegalArgumentException("Number of monitors can not be negative or less than zero");
        }

        // Number of orders
        int N = D.length;

        // Create an array of orders to store distances and corresponding monitor requests
        int[][] orders = new int[N][2];
        for (int i = 0; i < N; i++) {
            orders[i][0] = D[i];
            orders[i][1] = C[i];
        }

        // Sort orders by the distance (ascending order)
        Arrays.sort(orders, Comparator.comparingInt(a -> a[0]));

        // Initialize the count of fulfilled orders
        int fulfilledOrders = 0;

        // Process each order in the sorted list
        for (int i = 0; i < N; i++) {
            int requiredMonitors = orders[i][1];

            if (P >= requiredMonitors) {
                // Fulfill the order
                P -= requiredMonitors;
                fulfilledOrders++;
            } else {
                // If not enough monitors to fulfill this order, break out
                break;
            }
        }

        return fulfilledOrders;
    }
}
