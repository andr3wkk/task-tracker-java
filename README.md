# Personal Task Tracker

![Java CI](https://github.com/andr3wkk/task-tracker-java/actions/workflows/ci.yml/badge.svg)

A Java command-line task tracker created for the Software Enterprise Construction and Transition project.

The application allows users to create, list, update, delete, search, filter, sort, export, and summarize personal tasks. Task data is saved locally in JSON format so tasks remain available between program runs.

---

## Features

* Create tasks with title, description, priority, category, and due date
* List saved tasks in a readable table
* Update existing tasks by ID
* Delete tasks by ID
* Search tasks by keyword
* Filter tasks by priority or status
* Sort tasks by due date or priority
* Save and load tasks using JSON persistence
* Export tasks to JSON or CSV
* Display task statistics
* Run automated JUnit tests
* Generate JaCoCo coverage reports
* Run Checkstyle static analysis
* Build and test automatically with GitHub Actions

---

## Technologies Used

* Java 17
* Gradle
* JUnit 5
* JaCoCo
* Checkstyle
* Jackson JSON library
* GitHub Actions

---

## Project Structure

```text
task-tracker-java/
├── .github/
│   └── workflows/
│       └── ci.yml
├── config/
│   └── checkstyle/
│       └── checkstyle.xml
├── docs/
│   ├── DESIGN_PATTERNS.md
│   ├── ESTIMATION.md
│   ├── REFACTORING_REPORT.md
│   ├── TDD_EVIDENCE.md
│   └── TEST_PLAN.md
├── src/
│   ├── main/java/edu/asu/sdt/tasktracker/
│   │   ├── cli/
│   │   ├── exception/
│   │   ├── export/
│   │   ├── model/
│   │   ├── patterns/strategy/
│   │   ├── service/
│   │   ├── storage/
│   │   └── Main.java
│   └── test/java/edu/asu/sdt/tasktracker/
│       ├── acceptance/
│       ├── export/
│       ├── model/
│       ├── service/
│       └── storage/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── CHANGELOG.md
└── README.md
```

---

## Requirements

Java 17 is required.

Check your Java version:

```bash
java -version
```

The project uses the Gradle wrapper, so a separate Gradle installation is not required.

---

## Build the Project

```bash
./gradlew clean build
```

This compiles the project, runs tests, and runs configured verification tasks.

---

## Run the Application

General format:

```bash
./gradlew run --args="<command>"
```

### Help

```bash
./gradlew run --args="help"
```

### Add a Task

```bash
./gradlew run --args="add Study Read HIGH School 2026-06-01"
```

Format:

```text
add <title> <description> <priority> <category> <dueDate>
```

Supported priorities:

```text
LOW
MEDIUM
HIGH
```

### List Tasks

```bash
./gradlew run --args="list"
```

### Update a Task

```bash
./gradlew run --args="update 1 Study Reading MEDIUM School 2026-06-10 IN_PROGRESS"
```

Format:

```text
update <id> <title> <description> <priority> <category> <dueDate> <status>
```

Supported statuses:

```text
TODO
IN_PROGRESS
DONE
```

### Delete a Task

```bash
./gradlew run --args="delete 1"
```

### Search Tasks

```bash
./gradlew run --args="search Study"
```

### Filter Tasks

Filter by priority:

```bash
./gradlew run --args="filter priority HIGH"
```

Filter by status:

```bash
./gradlew run --args="filter status TODO"
```

### Sort Tasks

Sort by due date:

```bash
./gradlew run --args="sort dueDate"
```

Sort by priority:

```bash
./gradlew run --args="sort priority"
```

### Show Statistics

```bash
./gradlew run --args="stats"
```

### Export Tasks

Export to JSON:

```bash
./gradlew run --args="export json exports/tasks.json"
```

Export to CSV:

```bash
./gradlew run --args="export csv exports/tasks.csv"
```

---

## Testing

Run all tests:

```bash
./gradlew test
```

Run full build:

```bash
./gradlew clean build
```

Generate JaCoCo coverage report:

```bash
./gradlew clean test jacocoTestReport
```

Open the HTML coverage report on macOS:

```bash
open build/reports/jacoco/test/html/index.html
```

Current business-logic coverage:

* Instruction coverage: 91%
* Branch coverage: 75%

The JaCoCo report excludes CLI startup/presentation code so the report focuses on business logic.

---

## Static Analysis

Checkstyle is configured through:

```text
config/checkstyle/checkstyle.xml
```

The full Gradle build runs verification tasks:

```bash
./gradlew clean build
```

---

## Design Patterns

This project uses two main design patterns:

1. **Builder Pattern**
   Used in `Task` to make task object creation readable and flexible.

2. **Strategy Pattern**
   Used for sorting tasks by due date or priority without hard-coding every sorting rule into the service.

More detail is documented in:

```text
docs/DESIGN_PATTERNS.md
```

---

## Documentation

Project documentation is stored in the `docs/` folder:

* `TEST_PLAN.md` — testing strategy, black-box tests, white-box tests, acceptance tests, and coverage
* `TDD_EVIDENCE.md` — red-green-refactor evidence for category filtering in the service layer
* `DESIGN_PATTERNS.md` — Builder and Strategy pattern documentation
* `ESTIMATION.md` — WBS estimation, T-shirt sizing, timeline, and risk register
* `REFACTORING_REPORT.md` — code smells, refactoring work, and metrics

---

## GitHub Workflow

The project uses a feature-branch workflow:

1. Create a feature or documentation branch.
2. Commit focused changes.
3. Push the branch to GitHub.
4. Open a pull request into `main`.
5. Wait for CI checks to pass.
6. Review the changes.
7. Merge the pull request.
8. Pull the updated `main` branch locally.

This workflow was used to provide evidence of CI, pull requests, code review comments, and iterative development.

---

## Current Status

The main project implementation is complete. The current version includes the core task tracker features, automated tests, CI, coverage reporting, static analysis, design pattern documentation, TDD evidence, estimation, and refactoring documentation.
