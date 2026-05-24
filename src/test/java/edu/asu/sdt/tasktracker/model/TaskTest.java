package edu.asu.sdt.tasktracker.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void builderCreatesTaskWithExpectedFields() {
        Task task = Task.builder()
                .id(7)
                .title("Finish report")
                .description("Write final project report")
                .priority(Priority.HIGH)
                .category("School")
                .dueDate(LocalDate.of(2026, 5, 30))
                .status(TaskStatus.TODO)
                .build();

        assertEquals(7, task.getId());
        assertEquals("Finish report", task.getTitle());
        assertEquals(Priority.HIGH, task.getPriority());
        assertEquals("School", task.getCategory());
    }

    @Test
    void containsKeywordSearchesTextFields() {
        Task task = Task.builder()
                .title("Study Java")
                .description("Practice JUnit tests")
                .category("School")
                .build();

        assertTrue(task.containsKeyword("junit"));
        assertTrue(task.containsKeyword("school"));
    }
}
