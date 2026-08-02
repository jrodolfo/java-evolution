# Changelog

All notable changes to this project are documented here.

This project uses simple release notes rather than a strict changelog taxonomy. The repository is educational, so each release entry focuses on learning value, documentation coverage, and validation status.

## Unreleased

- Prepare the first public release.

## v1.0.0

Initial public release of `java-evolution`.

### Added

- Java release packages from Java 8 through Java 25.
- Focused example classes for language and API features that can be demonstrated cleanly with JDK 25-compatible source code.
- Notes classes for preview, incubator, runtime, tooling, cryptography, source-launcher, JVM, GC, and platform features that are not a good fit for tiny portable JUnit examples.
- JUnit 5 tests used as executable documentation.
- Version README files explaining the problem each feature solved.
- JavaDoc generation with `make docs`.
- GitHub Pages workflow for publishing generated JavaDoc.
- Build workflow for compiling and testing with JDK 25.
- Markdown links workflow.
- Documentation navigation audit with `make docs-audit`.
- Study, learning path, interview, demo, migration, feature map, status matrix, JEP index, contribution, security, and release process documentation.

### Validation

- `make docs-audit`
- `make docs`
- `make check`

### Known Notes

- The Java 11 HTTP Client test can be skipped in restricted environments when a local HTTP server cannot bind to a loopback port. The skip is intentional and guarded so the suite remains portable.
