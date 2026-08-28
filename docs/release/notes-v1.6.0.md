# Release Notes: v1.6.0

`java-evolution` v1.6.0 moves the repository build baseline from JDK 25 to JDK 26.

The repository still covers Java 1 through Java 26 as a didactic history of the language and platform. Java 26 is now the build and JavaDoc baseline, while Java 26 feature modules remain notes-only until each topic is separately evaluated for a faithful executable example.

## Highlights

- Updated Maven compilation and JavaDoc generation to use Java 26.
- Updated GitHub Actions build and JavaDoc workflows to install Oracle JDK 26.
- Replaced Java 25 helper scripts with Java 26 helper scripts for macOS, Linux, Windows Git Bash, and Windows PowerShell.
- Updated the Makefile guard from `check-java-25` to `check-java-26`.
- Updated documentation and JavaDoc-facing comments so learners see JDK 26 as the active project baseline.
- Preserved Java 25 preview examples as Java 25 material. Under JDK 26, tests skip only the child-compilation workflows that require a JDK 25 preview compiler, while still checking generated source and explanatory boundaries.
- Updated Class-File API validation for the Java 26 class-file version.
- Adjusted the ZGC generational-mode test for modern JDK behavior where the removed `ZGenerational` option can be rejected outright.

## Why This Matters

Moving the build baseline to JDK 26 keeps the repository current while preserving its historical teaching model.

The important boundary remains intact: a newer project JDK does not automatically turn every current-release feature into a normal executable example. Preview APIs, incubator modules, removed APIs, runtime behavior, and performance-oriented features still need feature-by-feature evaluation before they move from notes-only material to runnable examples.

The Java 25 preview examples also remain historically honest. A JDK 26 compiler cannot serve as a JDK 25 preview compiler, so those child-process tests skip the old-preview execution path on JDK 26 instead of silently becoming Java 26 examples.

## Validation

Before publishing this release, run:

```bash
make java-version
make check-java-26
mvn test
make docs
make links
node scripts/check-doc-navigation.mjs
make demos
git diff --check
```

Expected result:

- Java and Maven report JDK 26
- full Maven test suite passes on JDK 26
- JavaDoc generation succeeds
- Markdown link check passes
- documentation navigation audit passes
- practical demo test group passes
- whitespace diff check passes
- GitHub Actions `build` workflow passes on Ubuntu, macOS, and Windows with Oracle JDK 26

Observed release-preparation context:

- `make java-version` reported Homebrew OpenJDK 26.0.2.1 and Maven using Java 26.0.2.1
- `make check-java-26` passed
- `make test` passed with 437 tests, 0 failures, 0 errors, and 11 skipped
- `make docs` passed
- `make docs-audit` passed
- `make demos` passed
- `git diff --check` passed
- `make links` could not complete inside the restricted Codex sandbox because broad external URL checks failed with connection errors; it should be run locally before publishing

## Suggested GitHub Release Text

```text
java-evolution v1.6.0 moves the repository build baseline from JDK 25 to JDK 26.

Highlights:
- updated Maven, Makefile guards, helper scripts, and GitHub Actions workflows for JDK 26
- replaced Java 25 helper scripts with Java 26 helpers for macOS, Linux, Windows Git Bash, and Windows PowerShell
- updated repository documentation and JavaDoc-facing comments for the JDK 26 baseline
- kept Java 26 feature modules notes-only until feature-by-feature executable-example review
- preserved Java 25 preview examples as Java 25 material by skipping old-preview child-compilation workflows under JDK 26
- updated Class-File API and ZGC runtime-boundary tests for JDK 26 behavior

Validation:
- make java-version
- make check-java-26
- make test
- make docs
- make docs-audit
- make demos
- git diff --check
- make links locally before publishing
```
