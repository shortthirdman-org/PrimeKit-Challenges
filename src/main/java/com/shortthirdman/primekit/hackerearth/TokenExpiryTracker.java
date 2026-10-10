package com.shortthirdman.primekit.hackerearth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Manages the lifecycle, renewal, and state evaluation of authentication tokens.
 * <p>
 * This class tracks token creation and reset commands over discrete time units,
 * enforcing global expiration limits and determining token availability at the
 * conclusion of event processing.
 * </p>
 *
 * @author ShortThirdMan
 * @since 1.2.1
 */
public class TokenExpiryTracker {

    private static final int CMD_TYPE_INDEX = 0;
    private static final int CMD_TOKEN_ID_INDEX = 1;
    private static final int CMD_TIME_INDEX = 2;

    private static final int ACTION_CREATE = 0;
    private static final int ACTION_RESET = 1;

    /**
     * Calculates the number of tokens that remain active after executing all specified commands.
     * <p>
     * A token is active immediately upon creation at time {@code T} and expires at {@code T + expiryLimit}.
     * Subsequent reset commands at time {@code T} are valid only if the token already exists and has not yet
     * expired (i.e., its current expiration time is greater than or equal to {@code T}).
     * </p>
     * <p>
     * After processing all commands, a token is considered active if its expiration time is strictly
     * greater than the timestamp of the last executed command.
     * </p>
     *
     * @param expiryLimit the fixed lifespan added to the execution timestamp upon token creation or reset;
     *                    must be greater than or equal to 1
     * @param commands    a chronological list of 3-element integer tuples representing operations:
     *                    <ul>
     *                      <li>{@code [0, tokenId, T]} - Creates a new token with ID {@code tokenId} at time {@code T}.</li>
     *                      <li>{@code [1, tokenId, T]} - Resets the expiration of an existing token with ID {@code tokenId} at time {@code T}.</li>
     *                    </ul>
     *                    Must be sorted in non-decreasing order of {@code T}.
     * @return the total count of active tokens remaining after all commands have been evaluated;
     *         returns {@code 0} if {@code commands} is empty
     * @throws NullPointerException     if {@code commands} is {@code null} or contains {@code null} entries
     * @throws IllegalArgumentException if {@code expiryLimit} is non-positive or if any command does not
     *                                  contain exactly three elements
     */
    public int numberOfTokens(int expiryLimit, List<List<Integer>> commands) {
        Objects.requireNonNull(commands, "commands list must not be null");
        if (expiryLimit <= 0) {
            throw new IllegalArgumentException("expiryLimit must be positive: " + expiryLimit);
        }
        if (commands.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> tokenExpiries = new HashMap<>();

        for (List<Integer> cmd : commands) {
            Objects.requireNonNull(cmd, "command entry must not be null");
            if (cmd.size() != 3) {
                throw new IllegalArgumentException("Command must contain exactly 3 integers: [type, tokenId, T]");
            }

            int type = cmd.get(CMD_TYPE_INDEX);
            int tokenId = cmd.get(CMD_TOKEN_ID_INDEX);
            int timestamp = cmd.get(CMD_TIME_INDEX);

            if (type == ACTION_CREATE) {
                tokenExpiries.put(tokenId, timestamp + expiryLimit);
            } else if (type == ACTION_RESET) {
                Integer currentExpiry = tokenExpiries.get(tokenId);
                if (currentExpiry != null && currentExpiry >= timestamp) {
                    tokenExpiries.put(tokenId, timestamp + expiryLimit);
                }
            }
        }

        int finalTimestamp = commands.getLast().get(CMD_TIME_INDEX);

        return (int) tokenExpiries.values().stream()
                .filter(expiry -> expiry > finalTimestamp)
                .count();
    }
}
