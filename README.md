# Java Evolution

[![build](https://github.com/jrodolfo/java-evolution/actions/workflows/build.yml/badge.svg)](https://github.com/jrodolfo/java-evolution/actions/workflows/build.yml)
[![links](https://github.com/jrodolfo/java-evolution/actions/workflows/links.yml/badge.svg)](https://github.com/jrodolfo/java-evolution/actions/workflows/links.yml)
[![license: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

`java-evolution` is a practical Java reference project that demonstrates or documents features introduced in each Java release from Java 1 through Java 26.

I built this repository as a place to learn, test, and document how Java has evolved over time. It is primarily for my own continued growth, but it is also meant to help other developers who want concise examples of newer language features, APIs, and idioms.

The project is intentionally lightweight. Spring Boot provides the project shell and Maven setup, but the examples are plain Java classes with focused JUnit tests. The main goal is to make each feature easy to read, run, and revisit during study or interview preparation.

## Why This Exists

Java keeps evolving, and day-to-day work can easily settle into older habits. This repository makes that evolution concrete through code: what changed, why it matters, and how newer Java can be used clearly and effectively.

The emphasis is clarity over cleverness. Examples should stay small, readable, and easy to run. When useful, they should show not only how a feature works, but also what problem it solves.

## Requirements

- JDK 26
- Maven 3.9+
- GNU Make, if you want to use the documented `make` targets
- Node.js, if you want to run `make docs-audit`, `make docs-check`, or `make release-check` locally
- [`lychee`](https://github.com/lycheeverse/lychee), a Markdown/HTML link checker, if you want to run `make docs-check` or `make release-check` locally

This repository uses the local Maven installation on your machine. It does not use the Maven wrapper.

## Platform Support

The project has been validated on macOS, Windows 11, and Linux. The examples are plain Java, and the helper scripts under `scripts/` provide platform-specific ways to switch the current terminal session to JDK 26 before running Maven.

### Java 26 Helpers

If you usually keep another JDK on your machine, use one of these helpers to switch the current terminal session to JDK 26 before running Maven. These helpers affect only the current shell session; they do not change your global Java default.

On macOS:

```bash
source scripts/use-java-26-mac.sh
```

On Linux:

```bash
source scripts/use-java-26-linux.sh
```

On Windows with Git Bash:

```bash
source scripts/use-java-26-windows.sh
```

On Windows with PowerShell:

```powershell
.\scripts\use-java-26-windows.ps1
```

If PowerShell blocks local scripts on a fresh Windows install, allow scripts only for the current terminal session and then run the helper:

```powershell
Set-ExecutionPolicy -Scope Process Bypass
.\scripts\use-java-26-windows.ps1
```

The Linux helper looks for JDK 26 in `JAVA26_HOME`, `JDK26_HOME`, `/usr/lib/jvm`, `/opt`, `/usr/local`, and SDKMAN candidate directories. If your JDK is somewhere else, pass it explicitly:

```bash
source scripts/use-java-26-linux.sh /usr/lib/jvm/jdk-26.0.2.1
```

The Windows helpers look for JDK 26 in `JAVA26_HOME`, `JDK26_HOME`, `C:\dev\apps`, and common `Program Files` Java install directories. If your JDK is somewhere else, pass it explicitly:

```bash
source scripts/use-java-26-windows.sh /c/dev/apps/jdk-26.0.2.1
```

```powershell
.\scripts\use-java-26-windows.ps1 -JavaHome C:\dev\apps\jdk-26.0.2.1
```

After switching Java, confirm Maven is also using JDK 26:

```bash
mvn --version
```

Then run the test suite:

```bash
make test
```

To run only the focused practical demo tests:

```bash
make demos
```

To print the active Java/Maven versions and run the test suite:

```bash
make check
```

To run the final local pre-release gate:

```bash
make release-check
```

To check documentation navigation consistency:

```bash
make docs-audit
```

To run the full documentation validation gate:

```bash
make docs-check
```

To generate the local JavaDoc reference:

```bash
make docs
```

The generated JavaDoc is written to `target/site/apidocs/index.html`.
The published JavaDoc site is available at https://jrodolfo.github.io/java-evolution/.

## Repository Status

- Examples are built and tested against the required JDK listed above.
- Spring Boot is used as lightweight project tooling; the examples themselves are plain Java.
- Some features are represented by `Notes` classes or explanatory modules because they involve preview flags, incubator modules, native code, JVM flags, external tools, cryptography providers, source-launcher behavior, or runtime behavior.
- `Notes` does not mean a Java feature is unfinished. Some final features are documented as notes or explanatory modules because they are not a good fit for a tiny portable JUnit example.
- Documentation health is checked locally with `make docs-check`; navigation and links are also checked in GitHub Actions.
- JavaDoc is generated locally with `make docs` and published through GitHub Pages.
- GitHub Actions use the same Make targets where practical, so local commands and CI stay aligned.

## Why a Single Maven Module?

This repository is intentionally a single-module Maven project.

The `java01` through `java26` packages are learning chapters that organize examples by the Java release in which features were introduced. They are not separate libraries or deployable artifacts.

Keeping the repository as one Maven project provides a single source tree, test suite, JavaDoc site, dependency configuration, and build model.

Maven remains the Java build system and owns compilation, testing, and JavaDoc generation.

The Makefile does not replace Maven. It provides convenient repository-level commands that combine Maven with other development and documentation tasks, such as JDK checks, documentation audits, link checking, demo test groups, and release validation.

## Important Design Choice

The Maven build uses a single Java release for every package:

```xml
<java.version>25</java.version>
```

Each package demonstrates features introduced in a specific Java release. For example, `java01` refreshes early Java foundations, `java05` demonstrates Java 5 features, and `java21` demonstrates Java 21 features.

Some older preview or incubator features changed before becoming final. In those cases, the project either:

- uses syntax compatible with the configured project JDK and explains the original preview status, or
- uses a `Notes` class when the original feature requires preview flags, incubator modules, native code, JVM flags, external processes, or platform-specific setup.

When a feature is final but represented by a notes class or explanatory module, the status tables use labels such as `final, notes-only` or `final, explanatory module`: final describes the Java feature, and the second label describes how this repository documents it.

## Repository Structure

```text
src/main/java/net/jrodolfo/java_evolution/
  java01/
  java02/
  java03/
  java04/
  java05/
  java06/
  java07/
  java08/
  java09/
  java10/
  ...
  java26/

src/test/java/net/jrodolfo/java_evolution/
  java01/
  java02/
  java03/
  java04/
  java05/
  java06/
  java07/
  java08/
  java09/
  java10/
  ...
  java26/
```

Example classes contain JavaDoc. Test classes are written as executable documentation with clear assertions. Packages can also include a version README with a deeper explanation of the problems each feature solved.

## Documentation

Start here depending on what you need:

- New to the repository: read this README, then follow the [Study Guide](docs/study-guide.md).
- Moving from an older Java baseline: use the [Migration Guide](docs/migration-guide.md).
- Studying over multiple sessions: use the [Learning Path](docs/learning-path.md).
- Wanting the historical dates: use the [Java Release Timeline](docs/java-release-timeline.md).
- Looking for hands-on walkthroughs: use the [Practical Demos](docs/practical-demos.md).
- Looking up acronyms and recurring platform terms: use the [Glossary](docs/glossary.md).
- Looking for one feature quickly: use the [Feature Map](docs/feature-map.md).
- Checking maturity or official references: use the [Status Matrix](docs/status-matrix.md) and [JEP Index](docs/jep-index.md).
- Preparing to present the project: use the [Interview Guide](docs/interview-guide.md) and [Demo Script](docs/demo-script.md).
- Preparing a release: use the [Release Checklist](docs/release/checklist.md).
- Browsing API-style documentation: use the [JavaDoc Site](https://jrodolfo.github.io/java-evolution/).

- [Changelog](CHANGELOG.md): repository release history.
- [Study Guide](docs/study-guide.md): suggested learning path through Java 1-26.
- [Java Release Timeline](docs/java-release-timeline.md): release months and historical naming context from Java 1 through Java 26.
- [Migration Guide](docs/migration-guide.md): practical guide for moving from Java 8, 11, 17, or 21 to newer baselines.
- [Learning Path](docs/learning-path.md): staged study sessions with files to read, tests to run, and interview angles.
- [Practical Demos](docs/practical-demos.md): focused walkthroughs for tooling, security, native, and class-file features, with `make demos` as a pre-demo check.
- [Glossary](docs/glossary.md): acronyms and recurring Java platform terms used throughout the repository.
- [Interview Guide](docs/interview-guide.md): talking points and demo flow for interviews.
- [Demo Script](docs/demo-script.md): practical 5-10 minute live walkthrough.
- [JEP Index](docs/jep-index.md): centralized JEP links with status labels.
- [Feature Map](docs/feature-map.md): class-by-class navigation table.
- [Status Matrix](docs/status-matrix.md): feature status, example class, test class, and reference links.
- [Release Checklist](docs/release/checklist.md): repeatable release process.
- [v1.5.3 Release Notes](docs/release/notes-v1.5.3.md): latest GitHub release text; older release notes live in `docs/release/`.
- [JavaDoc Site](https://jrodolfo.github.io/java-evolution/): generated API documentation published by GitHub Pages.

## Feature Index

| Java | Released | Package | Highlights |
|---|---|---|---|
| 1 | Jan 1996 / Feb 1997 | [`java01`](src/main/java/net/jrodolfo/java_evolution/java01/README.md) | Object-Oriented Foundations, Interfaces, Checked Exceptions, Threads, `java.io`, Inner Classes, Reflection, Serialization, JDBC, RMI, and JavaBeans examples |
| 2 | Dec 1998 | [`java02`](src/main/java/net/jrodolfo/java_evolution/java02/README.md) | Collections Framework, Sorting, `strictfp`, Swing, Java 2D, and Security Policy examples |
| 3 | May 2000 | [`java03`](src/main/java/net/jrodolfo/java_evolution/java03/README.md) | Dynamic Proxy, Timer, Shutdown Hooks and JNDI examples, Legacy Integration notes |
| 4 | Feb 2002 | [`java04`](src/main/java/net/jrodolfo/java_evolution/java04/README.md) | Assertions, Regex, NIO, Logging, Chained Exceptions, XML, and Security examples, Preferences notes |
| 5 | Sep 2004 | [`java05`](src/main/java/net/jrodolfo/java_evolution/java05/README.md) | Generics, Enhanced For Loop, Autoboxing, Enums, Varargs, Static Import, Annotations, Concurrency Utilities |
| 6 | Dec 2006 | [`java06`](src/main/java/net/jrodolfo/java_evolution/java06/README.md) | Navigable Collections, Monitoring/MXBeans example, Scripting/Compiler/Console/Web-Service explanatory modules |
| 7 | Jul 2011 | [`java07`](src/main/java/net/jrodolfo/java_evolution/java07/README.md) | Try-With-Resources, Multi-Catch, Diamond Operator, String Switch, NIO.2, Fork/Join, `invokedynamic` example |
| 8 | Mar 2014 | [`java08`](src/main/java/net/jrodolfo/java_evolution/java08/README.md) | Lambdas, Streams, Optional, Method References, CompletableFuture, Default Methods, Date/Time API |
| 9 | Sep 2017 | [`java09`](src/main/java/net/jrodolfo/java_evolution/java09/README.md) | Collection Factories, Optional/Stream Enhancements, Private Interface Methods, Process API, StackWalker, Module System example |
| 10 | Mar 2018 | [`java10`](src/main/java/net/jrodolfo/java_evolution/java10/README.md) | `var`, Unmodifiable Collectors, `Optional.orElseThrow()` |
| 11 | Sep 2018 | [`java11`](src/main/java/net/jrodolfo/java_evolution/java11/README.md) | String APIs, Files read/write string, HTTP Client, `Predicate.not`, lambda `var`, `Optional.isEmpty` |
| 12 | Mar 2019 | [`java12`](src/main/java/net/jrodolfo/java_evolution/java12/README.md) | Switch Expressions preview, `Collectors.teeing`, `String.indent`, `Files.mismatch`, Compact Number Format |
| 13 | Sep 2019 | [`java13`](src/main/java/net/jrodolfo/java_evolution/java13/README.md) | Text Blocks preview, switch `yield` preview, `FileSystems.newFileSystem(Path)` |
| 14 | Mar 2020 | [`java14`](src/main/java/net/jrodolfo/java_evolution/java14/README.md) | Switch Expressions final, Helpful NullPointerExceptions, Records preview, Pattern Matching for `instanceof` preview |
| 15 | Sep 2020 | [`java15`](src/main/java/net/jrodolfo/java_evolution/java15/README.md) | Text Blocks final, Sealed Classes preview, Hidden Classes example |
| 16 | Mar 2021 | [`java16`](src/main/java/net/jrodolfo/java_evolution/java16/README.md) | Records final, Pattern Matching for `instanceof` final, `Stream.toList`, Unix-domain socket example |
| 17 | Sep 2021 | [`java17`](src/main/java/net/jrodolfo/java_evolution/java17/README.md) | Sealed Classes final, Pattern Matching for switch preview, Random Generator API, `HexFormat`, Strong Encapsulation example |
| 18 | Mar 2022 | [`java18`](src/main/java/net/jrodolfo/java_evolution/java18/README.md) | UTF-8 default charset, Simple Web Server example, JavaDoc snippets example, Internet-Address Resolution SPI example |
| 19 | Sep 2022 | [`java19`](src/main/java/net/jrodolfo/java_evolution/java19/README.md) | Virtual Threads preview, Record Patterns preview, Pattern Matching switch preview, Structured Concurrency notes, FFM notes |
| 20 | Mar 2023 | [`java20`](src/main/java/net/jrodolfo/java_evolution/java20/README.md) | Record Patterns second preview, Pattern Matching switch fourth preview, Vector API example, Scoped Values/Structured Concurrency/FFM notes |
| 21 | Sep 2023 | [`java21`](src/main/java/net/jrodolfo/java_evolution/java21/README.md) | Virtual Threads final, Record Patterns final, Pattern Matching for switch final, Sequenced Collections, KEM example |
| 22 | Mar 2024 | [`java22`](src/main/java/net/jrodolfo/java_evolution/java22/README.md) | Unnamed Variables and Patterns final, FFM executable native example, Stream Gatherers explanatory module, Class-File API explanatory module |
| 23 | Sep 2024 | [`java23`](src/main/java/net/jrodolfo/java_evolution/java23/README.md) | Markdown Documentation Comments, Unsafe Memory-Access, and ZGC examples, preview notes |
| 24 | Mar 2025 | [`java24`](src/main/java/net/jrodolfo/java_evolution/java24/README.md) | Stream Gatherers final, Class-File API, Security Manager/AOT/runtime modules, post-quantum crypto examples, preview bridge modules |
| 25 | Sep 2025 | [`java25`](src/main/java/net/jrodolfo/java_evolution/java25/README.md) | Scoped Values final, Flexible Constructor Bodies final, Module Imports, KDF, Compact Source Files, JFR, AOT, and Object Headers examples, GC notes |
| 26 | Mar 2026 | [`java26`](src/main/java/net/jrodolfo/java_evolution/java26/README.md) | HTTP/3 notes, Final Field restrictions, Applet API removal, AOT Object Caching, G1, PEM/Structured Concurrency/Lazy Constants/Vector/Primitive Patterns notes |

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

Java 1 through Java 7 predate the modern OpenJDK release-project pages and the JEP process used for later releases. Their historical release notes, specifications, and API references are tracked in [docs/jep-index.md](docs/jep-index.md#java-1-7) and in the individual version READMEs.

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
- [OpenJDK JDK 26](https://openjdk.org/projects/jdk/26/)

## Contact

- Software Developer: Rod Oliveira
- GitHub: https://github.com/jrodolfo
- Webpage: https://jrodolfo.net

## License

- MIT License
- Copyright (c) 2026 Rod Oliveira
- See [LICENSE](./LICENSE)
