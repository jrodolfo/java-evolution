# Interview Guide

This guide turns the repository into interview talking points. The goal is not to recite every release. The goal is to explain how Java evolved and show that you can reason about language features, APIs, and tradeoffs.

## How To Use This In An Interview

If you share the repository, point to three things:

1. **Package READMEs** explain what problem each feature solved.
2. **Example classes** show focused code without application noise.
3. **Tests** prove the behavior and act as executable documentation.

Good framing:

```text
I built this repository to study Java release by release. Each package shows only the features introduced in that version, and the tests document the expected behavior.
```

## Java 8: Functional Style

Key topics:

- Lambdas made behavior easier to pass as data.
- Streams made collection processing more declarative.
- Method references reduced lambda noise when an existing method already expressed the behavior.
- `Optional` gave APIs a way to represent absence without returning `null`.
- `CompletableFuture` introduced richer asynchronous composition.
- The date/time API replaced many painful parts of `Date` and `Calendar`.

Possible prompt:

```text
What changed in Java 8?
```

Strong answer:

```text
Java 8 changed the style of Java. Lambdas and streams made collection processing more declarative, default methods allowed interfaces to evolve, Optional made missing values explicit, CompletableFuture improved async composition, and the java.time API gave Java a much better date/time model.
```

Repo path:

```text
src/main/java/net/jrodolfo/java_evolution/java08
```

## Java 9-11: Platform And API Modernization

Key topics:

- Java 9 introduced the module system.
- Collection factory methods reduced boilerplate for small immutable collections.
- Stream and Optional gained useful additions.
- Process API and StackWalker improved diagnostics/runtime inspection.
- Java 10 added local variable type inference with `var`.
- Java 11 finalized the standard HTTP Client and added useful String/File APIs.

Possible prompt:

```text
Is var dynamic typing?
```

Strong answer:

```text
No. var is local variable type inference. The compiler infers a static type from the initializer. It reduces noise, but Java remains statically typed.
```

Repo paths:

```text
src/main/java/net/jrodolfo/java_evolution/java09
src/main/java/net/jrodolfo/java_evolution/java10
src/main/java/net/jrodolfo/java_evolution/java11
```

## Java 12-17: Language Cleanup And Java 17 LTS

Key topics:

- Switch expressions made `switch` produce values directly.
- Text blocks made multiline strings readable.
- Records removed boilerplate for transparent data carriers.
- Pattern matching for `instanceof` combined type check and binding.
- Sealed classes made closed type hierarchies explicit.
- Java 17 is an LTS release and finalized sealed classes.

Possible prompt:

```text
When would you use a record?
```

Strong answer:

```text
I would use a record for a transparent data carrier where the components define the value. It removes boilerplate for constructor, accessors, equals, hashCode, and toString, but it is not meant to replace every class.
```

Possible prompt:

```text
Why are sealed classes useful?
```

Strong answer:

```text
They let the type declare its permitted implementations. That is useful when the domain has a closed set of cases, and it works especially well with pattern matching and exhaustive switch handling.
```

Repo paths:

```text
src/main/java/net/jrodolfo/java_evolution/java12
src/main/java/net/jrodolfo/java_evolution/java13
src/main/java/net/jrodolfo/java_evolution/java14
src/main/java/net/jrodolfo/java_evolution/java15
src/main/java/net/jrodolfo/java_evolution/java16
src/main/java/net/jrodolfo/java_evolution/java17
```

## Java 18-22: Modern Concurrency And Pattern Matching

Key topics:

- Java 18 standardized UTF-8 as the default charset.
- Java 19 previewed virtual threads and record patterns.
- Java 20 refined preview/incubator features.
- Java 21 finalized virtual threads, record patterns, and pattern matching for `switch`, and introduced sequenced collections as a final Collections Framework feature.
- Java 22 finalized unnamed variables and patterns and the Foreign Function and Memory API.

Possible prompt:

```text
What problem do virtual threads solve?
```

Strong answer:

```text
They make thread-per-task code scale much better for blocking I/O workloads. Platform threads are expensive, while async/reactive code can be harder to read. Virtual threads keep the simple blocking style while supporting much higher concurrency.
```

Possible prompt:

```text
How do records, sealed classes, and pattern matching fit together?
```

Strong answer:

```text
Records model data concisely, sealed classes model closed hierarchies, record patterns deconstruct data, and pattern matching for switch dispatches by type or shape. Together they make domain modeling and exhaustive branching cleaner.
```

Repo paths:

```text
src/main/java/net/jrodolfo/java_evolution/java18
src/main/java/net/jrodolfo/java_evolution/java19
src/main/java/net/jrodolfo/java_evolution/java20
src/main/java/net/jrodolfo/java_evolution/java21
src/main/java/net/jrodolfo/java_evolution/java22
```

## Java 23-26: Current Release Awareness

Key topics:

- Java 23 introduced Markdown documentation comments and continued several previews.
- Java 24 finalized Stream Gatherers and the Class-File API.
- Java 25 finalized scoped values, flexible constructor bodies, module import declarations, compact source files, and the KDF API.
- Some Java 25 features are still preview or incubator, such as primitive patterns, stable values, PEM encodings, structured concurrency, and the Vector API.
- Java 26 adds HTTP/3 support, removes the Applet API, and continues preview/incubator/runtime work. This repository documents Java 26 as notes-only while the build baseline remains JDK 25.

Possible prompt:

```text
How do you talk about preview features responsibly?
```

Strong answer:

```text
I separate final features from preview and incubator features. A preview feature is available for feedback but can still change, so I would not present it as a stable production contract without checking the release status.
```

Possible prompt:

```text
Why does this repository document Java 26 without moving the build baseline from JDK 25?
```

Strong answer:

```text
JDK 25 is the stable build baseline for the project. Java 26 is still valuable for current-release awareness, but several topics involve JDK 26-only APIs, preview APIs, incubator modules, runtime behavior, or removed APIs. Notes keep the learning accurate without forcing the whole project to compile or run on JDK 26.
```

Repo paths:

```text
src/main/java/net/jrodolfo/java_evolution/java23
src/main/java/net/jrodolfo/java_evolution/java24
src/main/java/net/jrodolfo/java_evolution/java25
src/main/java/net/jrodolfo/java_evolution/java26
```

## Good Demo Flow

Use this flow if you have only a few minutes:

1. Show `java08/StreamExamples` to demonstrate Java 8 functional style.
2. Show `java16/RecordExamples` to demonstrate data-carrier simplification.
3. Show `java17/SealedClassesExamples` to demonstrate closed hierarchies.
4. Show `java21/VirtualThreadsExamples` to demonstrate modern concurrency.
5. Show `java25/scoped_values/README.md` and `java26/README.md` to demonstrate current-release awareness and maturity-status discipline.

Then show the tests to prove that the examples are not just snippets.

## Questions To Practice

- What did Java 8 change about everyday Java code?
- What is the difference between `var` and dynamic typing?
- When should you use `Optional`, and when should you avoid it?
- Why are records not just Lombok replacements?
- What problem do sealed classes solve?
- Why did virtual threads matter after years of reactive programming?
- What is the difference between final, preview, and incubator features?
- Why does this project use notes classes for some features instead of runnable demos?
