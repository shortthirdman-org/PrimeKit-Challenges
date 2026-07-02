package com.shortthirdman.primekit.core.agents;

import java.util.Map;

/**
 * Represents an action that can be executed to transition between states.
 *
 * @param name          the unique identifier or descriptive name of the action
 * @param preconditions the state requirements that must be met before this action can be executed
 * @param effects       the state changes resulting from the execution of this action
 * @param cost          the numerical cost associated with performing this action
 */
public record Action(String name,
                     Map<String, Object> preconditions,
                     Map<String, Object> effects,
                     double cost) {
}
