# Stream Gatherers Preview

Java 22 introduced Stream Gatherers as a preview feature.

This module is explanatory because the Java 22 API was still being evaluated. The final runnable example belongs to Java 24, where Stream Gatherers became final.

## What Problem Does This Feature Solve?

Java streams already have useful intermediate operations such as `map`, `filter`, `limit`, and `sorted`.

Those operations work well when each input element can be handled independently, or when the operation is already built into the JDK.

Some useful transformations do not fit that model:

```text
Input stream:
1, 2, 3, 4, 5

Fixed windows of size 3:
[1, 2, 3], [4, 5]

Running total:
1, 3, 6, 10, 15
```

Both examples need more than a simple one-element transformation.

A fixed-window operation must collect several input elements before it emits one output value. A running total must remember state while the stream is still flowing.

Before gatherers, developers usually had to choose between:

- leaving the stream pipeline and writing an ordinary loop
- forcing the logic into a collector that only runs as a terminal operation
- writing custom stream infrastructure that was difficult to read and maintain

## How Was This Commonly Done Before?

For simple cases, the clearest answer was often a loop:

```java
int runningTotal = 0;
List<Integer> totals = new ArrayList<>();

for (int number : numbers) {
	runningTotal += number;
	totals.add(runningTotal);
}
```

That code is honest and readable, but it cannot stay inside a stream pipeline.

A collector can help when the result is produced at the end of the stream. It is less natural when the transformation should emit new elements while the stream continues.

## What Did Java Introduce?

Java introduced Stream Gatherers as an extension point for custom intermediate stream operations.

The important phrase is **intermediate operation**.

An intermediate stream operation receives a stream and produces another stream. For example:

```text
stream
  -> filter(...)
  -> map(...)
  -> gather(...)
  -> collect(...)
```

The `gather(...)` step can express transformations that need buffering, state, or a custom rule for when output elements are emitted.

## Important Terminology

**Stream pipeline**

The chain of operations applied to a stream before a terminal operation runs.

**Intermediate operation**

An operation in the middle of the stream pipeline. It transforms one stream into another stream.

**Terminal operation**

The operation that starts stream processing and produces a final result, such as `toList()`, `count()`, or `collect(...)`.

**Gatherer**

A component that describes how to consume input elements from a stream and emit output elements into the next stage of the stream pipeline.

**Preview feature**

A feature included in a JDK release so developers can try it and provide feedback before it becomes final. Java 22 gatherers were preview; Java 24 gatherers are final.

## Why This Module Has Notes Instead Of A Java 22 Example

This repository is compiled with a current JDK and avoids keeping old preview syntax active across the whole build.

The faithful runnable example is in Java 24:

```text
src/main/java/net/jrodolfo/java_evolution/java24/StreamGatherersExamples.java
```

That class demonstrates final Stream Gatherers with:

- fixed-size windows
- running scans

The Java 22 module explains why the feature appeared and what problem it was designed to solve.

## Realistic Use Case

Imagine reading events from a stream:

```text
login, click, click, purchase, logout
```

You might want to group events into sessions, compute rolling metrics, or emit alerts when several related events appear together.

Those transformations are not just `map` or `filter`. They depend on state, neighboring elements, or rules about when enough input has been seen to produce output.

Gatherers give the stream API a standard place for this kind of logic.

## When Not To Use It

Use the ordinary stream operations when they already express the idea clearly.

For example, this does not need a gatherer:

```java
numbers.stream()
		.filter(number -> number > 0)
		.map(number -> number * 2)
		.toList();
```

Use a gatherer when the operation needs custom intermediate behavior that the built-in stream operations do not express well.

## Remember This

Stream Gatherers let a stream pipeline contain custom intermediate operations. They are useful when output depends on buffering, state, windows, scans, or other logic that does not fit cleanly into `map`, `filter`, or a terminal collector.
