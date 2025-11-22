package com.shortthirdman.primekit.codility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A product has a request history that is tracked in an ordered list. The most recent request is at the end of the list.
 * Product IDs in the request list may be duplicated. Return the k most recent distinct product request IDs from the list.
 *
 * @author ShortThirdMan
 */
public class RecentRequestsTracker {

    /**
     * Returns the k most recent distinct product request IDs from the provided list,
     * ordered from most recent to least recent.
     *
     * <p>Each request must consist only of lowercase letters and digits (a-z, 0-9).
     * If any request violates this constraint, an IllegalArgumentException is thrown.
     *
     * @param requests the list of product request IDs in chronological order
     * @param k the number of most recent distinct request IDs to return
     * @return a list of the k most recent distinct request IDs
     * @throws IllegalArgumentException if any request contains invalid characters
     */
    public List<String> getLatestKRequests(List<String> requests, int k) {
        int n = requests.size();
        if (n < 1 || k < 1) {
            throw new IllegalArgumentException("Requests list and k must be greater than 0");
        }

        boolean allValid = requests.stream()
                .allMatch(req -> req.matches("[a-z0-9]+"));

        if (!allValid) {
            throw new IllegalArgumentException("All product request IDs must contain only lowercase letters and digits (a-z, 0-9).");
        }

        Set<String> seen = new HashSet<>();
        List<String> result = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {
            String request = requests.get(i);

            // Only add if not seen
            if (!seen.contains(request)) {
                seen.add(request);
                result.add(request); // Add to front so we don’t reverse later
            }

            // Stop once we have k distinct entries
            if (n == k) {
                break;
            }
        }

        return result;
    }
}
