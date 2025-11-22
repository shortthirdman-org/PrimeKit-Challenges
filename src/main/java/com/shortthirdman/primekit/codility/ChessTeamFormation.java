package com.shortthirdman.primekit.codility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HackerRank is organizing a chess tournament for its employees. There are n employees, each with a unique ID from 1 to n,
 * and the employee with ID x has a rating of rating[x]. Two employees can form a team if they have the same rating, and each employee
 * can be part of only one team.
 * <br/>
 * There are q queries, each of the form (l, r). For each query, determine the maximum number of teams
 * that can be formed from employees with IDs between l and r, inclusive. Each query is independent of the others.
 *
 * @author ShortThirdMan
 */
public class ChessTeamFormation {

    public List<Integer> countTeams(List<Integer> rating, List<List<Integer>> queries) {
        List<Integer> result = new ArrayList<>();

        if (rating.isEmpty() || queries.isEmpty()) {
            // If no ratings or queries, return empty result
            return result;
        }

        for (List<Integer> query : queries) {
            int l = query.get(0);
            int r = query.get(1);

            if (l < 0 || r >= rating.size() || l > r) {
                throw new IndexOutOfBoundsException("Query range is invalid.");
            }

            // Count frequency of each rating in the range [l, r]
            Map<Integer, Integer> frequency = new HashMap<>();
            for (int i = l; i <= r; i++) {
                int rate = rating.get(i);
                frequency.put(rate, frequency.getOrDefault(rate, 0) + 1);
            }

            // Calculate teams as floor(count / 2) for each rating group
            int teams = 0;
            for (int count : frequency.values()) {
                teams += count / 2;
            }

            result.add(teams);
        }

        return result;
    }
}
