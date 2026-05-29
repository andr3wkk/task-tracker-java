package edu.asu.sdt.tasktracker.export;

import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.asu.sdt.tasktracker.model.Priority;
import edu.asu.sdt.tasktracker.model.Task;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class TaskExporterTest {
    @TempDir
    Path tempDir;

    @Test
    void csvExporterWritesHeaderAndTaskData() throws IOException {
        // Black-box test: verifies CSV export output format.
        Path output = tempDir.resolve("tasks.csv");

        new CsvTaskExporter().export(List.of(sampleTask()), output);

        String csv = Files.readString(output);
        assertTrue(csv.contains("id,title,description,priority,category,dueDate,status"));
        assertTrue(csv.contains("1,Study,Read,HIGH,School,2026-06-01,TODO"));
    }

    @Test
    void jsonExporterWritesTaskData() throws IOException {
        // Black-box test: verifies JSON export output contains task data.
        Path output = tempDir.resolve("tasks.json");

        new JsonTaskExporter().export(List.of(sampleTask()), output);

        String json = Files.readString(output);
        assertTrue(json.contains("\"title\""));
        assertTrue(json.contains("Study"));
        assertTrue(json.contains("School"));
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