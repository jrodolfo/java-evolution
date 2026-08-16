# Changelog

All notable changes to this project are documented here.

This project uses simple release notes rather than a strict changelog taxonomy. The repository is educational, so each release entry focuses on learning value, documentation coverage, and validation status.

## Unreleased

### Changed

- Improved teaching clarity across Java 9 through Java 25 examples, notes, tests, and feature documentation.
- Added explanatory learning modules for substantial notes-only topics such as the Java 9 module system, Java 15 hidden classes, and Java 16 Unix-domain socket channels.
- Clarified practical demo documentation so it matches the focused `make demos` validation target.
- Expanded glossary coverage for recurring Java platform acronyms and terms.

### Validation

- `make docs-audit`
- `make docs-check`
- `make demos`
- `mvn -B clean test -q`

## v1.1.3

Patch release focused on the Java 21 virtual-thread naming example.

### Changed

- Reused one configured virtual-thread builder so the Java 21 example demonstrates incrementing thread names.
- Strengthened the virtual-thread naming test to verify `worker-1`, `worker-2`, and `worker-3` directly.

### Validation

- `make release-check`

## v1.1.2

Patch release focused on glossary and acronym clarity for learners.

### Changed

- Expanded glossary coverage for recurring acronyms used across documentation.
- Made first-use acronym definitions explicit in cryptography feature READMEs.
- Added glossary links from deep feature documentation for KEM, ML-KEM, ML-DSA, HKDF, HMAC, AES, KDF, JEP, and JDK context.

### Validation

- `make release-check`

## v1.1.1

Patch release focused on JavaDoc and documentation navigation clarity.

### Changed

- Removed duplicate notes classes for features that now have dedicated executable example packages.
- Removed matching redundant notes tests.
- Updated demo commands, feature maps, status matrix, learning path, migration guide, and version READMEs to point directly to executable examples.

### Validation

- `make release-check`

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
