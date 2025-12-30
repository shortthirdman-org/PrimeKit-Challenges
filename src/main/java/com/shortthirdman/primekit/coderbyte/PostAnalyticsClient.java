package com.shortthirdman.primekit.coderbyte;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.shortthirdman.primekit.common.Post;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PostAnalyticsClient {

    private static final String ALL_POSTS = "https://coderbyte.com/api/challenges/json/all-posts";

    /**
     * Retrieve a list of posts.
     * @return list of {@link Post}
     */
    public static List<Post> fetchPosts() {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ALL_POSTS))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();

            // Response is a JSON ARRAY
            JsonArray jsonArray = gson.fromJson(response.body(), JsonArray.class);

            return gson.fromJson(
                    jsonArray,
                    new TypeToken<List<Post>>() {}.getType()
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch posts", e);
        }
    }

    /**
     * Summary of how many posts each user has made
     * @param posts the input list of posts
     * @return a list of objects each containing only the keys userId and number of {@link Post}s with userId appearing first.
     */
    public static List<Map<String, Integer>> formatPosts(List<Post> posts) {
        if (posts == null || posts.isEmpty()) {
            throw new IllegalArgumentException("No posts provided to format");
        }

        return posts.stream()
                .collect(Collectors.groupingBy(
                        Post::userId,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ))
                .entrySet()
                .stream()
                .sorted((a, b) -> {
                    int cmp = Integer.compare(b.getValue(), a.getValue());
                    return (cmp != 0) ? cmp : Integer.compare(b.getKey(), a.getKey());
                })
                .map(entry -> {
                    Map<String, Integer> map = new LinkedHashMap<>();
                    map.put("userId", entry.getKey());
                    map.put("numberOfPosts", entry.getValue());
                    return map;
                })
                .toList();
    }

    public static List<Map<String, Integer>> fetchAndFormat() {
        List<Post> posts = fetchPosts();
        return formatPosts(posts);
    }
}
