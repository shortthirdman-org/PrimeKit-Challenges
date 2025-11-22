package com.shortthirdman.primekit.codesignal;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * A space station network has two major hubs, Alpha and Beta, connected by shuttle services.
 * Shuttles from Alpha to Beta take 100 time units and depart according to a sorted integer array alpha2beta.
 * Similarly, shuttles from Beta to Alpha also take 100 time units and depart at times specified in a sorted integer array beta2alpha.
 * <br/>
 * You need to complete missions where each mission requires traveling from Alpha to Beta and back to Alpha.
 * Calculate the time unit when you will complete all missions, assuming you always take the earliest available shuttle.
 * <br/>
 * It is guaranteed that it is possible to complete all missions using the provided shuttle schedules.
 * <br/>
 * All times are measured in time units from the start of your journey.
 *
 * @author ShortThirdMan
 */
public class ShuttleMissionScheduler {

    /**
     * @param alpha2beta the time taken from Alpha to Beta shuttle
     * @param beta2alpha the time taken from Beta to Alpha shuttle
     * @param missions the number of mission
     * @return
     */
    public int scheduleShuttle(int[] alpha2beta, int[] beta2alpha, int missions) {
        if (alpha2beta.length == 0 || beta2alpha.length == 0) {
            return -1;
        }

        if (missions < 1 || missions > 10) {
            throw new IllegalArgumentException("missions must be between 1 and 10");
        }

        if (alpha2beta.length > 100 ||  beta2alpha.length > 100) {
            throw new IllegalArgumentException("alpha2beta and beta2alpha length should be between 1 and 100");
        }

        Predicate<int[]> isValidArray = arr -> Arrays.stream(arr).allMatch(x -> x >= 0 && x <= 2000);

        if (!isValidArray.test(alpha2beta) || !isValidArray.test(beta2alpha)) {
            throw new IllegalArgumentException("alpha2beta and beta2alpha elements should be between 0 and 2000");
        }

        Arrays.sort(alpha2beta);
        Arrays.sort(beta2alpha);

        int currentTime = 0;

        for (int i = 0; i < missions; i++) {
            // Step 1: Take the earliest shuttle from Alpha to Beta
            int depAlphaToBeta = findNextShuttle(alpha2beta, currentTime);
            int arriveAtBeta = depAlphaToBeta + 100;

            // Step 2: Take the earliest shuttle from Beta to Alpha
            int depBetaToAlpha = findNextShuttle(beta2alpha, arriveAtBeta);
            currentTime = depBetaToAlpha + 100; // back at Alpha
        }

        return currentTime;
    }

    private int findNextShuttle(int[] schedule, int currentTime) {
        int left = 0, right = schedule.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (schedule[mid] >= currentTime) {
                result = schedule[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}
