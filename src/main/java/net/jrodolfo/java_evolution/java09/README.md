# Java 9

Java 9 was a large release. Its headline feature was the Java Platform Module System (JPMS), but it also added several everyday improvements that made Java 8-style code easier to write and maintain.

The examples in this package keep the Spring Boot project non-modular on purpose. That lets the repository stay simple while still explaining what modules are for.

## Collection Factory Methods

Before Java 9, creating a small read-only collection was surprisingly verbose. You often used `Arrays.asList(...)`, wrapped it with `Collections.unmodifiableList(...)`, or manually created and populated a collection.

Java 9 added `List.of`, `Set.of`, and `Map.of` so small immutable collections could be created directly:

```java
List.of("modules", "collection factories", "stream enhancements")
```

These factories reject `null`, reject duplicate set elements or map keys, and return collections that cannot be modified.

Example: `CollectionFactoryExamples`

Test: `CollectionFactoryExamplesTest`

## Optional Enhancements

Java 8 introduced `Optional`, but some common operations were still awkward. Fallback lookups, handling both present and missing branches, and flattening `Optional` values into streams required extra code.

Java 9 added:

- `or(...)` for fallback `Optional` values
- `ifPresentOrElse(...)` for explicit present/missing branches
- `stream()` for using `Optional` inside stream pipelines

Example: `OptionalEnhancementExamples`

Test: `OptionalEnhancementExamplesTest`

## Stream Enhancements

Java 8 streams were powerful, but some common ordered stream operations were missing. Developers often had to write custom logic to keep values until a condition stopped being true, skip values until a boundary, or safely stream a possibly null value.

Java 9 added `takeWhile`, `dropWhile`, `Stream.ofNullable`, and a bounded form of `Stream.iterate`. These methods make stream pipelines more expressive, especially for ordered data.

Example: `StreamEnhancementExamples`

Test: `StreamEnhancementExamplesTest`

## Private Interface Methods

Java 8 default methods allowed interfaces to contain reusable behavior, but shared helper logic inside an interface had to be public or duplicated between default methods.

Java 9 allowed private methods in interfaces. That means default methods can share implementation details without exposing those helpers as part of the public API.

Example: `PrivateInterfaceMethodExamples`

Test: `PrivateInterfaceMethodExamplesTest`

## Try-With-Resources Improvement

Java 7 introduced try-with-resources, but the resource usually had to be declared inside the `try (...)` block. If you already had a final or effectively final resource variable, you often had to repeat it with another local variable.

Java 9 relaxed that rule. An existing final or effectively final resource can be used directly in the resource list.

Example: `TryWithResourcesExamples`

Test: `TryWithResourcesExamplesTest`

## Process API

Before Java 9, Java code had limited standard support for inspecting operating-system processes. Developers often had to rely on platform-specific shell commands or native code to get process IDs and related metadata.

Java 9 added `ProcessHandle`, which gives Java code a standard way to inspect the current process, parent process, child processes, and process metadata.

Example: `ProcessApiExamples`

Test: `ProcessApiExamplesTest`

## StackWalker

Before Java 9, stack inspection usually meant calling `Thread.currentThread().getStackTrace()`, which eagerly created an array of stack frames and exposed a relatively blunt API.

Java 9 added `StackWalker`, a lazy and structured API for walking stack frames. It is useful for diagnostics, logging, security-sensitive libraries, and frameworks that need caller information.

Example: `StackWalkerExamples`

Test: `StackWalkerExamplesTest`

## Module System

Before Java 9, the classpath had no strong module boundaries. Large applications could accidentally depend on internal packages, and the JDK itself was difficult to split into smaller pieces.

The Java Platform Module System (JPMS) introduced `module-info.java`, `requires`, and `exports`. It lets code declare which modules it depends on and which packages it exposes.

This repository explains modules without converting the Spring Boot app into a modular application, because the learning examples are meant to stay independent.

Explanatory module: [`module_system`](module_system/README.md)

Test: `ModuleSystemNotesTest`

## How To Read This Package

Start with `CollectionFactoryExamples`, `OptionalEnhancementExamples`, and `StreamEnhancementExamples` to see the everyday API improvements. Then read `PrivateInterfaceMethodExamples`, `TryWithResourcesExamples`, `ProcessApiExamples`, `StackWalkerExamples`, and `module_system/README.md`.

Run the focused tests:

```bash
mvn -Dtest=CollectionFactoryExamplesTest,OptionalEnhancementExamplesTest,StreamEnhancementExamplesTest test
mvn -Dtest=PrivateInterfaceMethodExamplesTest,TryWithResourcesExamplesTest test
mvn -Dtest=ProcessApiExamplesTest,StackWalkerExamplesTest,ModuleSystemNotesTest test
```

`module_system/README.md` explains JPMS as an explanatory module because a real module demo is clearer as a multi-module project than as a small class inside this Spring Boot shell. After this package, continue with Java 10 for local variable type inference and collector refinements.

## References

- [OpenJDK JDK 9 project](https://openjdk.org/projects/jdk9/)
- [JEP 261: Module System](https://openjdk.org/jeps/261)
- [JEP 269: Convenience Factory Methods for Collections](https://openjdk.org/jeps/269)
- [JEP 102: Process API Updates](https://openjdk.org/jeps/102)
- [JEP 259: Stack-Walking API](https://openjdk.org/jeps/259)
- [JEP 213: Milling Project Coin](https://openjdk.org/jeps/213)
