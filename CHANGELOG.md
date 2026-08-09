# Changelog

All notable changes to this project are documented here.

This project uses simple release notes rather than a strict changelog taxonomy. The repository is educational, so each release entry focuses on learning value, documentation coverage, and validation status.

## Unreleased

- No unreleased changes.

## v1.1.0

Second public release of `java-evolution`, focused on turning several notes-only topics into executable, learner-friendly examples and improving cross-platform validation.

### Added

- Executable Key Encapsulation Mechanism example for Java 21.
- Executable Foreign Function and Memory API example for Java 22.
- Executable Class-File API example for Java 24.
- Executable ML-KEM and ML-DSA examples for Java 24.
- Executable HKDF key derivation example for Java 25.
- Java 18 JavaDoc `@snippet` examples.
- Java 18 Simple Web Server example.
- Practical demo guide and `make demos` target for focused walkthroughs.
- Glossary documentation, including Java Platform Module System (JPMS) search terms.
- Java 25 guardrail for Make targets.
- Java 25 helper scripts for macOS, Linux, Windows Git Bash, and Windows PowerShell.
- README platform support note for macOS, Windows 11, and Linux.

### Changed

- Improved release automation with `make docs-check` and `make release-check`.
- Aligned build workflow with the local `make check` behavior.
- Improved documentation and tests after code, documentation, and test reviews.
- Clarified Foreign Function and Memory API `invokeExact` cast requirements.
- Renamed and organized release documentation under `docs/release/`.
- Improved Pattern Matching for switch discoverability.

### Validation

- `make release-check`
- macOS validation with JDK 25 and Maven.
- Windows 11 validation with JDK 25, Maven, PowerShell helper, Git Bash helper, and Make.
- Linux validation with JDK 25 helper flow.

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
