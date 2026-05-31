# Estimation

## 1. Project Overview and Scope

The Personal Task Tracker is a command-line Java application that allows users to manage personal tasks. Users can create, list, update, delete, search, filter, sort, export, and summarize tasks. The application stores task data in a local JSON file so that tasks are saved between program runs.

This estimation document includes two estimation views:

1. A Work Breakdown Structure (WBS) with T-shirt sizes and focused work-hour estimates.
2. Agile user stories with Fibonacci story points and actual effort reflection.

This project assumes a basic student-level Java Gradle application with command-line interaction, JSON persistence, CSV/JSON export, task statistics, automated tests, CI, static analysis, design pattern documentation, TDD evidence, refactoring evidence, and final project documentation.

Advanced features such as a graphical user interface, database storage, cloud synchronization, user accounts, reminders, calendar integration, mobile support, or web deployment are outside the current scope and would need to be estimated separately.

---

## 2. T-shirt Size Reference

The WBS estimates use T-shirt sizing. Estimated time is written as focused work hours, meaning time spent actively working on implementation, testing, debugging, GitHub workflow, or documentation.

| Size | Meaning                                          | Est. Focused Hours |
| ---- | ------------------------------------------------ | -----------------: |
| XS   | Very small task or simple setup                  |           0.25–0.5 |
| S    | Small and well-understood task                   |             0.75–1 |
| M    | Medium task with logic, testing, or integration  |             1.25–2 |
| L    | Larger or riskier task with multiple parts       |              2.5–4 |
| XL   | Too large and should be split into smaller tasks |                 4+ |

No tasks are left as XL because tasks that felt too large were split into smaller Level 3 tasks.

---

## 3. Work Breakdown Structure (WBS)

The WBS uses three levels. Level 1 is the full project, Level 2 contains the main project areas, and Level 3 contains the actual leaf tasks. Only the Level 3 tasks are estimated.

| ID    | Level | Task                                                                                         | Size  | Est. Hours |
| ----- | ----: | -------------------------------------------------------------------------------------------- | ----- | ---------: |
| 1.0   |     1 | Personal Task Tracker                                                                        | Group |          — |
| 1.1   |     2 | Project Setup and Planning                                                                   | Group |          — |
| 1.1.1 |     3 | Review project requirements and choose the Personal Task Tracker domain                      | XS    |       0.25 |
| 1.1.2 |     3 | Confirm Java, Gradle, and IntelliJ as the development setup                                  | XS    |       0.50 |
| 1.1.3 |     3 | Create the Gradle project structure with main and test folders                               | S     |       0.75 |
| 1.1.4 |     3 | Create GitHub repository and connect the local project                                       | XS    |       0.50 |
| 1.1.5 |     3 | Add `.gitignore`, README, changelog, and starter documentation files                         | S     |       0.75 |
| 1.2   |     2 | Task Data Model and Core Services                                                            | Group |          — |
| 1.2.1 |     3 | Create task model with title, description, priority, category, due date, and status          | S     |       1.00 |
| 1.2.2 |     3 | Add Builder pattern for readable task creation                                               | S     |       0.75 |
| 1.2.3 |     3 | Implement task service logic for create, update, delete, search, filter, and sort behavior   | M     |       1.50 |
| 1.2.4 |     3 | Add custom exception handling for missing task IDs                                           | XS    |       0.50 |
| 1.3   |     2 | Command-Line Interface Features                                                              | Group |          — |
| 1.3.1 |     3 | Implement help command and basic command routing                                             | XS    |       0.50 |
| 1.3.2 |     3 | Implement add and list commands                                                              | S     |       1.00 |
| 1.3.3 |     3 | Implement update and delete commands by task ID                                              | M     |       1.50 |
| 1.3.4 |     3 | Implement search, filter, and sort commands                                                  | M     |       1.25 |
| 1.3.5 |     3 | Implement export and statistics commands                                                     | S     |       1.00 |
| 1.3.6 |     3 | Add validation and user-friendly error messages for common invalid input                     | S     |       0.75 |
| 1.4   |     2 | Persistence, Export, and Statistics                                                          | Group |          — |
| 1.4.1 |     3 | Implement JSON task storage and loading from file                                            | M     |       1.25 |
| 1.4.2 |     3 | Save tasks after create, update, and delete operations                                       | S     |       0.75 |
| 1.4.3 |     3 | Implement export to JSON and CSV                                                             | S     |       1.00 |
| 1.4.4 |     3 | Implement statistics for total, completed, overdue, and category counts                      | S     |       1.00 |
| 1.5   |     2 | Testing and Coverage                                                                         | Group |          — |
| 1.5.1 |     3 | Add service tests for create, update, delete, search, filter, sort, and exception paths      | M     |       1.50 |
| 1.5.2 |     3 | Add tests for statistics, JSON storage, and exporters                                        | S     |       1.00 |
| 1.5.3 |     3 | Add Given-When-Then acceptance tests                                                         | S     |       0.75 |
| 1.5.4 |     3 | Configure JaCoCo coverage reporting for business logic                                       | S     |       0.75 |
| 1.5.5 |     3 | Write and update `TEST_PLAN.md` with black-box, white-box, acceptance, and coverage evidence | S     |       1.00 |
| 1.6   |     2 | CI, Static Analysis, TDD, and Design Documentation                                           | Group |          — |
| 1.6.1 |     3 | Configure GitHub Actions CI and Checkstyle static analysis                                   | S     |       1.00 |
| 1.6.2 |     3 | Use feature branches, pull requests, CI checks, and self-review comments                     | S     |       0.75 |
| 1.6.3 |     3 | Complete TDD red-green-refactor evidence for category filtering                              | S     |       1.00 |
| 1.6.4 |     3 | Document Builder and Strategy pattern usage in `DESIGN_PATTERNS.md`                          | S     |       0.75 |
| 1.6.5 |     3 | Document WBS and story-point estimation in `ESTIMATION.md`                                   | S     |       0.75 |
| 1.6.6 |     3 | Document refactoring work and code smells in `REFACTORING_REPORT.md`                         | M     |       1.25 |
| 1.7   |     2 | Final Submission Preparation                                                                 | Group |          — |
| 1.7.1 |     3 | Polish README and CHANGELOG                                                                  | S     |       0.75 |
| 1.7.2 |     3 | Prepare final project report                                                                 | L     |       2.50 |
| 1.7.3 |     3 | Complete self-assessment checklist                                                           | S     |       0.75 |
| 1.7.4 |     3 | Perform final cleanup, build verification, and submission review                             | S     |       0.75 |

