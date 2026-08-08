# Contributing

This repository is a didactic Java reference. Contributions should keep each example small, readable, and easy to verify with tests.

## Example Pattern

Use one focused class for each feature:

```text
src/main/java/net/jrodolfo/java_evolution/javaXX/FeatureNameExamples.java
src/test/java/net/jrodolfo/java_evolution/javaXX/FeatureNameExamplesTest.java
```

Prefer names that describe the feature directly, such as `StreamExamples`, `RecordExamples`, or `SequencedCollectionsExamples`.

## Code Guidelines

- Keep each class focused on one language or library feature.
- Use plain Java examples unless Spring Boot is directly relevant to the feature.
- Add JavaDoc to classes and public methods.
- Add short comments only when they clarify a non-obvious language detail.
- Keep examples compatible with the repository JDK version.
- Use `Notes` classes for features that require preview flags, incubator modules, native code, JVM flags, external tools, or platform-specific setup.

## Test Guidelines

- Add a matching JUnit 5 test class for every example class.
- Treat tests as executable documentation.
- Use assertion messages when they help explain the expectation.
- Prefer small, deterministic examples over broad demonstrations.
- Avoid network, clock, file-system, and process dependencies unless the feature requires them.

## Documentation Guidelines

- Update `README.md` when adding a new Java version or major feature.
- Update `docs/feature-map.md` with the example and test class names.
- Link to JEPs or official documentation when a feature needs historical context.
- Run `make docs` after adding or changing JavaDoc.

## Practical Demos

When a feature becomes a practical demo, update the curated demo path:

- Add or update the package README.
- Update `docs/practical-demos.md`.
- Add the focused test class to the `demos` target in `Makefile`.
- Update the relevant version README, `docs/feature-map.md`, and `docs/status-matrix.md`.

## Local Validation

Use JDK 25 and run:

```bash
make check
make docs
```
