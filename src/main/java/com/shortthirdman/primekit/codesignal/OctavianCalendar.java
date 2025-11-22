package com.shortthirdman.primekit.codesignal;

import java.util.Arrays;

/**
 * On the planet Octavia, astronomers track time using 8 distinct lunar phases instead of weekdays:
 * "NewMoon", "Crescent", "Quarter", "Gibbous", "Full", "Waning", "Eclipse", and "Twilight". These phases repeat cyclically.
 * <br/>
 * The Octavian year is divided into 12 seasons (identical to Earth months in length), and each date has a corresponding lunar phase.
 * <br/>
 * Given a specific date (a season and day number) and the lunar phase with which the year began,
 * determine the lunar phase for that date.
 * <br/>
 * <pre>
 *     The number of days in each season in a non-leap year is, in order:
 *     <code>January - 31, February - 28, March - 31, April - 30, May - 31, June - 30, July - 31, August - 31, September - 30, October - 31, November - 30, December - 31</code>
 *     The lunar phases in the Octavian calendar are, in order:
 *     <code>NewMoon, Crescent, Quarter, Gibbous, Full, Waning, Eclipse, and Twilight</code>
 * </pre>
 * @author ShortThirdMan
 */
public class OctavianCalendar {

    private static final String[] SEASONS = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    private static final String[] PHASES = {
        "NewMoon", "Crescent", "Quarter", "Gibbous",
        "Full", "Waning", "Eclipse", "Twilight"
    };

    private static final int[] DAYS_IN_SEASON = {
        31, 28, 31, 30, 31, 30,
        31, 31, 30, 31, 30, 31
    };

    /**
     * Calculates the lunar phase on the planet Octavia for a given date,
     * based on the season, day of the season, and the initial lunar phase
     * at the start of the year.
     *
     * @param season the name of the season (equivalent to Earth months)
     * @param dayCount the day number within the season (1-based index)
     * @param initialPhase the lunar phase on the first day of the year
     * @return the lunar phase for the given date in the Octavian calendar
     * @throws IllegalArgumentException if the season or phase is invalid, or if dayCount is out of valid range
     */
    public String computeOctavianLunarPhase(String season, int dayCount, String initialPhase) {
        var daysInMonth = DAYS_IN_SEASON;
        var seasons = SEASONS;
        var phases = PHASES;

        // Validate season
        if (!Arrays.asList(seasons).contains(season)) {
            throw new IllegalArgumentException("Invalid season: " + season);
        }

        // Validate phase
        if (!Arrays.asList(phases).contains(initialPhase)) {
            throw new IllegalArgumentException("Invalid initial phase: " + initialPhase);
        }

        // Validate dayCount
        if (dayCount < 1 || dayCount > daysInMonth[Arrays.asList(seasons).indexOf(season)]) {
            throw new IllegalArgumentException("Invalid day count: " + dayCount);
        }

        // Step 1: Calculate days since beginning of the year
        int daysElapsed = 0;
        for (int i = 0; i < seasons.length; i++) {
            if (seasons[i].equals(season)) {
                break;
            }
            daysElapsed += daysInMonth[i];
        }

        // Add current day - 1 since day 1 is the first day
        daysElapsed += (dayCount - 1);

        // Step 2: Find index of initial phase
        int initialIndex = -1;
        for (int i = 0; i < phases.length; i++) {
            if (phases[i].equals(initialPhase)) {
                initialIndex = i;
                break;
            }
        }

        // Step 3: Compute the new phase index
        int finalIndex = (initialIndex + daysElapsed) % phases.length;

        // Step 4: Return the resulting phase
        return phases[finalIndex];
    }
}