---

## 4. Effort by Main Area

| Main Area                                          | Est. Focused Hours |
| -------------------------------------------------- | -----------------: |
| Project Setup and Planning                         |               2.75 |
| Task Data Model and Core Services                  |               3.75 |
| Command-Line Interface Features                    |               6.00 |
| Persistence, Export, and Statistics                |               4.00 |
| Testing and Coverage                               |               5.00 |
| CI, Static Analysis, TDD, and Design Documentation |               5.50 |
| Final Submission Preparation                       |               4.75 |
| **Total**                                          |          **31.75** |

The total is slightly above the course’s general 20–30 hour estimate because this WBS includes final reporting, self-assessment, documentation polishing, and compliance fixes as separate visible tasks.

---

## 5. User Stories, Story Points, and Actual Effort

The WBS above estimates detailed work in focused hours. This section estimates the main features as Agile user stories using Fibonacci story points.

Story points are relative estimates, not exact hours. They consider implementation complexity, uncertainty, testing effort, validation, integration, and documentation.

| ID    | User Story                                                                                                               | Acceptance Criteria                                                                                                                            | Story Points | Actual Effort   | Reflection                                                                                                                           |
| ----- | ------------------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------------------------------------------------------- | -----------: | --------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| US-01 | As a user, I want to add a task so that I can track something I need to complete.                                        | A task can be created with title, description, priority, category, and due date. The task is saved after creation.                             |            3 | Medium          | Estimate was accurate because the command required parsing, task creation, validation, and persistence.                              |
| US-02 | As a user, I want to list all tasks so that I can see my saved records.                                                  | The `list` command displays saved tasks in formatted table output.                                                                             |            2 | Small           | This was straightforward after the task model and storage were available.                                                            |
| US-03 | As a user, I want to update a task by ID so that I can correct task information.                                         | The user can update title, description, priority, category, due date, and status by ID. Invalid IDs and invalid values are handled gracefully. |            5 | Medium/Large    | This took more effort than expected because update touches many fields and needs several validation paths.                           |
| US-04 | As a user, I want to delete a task by ID so that I can remove tasks I no longer need.                                    | An existing task can be deleted by ID. Invalid IDs are handled without crashing.                                                               |            2 | Small           | Estimate was accurate because deletion reused service logic and persistence behavior.                                                |
| US-05 | As a user, I want to search tasks by keyword so that I can quickly find matching tasks.                                  | Search checks task text fields and returns matching tasks.                                                                                     |            3 | Small/Medium    | This was easier because the task model already supported keyword matching.                                                           |
| US-06 | As a user, I want to filter tasks by priority so that I can focus on important tasks.                                    | The `filter priority` command returns only tasks with the selected priority.                                                                   |            2 | Small           | Estimate was accurate because priority is an enum and the filtering path was simple.                                                 |
| US-07 | As a user, I want to filter tasks by status so that I can focus on TODO, IN_PROGRESS, or DONE tasks.                     | The `filter status` command returns only tasks with the selected status.                                                                       |            2 | Small           | This was similar to priority filtering and reused the same command structure.                                                        |
| US-08 | As a user, I want to sort tasks by due date so that I can see the earliest tasks first.                                  | The `sort dueDate` command displays tasks ordered by due date.                                                                                 |            3 | Medium          | This required connecting CLI routing, service behavior, and a sorting strategy.                                                      |
| US-09 | As a user, I want to sort tasks by priority so that I can see high-priority tasks first.                                 | The `sort priority` command displays tasks ordered by priority.                                                                                |            3 | Medium          | This was similar to due-date sorting and benefited from the Strategy pattern.                                                        |
| US-10 | As a user, I want task data saved between sessions so that my tasks are not lost.                                        | Tasks are loaded from a JSON file when the app starts and saved after changes. Missing files are handled safely.                               |            5 | Medium/Large    | Persistence was one of the more important features because it required JSON serialization, file handling, and storage tests.         |
| US-11 | As a user, I want to export tasks to JSON and CSV so that I can share or back up task data.                              | The app exports records to both JSON and CSV and creates the output folder if needed.                                                          |            5 | Medium          | The estimate was reasonable because two export formats were required, but exporter classes kept the logic organized.                 |
| US-12 | As a user, I want to see task statistics so that I can understand my workload.                                           | The `stats` command shows total tasks, completed tasks, overdue tasks, and tasks by category.                                                  |            3 | Medium          | This required separate summary logic but was not overly complex.                                                                     |
| US-13 | As a developer, I want automated tests so that I can verify the application works correctly.                             | Unit tests, storage tests, exporter tests, statistics tests, and acceptance tests are included and passing.                                    |            5 | Large           | This took significant effort because the project required both black-box and white-box evidence.                                     |
| US-14 | As a developer, I want coverage reporting so that I can measure test quality.                                            | JaCoCo measures business-logic coverage and the project exceeds the 70% target.                                                                |            3 | Medium          | The initial coverage was dragged down by CLI code, so JaCoCo was configured to focus on business logic.                              |
| US-15 | As a developer, I want CI and static analysis so that every change is checked automatically.                             | GitHub Actions runs build, tests, Checkstyle, and coverage on pushes and pull requests.                                                        |            5 | Medium/Large    | CI required workflow setup, Checkstyle integration, and a later compliance fix to trigger on all pushes and upload coverage reports. |
| US-16 | As a developer, I want design patterns documented so that design decisions are clear.                                    | Builder and Strategy patterns are documented with explanations, file references, examples, and UML diagrams.                                   |            3 | Medium          | The patterns were already useful in the app, so the main effort was documenting them clearly.                                        |
| US-17 | As a developer, I want TDD evidence so that the red-green-refactor process is visible.                                   | `TDD_EVIDENCE.md` documents a failing test, passing implementation, and refactoring commits.                                                   |            5 | Medium/Large    | This required careful commit sequencing and documentation.                                                                           |
| US-18 | As a developer, I want refactoring evidence so that code quality improvement is documented.                              | `REFACTORING_REPORT.md` identifies code smells, refactorings, metrics, and verification steps.                                                 |            5 | Large           | This was larger because the report needed to connect code changes to course smell categories and quality metrics.                    |
| US-19 | As a developer, I want project documentation so that the repository can be reviewed and graded clearly.                  | README, changelog, test plan, design patterns, estimation, TDD evidence, and refactoring documents are completed.                              |            5 | Large           | Documentation took significant effort because the project grade depends heavily on engineering evidence, not only code.              |
| US-20 | As a student, I want a final report and self-assessment so that I can submit the project according to the specification. | A 5–10 page final report and self-assessment checklist are prepared for submission.                                                            |            5 | Remaining/Large | This remains as the final submission task after the repository implementation and documentation are complete.                        |

