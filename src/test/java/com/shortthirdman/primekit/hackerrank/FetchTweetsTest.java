package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FetchTweetsTest {

    FetchTweets app;

    @BeforeEach
    void setUp() {
        app = new FetchTweets();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFetchTweetsWithValidK() {
        app.storeTweet(1, 1, 1);
        app.storeTweet(2, 2, 2);
        app.storeTweet(3, 3, 3);

        List<Integer> result = app.fetchTweets(1, 1, 2);
        assertEquals(List.of(1, 2), result);
    }

    @Test
    void testFetchTweetsWithEmptyList() {
        List<Integer> result = app.fetchTweets(1, 1, 2);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFetchTweetsWithInvalidK() {
        app.storeTweet(1, 1, 1);
        app.storeTweet(2, 2, 2);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.fetchTweets(1, 1, 25));
        assertEquals("K should be in the range [1, 20]", exception.getMessage());
    }

    @Test
    void testFetchTweetsWithInvalidCoordinates() {
        app.storeTweet(1, 1, 1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.fetchTweets(11, 11, 1));
        assertEquals("Coordinate values for X and Y should be in the range [1, 10]", exception.getMessage());
    }

    @Test
    void testFetchClosestTweetsWithValidK() {
        app.storeTweet(1, 1, 1);
        app.storeTweet(2, 2, 2);
        app.storeTweet(3, 3, 3);

        List<Integer> result = app.fetchClosestTweets(1, 1, 2);
        assertEquals(List.of(1, 2), result);
    }

    @Test
    void testFetchClosestTweetsWithEmptyList() {
        List<Integer> result = app.fetchClosestTweets(1, 1, 2);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFetchClosestTweetsWithInvalidK() {
        app.storeTweet(1, 1, 1);
        app.storeTweet(2, 2, 2);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.fetchClosestTweets(1, 1, 25));
        assertEquals("K should be in the range [1, 20]", exception.getMessage());
    }

    @Test
    void testFetchClosestTweetsWithInvalidCoordinates() {
        app.storeTweet(1, 1, 1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.fetchClosestTweets(11, 11, 1));
        assertEquals("Coordinate values for X and Y should be in the range [1, 10]", exception.getMessage());
    }

    @Test
    void testFetchClosestTweetsWithManhattanDistance() {
        app.storeTweet(1, 1, 1);
        app.storeTweet(2, 3, 2);
        app.storeTweet(4, 6, 3);

        List<Integer> result = app.fetchClosestTweets(1, 1, 2);
        assertEquals(List.of(1, 2), result);
    }
}