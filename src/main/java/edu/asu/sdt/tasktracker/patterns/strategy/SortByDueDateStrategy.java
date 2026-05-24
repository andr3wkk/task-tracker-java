package edu.asu.sdt.tasktracker.patterns.strategy;

import edu.asu.sdt.tasktracker.model.Task;
import java.util.Comparator;

/**
 * Sorts tasks by due date.
 */
public class SortByDueDateStrategy implements TaskSortStrategy {
    @Override
    public Comparator<Task> comparator() {
        return Comparator.comparing(Task::getDueDate);
    }
}
