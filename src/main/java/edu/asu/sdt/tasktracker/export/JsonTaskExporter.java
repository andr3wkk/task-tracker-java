package edu.asu.sdt.tasktracker.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.asu.sdt.tasktracker.model.Task;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Exports tasks to JSON format.
 */
public class JsonTaskExporter implements TaskExporter {
    private final ObjectMapper objectMapper;

    public JsonTaskExporter() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void export(List<Task> tasks, Path outputPath) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputPath.toFile(), tasks);
    }
}
