# Java 21

Java 21 is a long-term support release and one of the most important modern Java milestones. Several features that had been previewed in earlier releases became final here, especially virtual threads, record patterns, pattern matching for `switch`, and sequenced collections.

For learners, Java 21 is where many pieces of modern Java start to feel connected: records describe data, sealed types describe limited hierarchies, record patterns deconstruct data, pattern matching for `switch` dispatches by shape, and virtual threads make straightforward blocking code scale better.

## Virtual Threads Final

Before virtual threads, server-side Java often had an uncomfortable concurrency choice.

Using one platform thread per request was simple to understand, but platform threads are relatively expensive. At high concurrency, thread count could become a bottleneck.

Using asynchronous or reactive code could scale well, but the code often became harder to read, debug, and maintain.

Virtual threads solve this by making threads lightweight enough for thread-per-task code to scale much further. The point is not to make CPU-bound work faster. The point is to make blocking I/O code easier to write while still supporting high concurrency.

Example: `VirtualThreadsExamples`

Test: `VirtualThreadsExamplesTest`

## Record Patterns Final

Records made data-carrier classes concise, but reading nested record data still required accessor calls.

Record patterns solve that by allowing code to check a record type and bind its components at the same time:

```java
if (shape instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
	return "rectangle from " + x1 + "," + y1 + " to " + x2 + "," + y2;
}
```

This makes pattern matching more useful for domain data modeled with records.

Example: `RecordPatternsExamples`

Test: `RecordPatternsExamplesTest`

## Pattern Matching for switch Final

Before pattern matching for `switch`, type-based branching usually required `if`/`else if` chains with `instanceof` checks and casts.

Java 21 finalized pattern matching for `switch`, allowing a switch expression to branch directly on type patterns. When combined with sealed hierarchies, the compiler can also reason about whether all permitted cases are covered.

This is useful for command models, event models, protocol messages, and other closed sets of domain types.

Example: `PatternMatchingSwitchExamples`

Test: `PatternMatchingSwitchExamplesTest`

## Sequenced Collections

Before Java 21, Java had ordered collections, but there was no common interface for operations like "give me the first element", "give me the last element", or "view this collection in reverse order."

Different collection types exposed different APIs, so generic code had to know whether it was working with a `List`, `Deque`, `LinkedHashMap`, or something else.

Sequenced collections solve this by adding common abstractions for encounter order:

- `SequencedCollection`
- `SequencedSet`
- `SequencedMap`

Example: `SequencedCollectionsExamples`

Test: `SequencedCollectionsExamplesTest`

## Unnamed Patterns and Variables Preview

Sometimes a variable exists only because the syntax requires a name, even though the program intentionally ignores the value.

Before unnamed variables, developers often used names like `ignored`, `unused`, or `e`. Those names communicate intent informally, but they are still ordinary variables.

Java 21 previewed `_` for values that are intentionally unused. The feature became final in Java 22.

Example: `UnnamedPatternsVariablesPreviewExamples`

Test: `UnnamedPatternsVariablesPreviewExamplesTest`

## Scoped Values Preview

Thread-local variables are often used to pass contextual information, such as a request ID, through a call chain. The problem is that `ThreadLocal` values are mutable and can be difficult to clean up correctly, especially with many tasks and threads.

Scoped values provide a safer model for immutable contextual data bound to a limited execution scope.

This repository keeps the Java 21 version as notes because the API was preview and continued to evolve.

Example: `ScopedValuesPreviewNotes`

Test: `ScopedValuesPreviewNotesTest`

## Structured Concurrency Preview

Concurrent code is easier to reason about when related tasks have a clear parent scope. If one child task fails, the parent should have a clear policy for cancelling, joining, and reporting the failure.

Structured concurrency addresses that by treating related subtasks as one unit of work.

This repository keeps the Java 21 version as notes because the API was preview and changed in later releases.

Example: `StructuredConcurrencyPreviewNotes`

Test: `StructuredConcurrencyPreviewNotesTest`

## Key Encapsulation Mechanism API

A Key Encapsulation Mechanism helps two parties establish shared symmetric key material using asymmetric cryptography.

The problem is common in secure communication: two sides need to agree on a secret without sending that secret directly over the network.

Java 21 introduced KEM as a final security API in JEP 452. This repository keeps KEM as notes because a realistic example depends on cryptographic provider details and would distract from the core Java-version learning path.

Example: `KeyEncapsulationMechanismNotes`

Test: `KeyEncapsulationMechanismNotesTest`

## How To Read This Package

Start with the final features: `VirtualThreadsExamples`, `RecordPatternsExamples`, `PatternMatchingSwitchExamples`, and `SequencedCollectionsExamples`. Then read the preview and API notes: `UnnamedPatternsVariablesPreviewExamples`, `ScopedValuesPreviewNotes`, `StructuredConcurrencyPreviewNotes`, and `KeyEncapsulationMechanismNotes`.

Run the focused tests:

```bash
mvn -Dtest=VirtualThreadsExamplesTest,RecordPatternsExamplesTest,PatternMatchingSwitchExamplesTest test
mvn -Dtest=SequencedCollectionsExamplesTest,UnnamedPatternsVariablesPreviewExamplesTest test
mvn -Dtest=ScopedValuesPreviewNotesTest,StructuredConcurrencyPreviewNotesTest,KeyEncapsulationMechanismNotesTest test
```

Java 21 is an LTS release, so spend extra time here. After this package, continue with Java 22 to see unnamed variables become final and more preview/incubator work continue.

## References

- [OpenJDK JDK 21 project](https://openjdk.org/projects/jdk/21/)
- [JEP 444: Virtual Threads](https://openjdk.org/jeps/444)
- [JEP 440: Record Patterns](https://openjdk.org/jeps/440)
- [JEP 441: Pattern Matching for switch](https://openjdk.org/jeps/441)
- [JEP 431: Sequenced Collections](https://openjdk.org/jeps/431)
- [JEP 443: Unnamed Patterns and Variables](https://openjdk.org/jeps/443)
- [JEP 446: Scoped Values](https://openjdk.org/jeps/446)
- [JEP 453: Structured Concurrency](https://openjdk.org/jeps/453)
- [JEP 452: Key Encapsulation Mechanism API](https://openjdk.org/jeps/452)
