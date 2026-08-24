# Compact Object Headers

Java 25 introduced Compact Object Headers as a product feature in JEP 519.

This is an executable runtime-option example. It launches a child JVM with `-XX:+UseCompactObjectHeaders` and verifies that Java 25 reports the option as enabled through `-XX:+PrintFlagsFinal`.

The example deliberately does not try to prove object-layout savings. Object headers are JVM implementation details, and meaningful memory savings require heap measurements and object-layout tooling against a real workload.

## 1. What Problem Does This Feature Solve?

Java programs often create many objects:

- strings
- collections
- records
- domain objects
- framework metadata
- temporary objects used during request processing

When an application creates millions of objects, each object has a cost. That cost is not only the fields that the Java code declares.

Every Java object also needs runtime metadata used by the Java Virtual Machine (JVM).

The mental model is:

```text
Java object
+-------------------+
| object header     |  JVM metadata
+-------------------+
| object payload    |  fields visible to Java code
+-------------------+
| alignment padding |  possible extra bytes for layout
+-------------------+
```

If the header is smaller, each object may use less heap memory. In object-heavy applications, small per-object savings can become significant.

## 2. How Was This Commonly Handled Before?

Before compact object headers, the usual advice for memory-heavy Java applications was to reduce the number or size of objects:

- avoid unnecessary object allocation
- use primitive arrays for dense numeric data
- reuse objects carefully where appropriate
- choose data structures with less overhead
- measure heap usage with profilers

Those techniques still matter, but they require application-level design decisions.

Compact object headers attack the problem from a different angle: reduce JVM metadata overhead for ordinary objects.

## 3. What Did Java Introduce?

Java 24 introduced compact object headers as an experimental feature.

Java 25 changed compact object headers into a product feature. That means the option no longer requires unlocking experimental VM options.

In Java 25, the feature can be enabled with:

```bash
java -XX:+UseCompactObjectHeaders ...
```

It is important to notice what Java 25 did and did not do:

- it made compact object headers a product feature
- it removed the need for `-XX:+UnlockExperimentalVMOptions`
- it did not make compact object headers the default object-header layout

## 4. Terminology In Plain English

Object:

A runtime instance created by Java code, such as `new Customer(...)` or a record instance.

Payload:

The data fields that belong to the object from the Java programmer's point of view.

Object header:

JVM metadata stored with the object. It is not a Java field, but the JVM uses it to manage the object.

Heap:

The memory area where Java objects live.

Memory footprint:

How much memory an application uses.

Product feature:

A supported JVM feature that can be used without unlocking experimental options.

Default:

The behavior used automatically when no special option is provided. In Java 25, compact object headers are a product feature but not the default layout.

## 5. Why Smaller Headers Matter

Imagine an application has 10 million small objects.

If each object saves only a few bytes, the total saving can still be meaningful:

```text
few bytes saved per object
        *
millions of objects
        =
large heap-footprint reduction
```

Lower heap usage can also reduce pressure on the garbage collector. If objects occupy less memory, the application may fit more live data into the same heap.

The exact benefit depends on the application, object graph, JVM configuration, and workload.

## 6. What The Example Shows

Object headers are not visible through normal Java syntax.

`CompactObjectHeadersExamples` shows the part of the feature that is faithful and portable in this repository:

- Java 25 accepts `-XX:+UseCompactObjectHeaders` as a product JVM option
- the option does not require `-XX:+UnlockExperimentalVMOptions`
- `-XX:+PrintFlagsFinal` exposes the selected `UseCompactObjectHeaders` state
- the option can be explicitly enabled or disabled for a child JVM

The example intentionally stops at the runtime-option boundary. Proving that objects are smaller would need tooling such as:

- heap measurements
- object-layout inspection tools
- before/after application runs
- garbage-collection and memory analysis

A normal JUnit test can verify that the JVM accepts and reports the option, but it cannot portably prove that object headers became smaller in a meaningful application.

## 7. What The Test Proves

`CompactObjectHeadersExamplesTest` does not inspect JVM object layout.

Instead, it verifies the executable learning points:

- a child JVM starts successfully with `-XX:+UseCompactObjectHeaders`
- `PrintFlagsFinal` reports `UseCompactObjectHeaders = true` when enabled
- `PrintFlagsFinal` reports `UseCompactObjectHeaders = false` when disabled
- the example explains that object-size and heap-footprint savings require separate measurement

## 8. Realistic Use Case

This feature matters most in object-heavy applications where heap footprint is important.

Examples include:

- services that keep large in-memory object graphs
- applications with many small domain objects
- data-processing jobs that allocate many temporary objects
- frameworks that create metadata objects

The right way to evaluate it is to run the real application with and without compact object headers, then compare heap usage, garbage-collection behavior, startup, and throughput.

## 9. When Not To Use It

Do not use compact object headers as a substitute for measuring memory behavior. It may help, but the benefit depends on the workload.

Do not assume regular Java code can observe object headers directly. They are part of JVM implementation, not the Java object model exposed to source code.

Do not confuse "product feature" with "default feature." In Java 25, the feature is available without experimental unlocking, but it still must be enabled explicitly.

## 10. Remember This

Compact object headers reduce JVM metadata overhead per object. Java 25 made the feature easier to use by turning it into a product feature, but learners should understand it as a runtime memory-layout option, not a Java syntax feature.
