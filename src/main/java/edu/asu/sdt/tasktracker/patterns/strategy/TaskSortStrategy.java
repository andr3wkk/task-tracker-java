package edu.asu.sdt.tasktracker.patterns.strategy;

import edu.asu.sdt.tasktracker.model.Task;
import java.util.Comparator;

/**
 * Strategy interface for task sorting rules.
 */
public interface TaskSortStrategy {
    Comparator<Task> comparator();
}
