# ZGC Generational Mode

Java 23 made generational mode the default for ZGC, the Z Garbage Collector.

This feature is documented as an explanatory module because it is a JVM runtime behavior change. A small unit test can verify the learning notes, but it cannot faithfully prove garbage collector efficiency without a workload, JVM flags, and measurement setup.

## What Problem Does This Feature Solve?

Java applications create objects. When objects are no longer reachable, the JVM can reclaim their memory.

That automatic memory management is called garbage collection.

The garbage collector tries to answer questions such as:

- Which objects are still alive?
- Which objects can be removed?
- How can memory be reclaimed with low pause times?
- How can the JVM avoid wasting work?

## What Is ZGC?

ZGC stands for Z Garbage Collector.

It is a garbage collector designed for low pause times, even with large heaps. That means it tries to do most of its work without stopping the application for long pauses.

## The Important Observation: Many Objects Die Young

Many Java objects are short-lived.

For example:

```text
handle request
  -> create temporary strings, lists, DTOs, buffers
  -> finish request
  -> many temporary objects are no longer needed
```

This observation is common enough that many garbage collectors use it as a design principle.

## What Generational Garbage Collection Means

Generational garbage collection separates objects by age.

Conceptually:

```text
young generation
  recently created objects
  many are expected to die soon

old generation
  objects that survived longer
  usually checked less aggressively
```

The collector can then spend effort differently for young and old objects.

## What Java 23 Changed

Java 23 made ZGC use generational mode by default.

Before this change, ZGC could run in non-generational mode. With Java 23, the default behavior uses the generational model because it is expected to improve efficiency for many workloads.

## Why This Can Improve Efficiency

If many objects die young, then frequently collecting young objects can reclaim memory efficiently without treating the entire heap the same way every time.

The practical lesson is:

```text
object lifetime patterns matter
  -> many temporary objects die young
  -> generational collection uses that pattern
  -> ZGC default changed to benefit from it
```

## Why This Module Has No GC Benchmark

Garbage collector behavior depends on workload, heap size, allocation rate, JVM flags, machine, and measurement method.

A tiny unit test would either prove nothing meaningful or become a fragile benchmark. This repository keeps the feature as notes so the learner understands the Java 23 runtime change without confusing it with microbenchmarking.

## Remember This

ZGC is a low-pause garbage collector. Java 23 made its generational mode the default because many objects die young, and treating young and old objects differently can improve garbage collection efficiency for many workloads.
