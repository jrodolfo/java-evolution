# Study Guide

This guide suggests one way to study the repository without treating every Java release as equally important on the first pass.

The repository is organized by release, but learning Java features is easier if you group them by theme: functional style, platform modernization, language simplification, pattern matching, concurrency, and recent preview work.

For release months and historical naming context, use [java-release-timeline.md](java-release-timeline.md).

## How To Use The Examples

For each feature, read in this order:

1. The version README, to understand the problem the feature solved.
2. The example or notes class, to see the smallest useful representation.
3. The matching test, to see the expected behavior as executable documentation.

For example:

```text
src/main/java/net/jrodolfo/java_evolution/java08/StreamExamples.java
src/test/java/net/jrodolfo/java_evolution/java08/StreamExamplesTest.java
```

Run the examples through tests:

```bash
make test
```

Use JavaDoc when you want a browsable API reference:

```bash
make docs
```

The generated site is written to:

```text
target/site/apidocs/index.html
```

## First Pass

Start here if the goal is interview preparation or practical Java fluency.

| Phase | Java Versions | Focus |
|---|---|---|
| Early foundations | 1-4 | Objects, interfaces, exceptions, threads, I/O, collections, reflection, regex, NIO |
| Pre-Java-8 foundations | 5-7 | Generics, enums, annotations, concurrency utilities, Project Coin, NIO.2 |
| Functional Java | 8 | Lambdas, streams, method references, `Optional`, default methods, date/time |
| Platform/API modernization | 9-11 | Collections factories, modules, HTTP Client, String/File APIs, `var` |
| Language simplification | 12-17 | Switch expressions, text blocks, records, pattern matching, sealed classes |
| Modern concurrency and data modeling | 18-22 | UTF-8 default, virtual threads, record patterns, pattern switch, sequenced collections |
| Post-Java-21 expansion | 22-24 | FFM, Stream Gatherers, Class-File API, source simplification, runtime/security notes |
| LTS and current-release maturity | 25-26 | Final vs preview/incubator status, scoped values, compact source files, runtime/removal notes |

Do not try to memorize every JEP number on the first pass. Focus on explaining why the feature exists and where you would use it.

## Recommended Study Order

1. **Java 1-4**
   Study object-oriented basics, interfaces, checked exceptions, threads, `java.io`, collections, dynamic proxies, regex, NIO, logging, and chained exceptions. These are the foundation beneath later Java evolution.

2. **Java 5-7**
   Study generics, enums, annotations, `java.util.concurrent`, try-with-resources, multi-catch, NIO.2, and fork/join. These explain the baseline that Java 8 built on.

3. **Java 8**
   Study lambdas, streams, `Optional`, method references, `CompletableFuture`, default methods, and the date/time API. These are still daily-use features.

4. **Java 10, 11**
   Study `var`, `HttpClient`, String APIs, files APIs, `Predicate.not`, and `Optional.isEmpty`. These show how Java became more concise without becoming dynamic.

5. **Java 14-17**
   Study switch expressions, records, pattern matching for `instanceof`, sealed classes, `HexFormat`, and strong encapsulation. These are common in modern code reviews and migration discussions.

6. **Java 21**
   Spend extra time here because Java 21 is an LTS release. Focus on virtual threads, record patterns, pattern matching for `switch`, and sequenced collections.

7. **Java 22-24**
   Study these as post-Java-21 expansion releases. Focus on final platform APIs, source simplification, and the difference between executable examples and explanatory modules.

8. **Java 25-26**
   Study these for LTS and current-version awareness. Pay attention to status labels: final, preview, incubator, notes-only, runtime, tooling, cryptography, or removal.

## What To Skip Initially

It is reasonable to skim these on the first pass:

- low-level runtime and GC notes
- cryptography provider details
- incubator APIs
- native interop details
- source launcher behavior
- JFR/AOT operational features

These are useful, but they are less likely to matter before you understand the language and library features used in everyday code.

## How To Review One Java Version

Use this checklist:

1. Read the package README.
2. Name each feature in one sentence.
3. Explain what problem each feature solved.
4. Open each example class and read the JavaDoc.
5. Open the matching test and read the assertion messages.
6. Run the tests for the whole project with `make test`.

For a quick version review, use [feature-map.md](feature-map.md).

For JEP lookup, use [jep-index.md](jep-index.md).

## Interview Prep Path

If time is limited, prioritize these topics:

- Java 8: lambdas, streams, `Optional`, date/time
- Java 10: `var`
- Java 11: HTTP Client and String APIs
- Java 14-17: switch expressions, records, pattern matching, sealed classes
- Java 21: virtual threads, record patterns, pattern matching for `switch`, sequenced collections
- Java 22-24: post-Java-21 platform expansion
- Java 25-26: final vs preview awareness and baseline discipline

The most convincing interview answer is not "I know Java 21." It is:

```text
I can explain what changed, why it changed, and I have small tested examples here.
```
