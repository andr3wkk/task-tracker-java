# Estimation

## 1. Project Overview and Scope

The Personal Task Tracker is a command-line Java application that allows users to manage personal tasks. Users can create, list, update, delete, search, filter, sort, export, and summarize tasks. The application stores task data in a local JSON file so that tasks are saved between program runs.

This WBS assumes a basic student-level Java Gradle application with command-line interaction, JSON persistence, CSV/JSON export, task statistics, automated tests, CI, static analysis, design pattern documentation, estimation documentation, refactoring evidence, and final project documentation.

Advanced features such as a graphical user interface, database storage, cloud synchronization, user accounts, reminders, calendar integration, mobile support, or web deployment are outside the current scope and would need to be estimated separately.

---

## 2. T-shirt Size Reference

The estimates use T-shirt sizing. Estimated time is written as focused work hours, meaning time spent actively working on implementation, testing, debugging, GitHub workflow, or documentation.

| Size | Meaning                                          | Est. Focused Hours |
| ---- | ------------------------------------------------ | -----------------: |
| XS   | Very small task or simple setup                  |              0.5–1 |
| S    | Small and well-understood task                   |                1–2 |
| M    | Medium task with logic, testing, or integration  |              2.5–4 |
| L    | Larger or riskier task with multiple parts       |                5–8 |
| XL   | Too large and should be split into smaller tasks |                 9+ |

No tasks are left as XL because tasks that felt too large were split into smaller Level 3 tasks.

---

## 3. Work Breakdown Structure (WBS)

The WBS uses three levels. Level 1 is the full project, Level 2 contains the main project areas, and Level 3 contains the actual leaf tasks. Only the Level 3 tasks are estimated.

