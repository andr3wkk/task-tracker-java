# CI Evidence

## Overview

This document records continuous integration evidence for the Personal Task Tracker project.

The project uses GitHub Actions as the CI platform. The workflow is defined in:

```text
.github/workflows/ci.yml
```

The workflow runs on pushes and pull requests. It checks out the repository, sets up Java 17, runs the Gradle build, runs tests, runs Checkstyle through Gradle verification, generates the JaCoCo coverage report, and uploads the JaCoCo HTML report as a workflow artifact.

---

## CI Workflow

The final CI workflow performs the following steps:

1. Checks out the repository.
2. Sets up Java 17.
3. Sets up Gradle.
4. Makes the Gradle wrapper executable.
5. Runs build, tests, Checkstyle, and JaCoCo coverage:

```text
./gradlew clean check jacocoTestReport
```

6. Uploads the JaCoCo HTML coverage report as an artifact.

This satisfies the CI requirement because the workflow runs automatically on pushes and pull requests and verifies the build, tests, static analysis, and coverage generation.

---

## Successful CI History

The repository includes many successful GitHub Actions runs across feature branches, documentation branches, pull requests, and merges into `main`.

Examples of successful CI evidence include:

| Evidence                    | Description                                                   |
| --------------------------- | ------------------------------------------------------------- |
| Feature branch checks       | Feature branches were checked before merge.                   |
| Pull request checks         | Pull requests ran CI before being merged into `main`.         |
| Main branch checks          | Merged changes triggered successful workflow runs on `main`.  |
| Documentation branch checks | Documentation-only branches also passed the full CI workflow. |
| Final compliance checks     | Final compliance and final report branches passed CI.         |

The Actions page shows repeated successful workflow runs, demonstrating that the project build and tests were verified throughout development.

---

## Failed CI Run and Fix Evidence

To verify that the CI pipeline detects broken changes, I created a separate temporary branch named:

```text
ci/failure-evidence
```

This branch was used only for CI evidence. It was not merged into `main`.

### Failed Run

A temporary failing JUnit test was added in this branch to intentionally cause the CI pipeline to fail.

**Failed workflow run:**
https://github.com/andr3wkk/task-tracker-java/actions/runs/26702443489

**Commit:** `b728adb`
**Commit message:** `test: add intentional failing CI evidence test`
**Branch:** `ci/failure-evidence`
**Result:** Failed

The failing test used an intentional failed assertion to confirm that GitHub Actions would detect a broken test and report the failure.

### Fix

The temporary failing test was then removed.

**Fixed successful workflow run:**
https://github.com/andr3wkk/task-tracker-java/actions/runs/26702486392

**Commit:** `39e54d7`
**Commit message:** `test: remove intentional CI failure evidence test`
**Branch:** `ci/failure-evidence`
**Result:** Passed

This confirms that the CI pipeline can detect a failing change and then return to a passing state after the issue is fixed.

---

## Final CI Status

The final CI setup is complete:

| Requirement                    | Status   |
| ------------------------------ | -------- |
| Runs on pushes                 | Complete |
| Runs on pull requests          | Complete |
| Checks out code                | Complete |
| Sets up Java and Gradle        | Complete |
| Runs build                     | Complete |
| Runs tests                     | Complete |
| Runs static analysis           | Complete |
| Generates coverage report      | Complete |
| Uploads coverage artifact      | Complete |
| Shows failed pipeline evidence | Complete |
| Shows fixed pipeline evidence  | Complete |
| Final workflow passes          | Complete |

## Conclusion

The CI evidence shows that the project uses GitHub Actions throughout development, that the workflow passes on normal project changes, and that the pipeline correctly detects a broken test and passes again after the issue is fixed.
