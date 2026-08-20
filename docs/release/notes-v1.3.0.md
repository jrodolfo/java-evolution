# Release Notes: v1.3.0

`java-evolution` v1.3.0 expands the project from a Java 8 through Java 25 reference into a Java 1 through Java 25 learning path.

The release adds the older foundations that many Java developers still need to refresh for interviews and day-to-day reasoning: object-oriented basics, interfaces, checked exceptions, threads, I/O, collections, reflection, serialization, JDBC, RMI, Swing-era platform changes, NIO, regex, generics, annotations, concurrency utilities, Project Coin, NIO.2, fork/join, and `invokedynamic`.

## Highlights

- Added Java 1 through Java 4 foundation packages with focused examples, explanatory notes, package READMEs, package JavaDoc, and tests.
- Added Java 5 through Java 7 packages covering the bridge from classic Java into modern Java language and platform features.
- Added explanatory modules for features that are better taught through notes than fragile local fixtures, such as JDBC, RMI, JNDI, JAAS, compiler tooling, scripting, web services, and `invokedynamic`.
- Expanded repository-level navigation so the README, feature map, status matrix, JEP/reference index, learning path, study guide, migration guide, and glossary cover Java 1 through Java 25.
- Improved historical accuracy by avoiding later syntax where it would weaken chronological learning, such as Java 7 diamond syntax in Java 5 and Java 6 examples.
- Strengthened Java 1 through Java 7 examples, tests, and documentation after source, test, documentation, and integration reviews.

## Why This Matters

The project is not only a guide to newer Java features. It is also a concept-refresh reference for developers who want to understand how Java reached its current shape.

Java 1 through Java 7 contain many interview and maintenance topics that still matter: why checked exceptions exist, how interfaces and polymorphism differ, why raw collections were painful, what generics solved, how synchronization differs from `java.util.concurrent`, why try-with-resources changed cleanup code, and how NIO.2 improved filesystem work.

This release keeps simple features simple and uses deeper explanatory modules only where a tiny runnable example would distort the feature or require unrealistic setup.

## Validation

Before publishing this release, run:

```bash
make release-check
```

Expected result outside restricted network and socket-binding environments:

- documentation navigation audit passes
- JavaDoc generation succeeds
- Markdown link check passes
- Maven tests pass on JDK 25
- practical demo tests pass

Known local-environment notes from the release preparation:

- `make links` can fail when external HTTP checks are blocked by firewall or sandbox rules.
- `mvn test` can fail in restricted environments if local socket binding is denied for the Java 18 Simple Web Server tests.

## Suggested GitHub Release Text

```text
java-evolution v1.3.0 expands the project from Java 8-25 coverage to a Java 1-25 learning path.

Highlights:
- added Java 1-4 foundation packages for classic Java concepts and early platform features
- added Java 5-7 packages for generics, annotations, concurrency utilities, Project Coin, NIO.2, fork/join, and invokedynamic
- added focused tests and explanatory notes for historical features that need context or special setup
- expanded README, feature map, status matrix, JEP/reference index, learning path, study guide, migration guide, and glossary coverage
- improved historical accuracy after source, test, documentation, and integration reviews

Validation:
- Java 1-7 focused Maven test suite
- node scripts/check-doc-navigation.mjs
- mvn javadoc:javadoc
- git diff --check
```
