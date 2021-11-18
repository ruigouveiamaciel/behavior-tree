package dev.smokewow.behavior.leafs;

import dev.smokewow.behavior.Node;

/**
 * Evaluates a condition and succeeds if the condition is true, and fails if the condition is false.
 * Never returns the RUNNING state.
 */
public abstract class Condition extends Node {
    /**
     * Evaluates the condition.
     *
     * @return The condition evaluation.
     */
    protected abstract boolean evaluate();

    public final void reset() {}

    @Override
    public final State tick() {
        return evaluate() ? State.SUCCEEDED : State.FAILED;
    }
}
