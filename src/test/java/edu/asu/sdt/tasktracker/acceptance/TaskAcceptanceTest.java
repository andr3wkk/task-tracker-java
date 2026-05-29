package edu.asu.sdt.tasktracker.acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.asu.sdt.tasktracker.model.Priority;
import edu.asu.sdt.tasktracker.model.Task;
import edu.asu.sdt.tasktracker.model.TaskStatus;
import edu.asu.sdt.tasktracker.patterns.strategy.SortByDueDateStrategy;
import edu.asu.sdt.tasktracker.service.TaskService;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class TaskAcceptanceTest {

    @Test
    void givenNewTaskWhenCreatedThenItAppearsInTaskList() {
        // Given a user has an empty task tracker
        TaskService service = new TaskService();

        // When the user creates a new task
        service.createTask(Task.builder()
                .title("Submit project")
                .description("Upload repository link")
                .priority(Priority.HIGH)
                .category("School")
                .dueDate(LocalDate.now().plusDays(2))
                .build());

        // Then the task appears in the task list
        assertEquals(1, service.getAllTasks().size());
    }

    @Test
    void givenExistingTaskWhenUpdatedThenTaskContainsNewValues() {
        // Given a user has an existing task
        TaskService service = new TaskService();
        Task created = service.createTask(Task.builder()
                .title("Draft report")
                .description("Write first version")
                .priority(Priority.MEDIUM)
                .category("School")
                .dueDate(LocalDate.of(2026, 6, 1))
                .build());

        // When the user updates the task
        Task updated = Task.builder()
                .title("Final report")
                .description("Submit polished version")
                .priority(Priority.HIGH)
                .category("School")
                .dueDate(LocalDate.of(2026, 6, 5))
                .status(TaskStatus.IN_PROGRESS)
                .build();
        service.updateTask(created.getId(), updated);

        // Then the task contains the new values
        assertEquals("Final report", service.getAllTasks().get(0).getTitle());
        assertEquals(TaskStatus.IN_PROGRESS, service.getAllTasks().get(0).getStatus());
    }

    @Test
    void givenMultipleTasksWhenSortedByDueDateThenEarliestTaskComesFirst() {
        // Given a user has multiple tasks with different due dates
        TaskService service = new TaskService();
        service.createTask(task("Later task", LocalDate.of(2026, 6, 10)));
        service.createTask(task("Earlier task", LocalDate.of(2026, 5, 30)));

        // When the user sorts by due date
        String firstTitle = service.sort(new SortByDueDateStrategy()).get(0).getTitle();

        // Then the earliest task comes first
        assertEquals("Earlier task", firstTitle);
    }

    private Task task(String title, LocalDate dueDate) {
        return Task.builder()
                .title(title)
                .description("Acceptance test task")
                .priority(Priority.MEDIUM)
                .category("School")
                .dueDate(dueDate)
                .build();
    }
}