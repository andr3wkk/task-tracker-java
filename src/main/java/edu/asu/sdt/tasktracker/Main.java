package edu.asu.sdt.tasktracker;

import edu.asu.sdt.tasktracker.cli.CommandLineApp;

/**
 * Application entry point.
 */
public final class Main {
    private Main() {
        // Utility class.
    }

    public static void main(String[] args) {
        new CommandLineApp().run(args);
    }
}
