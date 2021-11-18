package dev.smokewow.behavior.decorators;

import dev.smokewow.behavior.Node;

public final class Inverter extends Node {
    private final Node child;

    public Inverter(Node child) {
        this.child = child;
    }

    @Override
    public void reset() {
        child.reset();
    }

    @Override
    public State tick() {
        State childState = child.tick();
        if (childState == State.RUNNING) return State.RUNNING;
        return childState == State.SUCCEEDED ? State.FAILED : State.SUCCEEDED;
    }
}
