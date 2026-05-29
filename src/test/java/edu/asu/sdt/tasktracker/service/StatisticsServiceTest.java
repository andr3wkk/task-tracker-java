package edu.asu.sdt.tasktracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.asu.sdt.tasktracker.model.Priority;
import edu.asu.sdt.tasktracker.model.Task;
import edu.asu.sdt.tasktracker.model.TaskStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class StatisticsServiceTest {
    private final StatisticsService statisticsService = new StatisticsService();

    @Test
    void countCompletedCountsOnlyDoneTasks() {
        // Black-box test: verifies completed task statistics.
        List<Task> tasks = List.of(
                task("Finished", "School", TaskStatus.DONE, LocalDate.of(2026, 6, 1)),
                task("Unfinished", "School", TaskStatus.TODO, LocalDate.of(2026, 6, 1))
        );

        assertEquals(1, statisticsService.countCompleted(tasks));
    }

    @Test
    void countOverdueIgnoresCompletedTasks() {
        // White-box test: covers overdue branch and DONE exclusion branch.
        LocalDate today = LocalDate.of(2026, 6, 10);
        List<Task> tasks = List.of(
                task("Overdue", "School", TaskStatus.TODO, LocalDate.of(2026, 6, 1)),
                task("Done overdue", "School", TaskStatus.DONE, LocalDate.of(2026, 6, 1)),
                task("Future", "Health", TaskStatus.TODO, LocalDate.of(2026, 6, 20))
        );

        assertEquals(1, statisticsService.countOverdue(tasks, today));
    }

    @Test
    void countByCategoryGroupsTasksByCategory() {
        // Black-box test: verifies category summary behavior.
        List<Task> tasks = List.of(
                task("Study", "School", TaskStatus.TODO, LocalDate.of(2026, 6, 1)),
                task("Project", "School", TaskStatus.TODO, LocalDate.of(2026, 6, 2)),
                task("Gym", "Health", TaskStatus.TODO, LocalDate.of(2026, 6, 3))
        );

        Map<String, Long> counts = statisticsService.countByCategory(tasks);

        assertEquals(2, counts.get("School"));
        assertEquals(1, counts.get("Health"));
    }

    private Task task(String title, String category, TaskStatus status, LocalDate dueDate) {
        return Task.builder()
                .title(title)
                .description("Sample")
                .priority(Priority.MEDIUM)
                .category(category)
                .dueDate(dueDate)
                .status(status)
                .build();
    }
}