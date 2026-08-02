# Release Notes: v1.0.0

`java-evolution` v1.0.0 is the first public release of this didactic Java reference project.

The repository demonstrates features introduced from Java 8 through Java 25. It is organized by Java version, with focused classes, notes, tests, and package README files designed for learning and interview preparation.

## Highlights

- Java 8 through Java 25 package structure.
- Plain Java examples inside a lightweight Spring Boot/Maven project.
- Focused JUnit 5 tests that act as executable documentation.
- JavaDoc for browsing the classes as an API-style reference.
- GitHub Pages publication for generated JavaDoc.
- Documentation for studying, presenting, migrating, and extending the project.

## Documentation Included

- `README.md`: project overview and navigation.
- `docs/study-guide.md`: first-pass study order.
- `docs/learning-path.md`: staged study sessions.
- `docs/migration-guide.md`: practical guide for moving from Java 8, 11, 17, or 21 to newer baselines.
- `docs/interview-guide.md`: interview talking points and strong-answer examples.
- `docs/demo-script.md`: short live walkthrough script.
- `docs/feature-map.md`: class-by-class navigation.
- `docs/status-matrix.md`: feature maturity and repository representation.
- `docs/jep-index.md`: centralized official JEP links.
- `docs/release-template.md`: checklist for adding future Java releases.
- `docs/release-checklist.md`: repeatable release process.

## Validation

Before publishing this release, run:

```bash
make docs-audit
make docs
make check
```

Expected result:

- documentation navigation audit passes
- JavaDoc generation succeeds
- Maven tests pass on JDK 25

## Known Notes

- Some features are represented by `Notes` classes. This does not mean the Java feature is unfinished. It means the feature is better documented as notes in this repository because it requires preview flags, incubator modules, JVM options, cryptography providers, source-launcher behavior, runtime setup, native integration, or operational measurement.
- The Java 11 HTTP Client test can be skipped in restricted environments when a local HTTP server cannot bind to a loopback port. The skip is intentional and guarded.

## Suggested GitHub Release Text

```text
Initial public release of java-evolution.

This release provides a didactic Java reference from Java 8 through Java 25, with focused examples, notes classes, JUnit executable documentation, JavaDoc generation, GitHub Pages publishing, and study/interview/migration documentation.

Validation:
- make docs-audit
- make docs
- make check
```
