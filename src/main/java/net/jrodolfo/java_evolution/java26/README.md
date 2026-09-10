# Java 26

Released: March 2026 as Java SE 26.

Java 26 adds HTTP/3 support for the standard HTTP Client API, starts warning about deep-reflective final-field mutation, removes the long-deprecated Applet API, continues several preview and incubator features, and advances runtime work around ahead-of-time startup data, G1 garbage collection, and vector computation.

This package contains nine executable examples and one explanatory module. HTTP/3, final-field restrictions, Applet API removal, AOT object caching, PEM encodings, Lazy Constants, primitive patterns, Structured Concurrency, and the Vector API are executable because they can be demonstrated deterministically with isolated API, compiler, or child-JVM workflows. G1 synchronization reduction remains explanatory because its runtime behavior is not represented by a focused portable example.

## HTTP/3 for the HTTP Client API

Java 11 standardized the HTTP Client API. Java 26 extends that client with HTTP/3 support.

HTTP/3 matters because it runs over QUIC rather than TCP. HTTP/1.1 and HTTP/2
normally use TCP, while QUIC integrates secure connection establishment with
its transport model. QUIC streams can also avoid TCP-level head-of-line
blocking: packet loss affecting one stream does not block unrelated streams in
the same way. This may reduce some connection-establishment overhead and
improve behavior under packet loss, but it does not make HTTP/3 always faster
or eliminate every form of head-of-line blocking.

The developer-facing model remains the same: applications make HTTP requests
through the platform client, and HTTP/2 remains a valid choice when it fits the
server and workload.

Example: `Http3ClientExamples`

Test: `Http3ClientExamplesTest`

## Prepare to Make Final Mean Final

Java has long allowed final fields to be mutated through deep reflection. That
undermines the expectation that the field's stored reference or value remains
fixed after initialization and limits JVM optimization opportunities. A
declaration such as `final List<String> names` keeps the field reference fixed;
it does not make the referenced list deeply immutable.

Java 26 starts the migration by warning when deep reflection mutates final fields. The goal is to prepare applications for a future release where this behavior is restricted more strongly by default.

Executable runtime example: [`final_field_restrictions`](final_field_restrictions/README.md)

Test: `FinalFieldRestrictionsExamplesTest`

## Remove the Applet API

Applets belong to the browser-plugin era of Java. They were deprecated for removal long before Java 26.

Java 26 removes the Applet API from the platform. This is a removal and migration topic, so the example demonstrates the compile-time failure of obsolete applet source rather than showing how to write an applet.

Executable removal example: [`applet_api_removal`](applet_api_removal/README.md)

Test: `AppletApiRemovalExamplesTest`

## AOT Object Caching with Any GC

Java 25 improved ahead-of-time command-line ergonomics. Java 26 continues Project Leyden-related startup work by allowing ahead-of-time object caching with any garbage collector.

This is runtime behavior involving startup, JVM-managed cached data, and command-line workflows. The repository demonstrates the cache creation and reuse workflow in isolated child JVMs without measuring startup speed.

Executable runtime example: [`aot_object_caching`](aot_object_caching/README.md)

Test: `AotObjectCachingExamplesTest`

## G1 GC Throughput Improvement

Java 26 includes G1 work intended to improve throughput by reducing
synchronization in garbage-collector internals. Here, synchronization means
coordination among GC worker threads when they access or update shared
collector state; that coordination can make workers wait or perform extra
bookkeeping. Throughput means the amount of execution capacity available for
useful application work rather than collection and coordination overhead.

The intended causal chain is:

```text
less GC-worker coordination
    -> less synchronization overhead
        -> potentially less time and CPU spent on coordination
            -> potentially better overall throughput
```

The result depends on the workload and runtime conditions. A small
deterministic unit test cannot prove a throughput improvement; that requires
representative workloads and measurement.

This is useful operational knowledge, but it is not a source-level API. It belongs in runtime notes.

Explanatory module: [`g1_synchronization`](g1_synchronization/README.md)

Test: `G1SynchronizationNotesTest`

## PEM Encodings Second Preview

