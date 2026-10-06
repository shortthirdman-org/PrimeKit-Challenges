package com.shortthirdman.primekit.hackerearth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TokenExpiryTracker Tests")
class TokenExpiryTrackerTest {

    TokenExpiryTracker app;

    @BeforeEach
    void setUp() {
        app = new TokenExpiryTracker();
    }

    @Nested
    @DisplayName("Happy Path & Standard Scenarios")
    class StandardExecutionTests {

        @Test
        @DisplayName("Should return correct active count for standard problem example")
        void testProblemExampleScenario() {
            int expiryLimit = 4;
            List<List<Integer>> commands = List.of(
                    List.of(0, 1, 1), // Create 1 at T=1 -> exp: 5
                    List.of(0, 2, 2), // Create 2 at T=2 -> exp: 6
                    List.of(1, 1, 5), // Reset 1 at T=5 -> exp: 9
                    List.of(1, 2, 7)  // Reset 2 at T=7 -> ignored (expired at 6)
            );

            int result = app.numberOfTokens(expiryLimit, commands);

            assertEquals(1, result, "Only token 1 should remain active at T=7 (expires at 9 > 7)");
        }

        @Test
        @DisplayName("Should keep all tokens active when expiry exceeds maximum timestamp")
        void testAllTokensRemainActive() {
            int expiryLimit = 10;
            List<List<Integer>> commands = List.of(
                    List.of(0, 101, 1), // exp: 11
                    List.of(0, 102, 2), // exp: 12
                    List.of(0, 103, 3)  // exp: 13
            );

            int result = app.numberOfTokens(expiryLimit, commands);

            assertEquals(3, result, "All tokens expire after T=3 and must be active");
        }

        @Test
        @DisplayName("Should report zero active tokens when all tokens expire before or at final timestamp")
        void testAllTokensExpired() {
            int expiryLimit = 2;
            List<List<Integer>> commands = List.of(
                    List.of(0, 1, 1),  // exp: 3
                    List.of(0, 2, 2),  // exp: 4
                    List.of(1, 999, 10) // ignored reset on non-existent token at T=10
            );

            int result = app.numberOfTokens(expiryLimit, commands);

            assertEquals(0, result, "Tokens 1 and 2 expired prior to final timestamp T=10");
        }

        @Test
        @DisplayName("Should support multiple resets extending token lifespan repeatedly")
        void testMultipleSuccessfulResetsOnSameToken() {
            int expiryLimit = 5;
            List<List<Integer>> commands = List.of(
                    List.of(0, 42, 1),  // exp: 6
                    List.of(1, 42, 4),  // reset -> exp: 9
                    List.of(1, 42, 8),  // reset -> exp: 13
                    List.of(1, 42, 12)  // reset -> exp: 17
            );

            int result = app.numberOfTokens(expiryLimit, commands);

            assertEquals(1, result, "Token 42 should remain active up to T=17");
        }
    }

    @Nested
    @DisplayName("Boundary & Edge Cases")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should return 0 when commands list is empty")
        void testEmptyCommandsList() {
            int result = app.numberOfTokens(5, Collections.emptyList());

            assertEquals(0, result, "Empty commands list must produce 0 active tokens");
        }

        @Test
        @DisplayName("Should keep token active for a single create command")
        void testSingleCreateCommand() {
            List<List<Integer>> commands = List.of(List.of(0, 1, 10));

            int result = app.numberOfTokens(3, commands);

            assertEquals(1, result, "Token created at T=10 with expiry 13 is active at T=10");
        }

        @Test
        @DisplayName("Should consider token expired when expiration exactly equals final timestamp")
        void testTokenExpiresExactlyAtFinalTimestamp() {
            int expiryLimit = 5;
            List<List<Integer>> commands = List.of(
                    List.of(0, 1, 1),  // exp: 6
                    List.of(1, 99, 6)  // non-existent token reset at T=6
            );

            int result = app.numberOfTokens(expiryLimit, commands);

            assertEquals(0, result, "Token expires at 6, which is not strictly greater than max time 6");
        }

