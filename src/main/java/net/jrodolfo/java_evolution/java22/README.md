# Java 22

Java 22 continued the modern Java direction from Java 21. Some features became final, such as unnamed variables and patterns and the Foreign Function and Memory API. Others stayed in preview because the Java platform was still refining their syntax or API shape.

This package is intentionally mixed: runnable examples are used where JDK 25-compatible syntax keeps things simple, and notes are used where the original Java 22 feature requires preview behavior, launcher behavior, native setup, or APIs that changed later.

## Unnamed Variables and Patterns Final

Before unnamed variables, Java forced developers to name values even when those values were intentionally ignored. That often produced variables named `ignored`, `unused`, or `e`.

Java 22 finalized `_` for unused variables and patterns. This makes intent explicit:

```java
for (String _ : values) {
	count++;
}
```

The underscore tells the reader and compiler that the value is not part of the logic.

Example: `UnnamedVariablesPatternsExamples`

Test: `UnnamedVariablesPatternsExamplesTest`

## Foreign Function and Memory API Final

Before the Foreign Function and Memory API, Java native integration usually meant JNI. JNI is powerful, but it can be difficult to write, test, secure, and maintain.

Java 22 finalized a supported API for calling native functions and accessing memory outside the Java heap.

The executable example in this package calls small C standard-library functions through `Linker`, `Arena`, `MemorySegment`, and `FunctionDescriptor`. It uses `atoi` to parse a native string as an integer and `strlen` to compute the length of a native C string.

Executable example: [`foreign_function`](foreign_function/README.md)

Executable test: `ForeignFunctionExamplesTest`

## Stream Gatherers Preview

Streams are good for many transformations, but custom intermediate operations were historically difficult to express cleanly.

For example, operations such as windowing, scanning, batching, or stateful transformations often required dropping out of the stream pipeline or writing custom collectors that only run at the end.

Stream Gatherers address this by allowing custom intermediate stream operations.

This repository keeps Java 22 gatherers as notes because the API was preview and later became final in Java 24.

Example: `StreamGatherersPreviewNotes`

Test: `StreamGatherersPreviewNotesTest`

## Class-File API Preview

Frameworks, compilers, agents, and bytecode tools often need to read, generate, or transform `.class` files.

Before the Class-File API, projects usually depended on third-party bytecode libraries. Those libraries remain valuable, but the JDK needed a standard API that evolves with the class-file format itself.

Java 22 introduced the Class-File API as a preview feature.

Example: `ClassFileApiPreviewNotes`

Test: `ClassFileApiPreviewNotesTest`

## Statements before super Preview

Before this feature, an explicit `super(...)` or `this(...)` call had to be the first statement in a constructor. That made it awkward to validate or prepare constructor arguments before passing them to the superclass constructor.

Java 22 previewed statements before `super(...)`, under strict safety rules. The constructor still cannot use the object being created before the superclass is initialized.

Example: `StatementsBeforeSuperPreviewNotes`

Test: `StatementsBeforeSuperPreviewNotesTest`

## Launch Multi-File Source Programs

Java can run a single source file directly, which is useful for small scripts and learning examples. Java 22 improved this by supporting multi-file source programs from the launcher.

The problem it solves is setup friction: small Java programs should not always require a Maven or Gradle project before they can be tried.

This repository keeps the topic as notes because testing launcher behavior would require spawning separate Java processes and temporary source trees.

Example: `LaunchMultiFileSourceProgramsNotes`

Test: `LaunchMultiFileSourceProgramsNotesTest`

## Scoped Values Second Preview

Scoped values continued in preview in Java 22.

The problem remains the same as Java 21: immutable contextual data should be passed through a bounded execution scope without the cleanup and mutability hazards of many `ThreadLocal` designs.

Example: `ScopedValuesSecondPreviewNotes`

Test: `ScopedValuesSecondPreviewNotesTest`

## Structured Concurrency Second Preview

Structured concurrency also continued in preview in Java 22.

The goal is to make related concurrent subtasks easier to manage as one parent operation, with clearer joining, failure, cancellation, and observability boundaries.

Example: `StructuredConcurrencySecondPreviewNotes`

Test: `StructuredConcurrencySecondPreviewNotesTest`

## How To Read This Package

Start with `UnnamedVariablesPatternsExamples` because unnamed variables and patterns are final in Java 22. Then read the notes for stream gatherers, Class-File API, constructor-body changes, source launching, scoped values, and structured concurrency. For native interop, read `foreign_function/README.md` before the FFM example classes.

Run the focused tests:

```bash
mvn -Dtest=UnnamedVariablesPatternsExamplesTest test
mvn -Dtest=StreamGatherersPreviewNotesTest,ClassFileApiPreviewNotesTest test
mvn -Dtest=ForeignFunctionExamplesTest test
mvn -Dtest=StatementsBeforeSuperPreviewNotesTest,LaunchMultiFileSourceProgramsNotesTest,ScopedValuesSecondPreviewNotesTest,StructuredConcurrencySecondPreviewNotesTest test
```

This package mixes final APIs with previews and notes-only topics, so use it to practice separating stable features from evolving ones. After this package, continue with Java 23 for markdown documentation comments and another round of preview refinements.

## References

- [OpenJDK JDK 22 project](https://openjdk.org/projects/jdk/22/)
- [JEP 456: Unnamed Variables and Patterns](https://openjdk.org/jeps/456)
- [JEP 454: Foreign Function and Memory API](https://openjdk.org/jeps/454)
- [JEP 461: Stream Gatherers](https://openjdk.org/jeps/461)
- [JEP 457: Class-File API](https://openjdk.org/jeps/457)
- [JEP 447: Statements before super(...)](https://openjdk.org/jeps/447)
- [JEP 458: Launch Multi-File Source-Code Programs](https://openjdk.org/jeps/458)
- [JEP 464: Scoped Values](https://openjdk.org/jeps/464)
- [JEP 462: Structured Concurrency](https://openjdk.org/jeps/462)
