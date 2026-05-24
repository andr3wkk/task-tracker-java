package edu.asu.sdt.tasktracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.asu.sdt.tasktracker.exception.TaskNotFoundException;
import edu.asu.sdt.tasktracker.model.Priority;
import edu.asu.sdt.tasktracker.model.Task;
import edu.asu.sdt.tasktracker.patterns.strategy.SortByPriorityStrategy;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class TaskServiceTest {

    @Test
    void createTaskAssignsId() {
        TaskService service = new TaskService();
        Task task = service.createTask(sampleTask("Study", Priority.MEDIUM));

        assertEquals(1, task.getId());
        assertEquals(1, service.getAllTasks().size());
    }

    @Test
    void searchFindsMatchingTask() {
        TaskService service = new TaskService();
        service.createTask(sampleTask("Study Java", Priority.HIGH));
        service.createTask(sampleTask("Buy groceries", Priority.LOW));

        assertEquals(1, service.search("java").size());
    }

    @Test
    void filterByPriorityReturnsOnlyMatchingTasks() {
        TaskService service = new TaskService();
        service.createTask(sampleTask("Important", Priority.HIGH));
        service.createTask(sampleTask("Later", Priority.LOW));

        assertEquals(1, service.filterByPriority(Priority.HIGH).size());
    }

    @Test
    void sortByPriorityPlacesHighPriorityFirst() {
        TaskService service = new TaskService();
        service.createTask(sampleTask("Low", Priority.LOW));
        service.createTask(sampleTask("High", Priority.HIGH));

        assertEquals("High", service.sort(new SortByPriorityStrategy()).get(0).getTitle());
    }

    @Test
    void deleteMissingTaskThrowsException() {
        TaskService service = new TaskService();

        assertThrows(TaskNotFoundException.class, () -> service.deleteTask(99));
    }

    private Task sampleTask(String title, Priority priority) {
        return Task.builder()
                .title(title)
                .description("Sample description")
                .priority(priority)
                .category("General")
                .dueDate(LocalDate.now().plusDays(1))
                .build();
    }
}
