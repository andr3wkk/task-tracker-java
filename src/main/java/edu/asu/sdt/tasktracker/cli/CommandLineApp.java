package edu.asu.sdt.tasktracker.cli;

import edu.asu.sdt.tasktracker.exception.TaskNotFoundException;
import edu.asu.sdt.tasktracker.model.Priority;
import edu.asu.sdt.tasktracker.model.Task;
import edu.asu.sdt.tasktracker.model.TaskStatus;
import edu.asu.sdt.tasktracker.patterns.strategy.SortByDueDateStrategy;
import edu.asu.sdt.tasktracker.patterns.strategy.SortByPriorityStrategy;
import edu.asu.sdt.tasktracker.service.TaskService;
import edu.asu.sdt.tasktracker.storage.JsonTaskStorage;
import edu.asu.sdt.tasktracker.storage.TaskStorage;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Handles command-line input and output.
 */
public class CommandLineApp {
    private static final Path DATA_FILE = Path.of("data", "tasks.json");

    public void run(String[] args) {
        TaskStorage storage = new JsonTaskStorage(DATA_FILE);

        try {
            TaskService taskService = new TaskService(storage.load());
            handleCommand(args, taskService, storage);
        } catch (IOException exception) {
            System.out.println("Could not load or save task data.");
            System.out.println("Reason: " + exception.getMessage());
        }
    }

    private void handleCommand(String[] args, TaskService taskService, TaskStorage storage) throws IOException {
        if (args.length == 0) {
            printHelp();
            return;
        }

        String command = args[0].toLowerCase();

        switch (command) {
            case "help" -> printHelp();
            case "add" -> addTask(args, taskService, storage);
            case "list" -> listTasks(taskService.getAllTasks());
            case "update" -> updateTask(args, taskService, storage);
            case "delete" -> deleteTask(args, taskService, storage);
            case "search" -> searchTasks(args, taskService);
            case "filter" -> filterTasks(args, taskService);
            case "sort" -> sortTasks(args, taskService);
            case "export" -> printPlannedCommand("export");
            case "stats" -> printPlannedCommand("stats");
            default -> printUnknownCommand(command);
        }
    }

    private void addTask(String[] args, TaskService taskService, TaskStorage storage) throws IOException {
        if (args.length < 6) {
            System.out.println("Missing arguments for add command.");
            System.out.println("Usage:");
            System.out.println("  ./gradlew run --args=\"add <title> <description> <priority> <category> <dueDate>\"");
            System.out.println("Example:");
            System.out.println("  ./gradlew run --args=\"add Study Read HIGH School 2026-06-01\"");
            return;
        }

        try {
            Task task = Task.builder()
                    .title(args[1])
                    .description(args[2])
                    .priority(Priority.valueOf(args[3].toUpperCase()))
                    .category(args[4])
                    .dueDate(LocalDate.parse(args[5]))
                    .build();

            Task createdTask = taskService.createTask(task);
            storage.save(taskService.getAllTasks());

            System.out.println("Task created successfully with ID " + createdTask.getId() + ".");
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid priority. Use LOW, MEDIUM, or HIGH.");
        } catch (DateTimeParseException exception) {
            System.out.println("Invalid due date. Use format YYYY-MM-DD.");
        }
    }

    private void updateTask(String[] args, TaskService taskService, TaskStorage storage) throws IOException {
        if (args.length < 8) {
            System.out.println("Missing arguments for update command.");
            System.out.println("Usage:");
            System.out.println("  ./gradlew run --args=\"update <id> <title> <description> <priority> "
                    + "<category> <dueDate> <status>\"");
            System.out.println("Example:");
            System.out.println("  ./gradlew run --args=\"update 1 Study Reading MEDIUM School 2026-06-10 DONE\"");
            return;
        }

        try {
            int id = Integer.parseInt(args[1]);

            Task updatedTask = Task.builder()
                    .title(args[2])
                    .description(args[3])
                    .priority(Priority.valueOf(args[4].toUpperCase()))
                    .category(args[5])
                    .dueDate(LocalDate.parse(args[6]))
                    .status(TaskStatus.valueOf(args[7].toUpperCase()))
                    .build();

            taskService.updateTask(id, updatedTask);
            storage.save(taskService.getAllTasks());

            System.out.println("Task " + id + " updated successfully.");
        } catch (NumberFormatException exception) {
            System.out.println("Invalid task ID. Use a number.");
        } catch (DateTimeParseException exception) {
            System.out.println("Invalid due date. Use format YYYY-MM-DD.");
        } catch (TaskNotFoundException exception) {
            System.out.println(exception.getMessage());
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid priority or status.");
            System.out.println("Priority: LOW, MEDIUM, HIGH");
            System.out.println("Status: TODO, IN_PROGRESS, DONE");
        }
    }

