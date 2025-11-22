package com.shortthirdman.primekit.codility;

import java.util.Arrays;
import java.util.Collections;

/**
 * A group of friends is going on holiday together. They have come to a meeting point (the start of the journey)
 * using N cars. There are <code>P[K]</code> people and <code>S[K]</code> seats in the K-th car for K in range <code>[0...N-1]</code>.
 * Some of the seats in the cars may be free, so it is possible for some of the friends to change the car they are in.
 * The friends have decided that, in order to be ecological, they will leave some cars parked at the meeting point
 * and travel with as few cars as possible.
 *
 * @author ShortThirdMan
 * @since 1.0.0
 */
public class OptimalCarUtilization {

    public int optimizeCarsUsed(int[] P, int[] S) {
        int N = P.length;
        int totalPeople = 0;
        Integer[] seats = new Integer[N];
        int totalSeats = 0;

        // Calculate the total number of people and prepare the seats array
        for (int i = 0; i < N; i++) {
            totalPeople += P[i];
            seats[i] = S[i];
            totalSeats += S[i];
        }

        if (totalPeople > totalSeats) {
            throw new IllegalStateException("There are more people than available seats.");
        }

        if (totalPeople == 0) {
            return 0;
        }

        // Sort seats in descending order
        Arrays.sort(seats, Collections.reverseOrder());

        int carsUsed = 0;
        int accumulatedSeats = 0;

        // Use the cars with the most seats first
        for (int i = 0; i < N; i++) {
            accumulatedSeats += seats[i];
            carsUsed++;
            if (accumulatedSeats >= totalPeople) {
                break;
            }
        }

        return carsUsed;
    }
}
