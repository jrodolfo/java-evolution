# Release Notes: v1.5.0

`java-evolution` v1.5.0 is a learning-focused release that turns many previously explanatory modules into executable examples.

The release keeps the repository on the JDK 25 build baseline. Features that can be demonstrated faithfully and portably now have runnable examples and focused tests. Features that would require obsolete infrastructure, host mutation, superseded preview APIs, or Java 26-only APIs remain explanatory notes.

## Highlights

- Converted many notes-only modules into executable examples across Java 1 through Java 25.
- Added runnable examples for early platform foundations such as reflection, serialization, JavaBeans, JDBC, RMI, Swing, Java 2D, security policy, JNDI, assertions, XML/JAXP, and integrated security APIs.
- Added executable examples for tooling and runtime features such as the Java 6 Compiler API, Console API boundary, scripting service-provider discovery, `invokedynamic`, the Java 9 module system, hidden classes, Unix-domain socket channels, strong encapsulation, Internet-Address Resolution SPI, and the Java 22 multi-file source launcher.
- Added executable examples for modern runtime, preview, and incubator areas including Java 20 and Java 25 Vector API, Java 23 Markdown documentation comments, Unsafe memory-access deprecation, ZGC generational mode, Java 24 virtual-thread synchronization and AOT class loading, and Java 25 PEM encodings, Stable Values, Structured Concurrency, Primitive Patterns, Compact Object Headers, JFR enhancements, AOT command-line ergonomics, compact source files, and module import declarations.
- Preserved notes-only treatment where executable code would reduce clarity: Preferences backing stores, Java 6 web-service support, legacy integration, superseded preview/incubator APIs, and Java 26 material under the JDK 25 baseline.
- Improved portability for child-process and local-socket examples, including Windows JDK tool resolution, locale-stable operating-system checks, bounded child-JVM waits, Unix-domain socket cleanup, and sandbox-aware Simple Web Server tests.
- Updated repository documentation labels and navigation to reflect the broader executable-example coverage.
- Updated the Spring Boot starter parent from 4.1.0 to 4.1.1.

## Why This Matters

This repository is meant to help developers learn and refresh Java concepts, not only memorize feature lists.

Executable examples are valuable when they show the real API or language behavior without external systems or fragile setup. This release moves many modules into that stronger teaching mode: learners can read the example, run the focused test, and see the concept verified directly.

The release also keeps the important boundary intact. Notes remain the right representation when an example would mutate host state, require obsolete services, depend on removed platform modules, use superseded preview syntax, or require moving the project from JDK 25 to JDK 26.

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

Observed release-preparation result:

- maintainer local `mvn test`: 430 tests, 0 failures, 0 errors, 1 skipped
- restricted Codex `make test`: 430 tests, 0 failures, 0 errors, 7 skipped because local socket binding is blocked
- `make docs`: passed
- `make links`: passed
- `node scripts/check-doc-navigation.mjs`: passed
- `git diff`: clean before release-note preparation

## Suggested GitHub Release Text

```text
java-evolution v1.5.0 turns many previously notes-only learning modules into executable examples while keeping the JDK 25 build baseline.

Highlights:
- converted many notes-only modules into executable examples across Java 1 through Java 25
- added runnable examples for early Java platform foundations such as reflection, serialization, JavaBeans, JDBC, RMI, Swing, Java 2D, security policy, JNDI, assertions, XML/JAXP, and integrated security APIs
- added executable tooling/runtime examples for Compiler API, Console API, scripting SPI, invokedynamic, modules, hidden classes, Unix-domain sockets, strong encapsulation, Internet-Address Resolution SPI, and multi-file source launching
- added executable modern runtime, preview, and incubator examples for Vector API, Markdown documentation comments, Unsafe migration diagnostics, ZGC generational mode, virtual-thread synchronization, AOT workflows, PEM encodings, Stable Values, Structured Concurrency, Primitive Patterns, Compact Object Headers, JFR enhancements, compact source files, and module import declarations
- kept Preferences, Java 6 web-service support, legacy integration, superseded preview/incubator APIs, and Java 26 material as notes where executable examples would be misleading or environment-bound
- improved child-process and local-socket portability
- updated Spring Boot starter parent from 4.1.0 to 4.1.1

Validation:
- mvn test
- make docs
- make links
- node scripts/check-doc-navigation.mjs
- git diff --check
```
