package edu.asu.sdt.tasktracker.service;

import edu.asu.sdt.tasktracker.exception.TaskNotFoundException;
import edu.asu.sdt.tasktracker.model.Priority;
import edu.asu.sdt.tasktracker.model.Task;
import edu.asu.sdt.tasktracker.model.TaskStatus;
import edu.asu.sdt.tasktracker.patterns.strategy.TaskSortStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 * Business logic for managing tasks.
 */
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public Task createTask(Task task) {
        task.setId(nextId);
        nextId++;
        tasks.add(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public Task updateTask(int id, Task updatedTask) {
        Task existingTask = findById(id);
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setCategory(updatedTask.getCategory());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setStatus(updatedTask.getStatus());
        return existingTask;
    }

    public boolean deleteTask(int id) {
        Task task = findById(id);
        return tasks.remove(task);
    }

    public List<Task> search(String keyword) {
        return tasks.stream()
                .filter(task -> task.containsKeyword(keyword))
                .toList();
    }

    public List<Task> filterByPriority(Priority priority) {
        return tasks.stream()
                .filter(task -> task.getPriority() == priority)
                .toList();
    }

    public List<Task> filterByStatus(TaskStatus status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .toList();
    }

    public List<Task> sort(TaskSortStrategy strategy) {
        return tasks.stream()
                .sorted(strategy.comparator())
                .toList();
    }

    private Task findById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
