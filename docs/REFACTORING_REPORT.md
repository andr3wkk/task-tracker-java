# Refactoring Report

## 1. Overview

This document describes the refactoring work completed for the Personal Task Tracker project. The purpose of refactoring was to improve code readability, reduce duplication, improve modularity, and make the application easier to maintain without changing its external behavior.

The Week 6 module explains that code starts to smell because of issues such as duplication, over-complexity, lack of modularity, poor naming, and inconsistent standards. This report uses those ideas to identify and explain the most important code smells in the task tracker project.

The main completed refactoring was extracting shared filtering logic from `TaskService`. The report also documents additional code smells that were identified and handled through design decisions or left as future improvement items.

All completed refactoring was verified with automated tests and Gradle builds.

---

## 2. Refactoring Summary

| Area                            | Course Code Smell                                       | Refactoring / Design Response                  | Status     |
| ------------------------------- | ------------------------------------------------------- | ---------------------------------------------- | ---------- |
| `TaskService` filtering methods | Duplicate Code                                          | Extract Method                                 | Completed  |
| `CommandLineApp`                | Large Class / Long Method risk                          | Future Extract Class / Extract Command Handler | Identified |
| Sorting behavior                | Switch Statements / Over-Complexity risk                | Strategy Pattern                               | Completed  |
| Persistence and export logic    | Lack of Modularity / Divergent Change / God Object risk | Separate storage and export classes            | Completed  |

---

## 3. Completed Refactoring: Duplicate Code in TaskService

### File

```text
src/main/java/edu/asu/sdt/tasktracker/service/TaskService.java
```

### Related Commit

```text
5f8fb48 refactor: extract shared task filtering logic
```

### Course Smell Category

Duplicate Code

### Code Smell

The filtering-related methods in `TaskService` repeated the same stream/filter/toList structure.

Before refactoring, these methods had similar logic:

```text
search(String keyword)
filterByPriority(Priority priority)
filterByStatus(TaskStatus status)
filterByCategory(String category)
```

Each method directly used the same pattern:

```text
tasks.stream()
        .filter(...)
        .toList();
```

This was not causing incorrect behavior, but it was duplicated code. If the filtering process needed to change later, the same structure could have to be edited in several places. This matches the Week 6 idea that clean code should not contain duplication.

### Refactoring Applied

The refactoring used the **Extract Method** technique.

A new private helper method was added:

```text
private List<Task> filterTasks(Predicate<Task> condition) {
    return tasks.stream()
            .filter(condition)
            .toList();
}
```

Then the public filtering and search methods were simplified to call the helper method:

```text
public List<Task> filterByCategory(String category) {
    return filterTasks(task -> task.getCategory().equalsIgnoreCase(category));
}
```

### Before and After Comparison

| Metric                                                  | Before Refactoring | After Refactoring |
| ------------------------------------------------------- | -----------------: | ----------------: |
| Public methods with repeated stream/filter/toList logic |                  4 |                 0 |
| Shared filtering helper methods                         |                  0 |                 1 |
| Filtering behavior duplicated across methods            |                Yes |                No |
| Tests passing                                           |                Yes |               Yes |
| Full Gradle build passing                               |                Yes |               Yes |

### Git Change Size

The refactoring commit changed one file:

```text
1 file changed, 11 insertions(+), 12 deletions(-)
```

This was a small refactoring, but it improved maintainability without changing behavior.

### Why This Improved the Code

The refactoring improved the code because:

* It reduced duplicated filtering structure.
* It made the public service methods shorter.
* It made future filters easier to add.
* It kept business logic in `TaskService`.
* It preserved all existing behavior.

### Verification

After refactoring, the following commands were run successfully:

```text
./gradlew test
./gradlew clean build
```

This confirms that the refactoring did not break existing behavior.

---

## 4. Identified Smell: Large Class / Long Method Risk in CommandLineApp

### File

```text
src/main/java/edu/asu/sdt/tasktracker/cli/CommandLineApp.java
```

### Course Smell Category

Large Class / Long Method

### Code Smell

`CommandLineApp` handles many command-line responsibilities:

* Reading command arguments
* Routing commands
* Printing help text
* Validating command input
* Calling service methods
* Printing results

This creates a **Large Class** risk. Some command-handling methods may also become **Long Method** risks if more validation and output logic is added.

For the current project size, the class is still understandable. However, if more commands are added later, this class could become harder to maintain and test.

### Current Response

The current design reduces the risk by keeping business logic outside the CLI:

* `TaskService` handles task management.
* `JsonTaskStorage` handles persistence.
* `StatisticsService` handles statistics.
* Exporter classes handle JSON and CSV export.
* Strategy classes handle sorting behavior.

Because of this separation, `CommandLineApp` mostly handles user input and output rather than core business rules.

### Future Refactoring

A future improvement would be to extract each command into its own handler class, such as:

```text
AddTaskCommand
UpdateTaskCommand
DeleteTaskCommand
SearchTaskCommand
FilterTaskCommand
ExportTaskCommand
StatsCommand
```

This would reduce the size of `CommandLineApp` and make each command easier to test independently.

### Decision