    private void deleteTask(String[] args, TaskService taskService, TaskStorage storage) throws IOException {
        if (args.length < 2) {
            System.out.println("Missing task ID for delete command.");
            System.out.println("Usage:");
            System.out.println("  ./gradlew run --args=\"delete <id>\"");
            return;
        }

        try {
            int id = Integer.parseInt(args[1]);

            taskService.deleteTask(id);
            storage.save(taskService.getAllTasks());

            System.out.println("Task " + id + " deleted successfully.");
        } catch (NumberFormatException exception) {
            System.out.println("Invalid task ID. Use a number.");
        } catch (TaskNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void searchTasks(String[] args, TaskService taskService) {
        if (args.length < 2) {
            System.out.println("Missing keyword for search command.");
            System.out.println("Usage:");
            System.out.println("  ./gradlew run --args=\"search <keyword>\"");
            return;
        }

        List<Task> results = taskService.search(args[1]);
        listTasks(results);
    }

    private void filterTasks(String[] args, TaskService taskService) {
        if (args.length < 3) {
            System.out.println("Missing arguments for filter command.");
            System.out.println("Usage:");
            System.out.println("  ./gradlew run --args=\"filter priority <LOW|MEDIUM|HIGH>\"");
            System.out.println("  ./gradlew run --args=\"filter status <TODO|IN_PROGRESS|DONE>\"");
            return;
        }

        String filterType = args[1].toLowerCase();

        try {
            switch (filterType) {
                case "priority" -> {
                    Priority priority = Priority.valueOf(args[2].toUpperCase());
                    listTasks(taskService.filterByPriority(priority));
                }
                case "status" -> {
                    TaskStatus status = TaskStatus.valueOf(args[2].toUpperCase());
                    listTasks(taskService.filterByStatus(status));
                }
                default -> {
                    System.out.println("Unknown filter type: " + filterType);
                    System.out.println("Use priority or status.");
                }
            }
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid filter value.");
            System.out.println("Priority: LOW, MEDIUM, HIGH");
            System.out.println("Status: TODO, IN_PROGRESS, DONE");
        }
    }

    private void sortTasks(String[] args, TaskService taskService) {
        if (args.length < 2) {
            System.out.println("Missing sort field.");
            System.out.println("Usage:");
            System.out.println("  ./gradlew run --args=\"sort dueDate\"");
            System.out.println("  ./gradlew run --args=\"sort priority\"");
            return;
        }

        String sortField = args[1].toLowerCase();

        switch (sortField) {
            case "duedate" -> listTasks(taskService.sort(new SortByDueDateStrategy()));
            case "priority" -> listTasks(taskService.sort(new SortByPriorityStrategy()));
            default -> {
                System.out.println("Unknown sort field: " + sortField);
                System.out.println("Use dueDate or priority.");
            }
        }
    }

    private void listTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        System.out.printf("%-4s %-20s %-10s %-15s %-12s %-12s%n",
                "ID", "Title", "Priority", "Category", "Due Date", "Status");

        for (Task task : tasks) {
            System.out.printf("%-4d %-20s %-10s %-15s %-12s %-12s%n",
                    task.getId(),
                    task.getTitle(),
                    task.getPriority(),
                    task.getCategory(),
                    task.getDueDate(),
                    task.getStatus());
        }
    }

    private void printHelp() {
        System.out.println("Personal Task Tracker");
        System.out.println();
        System.out.println("Usage:");
        System.out.println("  ./gradlew run --args=\"<command> [options]\"");
        System.out.println();
        System.out.println("Available commands:");
        System.out.println("  help                         Show this help menu");
        System.out.println("  add                          Add a new task");
        System.out.println("  list                         List all tasks");
        System.out.println("  update                       Update a task by ID");
        System.out.println("  delete                       Delete a task by ID");
        System.out.println("  search                       Search tasks by keyword");
        System.out.println("  filter                       Filter tasks by priority or status");
        System.out.println("  sort                         Sort tasks by due date or priority");
        System.out.println("  export                       Export tasks to JSON or CSV");
        System.out.println("  stats                        Show task statistics");
    }

    private void printPlannedCommand(String command) {
        System.out.println("Command '" + command + "' is recognized but not implemented yet.");
        System.out.println("Use 'help' to see available commands.");
    }

    private void printUnknownCommand(String command) {
        System.out.println("Unknown command: " + command);
        System.out.println("Use 'help' to see available commands.");
    }
}