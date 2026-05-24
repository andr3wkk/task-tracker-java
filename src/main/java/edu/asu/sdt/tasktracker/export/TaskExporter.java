package edu.asu.sdt.tasktracker.export;

import edu.asu.sdt.tasktracker.model.Task;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Exports tasks to a file.
 */
public interface TaskExporter {
    void export(List<Task> tasks, Path outputPath) throws IOException;
}
