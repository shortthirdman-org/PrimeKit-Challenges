package com.shortthirdman.primekit.coderbyte;

import com.shortthirdman.primekit.common.Post;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PostAnalyticsClientMockitoTest {

    @Test
    void testFetchPosts_UsingMockito_NoHttpCall() throws Exception {

        // Prepare fake JSON response
        String json = """
            [
              {"userId":1,"id":101},
              {"userId":2,"id":201}
            ]
        """;

        HttpClient mockClient = mock(HttpClient.class);

        @SuppressWarnings("unchecked")
        HttpResponse<String> mockResponse = (HttpResponse<String>) mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(json);
        when(mockClient.send(
                any(HttpRequest.class),
                ArgumentMatchers.<HttpResponse.BodyHandler<String>>any()
        )).thenReturn(mockResponse);

        // Intercept static factory `HttpClient.newHttpClient()`
        try (MockedStatic<HttpClient> mockedStatic = mockStatic(HttpClient.class)) {
            mockedStatic.when(HttpClient::newHttpClient).thenReturn(mockClient);

            var posts = PostAnalyticsClient.fetchPosts();

            assertNotNull(posts);
            assertEquals(2, posts.size());

            mockedStatic.verify(HttpClient::newHttpClient);
            verify(mockClient, times(1))
                    .send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        }
    }

    @Test
    void testFetchAndFormat_UsingMockito_StaticMocking() {

        List<Post> mockPosts = List.of(
                new Post(1, 101, "abc", "ABC01"),
                new Post(1, 102, "pqr", "PQR02"),
                new Post(2, 201, "xyz", "XYZ01")
        );

        try (MockedStatic<PostAnalyticsClient> mocked = mockStatic(PostAnalyticsClient.class)) {

            mocked.when(PostAnalyticsClient::fetchPosts).thenReturn(mockPosts);

            // IMPORTANT: allow real implementation of formatPosts + fetchAndFormat
            mocked.when(PostAnalyticsClient::fetchAndFormat)
                    .thenCallRealMethod();
            mocked.when(() -> PostAnalyticsClient.formatPosts(mockPosts))
                    .thenCallRealMethod();

            var result = PostAnalyticsClient.fetchAndFormat();

            assertNotNull(result);
            assertEquals(3, result.size());

            assertEquals(201, result.getFirst().get("userId"));
            assertEquals(1, result.getFirst().get("numberOfPosts"));

            mocked.verify(PostAnalyticsClient::fetchPosts);
        }
    }
}
