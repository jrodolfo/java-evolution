# Java Evolution

[![build](https://github.com/jrodolfo/java-evolution/actions/workflows/build.yml/badge.svg)](https://github.com/jrodolfo/java-evolution/actions/workflows/build.yml)
[![links](https://github.com/jrodolfo/java-evolution/actions/workflows/links.yml/badge.svg)](https://github.com/jrodolfo/java-evolution/actions/workflows/links.yml)
[![license: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

`java-evolution` is a practical Java reference project covering features introduced from Java 1 through Java 26. It uses small plain-Java examples, focused JUnit tests, and JavaDoc to explain what changed, why it matters, and how the APIs are used.

## Why This Exists

Java keeps evolving, while everyday work can settle into older habits. This repository makes that evolution concrete through readable examples and tests suitable for study and interview preparation.

## Requirements

- JDK 26
- Maven 3.9+
- GNU Make for repository-level convenience targets
- Node.js for documentation audits
- [`lychee`](https://github.com/lycheeverse/lychee) for Markdown and HTML link checks

See [Platform Setup](docs/platform-setup.md) for platform-specific JDK helpers and optional-tool details.

## Quick Start

On macOS, select JDK 26 for the current terminal session, then verify Java and Maven:

```bash
source scripts/use-java-26-mac.sh
mvn --version
```

Use the Linux or Windows helper described in [Platform Setup](docs/platform-setup.md) on other platforms.

Run the main checks:

```bash
make check
```

Useful targets:

```bash
make test
make links
make demos
make docs
make docs-check
make release-check
```

The generated JavaDoc is written to `target/site/apidocs/index.html` and is published at the [JavaDoc site](https://jrodolfo.github.io/java-evolution/).

## Project Model

This is intentionally a single-module Maven project. The `java01` through `java26` packages are learning chapters, not separate libraries or deployable artifacts. One Maven project provides a single source tree, test suite, JavaDoc site, dependency configuration, and build model.

Maven owns compilation, testing, and JavaDoc generation. The Makefile does not replace Maven; it combines Maven with JDK checks, documentation audits, link checking, focused demo groups, and release validation.

The project uses one Java release for the build: `26`. Each package still teaches features from its historical release. Feature maturity and repository representation are separate concerns: a feature may be final while its repository representation remains a `Notes` class or explanatory module when a small portable example would be misleading or impractical.

## Coverage

- [Complete Java 1-26 Feature Index](docs/feature-index.md)
- [Class-by-Class Feature Map](docs/feature-map.md)
- [Feature Status Matrix](docs/status-matrix.md)
- [Java Release Timeline](docs/java-release-timeline.md)
- [JEP Index and Official References](docs/jep-index.md)

## Documentation

- [Study Guide](docs/study-guide.md): suggested learning order through Java 1-26.
- [Learning Path](docs/learning-path.md): staged sessions with files, tests, and interview angles.
- [Migration Guide](docs/migration-guide.md): moving from older Java baselines to newer releases.
- [Practical Demos](docs/practical-demos.md): focused hands-on walkthroughs.
- [Interview Guide](docs/interview-guide.md): interview talking points and demo flow.
- [Demo Script](docs/demo-script.md): a practical 5-10 minute project walkthrough.
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

Java 1 through Java 7 predate the modern OpenJDK release pages and JEP process, so their entries use historical release notes, specifications, and API documentation. Java 8 through Java 26 use the JEP process and OpenJDK release references where available. All historical and official links are organized in the [JEP Index](docs/jep-index.md) and [Java Release Timeline](docs/java-release-timeline.md).

## Contact

- Software Developer: Rod Oliveira
- GitHub: https://github.com/jrodolfo
- Webpage: https://jrodolfo.net

## License

- MIT License
- Copyright (c) 2026 Rod Oliveira
- See [LICENSE](./LICENSE)
