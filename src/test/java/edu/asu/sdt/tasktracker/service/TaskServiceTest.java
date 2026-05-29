package edu.asu.sdt.tasktracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.asu.sdt.tasktracker.exception.TaskNotFoundException;
import edu.asu.sdt.tasktracker.model.Priority;
import edu.asu.sdt.tasktracker.model.Task;
import edu.asu.sdt.tasktracker.model.TaskStatus;
import edu.asu.sdt.tasktracker.patterns.strategy.SortByDueDateStrategy;
import edu.asu.sdt.tasktracker.patterns.strategy.SortByPriorityStrategy;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class TaskServiceTest {

    @Test
    void createTaskAssignsId() {
        // Black-box test: verifies the create task feature from expected behavior.
        TaskService service = new TaskService();

        Task task = service.createTask(sampleTask("Study", Priority.MEDIUM));

        assertEquals(1, task.getId());
        assertEquals(1, service.getAllTasks().size());
    }

    @Test
    void updateTaskChangesExistingTaskFields() {
        // Black-box test: verifies update by ID.
        TaskService service = new TaskService();
        Task original = service.createTask(sampleTask("Study", Priority.HIGH));

        Task updated = Task.builder()
                .title("Study Java")
                .description("Practice unit tests")
                .priority(Priority.MEDIUM)
                .category("School")
                .dueDate(LocalDate.of(2026, 6, 10))
                .status(TaskStatus.IN_PROGRESS)
                .build();

        Task result = service.updateTask(original.getId(), updated);

        assertEquals("Study Java", result.getTitle());
        assertEquals(Priority.MEDIUM, result.getPriority());
        assertEquals(TaskStatus.IN_PROGRESS, result.getStatus());
    }

    @Test
    void deleteTaskRemovesExistingTask() {
        // Black-box test: verifies delete by ID.
        TaskService service = new TaskService();
        Task task = service.createTask(sampleTask("Delete me", Priority.LOW));

        service.deleteTask(task.getId());

        assertEquals(0, service.getAllTasks().size());
    }

    @Test
    void searchFindsMatchingTaskAcrossTextFields() {
        // Black-box test: search should match title, description, or category.
        TaskService service = new TaskService();
        service.createTask(sampleTask("Study Java", Priority.HIGH));
        service.createTask(sampleTask("Buy groceries", Priority.LOW));

        assertEquals(1, service.search("java").size());
    }

    @Test
    void filterByPriorityReturnsOnlyMatchingTasks() {
        // Black-box test: verifies priority filter.
        TaskService service = new TaskService();
        service.createTask(sampleTask("Important", Priority.HIGH));
        service.createTask(sampleTask("Later", Priority.LOW));

        List<Task> results = service.filterByPriority(Priority.HIGH);

        assertEquals(1, results.size());
        assertEquals("Important", results.get(0).getTitle());
    }

    @Test
    void filterByStatusReturnsOnlyMatchingTasks() {
        // Black-box test: verifies status filter.
        TaskService service = new TaskService();
        service.createTask(sampleTask("Todo task", Priority.MEDIUM));

        Task doneTask = sampleTask("Done task", Priority.MEDIUM);
        doneTask.setStatus(TaskStatus.DONE);
        service.createTask(doneTask);

        List<Task> results = service.filterByStatus(TaskStatus.DONE);

        assertEquals(1, results.size());
        assertEquals("Done task", results.get(0).getTitle());
    }

    @Test
    void sortByPriorityPlacesHighPriorityFirst() {
        // White-box test: verifies Strategy-based sorting branch for priority.
        TaskService service = new TaskService();
        service.createTask(sampleTask("Low", Priority.LOW));
        service.createTask(sampleTask("High", Priority.HIGH));

        assertEquals("High", service.sort(new SortByPriorityStrategy()).get(0).getTitle());
    }

    @Test
    void sortByDueDatePlacesEarliestTaskFirst() {
        // White-box test: verifies Strategy-based sorting branch for due date.
        TaskService service = new TaskService();
        service.createTask(taskWithDate("Later", Priority.MEDIUM, LocalDate.of(2026, 6, 10)));
        service.createTask(taskWithDate("Earlier", Priority.MEDIUM, LocalDate.of(2026, 5, 30)));

        assertEquals("Earlier", service.sort(new SortByDueDateStrategy()).get(0).getTitle());
    }

    @Test
    void updateMissingTaskThrowsException() {
        // White-box test: covers exception path in findById through updateTask.
        TaskService service = new TaskService();

        assertThrows(
                TaskNotFoundException.class,
                () -> service.updateTask(99, sampleTask("Missing", Priority.LOW))
        );
    }

    @Test
    void deleteMissingTaskThrowsException() {
        // White-box test: covers exception path in findById through deleteTask.
        TaskService service = new TaskService();

        assertThrows(TaskNotFoundException.class, () -> service.deleteTask(99));
    }

    private Task sampleTask(String title, Priority priority) {
        return taskWithDate(title, priority, LocalDate.now().plusDays(1));
    }

    private Task taskWithDate(String title, Priority priority, LocalDate dueDate) {
        return Task.builder()
                .title(title)
                .description("Sample description")
                .priority(priority)
                .category("General")
                .dueDate(dueDate)
                .build();
    }
}