package com.shortthirdman.primekit.coderbyte;

import com.shortthirdman.primekit.common.Post;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostAnalyticsClientTest {

    static List<Post> posts;

    @Test
    @Order(1)
    void fetchPosts() {
        posts = PostAnalyticsClient.fetchPosts();
        assertNotNull(posts);
        assertFalse(posts.isEmpty());
    }

    @Test
    @Order(2)
    void formatPosts() {
        assertNotNull(posts);
        var result = PostAnalyticsClient.formatPosts(posts);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}