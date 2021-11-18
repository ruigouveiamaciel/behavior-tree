package dev.smokewow.behavior;

public abstract class Node {
    /**
     * Represents the current state of the node.
     */
    public enum State {
        /**
         * The node is running.
         */
        RUNNING,
        /**
         * The node succeeded.
         */
        SUCCEEDED,
        /**
         * The node failed.
         */
        FAILED
    }

    /**
     * Resets the state of the node.
     */
    public abstract void reset();

    /**
     * Ticks the node and returns its new state.
     *
     * @return The new state.
     */
    public abstract State tick();
}
