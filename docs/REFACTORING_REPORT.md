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

### Before Refactoring

Before the refactoring, each filtering method performed its own stream operation:

```text
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

public List<Task> filterByCategory(String category) {
    return tasks.stream()
            .filter(task -> task.getCategory().equalsIgnoreCase(category))
            .toList();
}
```

The problem was not that the code failed. The problem was that the filtering structure was repeated four times.

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
```

### Why This Improved the Code

The refactoring improved the code because:

* It reduced duplicated filtering structure.
* It made the public service methods shorter.
* It made future filters easier to add.
* It kept business logic in `TaskService`.
* It preserved all existing behavior.

### Git Change Size

The refactoring commit changed one file:

```text
1 file changed, 11 insertions(+), 12 deletions(-)
```

This was a small refactoring, but it improved maintainability without changing application behavior.

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

## 8. Metrics Collection Method

The refactoring metrics were collected from production Java code using several tools and sources:

| Metric Type               | Source / Tool Used                                                 |
| ------------------------- | ------------------------------------------------------------------ |
| Before/after change size  | Git commit statistics from `5f8fb48`                               |
| Lines of code             | Local line-count command over `src/main/java`                      |
| Cyclomatic complexity     | Method-level McCabe decision-point count                           |
| Test and coverage metrics | JaCoCo report generated by `./gradlew clean test jacocoTestReport` |
| Static analysis result    | Checkstyle reports generated by `./gradlew clean build`            |
| Build verification        | Gradle build and test tasks                                        |

The main before/after comparison focuses on the completed refactoring commit:

```text
5f8fb48 refactor: extract shared task filtering logic
```

The “before” version is the code after the category filter was implemented but before the refactor:

```text
ec961aa feat: implement category filter
```

The “after” version is the refactored code:

```text
5f8fb48 refactor: extract shared task filtering logic
```

---

## 9. Lines of Code Metrics

### TaskService Before and After Refactoring

| Metric                                                  | Before Refactoring | After Refactoring | Change |
| ------------------------------------------------------- | -----------------: | ----------------: | -----: |
| `TaskService.java` physical lines                       |                 93 |                92 |     -1 |
| `TaskService.java` non-blank lines                      |                 79 |                77 |     -2 |
| Repeated public `stream/filter/toList` filtering blocks |                  4 |                 0 |     -4 |
| Shared filtering helper methods                         |                  0 |                 1 |     +1 |

The total line count changed only slightly, but the main improvement was not simply reducing file size. The main improvement was removing duplicated filtering structure from the public service methods.

### Current Production Code LOC by Package

| Package / Module    | Java Files | Physical LOC | Non-Blank LOC |
| ------------------- | ---------: | -----------: | ------------: |
| root package        |          1 |           16 |            13 |
| `cli`               |          1 |          325 |           281 |
| `exception`         |          1 |           10 |             9 |
| `export`            |          3 |           74 |            63 |
| `model`             |          3 |          190 |           156 |
| `patterns/strategy` |          3 |           39 |            33 |
| `service`           |          2 |          127 |           107 |
| `storage`           |          2 |           55 |            47 |
| **Total**           |     **16** |      **836** |       **709** |

The `cli` package is the largest package because it contains command parsing and user-facing output. The business logic is separated into service, storage, export, model, and strategy packages.

---

## 10. Cyclomatic Complexity Metrics

Cyclomatic complexity was measured using a simple McCabe decision-point count: each method starts at 1, and each explicit branch or decision point increases the value.

The completed refactoring did not target decision complexity. It targeted duplicated filtering logic. Because the affected filtering methods do not contain complex branching, their cyclomatic complexity stayed low before and after refactoring.

### Affected Methods Before and After Refactoring

| Method             | Before Refactoring | After Refactoring |        Change |
| ------------------ | -----------------: | ----------------: | ------------: |
| `search`           |                  1 |                 1 |             0 |
| `filterByPriority` |                  1 |                 1 |             0 |
| `filterByStatus`   |                  1 |                 1 |             0 |
| `filterByCategory` |                  1 |                 1 |             0 |
| `filterTasks`      |                N/A |                 1 | +1 new helper |

The complexity values did not decrease because the methods were already simple. The measurable improvement was in duplication and maintainability.

### TaskService Method Complexity After Refactoring

| Method             | Cyclomatic Complexity | Notes                                      |
| ------------------ | --------------------: | ------------------------------------------ |
| `createTask`       |                     1 | Straight-line task creation.               |
| `getAllTasks`      |                     1 | Returns a defensive copy.                  |
| `updateTask`       |                     1 | Straight-line update after lookup.         |
| `deleteTask`       |                     1 | Straight-line delete after lookup.         |
| `search`           |                     1 | Delegates filtering to helper method.      |
| `filterByPriority` |                     1 | Delegates filtering to helper method.      |
| `filterByStatus`   |                     1 | Delegates filtering to helper method.      |
| `filterByCategory` |                     1 | Delegates filtering to helper method.      |
| `sort`             |                     1 | Delegates ordering to strategy comparator. |
| `filterTasks`      |                     1 | Shared helper for filtering behavior.      |
| `findById`         |                     1 | Uses stream lookup and throws if missing.  |

### Complexity Interpretation

The complexity stayed low because `TaskService` avoids large conditional blocks. Sorting behavior is also kept simple by using the Strategy pattern instead of placing several sorting branches inside the service.

---

## 11. Additional Maintainability Metric: Duplication and Change Points

The additional metric used for this report is the number of duplicated filtering pipelines and the number of places that would need to change if filtering behavior changed later.

| Maintainability Metric                         | Before Refactoring | After Refactoring | Improvement |
| ---------------------------------------------- | -----------------: | ----------------: | ----------- |
| Public methods repeating filtering pipeline    |                  4 |                 0 | Yes         |
| Places to modify if filtering pipeline changes |                  4 |                 1 | Yes         |
| Shared filtering abstraction present           |                 No |               Yes | Yes         |
| Business-logic tests passing                   |                Yes |               Yes | Preserved   |
| Full Gradle build passing                      |                Yes |               Yes | Preserved   |
| Checkstyle violations                          |                  0 |                 0 | Preserved   |
| Business-logic instruction coverage            |                91% |               91% | Preserved   |
| Business-logic branch coverage                 |                75% |               75% | Preserved   |

This is a measurable maintainability improvement. If filtering behavior later needed null handling, logging, trimming, or case normalization, the change could be made in the helper method instead of being repeated across multiple public methods.

---

## 12. Verification After Refactoring

After the refactoring, the following commands were run successfully:

```text
./gradlew test
./gradlew clean build
./gradlew clean test jacocoTestReport
```

Verification results:

| Check                                  | Result                 |
| -------------------------------------- | ---------------------- |
| Automated tests                        | Passing                |
| Full Gradle build                      | Passing                |
| JaCoCo coverage report                 | Generated successfully |
| Business-logic instruction coverage    | 91%                    |
| Business-logic branch coverage         | 75%                    |
| Checkstyle static analysis             | 0 errors               |
| Static analysis error-level violations | 0                      |

This confirms that the refactoring did not break existing behavior and did not introduce static analysis problems.

---

## 13. Final Reflection

The most important lesson from this refactoring work is that refactoring does not always mean making a large change. The completed `TaskService` refactoring was small, but it improved maintainability by removing repeated filtering code and replacing it with one shared helper method.

The refactoring also showed why tests are important. Because the service tests and build were already passing before the refactor, they provided a safety net. After extracting the helper method, running the same tests confirmed that the behavior had not changed.

Another lesson is that not every identified smell should be refactored immediately. `CommandLineApp` has a Large Class risk, but splitting it into command handler classes near the end of the project would add unnecessary risk. For the current application size, it is better to document that as a future refactoring opportunity.

If I continued this project, the next refactoring I would do is splitting `CommandLineApp` into smaller command handler classes. That would address the Large Class / Long Method risk more directly and make command behavior easier to test independently.

Overall, this refactoring improved the codebase by reducing duplication, keeping tests passing, and making future filtering behavior easier to extend.

---

## 14. Conclusion

The most important completed refactoring was extracting shared filtering logic in `TaskService`. This addressed the **Duplicate Code** smell by removing repeated stream/filter/toList logic from multiple methods.

Other design decisions also helped avoid course-related code smells. The Strategy pattern reduces **Switch Statements / Over-Complexity** risk in sorting. Separate storage, export, statistics, and service classes reduce **Lack of Modularity**, **Divergent Change**, and **God Object** risk.

The project still has one clear future refactoring opportunity: `CommandLineApp` could be split into smaller command handler classes if the application grows. For the current project scope, the class is acceptable, but documenting this risk shows awareness of maintainability concerns.

All completed refactoring was verified by running automated tests, generating the coverage report, checking static analysis results, and running the full Gradle build successfully.
