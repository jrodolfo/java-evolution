# Java 25

Java 25 finalizes several features that were previewed in earlier releases and continues newer platform work around diagnostics, startup, memory layout, garbage collection, cryptography, and vector computation.

Because this is the newest release in the repository, this package is careful about status. Some examples are runnable with JDK 25. Others are notes because they involve preview APIs, incubator modules, JVM options, cryptographic providers, source-launcher behavior, or runtime ergonomics.

## Scoped Values Final

Scoped Values help code share immutable contextual data, such as a current user or request ID, with methods deeper in a call chain without passing that value through every intermediate parameter list.

Java 25 finalized scoped values in JEP 506.

Tutorial and executable example: [`scoped_values`](scoped_values/README.md)

Test: `ScopedValuesExamplesTest`

## Flexible Constructor Bodies Final

Before flexible constructor bodies, explicit constructor invocation had to come first. That made it awkward to validate arguments before passing them to another constructor.

Java 25 finalized flexible constructor bodies. Constructors can now perform safe validation or preparation before `super(...)` or `this(...)`, while still preventing unsafe use of the object before initialization.

Example: `FlexibleConstructorBodiesExamples`

Test: `FlexibleConstructorBodiesExamplesTest`

## Module Import Declarations Final

Module import declarations let source code import public top-level classes and interfaces from packages exported by a named module with one declaration.

Before this feature, code using common Java platform types often needed several individual imports:

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
```

With Java 25, a source file can use a module import declaration:

```java
import module java.base;
```

This makes public top-level types from packages exported by `java.base`, such as `java.util` and `java.util.stream`, available on demand.

This is useful for learning, scripts, and code that naturally uses a broad module surface. The feature was previewed in Java 23 by JEP 476, previewed again in Java 24 by JEP 494, and finalized in Java 25 by JEP 511.

This is a final Java 25 feature documented via notes because module imports are most natural in small source files, scripts, and examples outside this Spring Boot package tree.

Example: `ModuleImportDeclarationsNotes`

Test: `ModuleImportDeclarationsNotesTest`

## Compact Source Files and Instance Main Methods Final

Java traditionally required a class declaration and a static `main` method even for tiny programs.

Java 25 finalized compact source files and instance main methods, reducing ceremony for learning, scripts, and small utilities.

This is a final Java 25 feature documented via notes because compact source files belong naturally to source-launcher examples, not to the Spring Boot package tree.

Example: `CompactSourceFilesNotes`

Test: `CompactSourceFilesNotesTest`

## Key Derivation Function API Final

Applications often need to derive keys from existing secret material and contextual information.

Java 25 finalized the Key Derivation Function API in JEP 510. The executable example in this package uses `HKDF-SHA256` to derive purpose-specific 256-bit key material from input secret material, salt, and context.

Executable example: [`key_derivation`](key_derivation/README.md)

Executable test: `HkdfKeyDerivationExampleTest`

## Primitive Patterns Third Preview

Java 25 continued primitive patterns as a third preview. The long-term goal is a more uniform pattern-matching model where primitive values can participate naturally in `instanceof`, `switch`, and related pattern contexts.

Example: `PrimitivePatternsThirdPreviewNotes`

Test: `PrimitivePatternsThirdPreviewNotesTest`

## Stable Values Preview

Stable values model data initialized at most once. This can help the JVM optimize values that are not known at object construction time but become stable later.

Explanatory module: [`stable_values`](stable_values/README.md)

Test: `StableValuesPreviewNotesTest`

## PEM Encodings Preview

PEM is a common text encoding for cryptographic material. Java 25 previewed APIs for reading and writing cryptographic objects using PEM encodings.

Example: `PemEncodingsPreviewNotes`

Test: `PemEncodingsPreviewNotesTest`

## Structured Concurrency Fifth Preview

Structured concurrency continued as a fifth preview in Java 25. It keeps the focus on treating related concurrent subtasks as one observable unit of work.

Explanatory module: [`structured_concurrency`](structured_concurrency/README.md)

Test: `StructuredConcurrencyFifthPreviewNotesTest`

## Vector API Tenth Incubator

The Vector API continued as a tenth incubator. It is intended for computations that can benefit from CPU vector instructions.

This repository keeps it as notes because the API requires an incubator module and is still evolving.

Example: `VectorApiTenthIncubatorNotes`

Test: `VectorApiTenthIncubatorNotesTest`

## Runtime, Diagnostics, Memory, and GC Notes

Java 25 also includes several features that are better understood as runtime or operational improvements:

- ahead-of-time command-line ergonomics and method profiling
- Java Flight Recorder enhancements
- compact object headers
- Generational Shenandoah

These are important, but they are not ideal for tiny deterministic unit tests, so the repository documents them as notes.

## How To Read This Package

Start with the final executable examples: `scoped_values/README.md`, `FlexibleConstructorBodiesExamples`, and the HKDF example in `key_derivation/README.md`. Then read the notes for module imports, compact source files, primitive patterns, `stable_values/README.md`, PEM encodings, `structured_concurrency/README.md`, the Vector API, AOT, JFR, object headers, and GC behavior.

Run the focused tests:

```bash
mvn -Dtest=ScopedValuesExamplesTest,FlexibleConstructorBodiesExamplesTest test
mvn -Dtest=ModuleImportDeclarationsNotesTest,CompactSourceFilesNotesTest test
mvn -Dtest=HkdfKeyDerivationExampleTest test
mvn -Dtest=PrimitivePatternsThirdPreviewNotesTest,StableValuesPreviewNotesTest,PemEncodingsPreviewNotesTest test
mvn -Dtest=StructuredConcurrencyFifthPreviewNotesTest,VectorApiTenthIncubatorNotesTest test
mvn -Dtest=AotCommandLineErgonomicsNotesTest,JfrEnhancementsNotesTest,CompactObjectHeadersNotesTest,GenerationalShenandoahNotesTest test
```

Java 25 is the final package in this repository. After reading it, use `docs/learning-path.md` to review the full study sequence, `docs/interview-guide.md` to prepare answers, and `docs/jep-index.md` to verify official feature status.

## References

- [OpenJDK JDK 25 project](https://openjdk.org/projects/jdk/25/)
- [JEP 506: Scoped Values](https://openjdk.org/jeps/506)
- [JEP 513: Flexible Constructor Bodies](https://openjdk.org/jeps/513)
- [JEP 511: Module Import Declarations](https://openjdk.org/jeps/511)
- [JEP 512: Compact Source Files and Instance Main Methods](https://openjdk.org/jeps/512)
- [JEP 510: Key Derivation Function API](https://openjdk.org/jeps/510)
- [JEP 507: Primitive Types in Patterns, instanceof, and switch](https://openjdk.org/jeps/507)
- [JEP 502: Stable Values](https://openjdk.org/jeps/502)
- [JEP 470: PEM Encodings of Cryptographic Objects](https://openjdk.org/jeps/470)
- [JEP 505: Structured Concurrency](https://openjdk.org/jeps/505)
- [JEP 508: Vector API](https://openjdk.org/jeps/508)
- [JEP 514: Ahead-of-Time Command-Line Ergonomics](https://openjdk.org/jeps/514)
- [JEP 518: JFR Cooperative Sampling](https://openjdk.org/jeps/518)
- [JEP 519: Compact Object Headers](https://openjdk.org/jeps/519)
- [JEP 521: Generational Shenandoah](https://openjdk.org/jeps/521)
