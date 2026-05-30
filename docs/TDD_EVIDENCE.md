# TDD Evidence

## Overview

This document records one feature developed using Test-Driven Development (TDD). The selected feature was filtering tasks by category in the business logic layer.

The TDD process followed the red-green-refactor cycle:

1. **Red** — write a failing test before implementation.
2. **Green** — implement the minimum code needed to pass the test.
3. **Refactor** — improve the code structure while keeping tests passing.

---

## Feature Developed with TDD

### Feature

Filter tasks by category.

### Purpose

The task tracker already supported filtering by priority and status. Adding category filtering was a small, useful extension because tasks already contain a category field such as `School`, `Health`, or `Home`.

### Main Files Involved

```text
src/test/java/edu/asu/sdt/tasktracker/service/TaskServiceTest.java
src/main/java/edu/asu/sdt/tasktracker/service/TaskService.java
```

---

## Red Step

### Commit

```text
637a2f5 test: add failing category filter test
```

### What Was Done

A new test was added to `TaskServiceTest` before the feature existed in `TaskService`.

The test expected the service to return only tasks matching the requested category:

```text
filterByCategoryReturnsOnlyMatchingTasks
```

### Expected Failure

The test failed because the method `filterByCategory(String)` did not exist yet.

Error shown by Gradle:

```text
cannot find symbol
method filterByCategory(String)
location: variable service of type TaskService
```

This was the expected red step because the test described behavior that had not been implemented.

---

## Green Step

### Commit

```text
ec961aa feat: implement category filter
```

### What Was Done

The minimum implementation was added to `TaskService`:

```text
public List<Task> filterByCategory(String category) {
    return tasks.stream()
            .filter(task -> task.getCategory().equalsIgnoreCase(category))
            .toList();
}
```

### Result

After this implementation, the new category filter test passed.

This completed the green step because the code now satisfied the failing test.

---

## Refactor Step

### Commit

```text
5f8fb48 refactor: extract shared task filtering logic
```

### What Was Done

The filtering logic for search, priority, status, and category was cleaned up by extracting a shared helper method:

```text
private List<Task> filterTasks(Predicate<Task> condition) {
    return tasks.stream()
            .filter(condition)
            .toList();
}
```

The public methods then reused this helper:

```text
public List<Task> filterByCategory(String category) {
    return filterTasks(task -> task.getCategory().equalsIgnoreCase(category));
}
```

### Why This Refactoring Was Useful

Before refactoring, multiple methods repeated the same stream/filter/toList structure. The refactoring reduced duplication and made future filters easier to add.

### Result

After the refactor, the tests still passed:

```text
./gradlew test
./gradlew clean build
```

Both commands completed successfully.

---

## TDD Summary

| Step     | Commit                                                  | Result                                                |
| -------- | ------------------------------------------------------- | ----------------------------------------------------- |
| Red      | `637a2f5 test: add failing category filter test`        | Test failed because `filterByCategory` did not exist. |
| Green    | `ec961aa feat: implement category filter`               | Test passed after adding the minimum implementation.  |
| Refactor | `5f8fb48 refactor: extract shared task filtering logic` | Code was cleaned up and tests continued to pass.      |

This sequence demonstrates a complete TDD cycle: a failing test was written first, the feature was implemented to pass the test, and then the code was improved without changing behavior.