| ID    | Level | Task                                                                                    | Size  | Est. Hours |
| ----- | ----: | --------------------------------------------------------------------------------------- | ----- | ---------: |
| 1.0   |     1 | Personal Task Tracker                                                                   | Group |          — |
| 1.1   |     2 | Project Setup and Planning                                                              | Group |          — |
| 1.1.1 |     3 | Review the course project requirements and choose the Personal Task Tracker idea        | XS    |        0.5 |
| 1.1.2 |     3 | Confirm Java as the implementation language and IntelliJ as the IDE                     | XS    |        0.5 |
| 1.1.3 |     3 | Create Gradle project structure with main and test folders                              | S     |        1.0 |
| 1.1.4 |     3 | Create GitHub repository and connect local project                                      | XS    |        0.5 |
| 1.1.5 |     3 | Add `.gitignore`, README, changelog, and initial documentation files                    | S     |        1.0 |
| 1.2   |     2 | Task Data Model and Core Services                                                       | Group |          — |
| 1.2.1 |     3 | Create task model with title, description, priority, category, due date, and status     | M     |        2.5 |
| 1.2.2 |     3 | Add Builder pattern for task creation                                                   | S     |        1.5 |
| 1.2.3 |     3 | Create task service for create, update, delete, search, filter, and sort operations     | M     |        3.5 |
| 1.2.4 |     3 | Add custom exception handling for missing task IDs                                      | S     |        1.0 |
| 1.3   |     2 | Command-Line Interface Features                                                         | Group |          — |
| 1.3.1 |     3 | Implement help command and basic command routing                                        | S     |        1.0 |
| 1.3.2 |     3 | Implement add command                                                                   | S     |        1.5 |
| 1.3.3 |     3 | Implement list command                                                                  | S     |        1.5 |
| 1.3.4 |     3 | Implement update command by task ID                                                     | M     |        2.5 |
| 1.3.5 |     3 | Implement delete command by task ID                                                     | S     |        1.5 |
| 1.3.6 |     3 | Implement search command                                                                | S     |        1.5 |
| 1.3.7 |     3 | Implement filter command for priority and status                                        | M     |        2.5 |
| 1.3.8 |     3 | Implement sort command for due date and priority                                        | M     |        2.5 |
| 1.3.9 |     3 | Add validation and error messages for invalid or missing command arguments              | M     |        3.0 |
| 1.4   |     2 | Persistence, Export, and Statistics                                                     | Group |          — |
| 1.4.1 |     3 | Implement JSON task storage and loading from file                                       | M     |        2.5 |
| 1.4.2 |     3 | Save tasks after create, update, and delete operations                                  | S     |        1.5 |
| 1.4.3 |     3 | Implement export to JSON                                                                | S     |        1.5 |
| 1.4.4 |     3 | Implement export to CSV                                                                 | S     |        1.5 |
| 1.4.5 |     3 | Create output directory automatically for exports                                       | XS    |        0.5 |
| 1.4.6 |     3 | Implement statistics for total, completed, overdue, and category counts                 | M     |        2.5 |
| 1.5   |     2 | Sorting Design Pattern                                                                  | Group |          — |
| 1.5.1 |     3 | Create TaskSortStrategy interface                                                       | S     |        1.0 |
| 1.5.2 |     3 | Implement SortByDueDateStrategy                                                         | S     |        1.0 |
| 1.5.3 |     3 | Implement SortByPriorityStrategy                                                        | S     |        1.0 |
| 1.5.4 |     3 | Connect sort command and TaskService to strategy classes                                | S     |        1.5 |
| 1.6   |     2 | Testing and Coverage                                                                    | Group |          — |
| 1.6.1 |     3 | Add service tests for create, update, delete, search, filter, sort, and exception paths | M     |        3.5 |
| 1.6.2 |     3 | Add tests for statistics service                                                        | S     |        1.5 |
| 1.6.3 |     3 | Add tests for JSON storage                                                              | S     |        1.5 |
| 1.6.4 |     3 | Add tests for JSON and CSV exporters                                                    | S     |        1.5 |
| 1.6.5 |     3 | Add Given-When-Then acceptance tests                                                    | S     |        2.0 |
| 1.6.6 |     3 | Configure JaCoCo coverage reporting for business logic                                  | S     |        2.0 |
| 1.6.7 |     3 | Perform manual CLI testing for the main commands                                        | XS    |        1.0 |
| 1.7   |     2 | CI, Static Analysis, and GitHub Workflow                                                | Group |          — |
| 1.7.1 |     3 | Configure GitHub Actions CI workflow                                                    | S     |        1.5 |
| 1.7.2 |     3 | Configure Checkstyle and Gradle build checks                                            | S     |        2.0 |
| 1.7.3 |     3 | Use feature branches for major work                                                     | S     |        1.0 |
| 1.7.4 |     3 | Create pull requests with CI checks before merging                                      | S     |        1.5 |
| 1.7.5 |     3 | Add meaningful review comments on pull requests                                         | S     |        1.5 |
| 1.8   |     2 | Project Documentation                                                                   | Group |          — |
| 1.8.1 |     3 | Document testing strategy and coverage in `TEST_PLAN.md`                                | S     |        2.0 |
| 1.8.2 |     3 | Document Builder and Strategy pattern usage in `DESIGN_PATTERNS.md`                     | S     |        2.0 |
| 1.8.3 |     3 | Document WBS estimation in `ESTIMATION.md`                                              | S     |        2.0 |
| 1.8.4 |     3 | Document TDD evidence in `TDD_EVIDENCE.md`                                              | M     |        3.0 |
| 1.8.5 |     3 | Document refactoring work in `REFACTORING_REPORT.md`                                    | L     |        5.0 |
| 1.8.6 |     3 | Update README and changelog as the project changes                                      | XS    |        1.0 |
| 1.9   |     2 | Refactoring and Final Submission                                                        | Group |          — |
| 1.9.1 |     3 | Identify code smells and collect before/after metrics                                   | L     |        5.0 |
| 1.9.2 |     3 | Apply selected refactorings while keeping tests passing                                 | M     |        4.0 |
| 1.9.3 |     3 | Prepare final project report and self-assessment                                        | L     |        6.0 |
| 1.9.4 |     3 | Perform final cleanup, build verification, and submission check                         | XS    |        1.0 |

---

## 4. Effort by Main Area

| Main Area                                | Est. Focused Hours |
| ---------------------------------------- | -----------------: |
| Project Setup and Planning               |                3.5 |
| Task Data Model and Core Services        |                8.5 |
| Command-Line Interface Features          |               19.0 |
| Persistence, Export, and Statistics      |               10.0 |
| Sorting Design Pattern                   |                4.5 |
| Testing and Coverage                     |               13.0 |
| CI, Static Analysis, and GitHub Workflow |                7.5 |
| Project Documentation                    |               15.0 |
| Refactoring and Final Submission         |               16.0 |
| **Total**                                |           **97.0** |

---

## 5. Timeline and Milestones

The course timeline spreads the project across six weeks. In practice, this project was completed faster than the full timeline because the application scope was intentionally small, the project was CLI-based, and several small tasks were completed during the same focused work sessions.

