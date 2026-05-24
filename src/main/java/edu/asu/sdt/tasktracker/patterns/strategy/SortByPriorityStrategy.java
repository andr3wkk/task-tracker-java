package edu.asu.sdt.tasktracker.patterns.strategy;

import edu.asu.sdt.tasktracker.model.Task;
import java.util.Comparator;

/**
 * Sorts tasks by priority, with high priority first.
 */
public class SortByPriorityStrategy implements TaskSortStrategy {
    @Override
    public Comparator<Task> comparator() {
        return Comparator.comparing(Task::getPriority).reversed();
    }
}
