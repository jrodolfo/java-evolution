# ZGC Generational Mode

Java 23 made generational mode the default for ZGC, the Z Garbage Collector.

This module is an executable runtime-boundary example. It launches child JVMs with `-XX:+UseZGC`, reads `-XX:+PrintFlagsFinal`, and captures `-Xlog:gc+init=info` output to show that modern ZGC initializes with young and old generation workers.

It deliberately does not benchmark garbage collection. A unit test can verify runtime configuration and initialization logs, but it cannot prove garbage collector efficiency without a realistic workload, heap settings, and measurement setup.

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

## What The Example Demonstrates

`ZgcGenerationalModeExamples` demonstrates the runtime boundary:

- `java -XX:+UseZGC -version` starts on a ZGC-capable JDK build
- `-XX:+PrintFlagsFinal` reports `UseZGC = true`
- `-Xlog:gc+init=info` reports initialization of the Z Garbage Collector
- the initialization log mentions workers for both old and young generations

The test also captures a historical detail from Java 24 and later:

```text
Ignoring option ZGenerational; support was removed in 24.0
```

That warning explains why the example does not compare `-XX:+ZGenerational` with `-XX:-ZGenerational`. Generational ZGC became the default in Java 23, and the separate switch was removed after that transition.

## What The Example Does Not Prove

Garbage collector behavior depends on workload, heap size, allocation rate, JVM flags, machine, and measurement method.

A tiny unit test would either prove nothing meaningful or become a fragile benchmark. This repository uses child-JVM output so the learner understands the Java 23 runtime change without confusing it with performance measurement.

To evaluate ZGC in production, run a real application or benchmark with GC logging and compare pause times, throughput, CPU usage, and memory behavior.

## Remember This

ZGC is a low-pause garbage collector. Java 23 made its generational mode the default because many objects die young, and treating young and old objects differently can improve garbage collection efficiency for many workloads.
