# Java 20

Released: March 2023 as Java SE 20.

Java 20 was mostly a refinement release. Many features were in a second, fourth, or fifth preview/incubator round, which is useful to understand: important Java features often evolve publicly before they become final.

The practical lesson is that preview and incubator features are not promises of final API shape. They are a way for the Java platform to gather feedback while keeping the production language stable.

## Record Patterns Second Preview

Java 19 introduced record patterns as a preview feature. Java 20 refined them.

The problem remained the same: records are transparent carriers of data, so extracting their components should not require repetitive accessor calls when code is already matching on their shape.

Nested record patterns make the benefit clearer:

```java
if (value instanceof Customer(String name, Address(String city, String country))) {
	return name + " lives in " + city + ", " + country;
}
```

This repository uses the final JDK 26-compatible syntax while documenting that Java 20 was still preview territory.

Example: `RecordPatternsSecondPreviewExamples`

Test: `RecordPatternsSecondPreviewExamplesTest`

## Pattern Matching for switch Fourth Preview

Pattern matching for `switch` went through several preview rounds because it touches important language rules: null handling, exhaustiveness, type patterns, guarded patterns, and interaction with sealed hierarchies.

Java 20 continued refining the feature before finalization in Java 21.

The problem it solves is still straightforward: type-based branching should be expressible directly in `switch`, without long chains of `instanceof` checks and casts.

Example: `PatternMatchingSwitchFourthPreviewExamples`

Test: `PatternMatchingSwitchFourthPreviewExamplesTest`

## Virtual Threads Second Preview

Java 20 continued virtual threads as a second preview. The feature targeted the same problem introduced in Java 19: keeping thread-per-task code readable while making blocking concurrency scale much further than platform threads alone.

The final version arrived in Java 21. This package keeps Java 20 as notes to avoid repeating the same runnable demonstration in every preview package.

Read `VirtualThreadsExamples` in Java 21 for the final executable version.

Example: `VirtualThreadsSecondPreviewNotes`

Test: `VirtualThreadsSecondPreviewNotesTest`

## Scoped Values Incubator

Thread-local variables are useful for passing contextual data, such as request IDs or security information, without threading parameters through every method. But `ThreadLocal` can be hard to reason about: values are mutable, cleanup is easy to forget, and inheritance across threads can be surprising.

Scoped values were introduced as an incubating API to provide a safer model for sharing immutable contextual data within a bounded execution scope.

This repository keeps Java 20 scoped values as an explanatory module because the API was incubating and changed before finalization.

Explanatory module: [`scoped_values`](scoped_values/README.md)

Test: `ScopedValuesIncubatorNotesTest`

## Structured Concurrency Second Incubator

Java 20 continued structured concurrency as an incubating API.

The feature addresses a common concurrency problem: when a parent operation starts several child tasks, those tasks should have a clear lifetime relationship with the parent. Failure, cancellation, joining, and observability should be handled as one coordinated unit.

This repository keeps the feature as an explanatory module because the incubator API was still changing.

Explanatory module: [`structured_concurrency`](structured_concurrency/README.md)

Test: `StructuredConcurrencySecondIncubatorNotesTest`

## Foreign Function and Memory API Second Preview

Java 20 continued the Foreign Function and Memory API as a second preview.

The problem it solves is native interoperation. Java needed a supported alternative to JNI for calling native functions and accessing off-heap memory with clearer safety boundaries.

This repository keeps the feature as notes because native interop would distract from the main goal of simple, portable examples.

Read the Java 22 [`foreign_function`](../java22/foreign_function/README.md) module for the final executable version.

Example: `ForeignFunctionMemorySecondPreviewNotes`

Test: `ForeignFunctionMemorySecondPreviewNotesTest`

## Vector API Fifth Incubator

Many CPUs can process multiple values with one vector instruction. Before the Vector API, Java developers usually relied on the JIT compiler to discover vectorization opportunities automatically, or they used native libraries for performance-sensitive code.

The Vector API gives Java code a way to express vector computations more directly, while still letting the JVM map them to efficient hardware instructions when possible.

This repository demonstrates the feature with a child Java source file because the API was incubating and requires an incubator module.

Example module: [`vector_api`](vector_api/README.md)

Test: `VectorApiFifthIncubatorExamplesTest`

## How To Read This Package

Start with `RecordPatternsSecondPreviewExamples` and `PatternMatchingSwitchFourthPreviewExamples` to see the pattern matching work continue. Then read the notes classes for virtual threads and foreign memory. For contextual data, read `scoped_values/README.md` before continuing to the Java 21 and Java 25 scoped-values modules. For parent-child task lifetime, read `structured_concurrency/README.md` before the later structured-concurrency preview modules. For performance-oriented SIMD concepts, run the `vector_api` example before the later Java 25 Vector API incubator module.

Run the focused tests:

```bash
mvn -Dtest=RecordPatternsSecondPreviewExamplesTest,PatternMatchingSwitchFourthPreviewExamplesTest test
mvn -Dtest=VirtualThreadsSecondPreviewNotesTest,ScopedValuesIncubatorNotesTest,StructuredConcurrencySecondIncubatorNotesTest test
mvn -Dtest=ForeignFunctionMemorySecondPreviewNotesTest,VectorApiFifthIncubatorExamplesTest test
```

Most Java 20 topics are preview or incubator refinements, so pay attention to status rather than treating every API shape as final. After this package, continue with Java 21, where several of these features become final in an LTS release.

## References

- [OpenJDK JDK 20 project](https://openjdk.org/projects/jdk/20/)
- [JEP 432: Record Patterns](https://openjdk.org/jeps/432)
- [JEP 433: Pattern Matching for switch](https://openjdk.org/jeps/433)
- [JEP 436: Virtual Threads](https://openjdk.org/jeps/436)
- [JEP 429: Scoped Values](https://openjdk.org/jeps/429)
- [JEP 437: Structured Concurrency](https://openjdk.org/jeps/437)
- [JEP 434: Foreign Function and Memory API](https://openjdk.org/jeps/434)
- [JEP 438: Vector API](https://openjdk.org/jeps/438)
