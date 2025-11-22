package com.shortthirdman.primekit.hackerrank;

import java.util.Collections;
import java.util.List;

public class TeamEfficiencyCalculator {

    public long getTotalEfficiency(List<Integer> skill) {
        if (skill == null || skill.isEmpty() || skill.size() % 2 != 0) {
            return -1; // Can't form teams if the list is null or the number of participants isn't even
        }

        Collections.sort(skill); // Sort the skills to easily form pairs
        int n = skill.size();

        // Additional check for mixed positive and negative skills
        // This ensures that if there are both negative and positive values, pairing is invalid.
        if (skill.getFirst() < 0 && skill.get(n - 1) > 0) {
            return -1;
        }

        long totalEfficiency = 0;
        int targetSum = skill.getFirst() + skill.get(n - 1); // Calculate the target sum of skills for all pairs

        for (int i = 0; i < n / 2; i++) {
            int sum = skill.get(i) + skill.get(n - 1 - i); // Sum of skills for the current pair
            if (sum != targetSum) {
                return -1; // Return -1 if a pair doesn't satisfy the condition
            }
            totalEfficiency += (long) skill.get(i) * skill.get(n - 1 - i); // Add the product of skills to total efficiency
        }

        return totalEfficiency;
    }
}
