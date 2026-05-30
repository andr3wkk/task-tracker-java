package edu.asu.sdt.tasktracker.service;

import edu.asu.sdt.tasktracker.exception.TaskNotFoundException;
import edu.asu.sdt.tasktracker.model.Priority;
import edu.asu.sdt.tasktracker.model.Task;
import edu.asu.sdt.tasktracker.model.TaskStatus;
import edu.asu.sdt.tasktracker.patterns.strategy.TaskSortStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Business logic for managing tasks.
 */
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public TaskService() {
        // Starts with an empty task list.
    }

    public TaskService(List<Task> initialTasks) {
        tasks.addAll(initialTasks);
        nextId = tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
    }

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
        return filterTasks(task -> task.containsKeyword(keyword));
    }

    public List<Task> filterByPriority(Priority priority) {
        return filterTasks(task -> task.getPriority() == priority);
    }

    public List<Task> filterByStatus(TaskStatus status) {
        return filterTasks(task -> task.getStatus() == status);
    }

    public List<Task> filterByCategory(String category) {
        return filterTasks(task -> task.getCategory().equalsIgnoreCase(category));
    }

    public List<Task> sort(TaskSortStrategy strategy) {
        return tasks.stream()
                .sorted(strategy.comparator())
                .toList();
    }

    private List<Task> filterTasks(Predicate<Task> condition) {
        return tasks.stream()
                .filter(condition)
                .toList();
    }

    private Task findById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}