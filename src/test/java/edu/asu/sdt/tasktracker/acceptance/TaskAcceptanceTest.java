package edu.asu.sdt.tasktracker.acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.asu.sdt.tasktracker.model.Priority;
import edu.asu.sdt.tasktracker.model.Task;
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
}
