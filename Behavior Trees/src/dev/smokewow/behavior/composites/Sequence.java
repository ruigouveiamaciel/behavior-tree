package dev.smokewow.behavior.composites;

import dev.smokewow.behavior.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ticks children in order until one fails or all have been ticked.
 * Succeeds if all child succeed.
 */
public final class Sequence extends Node {
    private final List<Node> children;
    /**
     * The child we need to tick next.
     */
    private int nextChild = 0;

    public Sequence(List<Node> children) {
        this.children = Collections.unmodifiableList(new ArrayList<>(children.size()));
    }

    @Override
    public void reset() {
        // Reset all the children that have been ticked
        for (int i = 0; i < nextChild; i++) {
            children.get(i).reset();
        }

        nextChild = 0;
    }

    @Override
    public State tick() {
        if (children.size() == 0) return State.FAILED;

        State state = children.get(nextChild).tick();
        nextChild++;

        if (state == State.FAILED || (state == State.SUCCEEDED && nextChild == children.size())) {
            return state;
        }

        return State.RUNNING;
    }
}
