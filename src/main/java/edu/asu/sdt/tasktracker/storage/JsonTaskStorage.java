package edu.asu.sdt.tasktracker.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.asu.sdt.tasktracker.model.Task;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores tasks in a JSON file.
 */
public class JsonTaskStorage implements TaskStorage {
    private final Path filePath;
    private final ObjectMapper objectMapper;

    public JsonTaskStorage(Path filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public List<Task> load() throws IOException {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(filePath.toFile(), new TypeReference<List<Task>>() { });
    }

    @Override
    public void save(List<Task> tasks) throws IOException {
        if (filePath.getParent() != null) {
            Files.createDirectories(filePath.getParent());
        }
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), tasks);
    }
}
