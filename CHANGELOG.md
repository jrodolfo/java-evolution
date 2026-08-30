# Changelog

All notable changes to this project are documented here.

This project uses simple release notes rather than a strict changelog taxonomy. The repository is educational, so each release entry focuses on learning value, documentation coverage, and validation status.

## Unreleased

## v1.6.3

Patch release focused on completing selected Java 26 C1 migrations and aligning the learner documentation with the current feature coverage.

### Changed

- Converted Java 26 Structured Concurrency from notes-only material into an executable child-JVM preview API example with focused tests.
- Converted Java 26 Lazy Constants from notes-only material into an executable child-JVM preview API example with focused tests.
- Converted Java 26 PEM Encodings from notes-only material into an executable child-JVM preview API example with focused tests.
- Converted Java 26 Primitive Patterns from notes-only material into an executable child-JVM preview syntax example with focused tests.
- Updated Java 26 feature maps, learning guides, migration guidance, demo scripts, package documentation, and README content to distinguish eight C1 examples from two C2 explanatory modules.
- Corrected the PowerShell JDK 26 activation documentation to dot-source the helper script so environment changes persist in the current session.

### Validation

- JDK 26 Maven test suite passed with 459 tests, 0 failures, 0 errors, and 11 expected skips.
- JavaDoc generation passed.
- Documentation navigation audit passed.
- Markdown link validation passed with 1,092 links checked and 0 errors.
- `git diff --check` passed.

## v1.6.2

Patch release focused on GitHub Actions JDK selection and cleaner JDK 26 build output.

### Changed

- Fixed the Makefile to honor an explicitly selected `JAVA_HOME`, including JDKs provisioned by GitHub Actions on macOS.
- Suppressed expected JDK 26 removal warnings for the historical Java 2 `SocketPermission` example and test.

## v1.6.1

Patch release focused on cross-platform executable examples, historical API fidelity, and documentation consistency after the JDK 26 baseline migration.

### Changed

- Converted Java 26 HTTP/3 from notes-only material into an executable API-configuration example with focused tests.
- Converted Java 26 final-field restrictions from notes-only material into an executable child-JVM runtime-warning example with focused tests.
- Converted Java 26 Applet API removal from notes-only material into an executable child-compiler removal example with focused tests.
- Converted Java 26 AOT object caching from notes-only material into an executable child-JVM cache creation and reuse example with focused tests.
- Hardened socket-binding skips and child-process lifecycle handling across executable examples for restricted and cross-platform environments.
- Restored early Java examples to historically appropriate APIs and documented the later XML security hardening boundary in the Java 4 JAXP example.

## v1.6.0

Minor release focused on moving the repository build baseline from JDK 25 to JDK 26.

### Changed

- Updated the Maven Java release, Makefile guard, helper scripts, and GitHub Actions workflows to use JDK 26.
- Replaced the Java 25 helper scripts and runtime guard with Java 26 equivalents for macOS, Linux, Windows Git Bash, and Windows PowerShell.
- Updated repository documentation, learning guides, migration guidance, JavaDoc-facing comments, and version READMEs to describe JDK 26 as the project build baseline.
- Preserved Java 26 feature modules as notes-only material until each feature is separately evaluated for a faithful executable example.
- Kept Java 25 preview examples historically accurate by skipping old-preview child-compilation workflows when running under JDK 26 while still checking source shape and explanatory boundaries.
- Updated Java 24 Class-File API validation to expect the JDK 26 class-file version.
- Adjusted the Java 23 ZGC generational-mode test to accept modern JDK behavior where the removed `ZGenerational` option is rejected outright.

### Validation

- `make java-version`
- `make check-java-26`
- `make test`
- `make docs`
- `make docs-audit`
- `make demos`
- `git diff --check`

## v1.5.3

Patch release focused on cross-platform JDK 25 build validation.

### Changed

- Expanded the GitHub Actions build workflow to run the main Maven test gate on Ubuntu, macOS, and Windows.
- Kept the cross-platform CI check focused on JDK 25, matching the repository build baseline while Java 26 remains notes-only current-release awareness.
- Preserved separate documentation and link-check workflows so each validation gate reports the failure type clearly.

### Validation

- GitHub Actions `build` workflow on Ubuntu, macOS, and Windows with Oracle JDK 25
- Windows 11 IntelliJ Maven install
- Windows 11 `make test`
- Windows 11 `make links`

## v1.5.2

Patch release focused on Java 5 and Java 8 API-design learning clarity.

### Changed

- Improved Java 5 enhanced-for examples to emphasize that the loop works with arrays and any `Iterable`, not only `List`.
- Added a small custom `Iterable` example to show how application types can become enhanced-for friendly.
- Expanded Java 5 generics examples with upper-bounded wildcards and multiple bounds for API-design context.
- Expanded Java 5 varargs examples with a mandatory first-argument pattern and an `Iterable` overload for callers that already have grouped values.
- Expanded Java 8 lambda examples to show project-owned APIs accepting `Predicate`, `Function`, `Supplier`, and `Consumer`, clarifying that lambdas are not only a Streams feature.
- Standardized JavaDoc tag ordering for generic method type parameters.

### Validation

- Java 5 and Java 8 focused Maven test suites
- `mvn test`
- `make docs`
- `node scripts/check-doc-navigation.mjs`
- `git diff --check`

