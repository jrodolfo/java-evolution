# Java 26

Released: March 2026 as Java SE 26.

Java 26 adds HTTP/3 support for the standard HTTP Client API, starts warning about deep-reflective final-field mutation, removes the long-deprecated Applet API, continues several preview and incubator features, and advances runtime work around ahead-of-time startup data, G1 garbage collection, and vector computation.

This package contains eight C1 executable examples and two C2 explanatory modules. HTTP/3, final-field restrictions, Applet API removal, AOT object caching, PEM encodings, Lazy Constants, primitive patterns, and Structured Concurrency are executable because they can be demonstrated deterministically with isolated API, compiler, or child-JVM workflows. G1 synchronization reduction and the Vector API remain explanatory modules because their runtime and incubator behavior is not represented by a focused portable example.

## HTTP/3 for the HTTP Client API

Java 11 standardized the HTTP Client API. Java 26 extends that client with HTTP/3 support.

HTTP/3 matters because it runs over QUIC rather than TCP. That changes connection setup and transport behavior while preserving the developer-facing idea of making HTTP requests through the platform client.

Example: `Http3ClientExamples`

Test: `Http3ClientExamplesTest`

## Prepare to Make Final Mean Final

Java has long allowed final fields to be mutated through deep reflection. That weakens the meaning of `final`, can surprise developers, and limits JVM optimization opportunities.

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

Java 26 includes G1 work intended to improve throughput by reducing synchronization in garbage-collector internals.

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

Explanatory module: [`vector_api`](vector_api/README.md)

Test: `VectorApiEleventhIncubatorNotesTest`

## Primitive Patterns Fourth Preview

Primitive patterns continue as a fourth preview in Java 26. The goal is to make pattern matching more uniform across reference and primitive values while avoiding unsafe or lossy casts.

Executable preview example: `PrimitivePatternsFourthPreviewExamples`

Test: `PrimitivePatternsFourthPreviewExamplesTest`

## How To Read This Package

Start with `Http3ClientExamples`, then read the executable runtime modules for final-field restrictions and AOT object caching, the executable removal module for Applet API removal, and the isolated preview examples for PEM encodings, lazy constants, primitive patterns, and structured concurrency. After that, read the runtime notes for G1, followed by the Vector API incubator notes.

Run the focused tests:

```bash
mvn -Dtest=Http3ClientExamplesTest,PemEncodingsSecondPreviewExamplesTest,PrimitivePatternsFourthPreviewExamplesTest test
mvn -Dtest=FinalFieldRestrictionsExamplesTest,AppletApiRemovalExamplesTest,AotObjectCachingExamplesTest,G1SynchronizationNotesTest test
mvn -Dtest=StructuredConcurrencySixthPreviewExamplesTest,LazyConstantsSecondPreviewExamplesTest,VectorApiEleventhIncubatorNotesTest test
```

Java 26 contains eight C1 executable examples and two C2 explanatory modules. HTTP/3, final-field restrictions, Applet API removal, AOT object caching, PEM encodings, Lazy Constants, primitive patterns, and Structured Concurrency are represented as C1 executable examples after focused feasibility review. G1 synchronization reduction and the Vector API remain C2 explanatory modules. The preview APIs and syntax compile and run only in isolated child JVMs using matching JDK 26 preview flags.

## References

- [OpenJDK JDK 26 project](https://openjdk.org/projects/jdk/26/)
- [JEP 500: Prepare to Make Final Mean Final](https://openjdk.org/jeps/500)
- [JEP 504: Remove the Applet API](https://openjdk.org/jeps/504)
- [JEP 516: Ahead-of-Time Object Caching with Any GC](https://openjdk.org/jeps/516)
- [JEP 517: HTTP/3 for the HTTP Client API](https://openjdk.org/jeps/517)
- [JEP 522: G1 GC: Improve Throughput by Reducing Synchronization](https://openjdk.org/jeps/522)
- [JEP 524: PEM Encodings of Cryptographic Objects](https://openjdk.org/jeps/524)
- [JEP 525: Structured Concurrency](https://openjdk.org/jeps/525)
- [JEP 526: Lazy Constants](https://openjdk.org/jeps/526)
- [JEP 529: Vector API](https://openjdk.org/jeps/529)
- [JEP 530: Primitive Types in Patterns, instanceof, and switch](https://openjdk.org/jeps/530)
