package edu.asu.sdt.tasktracker.service;

import edu.asu.sdt.tasktracker.model.Task;
import edu.asu.sdt.tasktracker.model.TaskStatus;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculates simple task statistics.
 */
public class StatisticsService {

    public long countCompleted(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.DONE)
                .count();
    }

    public long countOverdue(List<Task> tasks, LocalDate today) {
        return tasks.stream()
                .filter(task -> task.getStatus() != TaskStatus.DONE)
                .filter(task -> task.getDueDate().isBefore(today))
                .count();
    }

    public Map<String, Long> countByCategory(List<Task> tasks) {
        Map<String, Long> counts = new HashMap<>();
        for (Task task : tasks) {
            counts.put(task.getCategory(), counts.getOrDefault(task.getCategory(), 0L) + 1);
        }
        return counts;
    }
}
