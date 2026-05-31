# Final Report: Personal Task Tracker

- **Course:** SDT 301: Software Enterprise: Construction and Transition SP26
- **Project:** Personal Task Tracker
- **Repository:** https://github.com/andr3wkk/task-tracker-java
- **Language:** Java
- **Build Tool:** Gradle
- **Interface:** Command Line Interface
- **CI Platform:** GitHub Actions

---

## 1. Introduction

The Personal Task Tracker is a Java command-line application developed for SDT 301: Software Enterprise: Construction and Transition SP26. The goal of this project was not only to build a working application, but also to demonstrate professional software engineering practices across the full development lifecycle. The project applies version control, feature branching, pull requests, testing, continuous integration, static analysis, design patterns, refactoring, software metrics, estimation, and documentation.

The application domain chosen was a personal task tracker. This domain was suitable because it allowed the project to include meaningful but manageable functionality: creating tasks, listing tasks, updating tasks, deleting tasks, searching, filtering, sorting, exporting records, and displaying statistics. The scope was intentionally kept focused on a command-line interface rather than a graphical user interface so that the main effort could be placed on engineering quality and project evidence.

The course requirements are organized by weekly engineering topics. In this report, I describe them as project phases because my implementation work was completed in a shorter, more intensive period of a little over one week. I followed the same sequence as the course requirements: setup, testing, TDD and reviews, CI and static analysis, design patterns, and refactoring. Therefore, the phases in this report describe how the project satisfies each required topic rather than claiming that each part took a full calendar week.

The final repository contains the working source code, tests, CI configuration, documentation, self-assessment, and engineering evidence required for the project. The project uses Java 17, Gradle, JUnit 5, JaCoCo, Checkstyle, Jackson, and GitHub Actions.

---

## 2. Application Description and Features

The Personal Task Tracker allows users to manage personal task records from the command line. Each task contains multiple fields, including title, description, priority, category, due date, and status. This satisfies the requirement that records contain at least four fields.

The application supports the following user-facing commands:

* `help` — shows the available commands.
* `add` — creates a new task.
* `list` — displays all tasks in formatted output.
* `update` — updates an existing task by ID.
* `delete` — deletes an existing task by ID.
* `search` — searches tasks by keyword.
* `filter` — filters tasks by priority or status.
* `sort` — sorts tasks by due date or priority.
* `export` — exports tasks to JSON or CSV.
* `stats` — displays task statistics.

The application persists task data using a local JSON file, so tasks remain available between sessions. It can also export task records to both JSON and CSV formats. The statistics feature displays total tasks, completed tasks, overdue tasks, and counts by category.

The command-line examples and setup instructions are documented in `README.md`. The commands were manually tested before final submission to make sure the README examples matched the real behavior of the application.

---

## 3. Project Architecture

The project is organized as a Java Gradle application with a clean package structure. The code is not stored in a single monolithic file. Instead, responsibilities are separated into different packages:

* `cli` — command-line input, routing, validation, and output.
* `model` — task model, priority enum, and status enum.
* `service` — business logic for task operations and statistics.
* `storage` — file-based persistence.
* `export` — JSON and CSV export behavior.
* `patterns.strategy` — sorting strategy classes.
* `exception` — custom exception handling.
* test packages — JUnit tests for model, service, storage, export, statistics, and acceptance behavior.

This structure keeps the command-line presentation code separate from the business logic. For example, `CommandLineApp` handles user input and command routing, while `TaskService` handles core task operations. Storage and export logic are also separated into their own packages so that persistence and output formats can be tested independently.

The repository includes supporting documentation in the `docs/` folder:

* `TEST_PLAN.md`
* `TDD_EVIDENCE.md`
* `ESTIMATION.md`
* `DESIGN_PATTERNS.md`
* `REFACTORING_REPORT.md`
* `FINAL_REPORT.md`

The root of the repository also includes `README.md`, `CHANGELOG.md`, and `COURSEWORK_RUBRIC.md`.

---

## 4. Project Phase 1: Agile Process, Git/GitHub, and Build Tools

The first engineering practice applied was project setup and version control. The project was created as a Java Gradle application and connected to a GitHub repository. Gradle was used as the build tool so the application could be compiled, tested, and verified consistently. The Gradle wrapper is included, which means the project can be built without requiring a separate Gradle installation.

The repository uses a stable `main` branch and separate feature or documentation branches for major changes. Examples of branches include CLI feature branches, testing branches, documentation branches, TDD evidence branches, and final compliance branches. Pull requests were used to merge changes back into `main`, and GitHub Actions checks were reviewed before merging.

