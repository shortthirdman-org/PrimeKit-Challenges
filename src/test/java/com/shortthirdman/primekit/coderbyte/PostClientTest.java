package com.shortthirdman.primekit.coderbyte;

import com.shortthirdman.primekit.common.Post;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostClientTest {

    private List<Post> posts;

    @BeforeAll
    void setup() {
        posts = PostAnalyticsClient.fetchPosts();
    }

    @Test
    void testFetchPosts() {
        assertNotNull(posts);
        assertFalse(posts.isEmpty());
    }

    @Test
    void testFormatPosts() {
        var result = PostAnalyticsClient.formatPosts(posts);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