### Story Point Summary

| Category                                   | Story Points |
| ------------------------------------------ | -----------: |
| Core task features                         |           27 |
| Persistence, export, and statistics        |           13 |
| Testing, coverage, CI, and quality process |           23 |
| Documentation and final submission         |           15 |
| **Total**                                  |       **78** |

### Estimation Reflection

The story point estimates were mostly accurate. Simple commands such as listing, deleting, and filtering were smaller. Features involving validation, persistence, update logic, testing, CI, and documentation were larger.

The most underestimated areas were testing, TDD evidence, and refactoring documentation. These tasks required more care because they were not only about making the app work; they also had to demonstrate process evidence for grading.

The project was completed faster than the full course timeline because several smaller tasks were completed in the same focused work sessions. However, the relative estimates still make sense: implementation tasks were generally smaller than testing, CI, refactoring, and final documentation tasks.

---

## 6. Timeline and Milestones

The course timeline spreads the project across six weeks. In practice, this project was completed faster than the full timeline because the application scope was intentionally small, the project was CLI-based, and several small tasks were completed during the same focused work sessions.

The six-week timeline still helped organize the project into checkpoints.

| Week   | Milestone              | What Should Be Done                                                                                    | Actual Project Status                                                                                                                                                                  |
| ------ | ---------------------- | ------------------------------------------------------------------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Week 1 | Requirements and setup | Understand project requirements and optionally set up the repository.                                  | Project idea selected, Java chosen, Gradle structure created, and repository initialized.                                                                                              |
| Week 2 | Early implementation   | Attend testing lectures and optionally start core features.                                            | Core model, JSON storage, and first CLI commands were started.                                                                                                                         |
| Week 3 | Checkpoint 1           | Repository created, build tool configured, CRUD implemented, first unit tests written, push to GitHub. | Repository, Gradle build, CI, create/list/update/delete, and GitHub workflow were completed.                                                                                           |
| Week 4 | Checkpoint 2           | TDD evidence, BDD scenarios, CI pipeline, static analysis, and at least 2 PRs.                         | Multiple PRs were created, CI was passing, review comments were added, and acceptance tests were added.                                                                                |
| Week 5 | Checkpoint 3           | Design patterns implemented and documented, test coverage at least 70%, all CI checks passing.         | Builder and Strategy patterns were implemented and documented. Business-logic coverage reached 91% instruction coverage and 75% branch coverage.                                       |
| Week 6 | Final Submission       | Refactoring completed with metrics, documentation finalized, and self-assessment submitted.            | Implementation, testing, CI, TDD evidence, design patterns, estimation, and refactoring documentation are complete. Final report and self-assessment remain as final submission items. |

