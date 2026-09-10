# Java Evolution

[![build](https://github.com/jrodolfo/java-evolution/actions/workflows/build.yml/badge.svg)](https://github.com/jrodolfo/java-evolution/actions/workflows/build.yml)
[![links](https://github.com/jrodolfo/java-evolution/actions/workflows/links.yml/badge.svg)](https://github.com/jrodolfo/java-evolution/actions/workflows/links.yml)
[![javadoc pages](https://github.com/jrodolfo/java-evolution/actions/workflows/javadoc-pages.yml/badge.svg)](https://github.com/jrodolfo/java-evolution/actions/workflows/javadoc-pages.yml)
[![latest release](https://img.shields.io/github/v/release/jrodolfo/java-evolution?display_name=tag&sort=semver)](https://github.com/jrodolfo/java-evolution/releases/latest)
[![Java 27](https://img.shields.io/badge/Java-27-orange.svg)](https://jdk.java.net/27/)
[![Maven 3.9+](https://img.shields.io/badge/Maven-3.9%2B-C71A36.svg)](https://maven.apache.org/download.cgi)
[![license: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

`java-evolution` is a practical Java reference project covering features introduced from Java 1 through Java 27. It uses small plain-Java examples, focused JUnit tests, and JavaDoc to explain what changed, why it matters, and how the APIs are used.

## Why This Exists

Java keeps evolving, while everyday work can settle into older habits. This repository makes that evolution concrete through readable examples and tests suitable for study and interview preparation.

## Requirements

- <a target="_blank" rel="noopener noreferrer" href="https://jdk.java.net/27/">JDK 27</a>
- <a target="_blank" rel="noopener noreferrer" href="https://maven.apache.org/download.cgi">Maven 3.9+</a>
- <a target="_blank" rel="noopener noreferrer" href="https://www.gnu.org/software/make/">GNU Make</a> for repository-level convenience targets
- <a target="_blank" rel="noopener noreferrer" href="https://nodejs.org/en/download">Node.js</a> for documentation audits
- <a target="_blank" rel="noopener noreferrer" href="https://github.com/lycheeverse/lychee"><code>lychee</code></a> for Markdown and HTML link checks

See [Platform Setup](docs/platform-setup.md) for platform-specific JDK helpers and optional-tool details.

## Quick Start

On macOS, select JDK 27 for the current terminal session, then verify Java and Maven:

```bash
source scripts/use-java-27-mac.sh
java --version
mvn --version
```

Use the Linux or Windows helper described in [Platform Setup](docs/platform-setup.md) on other platforms.

Run the main checks:

```bash
make check-build
```

Useful targets:

```bash
make run-tests
make check-links
make run-demos
make generate-docs
make check-docs
make check-release
```

The generated JavaDoc is written to `target/site/apidocs/index.html` and is published at the [JavaDoc site](https://jrodolfo.github.io/java-evolution/).

## Project Model

This is intentionally a single-module Maven project. The `java01` through `java27` packages are learning chapters, not separate libraries or deployable artifacts. One Maven project provides a single source tree, test suite, JavaDoc site, dependency configuration, and build model.

Maven owns compilation, testing, and JavaDoc generation. The Makefile does not replace Maven; it combines Maven with JDK checks, documentation audits, link checking, focused demo groups, and release validation.

The project uses one Java release for the build: `27`. Each package still teaches features from its historical release; for example, `java05` focuses on Java 5 generics, enhanced `for` loops, enums, varargs, and concurrency utilities. Although JDK 27 compiles and tests the project, the examples intentionally avoid later language features and APIs when illustrating an earlier release, so the code preserves the programming style and constraints that learners would have encountered at that time. The Java 1.1 JDBC example uses small local types rather than modern compatibility adapters for today's larger JDBC interfaces.

When demonstrating a preview feature from Java version `n`, an executable example may use later final syntax only when that syntax is also syntactically and semantically faithful to the Java `n` preview. The README for the example must identify the feature as preview in Java `n`. When the later syntax differs materially, the repository uses a historically faithful source snippet, generated source, descriptive model, or another suitable approach instead. Building with JDK 27 alone is not a reason to use later syntax in an earlier preview example.

Feature maturity and repository representation are separate decisions. A feature can be final in the Java platform while still being represented here by explanatory notes if a small, portable, and honest executable example would be misleading or impractical. When a feature can be demonstrated reliably, the repository provides executable example code with focused tests. When it depends on runtime behavior, workload measurements, special infrastructure, or other conditions that do not fit a small portable test, the repository provides explanatory notes with tests that verify the explanation. For example, Java 27 Compact Object Headers can be verified by inspecting the default VM flag, which is why the repository provides the `CompactObjectHeadersDefaultExamples.java` example class and focused tests. Java 27 G1 default selection can be verified with a child-JVM configuration probe, while its performance consequences require workload measurements, which is why the repository provides the `G1DefaultNotes.java` notes class and tests for both distinctions.

## Coverage

- [Java 1-27 Release-Level Feature Index](docs/feature-index.md)
- [Class-by-Class Feature Map](docs/feature-map.md)
- [Feature Status Matrix](docs/status-matrix.md)
- [Java Release Timeline](docs/java-release-timeline.md)
- [JEP Index and Official References](docs/jep-index.md)

## Documentation

- [Study Guide](docs/study-guide.md): suggested learning order through Java 1-27.
- [Learning Path](docs/learning-path.md): staged sessions with files, tests, and interview angles.
- [Migration Guide](docs/migration-guide.md): moving from older Java baselines to newer releases.
- [Practical Demos](docs/practical-demos.md): focused hands-on walkthroughs.
- [Interview Guide](docs/interview-guide.md): interview talking points and demo flow.
- [Demo Script](docs/demo-script.md): a practical 5-10 minute project walkthrough.
- [Questions](docs/questions.md): FAQ and cross-version reference supplement.
- [Glossary](docs/glossary.md): recurring Java platform terms and acronyms.
- [Changelog](CHANGELOG.md): repository release history.
- [Release Checklist](docs/release/checklist.md): repeatable release process.

## Study Workflow

Start with the package for the Java version you want to review. Read its version README, then the example class and matching test. The [Study Guide](docs/study-guide.md) provides a broader sequence.

To run one example, use its test class. For example:

```bash
mvn -Dtest=StreamExamplesTest test
mvn -Dtest=VirtualThreadsExamplesTest test
mvn -Dtest=ScopedValuesExamplesTest test
```

Use the [Feature Map](docs/feature-map.md) to find the class and test for a specific topic.

## Official References

Java 1 through Java 7 predate the modern OpenJDK release pages and JEP process, so their entries use historical release notes, specifications, and API documentation. Java 8 through Java 27 use the JEP process and OpenJDK release references where available. All historical and official links are organized in the [JEP Index](docs/jep-index.md) and [Java Release Timeline](docs/java-release-timeline.md).

## Contact

- Software Developer: Rod Oliveira
- GitHub: <a target="_blank" rel="noopener noreferrer" href="https://github.com/jrodolfo">https://github.com/jrodolfo</a>
- LinkedIn: <a target="_blank" rel="noopener noreferrer" href="https://linkedin.com/in/rodoliveira">https://linkedin.com/in/rodoliveira</a>
- Webpage: <a target="_blank" rel="noopener noreferrer" href="https://jrodolfo.net">https://jrodolfo.net</a>

## License

- MIT License
- Copyright (c) 2026 Rod Oliveira
- See [LICENSE](./LICENSE)