        @Test
        @DisplayName("Should allow token reset exactly on its expiration timestamp")
        void testTokenResetExactlyOnBoundaryTimestamp() {
            int expiryLimit = 4;
            List<List<Integer>> commands = List.of(
                    List.of(0, 1, 2),  // exp: 6
                    List.of(1, 1, 6)   // reset exactly at T=6 -> exp: 10
            );

            int result = app.numberOfTokens(expiryLimit, commands);

            assertEquals(1, result, "Reset on expiration timestamp is valid, new expiry becomes 10 > 6");
        }

        @Test
        @DisplayName("Should overwrite token expiration when recreation occurs with same token ID")
        void testTokenRecreationOverwritesPriorState() {
            int expiryLimit = 3;
            List<List<Integer>> commands = List.of(
                    List.of(0, 1, 1),  // exp: 4
                    List.of(0, 1, 10)  // re-created at T=10 -> exp: 13
            );

            int result = app.numberOfTokens(expiryLimit, commands);

            assertEquals(1, result, "Re-creating token 1 updates its lifetime to 13 > 10");
        }

        @Test
        @DisplayName("Should handle multiple commands occurring at the exact same timestamp")
        void testConcurrentTimestamps() {
            int expiryLimit = 3;
            List<List<Integer>> commands = List.of(
                    List.of(0, 1, 5),  // exp: 8
                    List.of(0, 2, 5),  // exp: 8
                    List.of(1, 1, 5)   // reset at T=5 -> exp: 8
            );

            int result = app.numberOfTokens(expiryLimit, commands);

            assertEquals(2, result, "Both tokens active at final timestamp T=5 with exp=8");
        }

        @Test
        @DisplayName("Should ignore reset commands for tokens that never existed")
        void testResetNonExistentTokenIgnored() {
            int expiryLimit = 5;
            List<List<Integer>> commands = List.of(
                    List.of(1, 555, 2),
                    List.of(1, 666, 4)
            );

            int result = app.numberOfTokens(expiryLimit, commands);

            assertEquals(0, result, "Non-existent tokens must not be created or counted");
        }

        @Test
        @DisplayName("Should ignore reset attempt strictly after expiration has passed")
        void testResetAfterExpirationIgnored() {
            int expiryLimit = 2;
            List<List<Integer>> commands = List.of(
                    List.of(0, 1, 1),  // exp: 3
                    List.of(1, 1, 4)   // ignored: T=4 is strictly greater than current exp=3
            );

            int result = app.numberOfTokens(expiryLimit, commands);

            assertEquals(0, result, "Expired token cannot be renewed and remains inactive");
        }
    }

    @Nested
    @DisplayName("Negative Scenarios & Exception Handling")
    class ExceptionTests {

        @Test
        @DisplayName("Should throw NullPointerException when commands list is null")
        void testNullCommandsListThrowsException() {
            assertThrows(NullPointerException.class, () ->
                    app.numberOfTokens(5, null)
            );
        }

        @Test
        @DisplayName("Should throw NullPointerException when a command row is null")
        void testNullCommandEntryThrowsException() {
            List<List<Integer>> commands = new ArrayList<>();
            commands.add(List.of(0, 1, 1));
            commands.add(null);

            assertThrows(NullPointerException.class, () ->
                    app.numberOfTokens(5, commands)
            );
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when expiryLimit is zero")
        void testZeroExpiryLimitThrowsException() {
            List<List<Integer>> commands = List.of(List.of(0, 1, 1));

            assertThrows(IllegalArgumentException.class, () ->
                    app.numberOfTokens(0, commands)
            );
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when expiryLimit is negative")
        void testNegativeExpiryLimitThrowsException() {
            List<List<Integer>> commands = List.of(List.of(0, 1, 1));

            assertThrows(IllegalArgumentException.class, () ->
                    app.numberOfTokens(-5, commands)
            );
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when command tuple has fewer than 3 elements")
        void testCommandTupleUnderflowThrowsException() {
            List<List<Integer>> commands = List.of(
                    List.of(0, 1) // Missing timestamp
            );

            assertThrows(IllegalArgumentException.class, () ->
                    app.numberOfTokens(5, commands)
            );
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when command tuple has more than 3 elements")
        void testCommandTupleOverflowThrowsException() {
            List<List<Integer>> commands = List.of(
                    List.of(0, 1, 2, 999) // Extra element
            );

            assertThrows(IllegalArgumentException.class, () ->
                    app.numberOfTokens(5, commands)
            );
        }
    }
}