## v1.5.1

Patch release focused on Windows validation stability for child-process examples.

### Changed

- Drained child-process output concurrently in selected executable examples so Windows validation does not block while waiting for verbose child JVM or tooling output.
- Applied the output-draining fix to `invokedynamic`, Java 20 Vector API, Java 23 ZGC generational mode, Java 24 AOT class loading, Java 25 AOT command-line ergonomics, and Java 25 compact object headers examples.

### Validation

- Windows 11 Maven build and test validation
- Ubuntu Maven build and test validation

## v1.5.0

Learning-focused release that turns many previously explanatory modules into executable examples while keeping the JDK 25 build baseline.

### Changed

- Converted the Java 1.1 reflection module from notes-only material into an executable example with focused tests.
- Converted the Java 1.1 serialization module from notes-only material into a trusted in-memory executable example with focused tests.
- Converted JavaBeans, Java 2D, XML/JAXP, and Java 6 monitoring/management from notes-only material into executable examples with focused tests.
- Converted Java 16 Unix-domain socket channels from notes-only material into an executable example with safe skips when local socket binding is blocked.
- Converted Java 15 hidden classes from notes-only material into an executable example using compiled template class bytes.
- Converted the Java 6 Compiler API module from notes-only material into a real file-based compiler example with diagnostics.
- Converted Java 4 assertions from notes-only material into an executable example that enables assertions explicitly in tests.
- Converted Java 3 shutdown hooks from notes-only material into an executable child-JVM example.
- Converted Java 22 multi-file source launcher from notes-only material into an executable child-JVM launcher example.
- Converted Java 23 Markdown documentation comments from notes-only material into an executable JavaDoc tooling example.
- Converted Java 9 module system from notes-only material into an executable modular source-tree example.
- Converted Java 2 Swing from notes-only material into a headless executable model/action/EDT example.
- Converted Java 25 compact source files from notes-only material into an executable source-launcher example.
- Converted Java 1.1 RMI from notes-only material into a local-registry executable example with safe skips when local socket binding is blocked.
- Converted Java 3 JNDI from notes-only material into an executable in-memory provider example.
- Converted Java 25 module import declarations from notes-only material into an executable source-tooling example.
- Converted Java 7 invokedynamic from notes-only material into an executable linkage and bytecode-inspection example.
- Converted Java 1.1 JDBC from notes-only material into an executable driver-registration example.
- Converted Java 4 integrated security APIs from notes-only material into executable local JCA/JCE examples.
- Converted Java 17 strong encapsulation from notes-only material into an executable child-JVM access-boundary example.
- Converted Java 2 `strictfp` from notes-only material into an executable example that also captures the Java 17+ compiler warning.
- Converted Java 6 Console API from notes-only material into an executable boundary example with deterministic fake-console tests.
- Converted Java 24 Security Manager disablement from notes-only material into an executable child-JVM runtime example.
- Converted Java 18 Internet-Address Resolution SPI from notes-only material into an executable child-JVM service-provider example.
- Converted Java 6 scripting support from notes-only material into an executable JSR 223 service-provider example.
- Converted Java 25 JFR method timing and tracing from notes-only material into an executable recording example, while keeping CPU-time profiling and cooperative sampling as documented runtime caveats.
- Converted Java 24 virtual-thread synchronization from notes-only material into an executable child-JVM scheduler-boundary example.
- Converted Java 25 AOT command-line ergonomics from notes-only material into an executable child-JVM cache workflow example.
- Converted Java 24 AOT class loading from notes-only material into an executable explicit record/create/use cache workflow example.
- Converted Java 25 PEM encodings from notes-only material into an executable child-JVM preview API example.
- Converted Java 25 Stable Values from notes-only material into an executable child-JVM preview API example.
- Converted Java 25 Vector API from notes-only material into an executable child-JVM incubator-module example.
- Converted Java 25 Structured Concurrency from notes-only material into an executable child-JVM preview API example.
- Converted Java 25 Primitive Patterns from notes-only material into an executable child-JVM preview syntax example.
- Converted Java 25 Compact Object Headers from notes-only material into an executable child-JVM runtime option example.
- Converted Java 2 security policy from notes-only material into an executable permission-model example.
- Converted Java 23 Unsafe memory-access deprecation from notes-only material into an executable migration-boundary example.
- Converted Java 23 ZGC generational mode from notes-only material into an executable child-JVM runtime-boundary example.
- Converted Java 20 Vector API from notes-only material into an executable child-JVM incubator-module example.
- Kept Preferences, Java 6 web-service support, legacy integration, older superseded previews/incubators, and Java 26 material as explanatory notes where executable examples would be environment-bound, obsolete, or misleading under the JDK 25 baseline.
- Improved child-process example portability with Windows JDK executable resolution, locale-stable operating-system checks, bounded JVM waits, and Unix-domain socket cleanup.
- Made Java 18 Simple Web Server tests skip cleanly when local socket binding is blocked in restricted environments.
- Updated stale documentation labels after notes-to-examples conversions.
- Updated the Spring Boot starter parent from 4.1.0 to 4.1.1.

### Validation

- `mvn test`
- `make docs`
- `make links`
- `node scripts/check-doc-navigation.mjs`
- `git diff --check`

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