Commit messages follow a conventional style such as `feat:`, `test:`, `docs:`, `refactor:`, and `ci:`. The repository contains at least twenty meaningful commits across implementation, testing, documentation, refactoring, and CI work. A `CHANGELOG.md` file tracks the major changes in the project.

This part of the project demonstrated the ability to manage a codebase professionally using Git, GitHub, feature branches, pull requests, meaningful commits, and build automation.

---

## 5. Project Phase 2: Unit Testing, Black-Box Testing, and White-Box Testing

Testing was a major part of the project. The project uses JUnit 5 for automated tests. Tests are organized under `src/test/java` and separated by responsibility, including model tests, service tests, storage tests, export tests, statistics tests, and acceptance tests.

The test plan is documented in `docs/TEST_PLAN.md`. It explains the testing strategy, identifies black-box and white-box tests, describes coverage measurement, and explains how JaCoCo is used. Black-box tests were written based on expected behavior from the specification, such as creating tasks, updating tasks, deleting tasks, filtering tasks, exporting tasks, and displaying statistics. White-box tests were written to cover specific internal branches and exception paths, such as missing task IDs and validation-related behavior.

The final test suite includes tests for the model, service layer, statistics service, storage, exporters, and acceptance-level workflows. The JaCoCo report shows that business-logic instruction coverage is 91% and branch coverage is 75%. This exceeds the required 70% business-logic coverage target. The coverage report excludes CLI startup and presentation code so the coverage result focuses on the application’s core logic.

The project also includes acceptance tests written in a Given-When-Then style. These tests verify important user-level workflows and provide BDD/ATDD-style evidence.

---

## 6. Project Phase 3: TDD, BDD/ATDD, Code Reviews, and Agile Estimation

The project includes a documented Test-Driven Development cycle. The feature selected for TDD was filtering tasks by category in the service layer. This was a small but useful feature because task records already include a category field.

The TDD sequence followed the red-green-refactor process:

1. Red — a failing test was committed first.
2. Green — the minimum implementation was added to pass the test.
3. Refactor — shared filtering logic was extracted into a helper method.

The commits documenting this cycle are recorded in `docs/TDD_EVIDENCE.md`:

* `637a2f5 test: add failing category filter test`
* `ec961aa feat: implement category filter`
* `5f8fb48 refactor: extract shared task filtering logic`

The project also includes at least three Given-When-Then acceptance tests in `TaskAcceptanceTest`. These tests show user-level behavior in a clear structure.

Pull requests were used throughout the project, and self-review comments were added to PRs. These comments addressed code organization, command behavior, use of service logic, design pattern use, and documentation quality. This helped demonstrate a code review process even though the project was completed individually.

Agile estimation is documented in `docs/ESTIMATION.md`. The document includes both a WBS-style estimate and Agile user stories with Fibonacci story points. It also records actual effort and reflects on estimation accuracy. The main lesson from estimation was that small CLI features were easier to estimate, while testing, TDD documentation, CI compliance, and refactoring evidence took more effort than expected.

---

## 7. Project Phase 4: Static Analysis and CI/CD Pipelines

The project uses GitHub Actions for continuous integration. The workflow is stored in `.github/workflows/ci.yml`. It runs on pushes and pull requests. The workflow checks out the repository, sets up Java 17, configures Gradle, runs the build, runs tests, runs Checkstyle through Gradle verification, generates the JaCoCo coverage report, and uploads the JaCoCo HTML report as a workflow artifact.

Checkstyle is used as the Java static analysis tool. The Checkstyle configuration is stored under `config/checkstyle/checkstyle.xml`. The final build passes Checkstyle with no error-level violations.

The README includes a CI badge so the build status is visible from the repository front page. GitHub Actions history shows repeated successful workflow runs across feature branches, documentation branches, pull requests, and merges into `main`.

The CI pipeline improved the project because every important change was checked automatically. This reduced the risk of merging code that did not compile, failed tests, or violated the static analysis rules.

---

## 8. Project Phase 5: Design Patterns

The project implements two design patterns from different categories: Builder and Strategy.

The Builder pattern is used in the `Task` model. A task has several fields, including title, description, priority, category, due date, and status. A long constructor with many parameters would be harder to read and easier to misuse. The Builder pattern makes task creation more readable by allowing code such as:

```text
Task task = Task.builder()
        .title("Study")
        .description("Read chapter notes")
        .priority(Priority.HIGH)
        .category("School")
        .dueDate(LocalDate.of(2026, 6, 1))
        .build();
```

This pattern is genuinely useful because it improves readability in application code and tests.

The Strategy pattern is used for sorting tasks. The project includes `TaskSortStrategy`, `SortByDueDateStrategy`, and `SortByPriorityStrategy`. Instead of hard-coding all sorting logic inside the CLI or service layer, the service accepts a strategy. This makes the sorting behavior easier to extend if more sorting options are added later.

