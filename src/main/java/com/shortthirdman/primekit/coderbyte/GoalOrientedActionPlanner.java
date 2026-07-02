package com.shortthirdman.primekit.coderbyte;

import com.shortthirdman.primekit.core.agents.Action;
import com.shortthirdman.primekit.core.agents.Plan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * A planner that uses the A* search algorithm to find an optimal sequence of actions
 * (a Plan) to transition from an initial state to a desired goal state.
 * @author shortthirdman
 * @since 1.2.0
 */
public class GoalOrientedActionPlanner {

    /**
     * Calculates the optimal plan to reach the goal state from the initial state
     * using the provided set of available actions.
     * <p>
     * This method implements the A* search algorithm, exploring states based on
     * accumulated cost (g-score) and a heuristic estimating the remaining distance
     * to the goal (f-score).
     *
     * @param initialState     the starting state map containing key-value condition pairs
     * @param goal             the target state map containing required key-value condition pairs
     * @param availableActions the list of actions the planner can utilize
     * @return an {@link Optional} containing the optimal {@link Plan} if a valid sequence
     * is found, or an empty {@link Optional} if the goal is unreachable
     */
    public Optional<Plan> plan(
            Map<String, Object> initialState,
            Map<String, Object> goal,
            List<Action> availableActions) {
        var open = new PriorityQueue<>(Comparator.comparingDouble(Node::f));
        var closed = new HashSet<Map<String, Object>>();

        open.add(new Node(initialState, List.of(), 0, heuristic(initialState, goal)));

        while (!open.isEmpty()) {
            Node current = open.poll();

            if (satisfies(current.state(), goal)) {
                return Optional.of(new Plan(current.actions(), current.g()));
            }

            if (!closed.add(current.state())) continue;

            for (Action action : availableActions) {
                if (!canApply(action, current.state())) continue;

                Map<String, Object> next = apply(action, current.state());
                List<Action> chain = new ArrayList<>(current.actions());
                chain.add(action);

                double g = current.g() + action.cost();
                double f = g + heuristic(next, goal);

                open.add(new Node(next, chain, g, f));
            }
        }

        return Optional.empty();
    }

    /**
     * Evaluates whether an action can be performed in the given state by verifying
     * that all preconditions of the action are met.
     *
     * @param action the action to evaluate
     * @param state  the current state to check against
     * @return {@code true} if all preconditions are satisfied, {@code false} otherwise
     */
    private boolean canApply(Action action, Map<String, Object> state) {
        return action.preconditions().entrySet().stream()
                .allMatch(e -> Objects.equals(state.get(e.getKey()), e.getValue()));
    }

    /**
     * Applies the effects of an action to a given state, generating a new resulting state.
     * <p>
     * This method does not mutate the original state; instead, it returns a newly allocated
     * map containing the merged state and effects.
     *
     * @param action the action whose effects are to be applied
     * @param state  the initial state before the action is executed
     * @return a new state map reflecting the applied effects
     */
    private Map<String, Object> apply(Action action, Map<String, Object> state) {
        Map<String, Object> result = new HashMap<>(state);
        result.putAll(action.effects());
        return result;
    }

    /**
     * Checks if the given state satisfies all conditions defined in the goal.
     *
     * @param state the current state to evaluate
     * @param goal  the target conditions that must be met
     * @return {@code true} if the state contains all goal conditions with matching values,
     * {@code false} otherwise
     */
    private boolean satisfies(Map<String, Object> state, Map<String, Object> goal) {
        return goal.entrySet().stream()
                .allMatch(e -> Objects.equals(state.get(e.getKey()), e.getValue()));
    }

    /**
     * Calculates a heuristic value estimating the cost to reach the goal from the current state.
     * <p>
     * The current implementation uses the number of unsatisfied goal conditions as the heuristic.
     * This acts as an admissible heuristic assuming each action costs at least 1.0 and satisfies
     * at most one goal condition.
     *
     * @param state the current state
     * @param goal  the target state
     * @return the count of goal conditions that differ from the current state
     */
    private double heuristic(Map<String, Object> state, Map<String, Object> goal) {
        return goal.entrySet().stream()
                .filter(e -> !Objects.equals(state.get(e.getKey()), e.getValue()))
                .count();
    }

    /**
     * Represents a discrete node in the A* search tree.
     *
     * @param state   the current world state at this node
     * @param actions the sequence of actions taken to reach this state
     * @param g       the exact cumulative cost from the initial state to this node (g-score)
     * @param f       the estimated total cost from the initial state to the goal through this node (f-score = g + heuristic)
     */
    private record Node(
            Map<String, Object> state,
            List<Action> actions,
            double g,
            double f
    ) {}
}
