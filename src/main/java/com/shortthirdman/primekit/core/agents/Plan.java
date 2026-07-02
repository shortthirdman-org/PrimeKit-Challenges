package com.shortthirdman.primekit.core.agents;

import java.util.List;

/**
 * Represents a calculated sequence of actions to achieve a specific goal,
 * along with the total accumulated cost.
 *
 * @param actions   the ordered list of actions to be executed
 * @param totalCost the sum of the costs of all actions in the plan
 */
public record Plan(List<Action> actions, double totalCost) {
}
