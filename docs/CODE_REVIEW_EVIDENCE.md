# Code Review Evidence

## Overview

This document records pull request and self-review evidence for the Personal Task Tracker project.

The project was completed individually, so the code review process used self-review comments. The comments were written as if reviewing a colleague’s code and focused on code quality, design, logic, maintainability, and requirement coverage.

The project used feature branches and pull requests before merging work into `main`. This document highlights three pull requests that contain substantive review comments.

---

## Pull Request Summary

| PR    | Title                                           | Branch                           | Review Evidence                    |
| ----- | ----------------------------------------------- | -------------------------------- | ---------------------------------- |
| PR #2 | Implement update and delete CLI commands        | `feature/update-delete-commands` | 3 substantive self-review comments |
| PR #3 | feat: implement search filter and sort commands | `feature/search-filter-sort`     | 3 substantive self-review comments |
| PR #4 | feat: implement export and statistics commands  | `feature/export-and-stats`       | 3 substantive self-review comments |

---

## PR #2 — Implement Update and Delete CLI Commands

**Pull Request:** [PR #2: Implement update and delete CLI commands](https://github.com/andr3wkk/task-tracker-java/pull/2)

### Purpose

This pull request added update and delete support to the command-line interface. It also improved invalid input handling and saved updated task data through JSON persistence.

### Self-Review Comments

1. **Invalid input handling**

   Comment summary:

   > Good handling of invalid task IDs here. This keeps the CLI from crashing when the user enters bad input.

   Why this matters:

   This comment addresses robustness and graceful error handling. The project requires the CLI to handle invalid input without crashing.

2. **Persistence after update**

   Comment summary:

   > The update command saves through TaskStorage after modifying the task, which keeps persistence behavior consistent with the add command.

   Why this matters:

   This comment checks logic consistency. Updating a task should not only change the in-memory task list; it should also persist the change so the data remains available between sessions.

3. **Maintainability of delete command**

   Comment summary:

   > This method keeps delete behavior simple and readable. If more commands are added later, this CLI class may need refactoring to avoid becoming too large.

   Why this matters:

   This comment addresses maintainability and future refactoring. It recognizes that the command-line class is acceptable for the current project scope but could become a Large Class risk if more commands are added.

---

## PR #3 — Implement Search, Filter, and Sort Commands

**Pull Request:** [PR #3: feat: implement search filter and sort commands](https://github.com/andr3wkk/task-tracker-java/pull/3)

### Purpose

This pull request added search, filter, and sort support to the command-line interface. It connected the CLI to existing service-layer logic and sorting strategy classes.

### Self-Review Comments

1. **Separation of CLI and business logic**

   Comment summary:

   > The search command reuses TaskService instead of duplicating search logic in the CLI, which keeps business rules separated from command-line handling.

   Why this matters:

   This comment addresses separation of concerns. The CLI should route user commands and print results, while the service layer should contain business rules.

2. **Requirement coverage for filtering**

   Comment summary:

   > Supporting both priority and status filters satisfies the requirement for filtering by at least two criteria while keeping the command syntax simple.

   Why this matters:

   This comment checks requirement coverage and usability. The project requires filtering by at least two criteria, and priority/status filtering satisfies that requirement.

3. **Strategy pattern use for sorting**

   Comment summary:

   > The sort command uses existing strategy classes for due date and priority, which makes this a real use of the Strategy design pattern rather than hard-coding all sorting logic in the CLI.

   Why this matters:

   This comment addresses design quality. It verifies that sorting behavior uses the Strategy pattern instead of a large conditional block in the CLI.

---

## PR #4 — Implement Export and Statistics Commands

**Pull Request:** [PR #4: feat: implement export and statistics commands](https://github.com/andr3wkk/task-tracker-java/pull/4)

### Purpose

This pull request added export and statistics support to the command-line interface. It allowed tasks to be exported to JSON and CSV, created export directories automatically when needed, and added task summary statistics.

### Self-Review Comments

1. **Two export formats**

   Comment summary:

   > The export command supports both JSON and CSV formats, satisfying the requirement to export records to at least two formats.

   Why this matters:

   This comment checks requirement coverage. The project requires exporting records to at least two formats, and the implementation supports JSON and CSV.

2. **Output directory creation**

   Comment summary:

   > Creating the output directory automatically improves usability because the user does not have to manually create an exports folder first.

   Why this matters:

   This comment addresses usability and error prevention. Automatically creating the output directory makes the command easier to use and avoids unnecessary file path errors.

3. **Statistics requirement**

   Comment summary:

   > The statistics command gives a useful summary of total tasks, completed tasks, overdue tasks, and category counts, which satisfies the project statistics requirement.

   Why this matters:

   This comment checks requirement coverage and feature completeness. The project requires a statistics summary, and this implementation provides several useful task metrics.

---

## Review Process Reflection

The self-review process helped improve the project by making each pull request more deliberate. Instead of only checking whether the code compiled, the comments focused on whether the implementation satisfied requirements, kept responsibilities separated, and avoided avoidable maintainability problems.

The most useful review comments were the ones about separation of concerns and future maintainability. For example, the search, filter, and sort work stayed connected to `TaskService` and strategy classes instead of moving business logic into the CLI. The update/delete review also identified that `CommandLineApp` could become a Large Class risk if the application grows.

Although the project was completed individually, the pull request workflow still provided useful checkpoints. Each feature branch was reviewed before being merged, CI checks were run, and comments documented design and quality considerations.
