package edu.asu.sdt.tasktracker.export;

import edu.asu.sdt.tasktracker.model.Task;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Exports tasks to CSV format.
 */
public class CsvTaskExporter implements TaskExporter {
    @Override
    public void export(List<Task> tasks, Path outputPath) throws IOException {
        StringBuilder builder = new StringBuilder("id,title,description,priority,category,dueDate,status\n");

        for (Task task : tasks) {
            builder.append(task.getId()).append(',')
                    .append(safe(task.getTitle())).append(',')
                    .append(safe(task.getDescription())).append(',')
                    .append(task.getPriority()).append(',')
                    .append(safe(task.getCategory())).append(',')
                    .append(task.getDueDate()).append(',')
                    .append(task.getStatus()).append('\n');
        }

        Files.writeString(outputPath, builder.toString());
    }

    private String safe(String value) {
        if (value == null) {
            return "";
        }
        return value.replace(",", " ");
    }
}