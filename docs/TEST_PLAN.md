# Test Plan

## Overview

This project uses automated JUnit 5 tests to verify the behavior of the Personal Task Tracker application. The tests focus mainly on business logic, persistence, exporting, statistics, and acceptance-level behavior.

The command-line interface is tested manually because it mostly handles user input, printed messages, and command routing. Business logic is tested through services, models, storage, exporters, and strategy classes.

## Testing Strategy

The test suite is organized by package:

* `model` tests verify task creation and task behavior.
* `service` tests verify task management and statistics logic.
* `storage` tests verify JSON persistence.
* `export` tests verify CSV and JSON export output.
* `acceptance` tests verify user-facing behavior using Given-When-Then style scenarios.

The main goal is to confirm that required task tracker features work correctly:

* Create tasks
* List stored tasks
* Update tasks by ID
* Delete tasks by ID
* Search tasks by keyword
* Filter tasks by priority and status
* Sort tasks by due date and priority
* Persist tasks between sessions
* Export tasks to JSON and CSV
* Display task statistics

## Black-Box Tests

Black-box tests are based on the project requirements and expected behavior, without depending on internal implementation details.

Examples:

* `createTaskAssignsId`
* `updateTaskChangesExistingTaskFields`
* `deleteTaskRemovesExistingTask`
* `searchFindsMatchingTaskAcrossTextFields`
* `filterByPriorityReturnsOnlyMatchingTasks`
* `filterByStatusReturnsOnlyMatchingTasks`
* `countCompletedCountsOnlyDoneTasks`
* `countByCategoryGroupsTasksByCategory`
* `csvExporterWritesHeaderAndTaskData`
* `jsonExporterWritesTaskData`
* `saveThenLoadPreservesTaskData`

These tests verify that the system produces the correct observable results for normal user actions.

## White-Box Tests

White-box tests are based on the internal structure of the code and are designed to cover important branches, exception paths, and strategy behavior.

Examples:

* `updateMissingTaskThrowsException`
* `deleteMissingTaskThrowsException`
* `loadReturnsEmptyListWhenFileDoesNotExist`
* `countOverdueIgnoresCompletedTasks`
* `sortByPriorityPlacesHighPriorityFirst`
* `sortByDueDatePlacesEarliestTaskFirst`

The white-box tests cover:

* Missing task exception paths
* Missing JSON file branch
* Overdue task decision logic
* Strategy-based sorting paths
* Branch behavior in service methods

## Acceptance Tests

Acceptance tests are written in a Given-When-Then style in:

```text
src/test/java/edu/asu/sdt/tasktracker/acceptance/TaskAcceptanceTest.java
```

Acceptance scenarios include:

* Given a new task tracker, when a task is created, then it appears in the task list.
* Given an existing task, when it is updated, then the task contains the new values.
* Given multiple tasks, when sorted by due date, then the earliest task appears first.

These tests support BDD/ATDD-style validation without adding a heavy external framework.

## Coverage Target

The project target is at least 70% line coverage for business logic.

JaCoCo is used to measure coverage:

```bash
./gradlew clean test jacocoTestReport
```

The HTML report can be opened with:

```bash
open build/reports/jacoco/test/html/index.html
```

The JaCoCo report excludes CLI and Main classes because those classes mainly contain user input/output and application startup code. The measured business-logic packages include:

* `service`
* `model`
* `storage`
* `export`
* `patterns.strategy`
* `exception`

Current measured business-logic coverage:

* Instruction coverage: 91%
* Branch coverage: 75%

This exceeds the required 70% business-logic coverage target.

## Manual CLI Testing

The CLI was also tested manually with commands such as:

```bash
./gradlew run --args="add Study Read HIGH School 2026-06-01"
./gradlew run --args="list"
./gradlew run --args="update 1 Study Reading MEDIUM School 2026-06-10 IN_PROGRESS"
./gradlew run --args="delete 1"
./gradlew run --args="search Study"
./gradlew run --args="filter priority HIGH"
./gradlew run --args="filter status TODO"
./gradlew run --args="sort dueDate"
./gradlew run --args="sort priority"
./gradlew run --args="stats"
./gradlew run --args="export json exports/tasks.json"
./gradlew run --args="export csv exports/tasks.csv"
```

Manual testing confirms the application works from the command line and handles common user workflows.

## Build and Git Commands

After saving this file as `docs/TEST_PLAN.md`, run:

```bash
./gradlew clean build
git status
git add docs/TEST_PLAN.md
git commit -m "docs: document testing strategy and coverage"
git push -u origin test/improve-coverage
```
