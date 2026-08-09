# Release Notes: v1.1.0

`java-evolution` v1.1.0 expands the project beyond reference notes by adding several executable examples for topics that originally looked too complex or platform-specific for small demos.

The release also improves the local developer workflow across macOS, Windows 11, and Linux, with clearer Java 25 guardrails and release validation targets.

## Highlights

- Java 21 Key Encapsulation Mechanism (KEM) executable example.
- Java 22 Foreign Function and Memory API executable example.
- Java 24 Class-File API executable example.
- Java 24 ML-KEM and ML-DSA executable examples.
- Java 25 HKDF key derivation executable example.
- Java 18 JavaDoc `@snippet` examples.
- Java 18 Simple Web Server executable example.
- Practical demo documentation with `make demos`.
- Cross-platform Java 25 helper scripts and guardrails.

## Learning Value

Several features that were previously represented as notes now include focused, runnable examples:

- KEM shows how a sender and receiver establish shared key material without transmitting the secret directly.
- Foreign Function and Memory API shows Java calling native C library functions through `Linker`, `Arena`, `FunctionDescriptor`, and `MethodHandle`.
- ML-KEM and ML-DSA show Java's quantum-resistant cryptography APIs in small, testable examples.
- HKDF shows how key derivation turns input key material into purpose-specific derived keys.
- Class-File API shows how Java can inspect class metadata without relying on ad hoc byte parsing.

These examples keep the repository aligned with its main purpose: explaining what changed in Java, why it matters, and how the feature behaves in code.

## Tooling And Platform Improvements

- Added `make docs-check` for documentation navigation, JavaDoc, and Markdown link validation.
- Added `make release-check` as the final local pre-release gate.
- Added `make demos` for focused practical walkthrough tests.
- Added a Java-based guard that verifies both `java` and Maven are using JDK 25 before Java-dependent Make targets run.
- Added Java 25 helper scripts for macOS, Linux, Windows Git Bash, and Windows PowerShell.
- Validated the project on macOS, Windows 11, and Linux.

## Documentation Improvements

- Added glossary documentation and Java Platform Module System (JPMS) search terms.
- Improved README purpose, platform, and tooling guidance.
- Added practical demo documentation.
- Organized release documentation under `docs/release/`.
- Improved documentation after code, documentation, and test review passes.

## Validation

Before publishing this release, run:

```bash
make release-check
```

Expected result:

- documentation navigation audit passes
- JavaDoc generation succeeds
- Markdown link check passes
- Maven tests pass on JDK 25
- practical demo tests pass

## Known Notes

- Some features remain represented by `Notes` classes because they require preview flags, incubator modules, JVM options, runtime setup, operational measurement, or provider-specific behavior that would distract from a small didactic example.
- Linux ARM64 users may need to install `lychee` from the GitHub release binary instead of Snap, because the Snap package may not be available for that architecture.

## Suggested GitHub Release Text

```text
java-evolution v1.1.0 expands the project with executable examples for KEM, Foreign Function and Memory API, Class-File API, ML-KEM, ML-DSA, HKDF, JavaDoc snippets, and the Simple Web Server.

This release also improves the cross-platform workflow with Java 25 helper scripts for macOS, Linux, Windows Git Bash, and Windows PowerShell, plus Java 25 guardrails for Make targets.

Validation:
- make release-check
- validated on macOS, Windows 11, and Linux
```
