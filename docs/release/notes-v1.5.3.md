# Release Notes: v1.5.3

`java-evolution` v1.5.3 is a cross-platform validation patch release for the JDK 25 build baseline.

This release adds operating-system coverage to the main build workflow so the repository is continuously checked on Ubuntu, macOS, and Windows.

## Highlights

- Expanded the GitHub Actions `build` workflow into a three-operating-system matrix:
  - `ubuntu-latest`
  - `macos-latest`
  - `windows-latest`
- Kept Oracle JDK 25 as the required CI runtime for the build matrix.
- Kept the build job running `make check`, matching the repository's local validation gate for compilation and tests.
- Left documentation generation, GitHub Pages publication, and link checking in their existing dedicated workflows so CI failures remain easier to diagnose.

## Why This Matters

The project contains many examples that launch child JVMs, inspect generated class files, bind local sockets, use preview or incubator flags, and exercise platform-sensitive JDK tooling.

Running the main JDK 25 test gate on Windows, macOS, and Linux gives earlier feedback when examples accidentally depend on one operating system's path handling, executable naming, process behavior, or local networking assumptions.

This release keeps the Java 26 material as notes-only awareness under the JDK 25 baseline. The CI matrix validates the supported build baseline instead of implying that older LTS releases or future early-access builds are required targets.

## Validation

Before publishing this release, run:

```bash
mvn test
make docs
make links
node scripts/check-doc-navigation.mjs
git diff --check
```

Expected result:

- full Maven test suite passes on JDK 25
- JavaDoc generation succeeds
- Markdown link check passes
- documentation navigation audit passes
- whitespace diff check passes
- GitHub Actions `build` workflow passes on Ubuntu, macOS, and Windows

Observed release-preparation context:

- GitHub Actions `build` workflow passed on Ubuntu, macOS, and Windows with Oracle JDK 25
- Windows 11 IntelliJ Maven install passed
- Windows 11 `make test` passed
- Windows 11 `make links` passed

## Suggested GitHub Release Text

```text
java-evolution v1.5.3 is a cross-platform validation patch for the JDK 25 build baseline.

Highlights:
- expanded the GitHub Actions build workflow to run on Ubuntu, macOS, and Windows
- kept Oracle JDK 25 as the required CI runtime for the build matrix
- kept the build job running make check, matching the local compilation and test gate
- left docs, pages, and link checks in their dedicated workflows for clearer failure reports

Validation:
- GitHub Actions build workflow on Ubuntu, macOS, and Windows with Oracle JDK 25
- Windows 11 IntelliJ Maven install
- Windows 11 make test
- Windows 11 make links
```
