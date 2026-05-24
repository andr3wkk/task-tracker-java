package edu.asu.sdt.tasktracker.storage;

import edu.asu.sdt.tasktracker.model.Task;
import java.io.IOException;
import java.util.List;

/**
 * Storage interface for loading and saving tasks.
 */
public interface TaskStorage {
    List<Task> load() throws IOException;

    void save(List<Task> tasks) throws IOException;
}
