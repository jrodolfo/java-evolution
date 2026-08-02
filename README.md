# Java Evolution

[![build](https://github.com/jrodolfo/java-evolution/actions/workflows/build.yml/badge.svg)](https://github.com/jrodolfo/java-evolution/actions/workflows/build.yml)
[![links](https://github.com/jrodolfo/java-evolution/actions/workflows/links.yml/badge.svg)](https://github.com/jrodolfo/java-evolution/actions/workflows/links.yml)
[![license: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

`java-evolution` is a didactic Java reference project that demonstrates features introduced in each Java release from Java 8 through Java 25.

The project is intentionally lightweight. Spring Boot provides the project shell and Maven setup, but the examples are plain Java classes with focused JUnit tests. The main goal is to make each feature easy to read, run, and revisit during study or interview preparation.

## Requirements

- JDK 25
- Maven 3.9+

This repository uses the local Maven installation on your machine. It does not use the Maven wrapper.

On macOS, this helper switches the current terminal session to JDK 25:

```bash
source scripts/use-java-25.sh
```

Then run the test suite:

```bash
make test
```

To print the active Java/Maven versions and run the test suite:

```bash
make check
```

To check documentation navigation consistency:

```bash
make docs-audit
```

To generate the local JavaDoc reference:

```bash
make docs
```

The generated JavaDoc is written to `target/site/apidocs/index.html`.
The published JavaDoc site is available at https://jrodolfo.github.io/java-evolution/.

## Repository Status

- Examples are built and tested with JDK 25.
- Spring Boot is used as lightweight project tooling; the examples themselves are plain Java.
- Some features are represented by `Notes` classes because they involve preview flags, incubator modules, native code, JVM flags, external tools, cryptography providers, source-launcher behavior, or runtime behavior.
- `Notes` does not mean a Java feature is unfinished. Some final features are documented as notes because they are not a good fit for a tiny portable JUnit example.
- Documentation navigation is checked locally with `make docs-audit` and in GitHub Actions.
- JavaDoc is generated locally with `make docs` and published through GitHub Pages.

## Important Design Choice

The whole project compiles with JDK 25:

```xml
<java.version>25</java.version>
```

Each package demonstrates features introduced in a specific Java release. For example, `java08` demonstrates Java 8 features, while `java21` demonstrates Java 21 features.

Some older preview or incubator features changed before becoming final. In those cases, the project either:

- uses current JDK 25-compatible syntax and explains the original preview status, or
- uses a `Notes` class when the original feature requires preview flags, incubator modules, native code, JVM flags, external processes, or platform-specific setup.

When a feature is final but represented by a notes class, the status tables say `final, notes-only`: final describes the Java feature, and notes-only describes how this repository documents it.

## Repository Structure

```text
src/main/java/net/jrodolfo/java_evolution/
  java08/
  java09/
  java10/
  ...
  java25/

src/test/java/net/jrodolfo/java_evolution/
  java08/
  java09/
  java10/
  ...
  java25/
```

Example classes contain JavaDoc. Test classes are written as executable documentation with clear assertions. Packages can also include a version README with a deeper explanation of the problems each feature solved.

## Documentation

Start here depending on what you need:

- New to the repository: read this README, then follow the [Study Guide](docs/study-guide.md).
- Moving from an older Java baseline: use the [Migration Guide](docs/migration-guide.md).
- Studying over multiple sessions: use the [Learning Path](docs/learning-path.md).
- Looking for one feature quickly: use the [Feature Map](docs/feature-map.md).
- Checking maturity or official references: use the [Status Matrix](docs/status-matrix.md) and [JEP Index](docs/jep-index.md).
- Preparing to present the project: use the [Interview Guide](docs/interview-guide.md) and [Demo Script](docs/demo-script.md).
- Preparing a release: use the [Release Checklist](docs/release-checklist.md).
- Browsing API-style documentation: use the [JavaDoc Site](https://jrodolfo.github.io/java-evolution/).

- [Changelog](CHANGELOG.md): release history.
- [Study Guide](docs/study-guide.md): suggested learning path through Java 8-25.
- [Migration Guide](docs/migration-guide.md): practical guide for moving from Java 8, 11, 17, or 21 to newer baselines.
- [Learning Path](docs/learning-path.md): staged study sessions with files to read, tests to run, and interview angles.
- [Interview Guide](docs/interview-guide.md): talking points and demo flow for interviews.
- [Demo Script](docs/demo-script.md): practical 5-10 minute live walkthrough.
- [JEP Index](docs/jep-index.md): centralized JEP links with status labels.
- [Feature Map](docs/feature-map.md): class-by-class navigation table.
- [Status Matrix](docs/status-matrix.md): feature status, example class, test class, and reference links.
- [Release Checklist](docs/release-checklist.md): repeatable release process.
- [v1.0.0 Release Notes](docs/release-notes-v1.0.0.md): draft text for the first GitHub release.
- [JavaDoc Site](https://jrodolfo.github.io/java-evolution/): generated API documentation published by GitHub Pages.

## Feature Index

| Java | Package | Highlights |
|---|---|---|
| 8 | [`java08`](src/main/java/net/jrodolfo/java_evolution/java08/README.md) | Lambdas, Streams, Optional, Method References, CompletableFuture, Default Methods, Date/Time API |
| 9 | [`java09`](src/main/java/net/jrodolfo/java_evolution/java09/README.md) | Collection Factories, Optional/Stream Enhancements, Private Interface Methods, Process API, StackWalker, Modules notes |
| 10 | [`java10`](src/main/java/net/jrodolfo/java_evolution/java10/README.md) | `var`, Unmodifiable Collectors, `Optional.orElseThrow()` |
| 11 | [`java11`](src/main/java/net/jrodolfo/java_evolution/java11/README.md) | String APIs, Files read/write string, HTTP Client, `Predicate.not`, lambda `var`, `Optional.isEmpty` |
| 12 | [`java12`](src/main/java/net/jrodolfo/java_evolution/java12/README.md) | Switch Expressions preview, `Collectors.teeing`, `String.indent`, `Files.mismatch`, Compact Number Format |
| 13 | [`java13`](src/main/java/net/jrodolfo/java_evolution/java13/README.md) | Text Blocks preview, switch `yield` preview, `FileSystems.newFileSystem(Path)` |
| 14 | [`java14`](src/main/java/net/jrodolfo/java_evolution/java14/README.md) | Switch Expressions final, Helpful NullPointerExceptions, Records preview, Pattern Matching for `instanceof` preview |
| 15 | [`java15`](src/main/java/net/jrodolfo/java_evolution/java15/README.md) | Text Blocks final, Sealed Classes preview, Hidden Classes notes |
| 16 | [`java16`](src/main/java/net/jrodolfo/java_evolution/java16/README.md) | Records final, Pattern Matching for `instanceof` final, `Stream.toList`, Unix-domain socket notes |
| 17 | [`java17`](src/main/java/net/jrodolfo/java_evolution/java17/README.md) | Sealed Classes final, Pattern Matching for switch preview, Random Generator API, `HexFormat`, Strong Encapsulation notes |
| 18 | [`java18`](src/main/java/net/jrodolfo/java_evolution/java18/README.md) | UTF-8 default charset, Simple Web Server notes, JavaDoc snippets notes, InetAddress resolver SPI notes |
| 19 | [`java19`](src/main/java/net/jrodolfo/java_evolution/java19/README.md) | Virtual Threads preview, Record Patterns preview, Pattern Matching switch preview, Structured Concurrency notes, FFM notes |
| 20 | [`java20`](src/main/java/net/jrodolfo/java_evolution/java20/README.md) | Record Patterns second preview, Pattern Matching switch fourth preview, Scoped Values/Structured Concurrency/FFM/Vector notes |
| 21 | [`java21`](src/main/java/net/jrodolfo/java_evolution/java21/README.md) | Virtual Threads final, Record Patterns final, Pattern Matching for switch final, Sequenced Collections, KEM notes |
| 22 | [`java22`](src/main/java/net/jrodolfo/java_evolution/java22/README.md) | Unnamed Variables and Patterns final, FFM final documented via notes, Stream Gatherers preview notes, Class-File API preview notes |
| 23 | [`java23`](src/main/java/net/jrodolfo/java_evolution/java23/README.md) | Markdown Documentation Comments, Primitive Patterns preview notes, Module Imports preview notes, ZGC Generational Mode notes |
| 24 | [`java24`](src/main/java/net/jrodolfo/java_evolution/java24/README.md) | Stream Gatherers final, Class-File API notes, Security Manager disabled, Virtual Thread synchronization notes, post-quantum crypto notes |
| 25 | [`java25`](src/main/java/net/jrodolfo/java_evolution/java25/README.md) | Scoped Values final, Flexible Constructor Bodies final, Module Imports and Compact Source Files final documented via notes, KDF/JFR/AOT/GC notes |

## How To Study This Repository

Start with the package for the Java version you want to review. Read the example class first, then read the matching test.

For example:

```text
src/main/java/net/jrodolfo/java_evolution/java08/StreamExamples.java
src/test/java/net/jrodolfo/java_evolution/java08/StreamExamplesTest.java
```

The tests are meant to explain the expected behavior. Running `make test` proves that the examples compile and behave as documented. Running `make docs` generates a browsable JavaDoc reference for the example classes.

## Run One Example

Tests are the executable examples. To focus on one topic, run only the matching test class:

```bash
mvn -Dtest=StreamExamplesTest test
mvn -Dtest=VirtualThreadsExamplesTest test
mvn -Dtest=ScopedValuesExamplesTest test
```

Use the class names in [docs/feature-map.md](docs/feature-map.md) to choose a specific example.

## Official References

- [OpenJDK JDK 8](https://openjdk.org/projects/jdk8/)
- [OpenJDK JDK 9](https://openjdk.org/projects/jdk9/)
- [OpenJDK JDK 10](https://openjdk.org/projects/jdk/10/)
- [OpenJDK JDK 11](https://openjdk.org/projects/jdk/11/)
- [OpenJDK JDK 12](https://openjdk.org/projects/jdk/12/)
- [OpenJDK JDK 13](https://openjdk.org/projects/jdk/13/)
- [OpenJDK JDK 14](https://openjdk.org/projects/jdk/14/)
- [OpenJDK JDK 15](https://openjdk.org/projects/jdk/15/)
- [OpenJDK JDK 16](https://openjdk.org/projects/jdk/16/)
- [OpenJDK JDK 17](https://openjdk.org/projects/jdk/17/)
- [OpenJDK JDK 18](https://openjdk.org/projects/jdk/18/)
- [OpenJDK JDK 19](https://openjdk.org/projects/jdk/19/)
- [OpenJDK JDK 20](https://openjdk.org/projects/jdk/20/)
- [OpenJDK JDK 21](https://openjdk.org/projects/jdk/21/)
- [OpenJDK JDK 22](https://openjdk.org/projects/jdk/22/)
- [OpenJDK JDK 23](https://openjdk.org/projects/jdk/23/)
- [OpenJDK JDK 24](https://openjdk.org/projects/jdk/24/)
- [OpenJDK JDK 25](https://openjdk.org/projects/jdk/25/)

## Contact

- Software Developer: Rod Oliveira
- GitHub: https://github.com/jrodolfo
- Webpage: https://jrodolfo.net

## License

- MIT License
- Copyright (c) 2026 Rod Oliveira
- See [LICENSE](./LICENSE)
