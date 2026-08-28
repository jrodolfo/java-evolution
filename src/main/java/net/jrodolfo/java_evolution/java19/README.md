# Java 19

Released: September 2022 as Java SE 19.

Java 19 was an important preview-and-incubator release. Several features that later became central to modern Java appeared here in early form: virtual threads, structured concurrency, record patterns, pattern matching for `switch`, and the Foreign Function and Memory API.

Because this project compiles on JDK 26, examples use the current compatible syntax where possible and explain the Java 19 preview origin.

## Virtual Threads Preview

Before virtual threads, Java developers often had to choose between two imperfect models for server-side concurrency.

One model used a platform thread per request. That code was easy to understand because it looked sequential, but platform threads are relatively expensive. At very high concurrency, thread count became a practical limit.

Another model used asynchronous callbacks, futures, or reactive pipelines. That improved scalability, but the code often became harder to read, debug, and step through.

Virtual threads try to preserve the simple thread-per-task programming style while making threads cheap enough for very large numbers of blocking tasks.

Java 19 introduced virtual threads as a preview feature. They became final in Java 21.

Example: `VirtualThreadsPreviewExamples`

Test: `VirtualThreadsPreviewExamplesTest`

## Record Patterns Preview

Records made simple data carriers concise, but extracting values from records still required calling accessor methods manually.

Record patterns solve the next problem: if a record is a transparent data carrier, code should be able to deconstruct it directly in pattern matching.

Instead of checking the type and then calling accessors one by one, record patterns let the shape of the data appear directly in the condition:

```java
if (value instanceof Point(int x, int y)) {
	return "point x=" + x + " y=" + y;
}
```

Java 19 introduced record patterns as a preview feature. They became final in Java 21.

Example: `RecordPatternsPreviewExamples`

Test: `RecordPatternsPreviewExamplesTest`

## Pattern Matching for switch Preview

Before pattern matching for `switch`, type-based dispatch was usually written as an `if`/`else if` chain. That became noisy when several types were involved.

Pattern matching for `switch` lets a switch branch by type pattern, making type dispatch more compact and easier to scan.

Java 19 continued the preview work. The feature became final in Java 21.

Example: `PatternMatchingSwitchPreviewExamples`

Test: `PatternMatchingSwitchPreviewExamplesTest`

## Structured Concurrency Incubator

Before structured concurrency, related tasks were often started in different places and joined somewhere else. When one task failed, cancellation and cleanup could become difficult to reason about.

Structured concurrency treats related concurrent subtasks as one unit of work. The goal is to make concurrent code behave more like structured sequential code: there is a clear scope, clear ownership, and clear failure behavior.

Java 19 introduced structured concurrency as an incubator API in JEP 428. This repository keeps the Java 19 version as an explanatory module because the API was incubating and changed in later releases.

The class name still uses `PreviewNotes` to preserve the existing navigation, but the official Java 19 status is incubator.

Explanatory module: [`structured_concurrency`](structured_concurrency/README.md)

Test: `StructuredConcurrencyPreviewNotesTest`

## Foreign Function and Memory API Preview

Before the Foreign Function and Memory API, Java code usually used JNI for serious native integration. JNI is powerful, but it is also complex, unsafe, and easy to make platform-specific.

The Foreign Function and Memory API aims to provide a supported way to call native functions and work with memory outside the Java heap.

Java 19 was an early preview step. This repository keeps the Java 19 version as notes because the API changed before becoming final, and preserving every historical preview shape would make the project harder to run.

For an executable example of the final API, read the Java 22 [`foreign_function`](../java22/foreign_function/README.md) module.

Example: `ForeignFunctionMemoryApiPreviewNotes`

Test: `ForeignFunctionMemoryApiPreviewNotesTest`

## How To Read This Package

Start with `VirtualThreadsPreviewExamples` because virtual threads are the headline concurrency feature that later becomes final in Java 21. Then read `RecordPatternsPreviewExamples`, `PatternMatchingSwitchPreviewExamples`, `structured_concurrency/README.md`, and `ForeignFunctionMemoryApiPreviewNotes`.

Run the focused tests:

```bash
mvn -Dtest=VirtualThreadsPreviewExamplesTest,RecordPatternsPreviewExamplesTest test
mvn -Dtest=PatternMatchingSwitchPreviewExamplesTest,StructuredConcurrencyPreviewNotesTest,ForeignFunctionMemoryApiPreviewNotesTest test
```

The structured concurrency and foreign memory examples are notes-based because their early incubator and preview forms changed across releases. After this package, continue with Java 20 to see those APIs refined.

## References

- [OpenJDK JDK 19 project](https://openjdk.org/projects/jdk/19/)
- [JEP 425: Virtual Threads](https://openjdk.org/jeps/425)
- [JEP 428: Structured Concurrency](https://openjdk.org/jeps/428)
- [JEP 405: Record Patterns](https://openjdk.org/jeps/405)
- [JEP 427: Pattern Matching for switch](https://openjdk.org/jeps/427)
- [JEP 424: Foreign Function and Memory API](https://openjdk.org/jeps/424)
