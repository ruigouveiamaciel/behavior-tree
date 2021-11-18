package dev.smokewow.behavior.leafs;

import dev.smokewow.behavior.Node;

/**
 * Executes a task that may take one or more ticks to complete.
 */
public abstract class Task extends Node {
    /**
     * Whether the task is running or not.
     * This variable is only necessary if isRunning() or isSuccessful() is used.
     */
    protected boolean executing = false;

    /**
     * Executes the tasks.
     *
     * Example: Open the bank interface.
     *
     * @return False if the task failed to execute, true otherwise.
     */
    protected abstract boolean execute();

    /**
     * Check if task is still running.
     *
     * Example: We're still opening the bank if the character is moving or
     * the mouse is moving.
     *
     * The use of this method is completely optional, you may place all your
     * logic inside execute() if you wish so.
     *
     * @return Whether the task is still running.
     */
    protected boolean isRunning() {
        return false;
    }

    /**
     * Check if task was successful.
     *
     * Example: The bank interface is open.
     *
     * The use of this method is completely optional, you may place all your
     * logic inside execute() if you wish so.
     *
     * @return Whether the task was successful.
     */
    protected boolean isSuccessful() {
        return true;
    }

    @Override
    public final void reset() {
        executing = false;
    }

    @Override
    public final State tick() {
        // If we haven't executed yet, execute.
        if (!executing) {
            executing = true;
            boolean succeededExecution = execute();

            if (!succeededExecution) {
                return State.FAILED;
            }
        }

        // If we're running, return the RUNNING state.
        if (isRunning()) return State.RUNNING;

        // Return success or failure.
        return isSuccessful() ? State.SUCCEEDED : State.FAILED;
    }
}