# Stream Gatherers Second Preview

Java 23 continued Stream Gatherers as a second preview feature.

This module is explanatory because the API was still preview in Java 23. The final runnable example belongs to Java 24.

## What Problem Does This Feature Solve?

Streams are good at many transformations:

- `map` transforms one element into another
- `filter` keeps or removes one element at a time
- `collect` gathers a final result at the end

Some operations need more than that.

Examples include:

- fixed-size windows
- running scans
- batching
- transformations that need state while the stream is flowing

These are custom intermediate stream operations: they happen in the middle of the stream pipeline and produce another stream.

## Relationship To Java 22

Java 22 introduced Stream Gatherers as a first preview.

If you want the fuller first-preview explanation, start here:

```text
src/main/java/net/jrodolfo/java_evolution/java22/stream_gatherers/README.md
```

Java 23 continued the feature as a second preview while the API was still being evaluated.

## What Did Java 23 Continue?

Java 23 continued the same core idea:

```text
give streams a standard extension point for custom intermediate operations
```

The goal was still to let stream pipelines express operations that otherwise required loops, custom collectors, or awkward stream workarounds.

## Important Terminology

**Intermediate operation**

An operation in the middle of a stream pipeline. It receives a stream and produces another stream.

**Gatherer**

A component that describes how input elements are consumed and output elements are emitted into the next stream stage.

**Windowing**

Grouping neighboring stream elements into fixed-size or rule-based groups.

**Scan**

Producing running accumulated results while the stream continues.

**Preview feature**

A feature included in a JDK release so developers can try it and provide feedback before it becomes final. Stream Gatherers were preview in Java 22 and Java 23, then finalized in Java 24.

## Why This Module Has Notes Instead Of A Java 23 Example

This repository avoids keeping old preview API shapes active across the whole build.

The final runnable example is in Java 24:

```text
src/main/java/net/jrodolfo/java_evolution/java24/StreamGatherersExamples.java
```

That class demonstrates final Stream Gatherers with fixed windows and running scans.

The Java 23 module records the second-preview step and points learners to the final code.

## Realistic Use Case

Imagine a stream of measurements:

```text
10, 12, 15, 13, 17, 20
```

You may want running totals or fixed-size windows:

```text
running total:
10, 22, 37, 50, 67, 87

windows of 3:
[10, 12, 15], [13, 17, 20]
```

Those operations need state or buffering. Gatherers provide a standard stream extension point for that kind of logic.

## When Not To Use It

Do not use a gatherer when existing stream operations already express the transformation clearly.

This is still clear without a gatherer:

```java
numbers.stream()
		.filter(number -> number > 0)
		.map(number -> number * 2)
		.toList();
```

Use gatherers for custom intermediate behavior that `map`, `filter`, and terminal collectors do not express well.

## Remember This

Java 23 kept Stream Gatherers in preview. The feature lets stream pipelines express custom intermediate operations, and Java 24 is where this repository shows the final runnable API.
