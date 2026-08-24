# Java 25

Released: September 2025 as Java SE 25.

Java 25 finalizes several features, continues some preview and incubator work, and adds newer platform work around diagnostics, startup, memory layout, garbage collection, cryptography, and vector computation.

Because Java 25 is a modern long-term support release, this package is careful about status. Some examples are runnable with JDK 25. Others are notes because they involve preview APIs, incubator modules, JVM options, cryptographic providers, source-launcher behavior, or runtime ergonomics.

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

This is a final Java 25 feature demonstrated with temporary source files, `javac`, and the JDK `java` launcher. Module import syntax stays out of the ordinary Maven source tree because it belongs naturally to small source files, scripts, and learning examples.

Example: `ModuleImportDeclarationsExamples`

Test: `ModuleImportDeclarationsExamplesTest`

## Compact Source Files and Instance Main Methods Final

Java traditionally required a class declaration and a static `main` method even for tiny programs.

Java 25 finalized compact source files and instance main methods, reducing ceremony for learning, scripts, and small utilities.

Before this feature, even a tiny program usually looked like this:

```java
class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Java");
    }
}
```

A compact source file can focus on the program body:

```java
void main() {
    IO.println("Hello, Java 25");
}
```

If that source is saved as `HelloWorld.java`, it can be launched directly:

```bash
java HelloWorld.java
```

This is a final Java 25 feature demonstrated with temporary source files and the JDK `java` launcher. Compact source syntax stays out of the ordinary Maven source tree because it belongs naturally to source-launcher examples.

Example: `CompactSourceFilesExamples`

Test: `CompactSourceFilesExamplesTest`

## Key Derivation Function API Final

Applications often need to derive keys from existing secret material and contextual information.

Java 25 finalized the Key Derivation Function API in JEP 510. The executable example in this package uses `HKDF-SHA256` to derive purpose-specific 256-bit key material from input secret material, salt, and context.

Executable example: [`key_derivation`](key_derivation/README.md)

Executable test: `HkdfKeyDerivationExampleTest`

## Primitive Patterns Third Preview

Java 25 continued primitive patterns as a third preview. The long-term goal is a more uniform pattern-matching model where primitive values can participate naturally in `instanceof`, `switch`, and related pattern contexts.

Before this work, Java pattern matching was most natural for reference types. Primitive values often required separate range checks, casts, or fallback branches.

The preview explores syntax such as:

```java
if (value instanceof byte b) {
    // b is available only when value can be converted to byte without loss
}
```

That matters because primitive casts can silently lose information. A primitive pattern combines the safety check and the binding step.

Because this is a preview feature, real code must be compiled and run with preview features enabled.

Example: `PrimitivePatternsThirdPreviewNotes`

Test: `PrimitivePatternsThirdPreviewNotesTest`

## Stable Values Preview

Stable values model data initialized at most once. This can help the JVM optimize values that are not known at object construction time but become stable later.

Because this is a preview API, the repository keeps the runnable project simple and explains the feature in a dedicated notes module instead of requiring preview-source examples in the main build.

Explanatory module: [`stable_values`](stable_values/README.md)

Test: `StableValuesPreviewNotesTest`

## PEM Encodings Preview

PEM originally stood for Privacy-Enhanced Mail, but today it is widely used as a text transport format for cryptographic objects such as public keys, private keys, certificates, and certificate revocation lists.

A PEM text has a recognizable envelope:

```text
-----BEGIN PUBLIC KEY-----
Base64-encoded binary data
-----END PUBLIC KEY-----
```

Before Java 25, applications often had to combine cryptographic object APIs, binary encodings, Base64 conversion, and careful text parsing themselves. Java 25 previewed APIs for reading and writing cryptographic objects using PEM encodings.

Because this is a preview API, the main Maven build does not import `PEMEncoder` or `PEMDecoder` directly. `PemEncodingsPreviewExamples` writes a small child source file, compiles it with `javac --enable-preview --release 25`, and runs it with `java --enable-preview`.

