package com.shortthirdman.primekit.hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Capacity Doubling Optimization - The Endpoint Upgrade Dilemma
 * @author shortthirdman
 */
public class RequestMaximizer {

    public long getMaxRequests(List<Integer> endpointCapacity, List<Integer> incomingTraffic, int k) {
        // --- Defensive Checks ---
        if (endpointCapacity == null || incomingTraffic == null) {
            throw new IllegalArgumentException("Input lists cannot be null.");
        }

        if (endpointCapacity.size() != incomingTraffic.size()) {
            throw new IllegalArgumentException("List sizes must match.");
        }

        if (k < 0) {
            throw new IllegalArgumentException("k cannot be negative.");
        }

        int n = endpointCapacity.size();
        List<Integer> benefitList = new ArrayList<>();
        long totalHandled = 0;

        for (int i = 0; i < n; i++) {
            int capacity = endpointCapacity.get(i);
            int traffic = incomingTraffic.get(i);

            int handledNormally = Math.min(capacity, traffic);
            int handledIfDoubled = Math.min(capacity * 2, traffic);

            totalHandled += handledNormally;
            benefitList.add(handledIfDoubled - handledNormally);
        }

        // Sort and apply k upgrades
        benefitList.sort(Collections.reverseOrder());

        for (int i = 0; i < Math.min(k, n); i++) {
            totalHandled += benefitList.get(i);
        }

        return totalHandled;
    }
}
