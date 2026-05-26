package edu.asu.sdt.tasktracker.cli;

/**
 * Handles command-line input and output.
 */
public class CommandLineApp {

    public void run(String[] args) {
        if (args.length == 0) {
            printHelp();
            return;
        }

        String command = args[0].toLowerCase();

        switch (command) {
            case "help" -> printHelp();
            case "add" -> printPlannedCommand("add");
            case "list" -> printPlannedCommand("list");
            case "update" -> printPlannedCommand("update");
            case "delete" -> printPlannedCommand("delete");
            case "search" -> printPlannedCommand("search");
            case "filter" -> printPlannedCommand("filter");
            case "sort" -> printPlannedCommand("sort");
            case "export" -> printPlannedCommand("export");
            case "stats" -> printPlannedCommand("stats");
            default -> printUnknownCommand(command);
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