The design patterns are documented in `docs/DESIGN_PATTERNS.md`. The document includes the pattern category, purpose, benefits, file references, example usage, and UML diagrams.

---

## 9. Project Phase 6: Refactoring, Metrics, and Code Quality

The main completed refactoring was extracting shared filtering logic from `TaskService`. Before refactoring, several methods repeated the same stream/filter/toList pattern. These methods included search, filter by priority, filter by status, and filter by category. This was identified as a Duplicate Code smell.

The named refactoring technique used was Extract Method. A shared helper method was added:

```text
private List<Task> filterTasks(Predicate<Task> condition)
```

After this change, the public search and filter methods became shorter and reused the helper method. The refactoring reduced duplicated filtering pipelines and made future filtering changes easier.

The refactoring report is documented in `docs/REFACTORING_REPORT.md`. It identifies several code smells or risks:

* Duplicate Code in `TaskService`
* Large Class / Long Method risk in `CommandLineApp`
* Switch Statements / Over-Complexity risk in sorting
* Lack of Modularity / God Object risk if storage, export, statistics, and task logic were combined

The report includes before/after code, LOC metrics, cyclomatic complexity discussion, maintainability metrics, verification steps, and a final reflection. After refactoring, tests and the full Gradle build continued to pass.

One future refactoring opportunity remains: splitting `CommandLineApp` into smaller command handler classes. This was documented but not completed because the current class is still manageable and changing CLI architecture near final submission would add unnecessary risk.

---

## 10. Challenges Encountered and Resolutions

One challenge was keeping the command-line interface simple while supporting many features. The CLI needed to handle add, list, update, delete, search, filter, sort, export, stats, and help commands. This was resolved by keeping business logic in `TaskService`, storage logic in `JsonTaskStorage`, export logic in exporter classes, and sorting logic in strategy classes.

Another challenge was maintaining accurate documentation. The project had several required documents, including a test plan, TDD evidence, estimation document, design patterns document, refactoring report, README, changelog, and self-assessment rubric. The solution was to update documentation in focused commits and then review it against the course requirements.

A third challenge was coverage measurement. The raw coverage report initially included CLI and startup code, which lowered the total coverage percentage. The build configuration was adjusted so that the JaCoCo report focused on business logic packages. This made the coverage report more meaningful and aligned with the project requirement for business-logic coverage.

Another issue was local Java version compatibility. A local Gradle run failed when Java 24 was selected, producing an unsupported class file version error. This was resolved by using Java 17, which matches the project configuration and GitHub Actions workflow.

The final challenge was making sure the repository matched the specification exactly. This required final compliance fixes such as updating CI triggers, uploading the coverage report artifact, adding the CI badge, adding story-point estimation, strengthening refactoring metrics, and adding the coursework self-assessment file.

---

## 11. Self-Assessment

The project self-assessment is documented in `COURSEWORK_RUBRIC.md`. The self-assessed score is 97 out of 100.

The project receives full credit in functional requirements because it implements all mandatory capabilities: create, list, update, delete, search, filter by at least two criteria, sort by at least two fields, persistence, export to two formats, and statistics.

Testing and coverage are also strong. The project includes JUnit tests across the model, service, storage, export, statistics, and acceptance layers. The JaCoCo report shows 91% instruction coverage and 75% branch coverage for business logic, exceeding the 70% target.

The project also demonstrates TDD, BDD/ATDD-style acceptance testing, pull request workflow, static analysis, CI, design patterns, refactoring, and metrics. The remaining limitations are mostly future improvement items rather than missing core requirements. The main future improvement would be splitting `CommandLineApp` into smaller command handler classes if the project grows.

The conservative self-score reflects that the project satisfies the main requirements while still recognizing that further architectural improvement could be done in a larger version.

---

## 12. Conclusion

The Personal Task Tracker project demonstrates the complete software engineering lifecycle on a focused Java command-line application. The application itself is intentionally simple, but the engineering process around it is comprehensive.

The final project includes working application features, file-based persistence, export support, statistics, automated tests, coverage reporting, static analysis, GitHub Actions CI, feature branches, pull requests, self-review comments, TDD evidence, acceptance tests, design patterns, refactoring evidence, metrics, estimation, documentation, and self-assessment.

The most valuable lesson from the project was that professional software engineering is not only about writing code. A reliable project also needs tests, automation, documentation, quality checks, code review, estimation, and maintainability improvements. This project helped practice those skills in a realistic but manageable codebase.

Overall, the Personal Task Tracker satisfies the functional and engineering requirements for SDT 301 and provides clear repository evidence of the development process.