This refactoring was not completed during the current project because the CLI is still manageable and changing the command structure near the end of the project could introduce unnecessary risk. It is documented as a future improvement.

---

## 5. Identified Smell: Switch Statements / Over-Complexity Risk in Sorting

### Files

```text
src/main/java/edu/asu/sdt/tasktracker/patterns/strategy/TaskSortStrategy.java
src/main/java/edu/asu/sdt/tasktracker/patterns/strategy/SortByDueDateStrategy.java
src/main/java/edu/asu/sdt/tasktracker/patterns/strategy/SortByPriorityStrategy.java
src/main/java/edu/asu/sdt/tasktracker/service/TaskService.java
```

### Course Smell Category

Switch Statements / Over-Complexity

### Code Smell

Sorting can easily become a long conditional block if every sorting option is hard-coded directly into the service or CLI.

For example, the application could have been designed with logic like:

```text
if sort field is due date
else if sort field is priority
else if sort field is another field
```

As more sorting options are added, this style becomes more complex and harder to extend. This connects to the Week 6 warning about complex switch operators or long if-statement sequences.

### Refactoring / Design Response

The project uses the **Strategy Pattern** to avoid this problem.

The common interface is:

```text
TaskSortStrategy
```

The concrete strategies are:

```text
SortByDueDateStrategy
SortByPriorityStrategy
```

`TaskService` accepts a strategy instead of hard-coding each sorting rule:

```text
sort(TaskSortStrategy strategy)
```

### Benefit

This design improves maintainability because new sorting options can be added by creating new strategy classes instead of rewriting the main service logic.

---

## 6. Identified Smell: Lack of Modularity / God Object Risk

### Files

```text
src/main/java/edu/asu/sdt/tasktracker/storage/TaskStorage.java
src/main/java/edu/asu/sdt/tasktracker/storage/JsonTaskStorage.java
src/main/java/edu/asu/sdt/tasktracker/export/TaskExporter.java
src/main/java/edu/asu/sdt/tasktracker/export/JsonTaskExporter.java
src/main/java/edu/asu/sdt/tasktracker/export/CsvTaskExporter.java
src/main/java/edu/asu/sdt/tasktracker/service/StatisticsService.java
```

### Course Smell Category

Lack of Modularity / God Object Risk / Divergent Change

### Code Smell

If persistence, export, statistics, and task management logic were all placed in one class, the project could develop a **God Object** problem. One class would know too much and do too much.

It would also create a **Divergent Change** risk. For example, changing export behavior, storage behavior, or statistics behavior would all require modifying the same class even though those features are separate responsibilities.

### Refactoring / Design Response

The project separates these responsibilities into dedicated interfaces and classes:

* `TaskService` manages task operations.
* `TaskStorage` defines storage behavior.
* `JsonTaskStorage` handles saving and loading task data.
* `TaskExporter` defines export behavior.
* `JsonTaskExporter` exports tasks to JSON.
* `CsvTaskExporter` exports tasks to CSV.
* `StatisticsService` calculates statistics.

### Benefit

This separation improves the project because each class has a clearer responsibility. It also makes testing easier because storage, export, statistics, and task management can be tested separately from CLI command handling.

---

## 7. Refactoring Rules Followed

The Week 6 module explains that refactoring should make the code cleaner, should not create new functionality during the refactoring step, and should keep all tests passing.

The category filter work followed this rule by separating the work into different commits:

| Step     | Commit                                                  | Purpose                                                           |
| -------- | ------------------------------------------------------- | ----------------------------------------------------------------- |
| Red      | `637a2f5 test: add failing category filter test`        | Added a failing test for new behavior.                            |
| Green    | `ec961aa feat: implement category filter`               | Added the minimum feature implementation.                         |
| Refactor | `5f8fb48 refactor: extract shared task filtering logic` | Cleaned up duplicate filtering logic without adding new behavior. |

This keeps the new feature and the refactoring separate. The refactoring commit improved code structure while keeping behavior the same.

---

## 8. Metrics Summary

| Metric                                      | Result  |
| ------------------------------------------- | ------- |
| Automated tests after refactoring           | Passing |
| Full Gradle build after refactoring         | Passing |
| Business-logic instruction coverage         | 91%     |
| Business-logic branch coverage              | 75%     |
| Completed Extract Method refactoring        | Yes     |
| Main duplicated filtering structure removed | Yes     |
| Refactoring commit recorded in Git history  | Yes     |

---

## 9. Conclusion

The most important completed refactoring was extracting shared filtering logic in `TaskService`. This addressed the **Duplicate Code** smell by removing repeated stream/filter/toList logic from multiple methods.

Other design decisions also helped avoid course-related code smells. The Strategy pattern reduces **Switch Statements / Over-Complexity** risk in sorting. Separate storage, export, statistics, and service classes reduce **Lack of Modularity**, **Divergent Change**, and **God Object** risk.

The project still has one clear future refactoring opportunity: `CommandLineApp` could be split into smaller command handler classes if the application grows. For the current project scope, the class is acceptable, but documenting this risk shows awareness of maintainability concerns.

All completed refactoring was verified by running automated tests and the full Gradle build successfully.