The six-week timeline still helped organize the project into checkpoints.

| Week   | Milestone              | What Should Be Done                                                                                    | Actual Project Status                                                                                                                            |
| ------ | ---------------------- | ------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------ |
| Week 1 | Requirements and setup | Understand project requirements and optionally set up the repository.                                  | Project idea selected, Java chosen, Gradle structure created, and repository initialized.                                                        |
| Week 2 | Early implementation   | Attend testing lectures and optionally start core features.                                            | Core model, JSON storage, and first CLI commands were started.                                                                                   |
| Week 3 | Checkpoint 1           | Repository created, build tool configured, CRUD implemented, first unit tests written, push to GitHub. | Repository, Gradle build, CI, create/list/update/delete, and GitHub workflow were completed.                                                     |
| Week 4 | Checkpoint 2           | TDD evidence, BDD scenarios, CI pipeline, static analysis, and at least 2 PRs.                         | Multiple PRs were created, CI was passing, review comments were added, and acceptance tests were added.                                          |
| Week 5 | Checkpoint 3           | Design patterns implemented and documented, test coverage at least 70%, all CI checks passing.         | Builder and Strategy patterns were implemented and documented. Business-logic coverage reached 91% instruction coverage and 75% branch coverage. |
| Week 6 | Final Submission       | Refactoring completed with metrics, documentation finalized, and self-assessment submitted.            | Most implementation, testing, CI, and documentation are complete. Refactoring report, final report, and final submission review remain.          |

The actual calendar time was shorter than six weeks, but the project still follows the course milestone structure. The timeline should be understood as a planned course schedule, not as proof that each milestone requires a full week of work.

---

## 6. Risk Register

| Risk                              | Why It Matters                                                                                                     | Planned Response                                                                            |
| --------------------------------- | ------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------- |
| CLI class becoming too large      | Many commands in one CLI class can make the code harder to maintain.                                               | Keep business logic in service classes and consider refactoring command handling if needed. |
| JSON persistence errors           | Task data should not be lost between program runs.                                                                 | Test save/load behavior and handle missing files safely.                                    |
| Invalid user input                | Users may enter invalid IDs, dates, priorities, statuses, or commands.                                             | Add validation and clear error messages.                                                    |
| Low test coverage                 | The project requires strong testing evidence and at least 70% business-logic coverage.                             | Add JUnit tests for services, storage, exporters, statistics, and acceptance scenarios.     |
| CI failure near submission        | A failing build would weaken the final project evidence.                                                           | Run Gradle locally before each push and check GitHub Actions after pull requests.           |
| Scope creep                       | Extra features such as GUI, database, notifications, accounts, or web deployment could make the project too large. | Keep the first version focused on the required CLI task tracker features.                   |
| Documentation falling behind code | The project is graded partly on process evidence and documentation.                                                | Update documentation after implementation and testing work is completed.                    |

---

## 7. Summary

This WBS breaks the Personal Task Tracker into 46 leaf tasks across the main project areas: setup, core services, CLI features, persistence/export/statistics, design patterns, testing, CI/static analysis, documentation, refactoring, and final submission. This covers the main deliverables of the project and follows the 100% rule.

The total estimated effort is approximately **97 focused hours**. The largest areas are **Command-Line Interface Features** with 19 focused hours, **Refactoring and Final Submission** with 16 focused hours, and **Project Documentation** with 15 focused hours. This makes sense because the project is not only about writing working application code. It also requires tests, CI, coverage evidence, pull requests, review comments, design documentation, estimation, refactoring evidence, and final reporting.

The final size breakdown is:

| Size | Number of Leaf Tasks |
| ---- | -------------------: |
| XS   |                    8 |
| S    |                   28 |
| M    |                    7 |
| L    |                    3 |
| XL   |                    0 |

No XL tasks remain because larger work was split into smaller implementation, testing, or documentation tasks.

The project was completed faster than the course’s full six-week timeline because many small tasks were completed during the same focused work sessions. However, the six-week timeline was still useful because it organized the work into checkpoints: setup, core features, CI/PR workflow, testing, design patterns, coverage, refactoring, and final submission.

The main risks are CLI complexity, persistence errors, invalid input, low coverage, CI failure, scope creep, and documentation falling behind the code. These risks can be reduced by keeping the design simple, separating business logic from CLI code, testing important branches, running CI frequently, and updating documentation throughout the project.
