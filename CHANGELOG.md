# Changelog

All notable changes to this project are documented here.

This project uses simple release notes rather than a strict changelog taxonomy. The repository is educational, so each release entry focuses on learning value, documentation coverage, and validation status.

## Unreleased

### Changed

- Converted the Java 1.1 reflection module from notes-only material into an executable example with focused tests.
- Converted the Java 1.1 serialization module from notes-only material into a trusted in-memory executable example with focused tests.
- Converted JavaBeans, Java 2D, XML/JAXP, and Java 6 monitoring/management from notes-only material into executable examples with focused tests.
- Converted Java 16 Unix-domain socket channels from notes-only material into an executable example with safe skips when local socket binding is blocked.
- Converted Java 15 hidden classes from notes-only material into an executable example using compiled template class bytes.
- Converted the Java 6 Compiler API module from notes-only material into a real file-based compiler example with diagnostics.
- Converted Java 4 assertions from notes-only material into an executable example that enables assertions explicitly in tests.
- Converted Java 3 shutdown hooks from notes-only material into an executable child-JVM example.

## v1.4.1

Patch release focused on learning-documentation clarity after the Java 26 release.

### Changed

- Aligned study-guide version ranges with the canonical learning path stages.
- Split current-release learning guidance into clearer Java 22-24 and Java 25-26 sections.
- Split migration guidance into Java 21 to Java 25 LTS migration plus Java 26 awareness.
- Updated interview and demo guidance to distinguish Java 25 LTS material from Java 26 notes-only current-release awareness.
- Improved Maven command readability and consistency in learning walkthroughs.

### Validation

- `node scripts/check-doc-navigation.mjs`
- `make docs`
- `make links`
- `git diff --check`

## v1.4.0

Learning-focused release that expands the repository from Java 1-25 coverage to Java 1-26 coverage while keeping JDK 25 as the build baseline.

### Added

- Added Java 26 as notes-only C2 explanatory material under the JDK 25 build baseline.
- Added Java 26 documentation for HTTP/3, final-field restrictions, Applet API removal, AOT object caching, G1 synchronization reductions, PEM encodings, structured concurrency, Lazy Constants, Vector API, and primitive patterns.
- Added focused tests for Java 26 notes classes.

### Changed

- Updated repository navigation, timeline, feature map, status matrix, JEP index, study guide, migration guide, and glossary for Java 26 coverage.

### Validation

- Java 26 focused Maven test suite
- `node scripts/check-doc-navigation.mjs`
- `make docs`
- `make links`
- `git diff --check`

## v1.3.1

Patch release focused on Java release chronology and documentation validation workflow.

### Added

- Added a Java release timeline with month/year release context from Java 1 through Java 25.

### Changed

- Added release-date context to version READMEs, root README navigation, the feature index, and study/navigation docs.
- Clarified the distinction between repository release history and Java platform release history.
- Clarified historical naming for Java 1.3 and Java 1.4, which were marketed under the Java 2 Platform, Standard Edition brand.
- Added `VERBOSE=-v` and `VERBOSE=-vv` pass-through support for `make links`.
- Removed a blocked timeline reference link that returned HTTP 403 during Markdown link checks.
- Cleaned up an unsupported JavaDoc tag in the Java 4 NIO example documentation.

### Validation

- `node scripts/check-doc-navigation.mjs`
- `make docs`
- `make links`
- `git diff --check`

## v1.3.0

Learning-focused release that expands the repository from Java 8-25 coverage to Java 1-25 coverage.

### Added

- Added Java 1 through Java 4 foundation packages covering object-oriented basics, interfaces, checked exceptions, threads, I/O, inner classes, collections, sorting, dynamic proxies, timers, regex, NIO, logging, chained exceptions, and major explanatory platform topics.
- Added Java 5 through Java 7 packages covering generics, enhanced for loops, autoboxing, enums, varargs, static imports, annotations, covariant returns, formatted output, concurrency utilities, Java 6 platform APIs, Project Coin, NIO.2, fork/join, and `invokedynamic`.
- Added focused JUnit tests for Java 1 through Java 7 examples, using tests as executable documentation.
- Added explanatory notes modules for historical features that are environment-bound, security-sensitive, obsolete, or misleading as tiny runnable examples.
- Expanded repository navigation so the README, feature map, status matrix, JEP/reference index, learning path, study guide, migration guide, and glossary cover Java 1 through Java 25.

### Changed

- Improved historical accuracy in early Java examples by avoiding later syntax where it would blur the teaching contrast, such as Java 7 diamond syntax in Java 5 and Java 6 examples.
- Strengthened early Java source, test, and documentation clarity after source, test, documentation, and integration reviews.
- Expanded glossary coverage for early Java platform acronyms and terms such as AWT, JDBC, JAAS, JMX, JAXB, JAX-WS, DOM, SAX, and XSLT.

### Validation

- Java 1-7 focused Maven test suite
- `node scripts/check-doc-navigation.mjs`
- `mvn javadoc:javadoc`
- `git diff --check`
- `make links` was attempted in a restricted environment; external HTTP checks failed with network/firewall errors.
- `mvn test` was attempted in a restricted environment; existing Java 18 simple web server tests failed because local socket binding was denied.

## v1.2.0

Learning-focused release that substantially improves the Java 8 through Java 25 reference material.

### Changed

- Expanded explanations of the problems solved by Java features across the version packages.
- Added and expanded explanatory learning modules for complex, preview, incubator, runtime, tooling, and platform features.
- Improved Java 8 through Java 25 READMEs, JavaDoc, navigation, glossary terminology, JEP references, and feature-status documentation.
- Strengthened tests as executable documentation with clearer scenarios, assertions, and historical context.
- Corrected Java-version API usage and preview/final status labels, including JEP 458's final tooling status.

### Validation

- `make docs-audit`
- `mvn -B javadoc:javadoc`
- `mvn -B test` on JDK 25 outside restricted loopback environments
- `make release-check`

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