The child program generates an RSA key pair, encodes the public key as PEM text, decodes it back to a `PublicKey`, and verifies that the encoded key bytes still match. It also demonstrates `PEMRecord`, the generic representation for PEM object types that do not have a dedicated Java security class.

Example: `PemEncodingsPreviewExamples`

Test: `PemEncodingsPreviewExamplesTest`

## Structured Concurrency Fifth Preview

Structured concurrency continued as a fifth preview in Java 25. It keeps the focus on treating related concurrent subtasks as one observable unit of work.

Because this is still a preview API, the repository explains the model and lifecycle without turning the main build into a preview API exercise.

Explanatory module: [`structured_concurrency`](structured_concurrency/README.md)

Test: `StructuredConcurrencyFifthPreviewNotesTest`

## Vector API Tenth Incubator

The Vector API continued as a tenth incubator. It is intended for computations that can benefit from CPU vector instructions.

The core idea is Single Instruction, Multiple Data (SIMD): one operation can be applied across multiple lanes of data. Instead of processing one `int` at a time, vector-style code can describe work over a group of `int` values and let the JVM map that work to CPU vector instructions when possible.

This repository keeps it as notes because the API requires the `jdk.incubator.vector` module and is still evolving.

Explanatory module: [`vector_api`](vector_api/README.md)

Test: `VectorApiTenthIncubatorNotesTest`

## Runtime, Diagnostics, Memory, and GC Modules

Java 25 also includes several features that are better understood as runtime or operational improvements:

- ahead-of-time command-line ergonomics: [`aot_command_line`](aot_command_line/README.md)
- Java Flight Recorder enhancements: [`jfr_enhancements`](jfr_enhancements/README.md)
- compact object headers: [`compact_object_headers`](compact_object_headers/README.md)
- Generational Shenandoah: [`generational_shenandoah`](generational_shenandoah/README.md)

Some of these remain notes because they involve memory layout or garbage-collector policy. The AOT command-line module is executable as a real cache workflow, and the JFR module demonstrates method timing and tracing through a real `.jfr` recording while keeping CPU-time profiling and cooperative sampling as explanatory runtime context.

## How To Read This Package

Start with the final executable examples: `scoped_values/README.md`, `FlexibleConstructorBodiesExamples`, `ModuleImportDeclarationsExamples`, `CompactSourceFilesExamples`, and the HKDF example in `key_derivation/README.md`. Then run the executable workflows for AOT command-line ergonomics, PEM encodings, and JFR method timing/tracing. Read the notes for primitive patterns, `stable_values/README.md`, `structured_concurrency/README.md`, `vector_api/README.md`, object headers, and GC behavior.

Run the focused tests:

```bash
mvn -Dtest=ScopedValuesExamplesTest,FlexibleConstructorBodiesExamplesTest test
mvn -Dtest=ModuleImportDeclarationsExamplesTest,CompactSourceFilesExamplesTest test
mvn -Dtest=HkdfKeyDerivationExampleTest test
mvn -Dtest=PrimitivePatternsThirdPreviewNotesTest,StableValuesPreviewNotesTest,PemEncodingsPreviewExamplesTest test
mvn -Dtest=StructuredConcurrencyFifthPreviewNotesTest,VectorApiTenthIncubatorNotesTest test
mvn -Dtest=AotCommandLineErgonomicsExamplesTest,JfrEnhancementsExamplesTest,CompactObjectHeadersNotesTest,GenerationalShenandoahNotesTest test
```

After Java 25, continue to `java26/README.md` for notes-only current-release material under the JDK 25 build baseline. Use `docs/learning-path.md` to review the full study sequence, `docs/interview-guide.md` to prepare answers, and `docs/jep-index.md` to verify official feature status.

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
- [JEP 509: JFR CPU-Time Profiling](https://openjdk.org/jeps/509)
- [JEP 518: JFR Cooperative Sampling](https://openjdk.org/jeps/518)
- [JEP 520: JFR Method Timing & Tracing](https://openjdk.org/jeps/520)
- [JEP 519: Compact Object Headers](https://openjdk.org/jeps/519)
- [JEP 521: Generational Shenandoah](https://openjdk.org/jeps/521)
