package edu.asu.sdt.tasktracker.exception;

/**
 * Thrown when a requested task ID does not exist.
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(int id) {
        super("Task with ID " + id + " was not found.");
    }
}
