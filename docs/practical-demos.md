# Practical Demos

This guide highlights examples that are especially useful for study, demos, and interviews because they turn a Java platform feature into a small, focused walkthrough.

Use this guide when you want a practical tour through the repository instead of reading every package in release order.

Before a study session, interview walkthrough, or live demo, run the complete practical demo check:

```bash
make demos
```

The table mirrors the focused checks run by `make demos` and also includes focused commands for running one demo at a time.

## How To Use This Guide

For each feature:

1. Read the package README first.
2. Inspect the example class.
3. Run the focused test command.
4. Compare the feature with the version README and the [JEP Index](jep-index.md).

The tests keep each demo honest. They show the expected behavior without requiring a large application.

## Demos

| Java | Feature | Package or class | What it demonstrates | Focused test command | Local requirements |
|---|---|---|---|---|---|
| 18 | Simple Web Server | [`simple_web_server`](../src/main/java/net/jrodolfo/java_evolution/java18/simple_web_server/README.md) | Starts a local static-file server with `SimpleFileServer`, serves a temporary directory, and verifies HTTP responses. | `mvn -Dtest=SimpleStaticFileServerTest test` | Binds a local loopback port. |
| 18 | JavaDoc snippets | [`javadoc_snippets`](../src/main/java/net/jrodolfo/java_evolution/java18/javadoc_snippets/README.md) | Shows JavaDoc `@snippet` markup in real generated documentation. | `mvn -Dtest=JavaDocSnippetExamplesTest test` | Run `make docs` to inspect rendered snippets. |
| 21 | Key Encapsulation Mechanism API | [`key_encapsulation`](../src/main/java/net/jrodolfo/java_evolution/java21/key_encapsulation/README.md) | Establishes shared secret material with encapsulation and decapsulation. | `mvn -Dtest=KeyEncapsulationExchangeTest test` | Requires a JDK/provider with the demonstrated KEM algorithm. |
| 22 | Foreign Function and Memory API | [`foreign_function`](../src/main/java/net/jrodolfo/java_evolution/java22/foreign_function/README.md) | Calls native C library functions from Java using the final FFM API. | `mvn -Dtest=ForeignFunctionExamplesTest test` | Requires native access and standard platform C library symbols. |
| 24 | Stream Gatherers | [`StreamGatherersExamples`](../src/main/java/net/jrodolfo/java_evolution/java24/StreamGatherersExamples.java) | Demonstrates final Stream Gatherers with fixed windows and running scans. | `mvn -Dtest=StreamGatherersExamplesTest test` | No external setup. |
| 24 | Class-File API | [`class_file`](../src/main/java/net/jrodolfo/java_evolution/java24/class_file/README.md) | Parses compiled `.class` bytes with `ClassFile.of().parse(...)` and summarizes metadata. | `mvn -Dtest=ClassFileInspectorTest test` | No external setup. |
| 24 | ML-KEM / ML-DSA | [`quantum_resistant_crypto`](../src/main/java/net/jrodolfo/java_evolution/java24/quantum_resistant_crypto/README.md) | Demonstrates module-lattice key encapsulation and digital signatures. | `mvn -Dtest=ModuleLatticeCryptoExamplesTest test` | Requires a JDK/provider with ML-KEM and ML-DSA support. |
| 25 | Scoped Values | [`scoped_values`](../src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/README.md) | Shows temporary scoped bindings for contextual data and verifies that the value is unbound afterward. | `mvn -Dtest=ScopedValuesExamplesTest test` | JDK 25 or newer. |
| 25 | Flexible Constructor Bodies | [`FlexibleConstructorBodiesExamples`](../src/main/java/net/jrodolfo/java_evolution/java25/FlexibleConstructorBodiesExamples.java) | Demonstrates validation and normalization before constructor delegation. | `mvn -Dtest=FlexibleConstructorBodiesExamplesTest test` | JDK 25 or newer. |
| 25 | Key Derivation Function API | [`key_derivation`](../src/main/java/net/jrodolfo/java_evolution/java25/key_derivation/README.md) | Derives independent keys from shared secret material with HKDF. | `mvn -Dtest=HkdfKeyDerivationExampleTest test` | Requires JDK 25 or newer KDF support. |

## Why These Demos Matter

These demos are useful because they go beyond syntax. They show Java evolving as a platform:

- local tooling for static files and documentation
- cryptography APIs for modern and post-quantum security
- native interoperability without third-party bindings
- class-file inspection without external bytecode libraries
- current Java language/API features that are useful in interviews

That makes them good interview material. They let you explain not only what changed in Java, but also why the change exists and how it can be tested in a small project.
