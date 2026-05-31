# Changelog

All notable changes to this project are documented in this file.

## [0.1.0] - 2026-05-31

### Added

* Created Java Gradle project structure.
* Added GitHub repository and GitHub Actions CI workflow.
* Added task model with title, description, priority, category, due date, and status.
* Added Builder pattern for task creation.
* Added task service for core business logic.
* Added JSON task storage for local persistence.
* Added command-line interface for task management.
* Added help command.
* Added add command for creating tasks.
* Added list command for viewing saved tasks.
* Added update command for modifying tasks by ID.
* Added delete command for removing tasks by ID.
* Added search command for keyword-based task search.
* Added filter command for priority and status.
* Added sort command for due date and priority.
* Added Strategy pattern for sorting behavior.
* Added statistics command for total, completed, overdue, and category counts.
* Added JSON and CSV export support.
* Added automatic export directory creation.
* Added JUnit tests for model, service, storage, export, statistics, and acceptance behavior.
* Added JaCoCo business-logic coverage reporting.
* Added Checkstyle static analysis configuration.
* Added TDD red-green-refactor evidence for category filtering in the service layer.
* Added design pattern documentation.
* Added test plan documentation.
* Added WBS estimation documentation.
* Added refactoring report documentation.

### Changed

* Improved CLI command help and usage messages.
* Improved task filtering logic in `TaskService`.
* Refactored repeated filtering logic into a shared helper method.
* Updated JaCoCo configuration to focus on business-logic packages.
* Updated documentation to match the final project structure and evidence.

### Verified

* `./gradlew clean build` passes.
* `./gradlew test` passes.
* GitHub Actions CI passes on pull requests and merges.
* Business-logic coverage reached 91% instruction coverage and 75% branch coverage.
* Main CLI workflows were manually tested.
