package com.shortthirdman.primekit.hackerrank;

import com.shortthirdman.primekit.common.Tweet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Design a class Fetch Tweets which stores tweets and retrieves the K closest tweets to the user's location.
 * Visualize the world as a 10 * 10 grid The location of a user is defined using integral x and y co-ordinates.
 * A tweet has attributes id;xCoord and yCoord. The definition of Tweet class is provided in the locked stub
 * Implement the FetchTweets class:<br><br/>
 *
 * <b>Fetch Tweets</b><br><br/>
 *
 * 1. Initialize the FetchTweets class.<br><br/>
 *
 * 2. <code>void storeTweet (int x Coord, int y Coord, int id)</code>: Create and store a tweet object given its
 * id and the (xCoord, ycoord) of the location it was tweeted from.<br><br/>
 *
 * 3. <code>Tweet[] fetchTweets(int xCoordofUser, int Y CoordOfUser, int K)</code>:
 * Given (xCoordOfUser, yCoordOfUser);Fetch the K tweets posted to that point that are closest by location
 * the locked stub in the editor validates the correctness of the FetchTweets class implementation.
 *
 * @author ShortThirdMan
 */
@SuppressWarnings("PMD.UselessParentheses")
public class FetchTweets {

    private final List<Tweet> tweets;

    public FetchTweets() {
        this.tweets = new ArrayList<>();
    }

    public void storeTweet(int xCoord, int yCoord, int id) {
        Tweet tweet = new Tweet(id, xCoord, yCoord);
        tweets.add(tweet);
    }

    public List<Integer> fetchTweets(int xCoordOfUser, int yCoordOfUser, int K) {
        if (K < 1 || K > 20) {
            throw new IllegalArgumentException("K should be in the range [1, 20]");
        }

        if (xCoordOfUser < 1 || yCoordOfUser < 1 || xCoordOfUser > 10 || yCoordOfUser > 10) {
            throw new IllegalArgumentException("Coordinate values for X and Y should be in the range [1, 10]");
        }

        // Priority queue to store tweets sorted by distance
        PriorityQueue<Tweet> pq = new PriorityQueue<>((t1, t2) -> {
            // Calculate squared Euclidean distance to avoid floating-point operations
            int dist1 = (t1.getXCoord() - xCoordOfUser) * (t1.getXCoord() - xCoordOfUser) +
                    (t1.getYCoord() - yCoordOfUser) * (t1.getYCoord() - yCoordOfUser);
            int dist2 = (t2.getXCoord() - xCoordOfUser) * (t2.getXCoord() - xCoordOfUser) +
                    (t2.getYCoord() - yCoordOfUser) * (t2.getYCoord() - yCoordOfUser);
            return Integer.compare(dist1, dist2);
        });

        // Add all tweets to the priority queue
        List<Integer> result = new ArrayList<>();
        if (tweets.isEmpty()) {
            return result;
        }
        pq.addAll(tweets);

        // Fetch the K-closest tweets
        while (K > 0 && !pq.isEmpty()) {
            result.add(pq.poll().getId());
            K--;
        }

        return result;
    }

    public List<Integer> fetchClosestTweets(int xCoordOfUser, int yCoordOfUser, int K) {
        if (K < 1 || K > 20) {
            throw new IllegalArgumentException("K should be in the range [1, 20]");
        }

        if (xCoordOfUser < 1 || yCoordOfUser < 1 || xCoordOfUser > 10 || yCoordOfUser > 10) {
            throw new IllegalArgumentException("Coordinate values for X and Y should be in the range [1, 10]");
        }

        List<Integer> closestTweets = new ArrayList<>();
        tweets.sort(Comparator.comparingInt(t -> (Math.abs(t.getXCoord() - xCoordOfUser) + Math.abs(t.getYCoord() - yCoordOfUser))));

        if (tweets.isEmpty()) {
            return closestTweets;
        }

        for (int i = 0; i < K && i < tweets.size(); i++) {
            closestTweets.add(tweets.get(i).getId());
        }

        return closestTweets;
    }

//    public Tweet[] fetchTweets(int xCoordOfUser, int yCoordOfUser, int K) {
//        // Create a comparator to sort tweets based on distance, X coordinate, Y coordinate, and time of creation
//        Comparator<Tweet> tweetComparator = (t1, t2) -> {
//            int distance1 = (t1.getXCoord() - xCoordOfUser) * (t1.getXCoord() - xCoordOfUser)
//                    + (t1.getYCoord() - yCoordOfUser) * (t1.getYCoord() - yCoordOfUser);
//            int distance2 = (t2.getXCoord() - xCoordOfUser) * (t2.getXCoord() - xCoordOfUser)
//                    + (t2.getYCoord() - yCoordOfUser) * (t2.getYCoord() - yCoordOfUser);
//
//            if (distance1 == distance2) {
//                if (t1.getXCoord() == t2.getXCoord()) {
//                    if (t1.getYCoord() == t2.getYCoord()) {
//                        return t1.getId() - t2.getId(); // Compare by time of creation (ID)
//                    }
//                    return t1.getYCoord() - t2.getYCoord();
//                }
//                return t1.getXCoord() - t2.getXCoord();
//            }
//
//            return distance1 - distance2;
//        };
//
//        // Sort the tweets using the comparator
//        tweets.sort(tweetComparator);
//
//        // Get the first K tweets
//        int size = Math.min(K, tweets.size());
//        Tweet[] result = new Tweet[size];
//        for (int i = 0; i < size; i++) {
//            result[i] = tweets.get(i);
//        }
//
//        return result;
//    }
}
