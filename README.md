# java-evolution

[![build](https://github.com/jrodolfo/java-evolution/actions/workflows/build.yml/badge.svg)](https://github.com/jrodolfo/java-evolution/actions/workflows/build.yml)
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

To generate the local JavaDoc reference:

```bash
make docs
```

The generated JavaDoc is written to `target/site/apidocs/index.html`.

## Important Design Choice

The whole project compiles with JDK 25:

```xml
<java.version>25</java.version>
```

Each package demonstrates features introduced in a specific Java release. For example, `java08` demonstrates Java 8 features, while `java21` demonstrates Java 21 features.

Some older preview or incubator features changed before becoming final. In those cases, the project either:

- uses current JDK 25-compatible syntax and explains the original preview status, or
- uses a `Notes` class when the original feature requires preview flags, incubator modules, native code, JVM flags, external processes, or platform-specific setup.

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

Example classes contain JavaDoc. Test classes are written as executable documentation with clear assertions.

For a class-by-class navigation table, see [docs/feature-map.md](docs/feature-map.md).

## Feature Index

| Java | Package | Highlights |
|---|---|---|
| 8 | `java08` | Lambdas, Streams, Optional, Method References, CompletableFuture, Default Methods, Date/Time API |
| 9 | `java09` | Collection Factories, Optional/Stream Enhancements, Private Interface Methods, Process API, StackWalker, Modules notes |
| 10 | `java10` | `var`, Unmodifiable Collectors, `Optional.orElseThrow()` |
| 11 | `java11` | String APIs, Files read/write string, HTTP Client, `Predicate.not`, lambda `var`, `Optional.isEmpty` |
| 12 | `java12` | Switch Expressions preview, `Collectors.teeing`, `String.indent`, `Files.mismatch`, Compact Number Format |
| 13 | `java13` | Text Blocks preview, switch `yield` preview, `FileSystems.newFileSystem(Path)` |
| 14 | `java14` | Switch Expressions final, Helpful NullPointerExceptions, Records preview, Pattern Matching for `instanceof` preview |
| 15 | `java15` | Text Blocks final, Sealed Classes preview, Hidden Classes notes |
| 16 | `java16` | Records final, Pattern Matching for `instanceof` final, `Stream.toList`, Unix-domain socket notes |
| 17 | `java17` | Sealed Classes final, Pattern Matching for switch preview, Random Generator API, `HexFormat`, Strong Encapsulation notes |
| 18 | `java18` | UTF-8 default charset, Simple Web Server notes, JavaDoc snippets notes, InetAddress resolver SPI notes |
| 19 | `java19` | Virtual Threads preview, Record Patterns preview, Pattern Matching switch preview, Structured Concurrency notes, FFM notes |
| 20 | `java20` | Record Patterns second preview, Pattern Matching switch fourth preview, Scoped Values/Structured Concurrency/FFM/Vector notes |
| 21 | `java21` | Virtual Threads final, Record Patterns final, Pattern Matching switch final, Sequenced Collections, KEM notes |
| 22 | `java22` | Unnamed Variables and Patterns final, FFM final notes, Stream Gatherers preview notes, Class-File API preview notes |
| 23 | `java23` | Markdown Documentation Comments, Primitive Patterns preview notes, Module Imports preview notes, ZGC Generational Mode notes |
| 24 | `java24` | Stream Gatherers final, Class-File API notes, Security Manager disabled, Virtual Thread synchronization notes, post-quantum crypto notes |
| 25 | `java25` | Scoped Values final, Flexible Constructor Bodies final, Module Imports final notes, Compact Source Files notes, KDF/JFR/AOT/GC notes |

## How To Study This Repository

Start with the package for the Java version you want to review. Read the example class first, then read the matching test.

For example:

```text
src/main/java/net/jrodolfo/java_evolution/java08/StreamExamples.java
src/test/java/net/jrodolfo/java_evolution/java08/StreamExamplesTest.java
```

The tests are meant to explain the expected behavior. Running `make test` proves that the examples compile and behave as documented. Running `make docs` generates a browsable JavaDoc reference for the example classes.

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
