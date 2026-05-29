package edu.asu.sdt.tasktracker.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.asu.sdt.tasktracker.model.Priority;
import edu.asu.sdt.tasktracker.model.Task;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class JsonTaskStorageTest {
    @TempDir
    Path tempDir;

    @Test
    void loadReturnsEmptyListWhenFileDoesNotExist() throws IOException {
        // White-box test: covers the missing-file branch in JsonTaskStorage.load.
        JsonTaskStorage storage = new JsonTaskStorage(tempDir.resolve("missing.json"));

        List<Task> tasks = storage.load();

        assertTrue(tasks.isEmpty());
    }

    @Test
    void saveThenLoadPreservesTaskData() throws IOException {
        // Black-box test: verifies persistence across save/load.
        Path file = tempDir.resolve("tasks.json");
        JsonTaskStorage storage = new JsonTaskStorage(file);

        storage.save(List.of(sampleTask()));

        List<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals("Study", loadedTasks.get(0).getTitle());
        assertEquals(Priority.HIGH, loadedTasks.get(0).getPriority());
    }

    private Task sampleTask() {
        Task task = Task.builder()
                .title("Study")
                .description("Read")
                .priority(Priority.HIGH)
                .category("School")
                .dueDate(LocalDate.of(2026, 6, 1))
                .build();
        task.setId(1);
        return task;
    }
}