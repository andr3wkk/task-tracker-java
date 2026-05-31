# Coursework Rubric Self-Assessment

## Project

**Project name:** Personal Task Tracker
**Repository:** https://github.com/andr3wkk/task-tracker-java
**Language:** Java
**Interface:** Command-line application
**Build tool:** Gradle
**CI:** GitHub Actions

---

## Self-Assessment Summary

| Area                                         | Max Points | Self Score | Justification                                                                                                                                                                                                                                                                                                 |
| -------------------------------------------- | ---------: | ---------: | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Functional Requirements                      |         20 |         20 | The application supports create, list, update, delete, search, filtering by priority/status, sorting by due date/priority, JSON persistence, JSON/CSV export, and statistics.                                                                                                                                 |
| Version Control and GitHub Workflow          |         15 |         14 | The repository uses meaningful commits, feature branches, pull requests, self-review comments, and a changelog. The main limitation is that some work was completed in focused sessions rather than evenly across the full course timeline.                                                                   |
| Testing and Coverage                         |         15 |         15 | JUnit tests cover service, model, storage, export, statistics, and acceptance behavior. The project documents black-box, white-box, and acceptance tests. Business-logic coverage is 91% instruction coverage and 75% branch coverage.                                                                        |
| TDD, BDD/ATDD, and Code Review               |         15 |         14 | The project includes a documented red-green-refactor TDD cycle, Given-When-Then acceptance tests, and multiple PRs with self-review comments.                                                                                                                                                                 |
| CI and Static Analysis                       |         10 |         10 | GitHub Actions runs on pushes and pull requests. The workflow builds the project, runs tests, runs Checkstyle, generates coverage, and uploads the JaCoCo report.                                                                                                                                             |
| Design Patterns                              |         10 |         10 | The project uses Builder as a creational pattern and Strategy as a behavioral pattern. Both are documented with file references, explanations, examples, and UML diagrams.                                                                                                                                    |
| Refactoring and Metrics                      |         10 |          9 | The project documents Duplicate Code, Large Class risk, over-complexity risk, and modularity risks. The completed Extract Method refactoring includes before/after metrics, complexity discussion, LOC metrics, maintainability metrics, and verification.                                                    |
| Documentation and Final Submission Readiness |          5 |          5 | README, CHANGELOG, TEST_PLAN, TDD_EVIDENCE, ESTIMATION, DESIGN_PATTERNS, REFACTORING_REPORT, and COURSEWORK_RUBRIC are completed and aligned with the project.                                                                                                                                                |
| **Total**                                    |    **100** |     **97** | The project satisfies the main functional and engineering requirements. The remaining limitations are mostly potential future-improvement items, such as splitting `CommandLineApp` into smaller command handler classes. These limitations do not prevent the project from satisfying the task requirements. |

---

## Detailed Justification

### Functional Requirements

The project satisfies the mandatory functional requirements for the Personal Task Tracker domain:

* Create a task with multiple fields
* List tasks with formatted output
* Update a task by ID
* Delete a task by ID
* Search tasks by keyword
* Filter tasks by priority and status
* Sort tasks by due date and priority
* Persist data between sessions using JSON storage
* Export records to JSON and CSV
* Display statistics such as total tasks, completed tasks, overdue tasks, and category counts

The application also handles common invalid input cases with user-friendly messages.

### Version Control and GitHub Workflow

The repository uses Git and GitHub with a stable `main` branch and feature/documentation branches. Pull requests were used for major changes, and CI checks were reviewed before merging.

Commit messages use a conventional style such as:

```text
feat: ...
test: ...
docs: ...
refactor: ...
ci: ...
```

The project also includes a `CHANGELOG.md` file.

### Testing and Coverage

The test suite uses JUnit 5 and is organized under `src/test/java`.

The test plan documents:

* Black-box tests
* White-box tests
* Acceptance tests
* Coverage target
* Coverage measurement process

The project exceeds the 70% business-logic coverage requirement:

* Business-logic instruction coverage: 91%
* Business-logic branch coverage: 75%

### TDD and Acceptance Tests

The project includes one feature developed with a documented TDD red-green-refactor cycle:

1. Red: failing category filter test
2. Green: minimal category filter implementation
3. Refactor: extracted shared filtering logic

The project also includes Given-When-Then style acceptance tests in `TaskAcceptanceTest`.

### CI and Static Analysis

GitHub Actions is configured to run on pushes and pull requests. The workflow:

* Checks out the repository
* Sets up Java 17
* Runs the Gradle build
* Runs tests
* Runs Checkstyle through Gradle verification
* Generates JaCoCo coverage
* Uploads the JaCoCo HTML report as an artifact

Checkstyle reports no error-level violations in the final project build.

### Design Patterns

The project uses two design patterns from different categories:

* Builder Pattern — creational
* Strategy Pattern — behavioral

The Builder pattern improves task creation readability. The Strategy pattern keeps sorting behavior extensible and avoids putting all sort logic into one conditional block.

### Refactoring and Metrics

The main completed refactoring was extracting shared filtering logic from `TaskService`. This addressed the Duplicate Code smell by replacing repeated stream/filter/toList logic with a shared helper method.

The refactoring report includes:

* Code smells
* Refactoring technique
* Before/after comparison
* LOC metrics
* Cyclomatic complexity discussion
* Maintainability metric based on duplicated filtering pipelines and change points
* Verification through tests, build, coverage, and static analysis

### Documentation

The repository includes the required documentation:

```text
README.md
CHANGELOG.md
docs/TEST_PLAN.md
docs/TDD_EVIDENCE.md
docs/ESTIMATION.md
docs/DESIGN_PATTERNS.md
docs/REFACTORING_REPORT.md
COURSEWORK_RUBRIC.md
```

---

## Final Reflection

This project helped me practice the full software engineering lifecycle on a small but complete application. The most useful part was learning how professional engineering work includes more than just writing code. Testing, CI, documentation, review comments, refactoring, and metrics all helped make the project more reliable and easier to understand.

The biggest challenge was keeping the documentation aligned with the actual code and course requirements. The application itself was not very large, but the engineering evidence required careful organization.

If I continued improving this project, I would split `CommandLineApp` into smaller command handler classes. That would reduce the Large Class risk and make each CLI command easier to test independently.