Java 25 previewed APIs for reading and writing cryptographic objects using PEM text encodings. Java 26 continues that work as a second preview.

PEM text is common for keys, certificates, certificate requests, and certificate revocation lists. Standard platform support reduces the need for application-specific parsing and encoding code.

Executable preview example: `PemEncodingsSecondPreviewExamples`

Test: `PemEncodingsSecondPreviewExamplesTest`

## Structured Concurrency Sixth Preview

Structured concurrency continues as a sixth preview in Java 26. The model treats related concurrent tasks as one unit of work so cancellation, failure, and observability are easier to reason about.

Executable preview example: [`structured_concurrency`](structured_concurrency/README.md)

Test: `StructuredConcurrencySixthPreviewExamplesTest`

## Lazy Constants Second Preview

Stable Values previewed in Java 25. Java 26 reworks that idea as Lazy Constants in a second preview.

The problem is common: some values should be initialized lazily, but after successful initialization they should behave like constants that the JVM can trust.

Executable preview example: [`lazy_constants`](lazy_constants/README.md)

Test: `LazyConstantsSecondPreviewExamplesTest`

## Vector API Eleventh Incubator

The Vector API continues as an eleventh incubator in Java 26. It lets Java express Single Instruction, Multiple Data (SIMD) computations that the JVM can map to CPU vector instructions when available.

Executable incubator module: [`vector_api`](vector_api/README.md)

Test: `VectorApiEleventhIncubatorNotesTest`

## Primitive Patterns Fourth Preview

Primitive patterns continue as a fourth preview in Java 26. The goal is to make pattern matching more uniform across reference and primitive values while avoiding unsafe or lossy casts.

Executable preview example: `PrimitivePatternsFourthPreviewExamples`

Test: `PrimitivePatternsFourthPreviewExamplesTest`

## How To Read This Package

Start with `Http3ClientExamples`, then read the executable runtime modules for final-field restrictions and AOT object caching, the executable removal module for Applet API removal, and the isolated preview examples for PEM encodings, lazy constants, primitive patterns, structured concurrency, and the Vector API. After that, read the runtime notes for G1 synchronization reduction.

Run the focused tests:

```bash
mvn -Dtest=Http3ClientExamplesTest,PemEncodingsSecondPreviewExamplesTest,PrimitivePatternsFourthPreviewExamplesTest test
mvn -Dtest=FinalFieldRestrictionsExamplesTest,AppletApiRemovalExamplesTest,AotObjectCachingExamplesTest,G1SynchronizationNotesTest test
mvn -Dtest=StructuredConcurrencySixthPreviewExamplesTest,LazyConstantsSecondPreviewExamplesTest,VectorApiEleventhIncubatorNotesTest test
```

Java 26 contains nine executable examples and one explanatory module. HTTP/3, final-field restrictions, Applet API removal, AOT object caching, PEM encodings, Lazy Constants, primitive patterns, Structured Concurrency, and the Vector API are represented by focused executable examples. G1 synchronization reduction remains explanatory because its runtime effect requires workload measurement. The preview APIs and syntax compile and run only in isolated child JVMs using matching JDK 26 preview flags.

## References

- <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/projects/jdk/26/">OpenJDK JDK 26 project</a>
- <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/500">JEP 500: Prepare to Make Final Mean Final</a>
- <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/504">JEP 504: Remove the Applet API</a>
- <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/516">JEP 516: Ahead-of-Time Object Caching with Any GC</a>
- <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/517">JEP 517: HTTP/3 for the HTTP Client API</a>
- <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/522">JEP 522: G1 GC: Improve Throughput by Reducing Synchronization</a>
- <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/524">JEP 524: PEM Encodings of Cryptographic Objects</a>
- <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/525">JEP 525: Structured Concurrency</a>
- <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/526">JEP 526: Lazy Constants</a>
- <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/529">JEP 529: Vector API</a>
- <a target="_blank" rel="noopener noreferrer" href="https://openjdk.org/jeps/530">JEP 530: Primitive Types in Patterns, instanceof, and switch</a>
