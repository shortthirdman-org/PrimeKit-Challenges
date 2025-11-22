package com.shortthirdman.primekit.codility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChessTeamFormationTest {

    ChessTeamFormation app;

    @BeforeEach
    void setUp() {
        app = new ChessTeamFormation();
    }

    @Test
    @DisplayName("Basic Test Case: One team possible from same ratings")
    void testSingleTeamFormed() {
        List<Integer> rating = List.of(2, 3, 4, 2);
        List<List<Integer>> queries = List.of(
                List.of(0, 3),
                List.of(2, 3)
        );
        List<Integer> expected = List.of(1, 0);
        assertEquals(expected, app.countTeams(rating, queries));
    }

    @Test
    @DisplayName("All Same Ratings: Max number of teams formed")
    void testAllSameRating() {
        List<Integer> rating = List.of(1, 1, 1, 1);
        List<List<Integer>> queries = List.of(
                List.of(0, 3)
        );
        List<Integer> expected = List.of(2);
        assertEquals(expected, app.countTeams(rating, queries));
    }

    @Test
    @DisplayName("All Different Ratings: No teams formed")
    void testAllDifferentRatings() {
        List<Integer> rating = List.of(1, 2, 3, 4);
        List<List<Integer>> queries = List.of(
                List.of(0, 3)
        );
        List<Integer> expected = List.of(0);
        assertEquals(expected, app.countTeams(rating, queries));
    }

    @Test
    @DisplayName("Empty Rating List: No data to form teams")
    void testEmptyRatingList() {
        List<Integer> rating = new ArrayList<>();
        List<List<Integer>> queries = List.of(
                List.of(0, 1)
        );
        List<Integer> expected = List.of();
        assertEquals(expected, app.countTeams(rating, queries));
    }

    @Test
    @DisplayName("Empty Queries List: No queries to process")
    void testEmptyQueriesList() {
        List<Integer> rating = List.of(1, 2, 2, 3);
        List<List<Integer>> queries = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, app.countTeams(rating, queries));
    }

    @Test
    @DisplayName("Out-of-Bounds Query Range: Expect IndexOutOfBoundsException")
    void testQueryOutOfBoundsIndex() {
        List<Integer> rating = List.of(1, 2, 3);
        List<List<Integer>> queries = List.of(
                List.of(0, 5)
        );
        assertThrows(IndexOutOfBoundsException.class, () -> app.countTeams(rating, queries));
    }

    @Test
    @DisplayName("Single Employee in Range: No team can be formed")
    void testQueryWithSingleElement() {
        List<Integer> rating = List.of(5, 6, 5);
        List<List<Integer>> queries = List.of(
                List.of(2, 2)
        );
        List<Integer> expected = List.of(0);
        assertEquals(expected, app.countTeams(rating, queries));
    }

    @Test
    @DisplayName("Multiple Ratings Forming Multiple Teams")
    void testMultiplePairs() {
        List<Integer> rating = List.of(1, 2, 2, 1, 2, 2);
        List<List<Integer>> queries = List.of(
                List.of(0, 5)
        );
        List<Integer> expected = List.of(3);
        assertEquals(expected, app.countTeams(rating, queries));
    }

    @Test
    @DisplayName("Large Input Test: 100k employees with same rating")
    void testLargeInput() {
        List<Integer> rating = new ArrayList<>(Collections.nCopies(100000, 1));
        List<List<Integer>> queries = List.of(
                List.of(0, 99999)
        );
        List<Integer> expected = List.of(50000);
        assertEquals(expected, app.countTeams(rating, queries));
    }

    @Test
    @DisplayName("Alternate Ratings: Evenly paired teams from different groups")
    void testAlternateRatings() {
        List<Integer> rating = List.of(1, 2, 1, 2, 1, 2);
        List<List<Integer>> queries = List.of(
                List.of(0, 5)
        );
        List<Integer> expected = List.of(2);
        assertEquals(expected, app.countTeams(rating, queries));
    }

    @Test
    @DisplayName("Invalid Query Range (l > r): Expect IndexOutOfBoundsException")
    void testQueryWhereLGreaterThanR() {
        List<Integer> rating = List.of(1, 2, 3);
        List<List<Integer>> queries = List.of(
                List.of(3, 2)
        );
        assertThrows(IndexOutOfBoundsException.class, () -> app.countTeams(rating, queries));
    }
}