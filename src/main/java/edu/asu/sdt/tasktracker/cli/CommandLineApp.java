package edu.asu.sdt.tasktracker.cli;

/**
 * Handles command-line input and output.
 */
public class CommandLineApp {

    public void run(String[] args) {
        if (args.length == 0 || "help".equalsIgnoreCase(args[0])) {
            printHelp();
            return;
        }

        System.out.println("Unknown command. Type 'help' to see available commands.");
    }

    private void printHelp() {
        System.out.println("Personal Task Tracker");
        System.out.println("Commands planned: add, list, update, delete, search, filter, sort, export, stats");
    }
}
