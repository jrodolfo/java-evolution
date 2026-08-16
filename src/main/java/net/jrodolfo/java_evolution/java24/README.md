# Java 24

Java 24 finalized a few APIs and continued several preview efforts. It is a good release for seeing the difference between language features, library APIs, launcher/runtime behavior, VM ergonomics, and security changes.

Runnable examples are used where the feature is stable and simple. Notes are used where the feature is operational, cryptographic, preview-only, or better demonstrated outside a lightweight Spring Boot project.

## Stream Gatherers Final

Streams are expressive, but custom intermediate operations were historically difficult. Developers often had to break out of the pipeline or force a problem into a collector that only runs at the end.

Java 24 finalized Stream Gatherers, which allow custom intermediate stream operations. This matters for operations that do not fit cleanly into one-element transformations such as `map`, one-element checks such as `filter`, or final aggregation with `collect`.

For example, a fixed window operation needs to look at several input elements before emitting one output element. A scan operation needs to remember accumulated state while the stream is still flowing. Gatherers let those patterns remain inside the stream pipeline. The JDK includes useful gatherers such as fixed-size windows and scans.

Example: `StreamGatherersExamples`

Test: `StreamGatherersExamplesTest`

## Class-File API Final

Bytecode tools need a reliable way to parse, generate, and transform class files. A standard API can evolve with the JDK class-file format.

Java 24 finalized the Class-File API. The executable example in this package parses an already-compiled project class with `ClassFile.of().parse(...)`, extracts class-file metadata, and keeps generation and transformation for later study.

Executable example: [`class_file`](class_file/README.md)

Executable test: `ClassFileInspectorTest`

## Security Manager Disabled

The Security Manager was once used as an in-process sandbox. It tried to restrict code running inside the same JVM with permission checks for actions such as file access, network access, and exiting the VM. Over time, that model became less effective and increasingly difficult to maintain.

Java 24 permanently disabled it. The practical lesson is architectural rather than syntactic: application isolation should come from operating-system permissions, containers, deployment boundaries, and process-level controls instead.

Example: `SecurityManagerDisabledNotes`

Test: `SecurityManagerDisabledNotesTest`

## Virtual Thread Synchronization

Virtual threads are most useful when blocking operations do not unnecessarily pin carrier platform threads. Java 24 improved synchronization behavior so virtual threads blocked in synchronized code can avoid pinning carrier threads in more cases.

This matters for existing code because synchronized blocks are common in older libraries.

Explanatory module: [`virtual_thread_synchronization`](virtual_thread_synchronization/README.md)

Test: `VirtualThreadSynchronizationNotesTest`

## Quantum-Resistant Crypto

Java 24 added support for ML-KEM and ML-DSA, algorithms intended for post-quantum security requirements.

The executable examples in this package use Java Cryptography Architecture APIs to demonstrate both sides:

- ML-KEM establishes matching shared secret material through encapsulation and decapsulation.
- ML-DSA signs a message, verifies the original message, and rejects a tampered message.

Executable example: [`quantum_resistant_crypto`](quantum_resistant_crypto/README.md)

Executable test: `ModuleLatticeCryptoExamplesTest`

## Ahead-of-Time Class Loading

Java startup can be affected by class loading and linking work. Java 24 introduced ahead-of-time class loading and linking to improve startup behavior.

This is an operational/runtime feature, so it is documented as notes.

Explanatory module: [`aot_class_loading`](aot_class_loading/README.md)

Test: `AotClassLoadingNotesTest`

## Key Derivation Function API Preview

Key derivation functions create cryptographic keys from secret material and context data.

Java 24 previewed a standard KDF API. It became final in Java 25, where this repository provides the final runnable example and deeper explanation in [`java25/key_derivation`](../java25/key_derivation/README.md).

Example: `KeyDerivationFunctionPreviewNotes`

Test: `KeyDerivationFunctionPreviewNotesTest`

## Continuing Preview Features

Java 24 also continued several language/API previews:

- flexible constructor bodies third preview, finalized in Java 25 as `FlexibleConstructorBodiesExamples`
- module import declarations second preview, finalized in Java 25 as `ModuleImportDeclarationsNotes`
- primitive patterns second preview, continued in Java 25 as `PrimitivePatternsThirdPreviewNotes`
- scoped values fourth preview, finalized in Java 25 in [`java25/scoped_values`](../java25/scoped_values/README.md)
- structured concurrency fourth preview, documented in [`structured_concurrency`](structured_concurrency/README.md) and continued in Java 25 in [`java25/structured_concurrency`](../java25/structured_concurrency/README.md)

These are represented as notes because the final or later form is covered in Java 25 where appropriate.

## How To Read This Package

Start with `StreamGatherersExamples` because stream gatherers are final in Java 24. For class-file tooling, read `class_file/README.md` before `ClassFileInspector`. Then read `SecurityManagerDisabledNotes`, `virtual_thread_synchronization/README.md`, `aot_class_loading/README.md`, `KeyDerivationFunctionPreviewNotes`, `structured_concurrency/README.md`, and the continuing preview notes. For post-quantum cryptography, read `quantum_resistant_crypto/README.md` before the module-lattice examples.

Run the focused tests:

```bash
mvn -Dtest=StreamGatherersExamplesTest test
mvn -Dtest=ClassFileInspectorTest,SecurityManagerDisabledNotesTest,VirtualThreadSynchronizationNotesTest test
mvn -Dtest=AotClassLoadingNotesTest,KeyDerivationFunctionPreviewNotesTest test
mvn -Dtest=ModuleLatticeCryptoExamplesTest test
mvn -Dtest=PrimitivePatternsSecondPreviewNotesTest,FlexibleConstructorBodiesThirdPreviewNotesTest,ModuleImportDeclarationsSecondPreviewNotesTest test
mvn -Dtest=ScopedValuesFourthPreviewNotesTest,StructuredConcurrencyFourthPreviewNotesTest test
```

This package combines executable final-feature examples with several runtime, security, tooling, and preview notes. After this package, continue with Java 25 to see scoped values, flexible constructor bodies, module imports, compact source files, and the KDF API reach final status.

## References

- [OpenJDK JDK 24 project](https://openjdk.org/projects/jdk/24/)
- [JEP 485: Stream Gatherers](https://openjdk.org/jeps/485)
- [JEP 484: Class-File API](https://openjdk.org/jeps/484)
- [JEP 486: Permanently Disable the Security Manager](https://openjdk.org/jeps/486)
- [JEP 491: Synchronize Virtual Threads without Pinning](https://openjdk.org/jeps/491)
- [JEP 496: Quantum-Resistant Module-Lattice-Based Key Encapsulation Mechanism](https://openjdk.org/jeps/496)
- [JEP 497: Quantum-Resistant Module-Lattice-Based Digital Signature Algorithm](https://openjdk.org/jeps/497)
- [JEP 483: Ahead-of-Time Class Loading & Linking](https://openjdk.org/jeps/483)
- [JEP 478: Key Derivation Function API](https://openjdk.org/jeps/478)
- [JEP 492: Flexible Constructor Bodies](https://openjdk.org/jeps/492)
- [JEP 494: Module Import Declarations](https://openjdk.org/jeps/494)
- [JEP 488: Primitive Types in Patterns, instanceof, and switch](https://openjdk.org/jeps/488)
- [JEP 487: Scoped Values](https://openjdk.org/jeps/487)
- [JEP 499: Structured Concurrency](https://openjdk.org/jeps/499)