The actual calendar time was shorter than six weeks, but the project still follows the course milestone structure. The timeline should be understood as a planned course schedule, not as proof that each milestone requires a full week of work.

---

## 7. Risk Register

| Risk                              | Why It Matters                                                                                                     | Planned Response                                                                                     |
| --------------------------------- | ------------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------------- |
| CLI class becoming too large      | Many commands in one CLI class can make the code harder to maintain.                                               | Keep business logic in service classes and consider extracting command handlers in a future version. |
| JSON persistence errors           | Task data should not be lost between program runs.                                                                 | Test save/load behavior and handle missing files safely.                                             |
| Invalid user input                | Users may enter invalid IDs, dates, priorities, statuses, or commands.                                             | Add validation and clear error messages.                                                             |
| Low test coverage                 | The project requires strong testing evidence and at least 70% business-logic coverage.                             | Add JUnit tests for services, storage, exporters, statistics, and acceptance scenarios.              |
| CI failure near submission        | A failing build would weaken the final project evidence.                                                           | Run Gradle locally before each push and check GitHub Actions after pull requests.                    |
| Scope creep                       | Extra features such as GUI, database, notifications, accounts, or web deployment could make the project too large. | Keep the first version focused on the required CLI task tracker features.                            |
| Documentation falling behind code | The project is graded partly on process evidence and documentation.                                                | Update documentation after implementation and testing work is completed.                             |

---

## 8. Summary

This WBS breaks the Personal Task Tracker into 34 Level 3 leaf tasks across the main project areas: project setup, core services, CLI features, persistence/export/statistics, testing/coverage, CI/static analysis/TDD/design documentation, and final submission preparation. This covers the main deliverables of the project and follows the 100% rule.

The total WBS estimate is approximately **31.75 focused hours**. This is close to the course’s general effort expectation, while still accounting for final documentation and compliance work. The largest estimated areas are **Command-Line Interface Features**, **CI / Static Analysis / TDD / Design Documentation**, and **Testing and Coverage**. This makes sense because the project is not only about implementing app features. It also requires engineering evidence such as tests, coverage, CI, pull requests, review comments, design patterns, TDD, refactoring, and documentation.

The final WBS size breakdown is:

| Size | Number of Leaf Tasks |
| ---- | -------------------: |
| XS   |                    8 |
| S    |                   19 |
| M    |                    6 |
| L    |                    1 |
| XL   |                    0 |

No XL tasks remain because larger work was split into smaller implementation, testing, or documentation tasks.

The project was completed faster than the full six-week timeline because many small tasks were completed during the same focused work sessions. However, the six-week timeline was still useful because it organized the work into checkpoints: setup, core features, CI/PR workflow, testing, design patterns, coverage, refactoring, and final submission.

The main risks are CLI complexity, persistence errors, invalid input, low coverage, CI failure, scope creep, and documentation falling behind the code. These risks were reduced by keeping the design simple, separating business logic from CLI code, testing important branches, running CI frequently, and updating documentation throughout the project.
