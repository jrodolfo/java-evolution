# Migration Guide

This guide explains what to study when moving from one common Java baseline to a newer one. It is not a full production migration manual. It focuses on the language, library, and platform changes that help a learner or interview candidate explain how Java evolved.

For exact feature status and JEP links, use [status-matrix.md](status-matrix.md) and [jep-index.md](jep-index.md). For class-by-class navigation, use [feature-map.md](feature-map.md).

For recurring acronyms such as JEP, LTS, AOT, JFR, and GC, see [glossary.md](glossary.md).

## Java 8 To Java 11

Java 8 is still common in older systems, but Java 11 changed the practical baseline for many projects. The biggest learning theme is platform modernization: fewer small bits of boilerplate, better standard APIs, and more awareness of modules.

What changed conceptually:

- Java gained a module system in Java 9. Even if an application does not become modular, developers need to understand stronger boundaries in the JDK.
- Small immutable collection creation became simple with `List.of`, `Set.of`, and `Map.of`.
- Streams, `Optional`, try-with-resources, process inspection, and stack walking became more expressive.
- Java 10 added `var` for local variable type inference while keeping Java statically typed.
- Java 11 added a standard HTTP Client and useful String/File APIs.

Compatibility concerns:

- Some Java EE and CORBA modules that existed in older JDKs were removed from the JDK in Java 11, so older applications may need explicit dependencies.
- Reflection against JDK internals became increasingly discouraged after Java 9.
- Build plugins and libraries may need updates before the source code itself changes.

Study these files:

- `src/main/java/net/jrodolfo/java_evolution/java09/CollectionFactoryExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java09/ModuleSystemNotes.java`
- `src/main/java/net/jrodolfo/java_evolution/java10/LocalVariableTypeInferenceExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java11/StringApiExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java11/FilesApiExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java11/HttpClientExamples.java`

Run:

Multi-test Maven commands quote `-Dtest=...` so the examples work in Bash, Git Bash, and PowerShell.

```bash
mvn "-Dtest=CollectionFactoryExamplesTest,ModuleSystemNotesTest" test
mvn "-Dtest=LocalVariableTypeInferenceExamplesTest,StringApiExamplesTest,FilesApiExamplesTest" test
mvn -Dtest=HttpClientExamplesTest test
```

Interview angle: explain Java 11 as the first post-Java-8 long-term modernization baseline. The key point is not that every project must use modules, but that Java became more explicit about platform boundaries and more convenient for everyday API work.

## Java 8 Or 11 To Java 17

Java 17 is an LTS release and a major step toward modern Java style. The learning theme is language simplification: common code shapes became shorter, clearer, and more type-safe.

What changed conceptually:

- Switch expressions reduce assignment boilerplate and accidental fall-through.
- Text blocks make multiline strings readable without manual newline and quote noise.
- Records remove repetitive code for transparent data carriers.
- Pattern matching for `instanceof` combines type check, cast, and local binding.
- Sealed classes let a type declare its permitted implementations.
- Strong encapsulation makes reliance on JDK internals a migration risk.

Compatibility concerns:

- Code that uses internal JDK APIs may break or require replacement because strong encapsulation became more strict.
- Reflection-heavy frameworks usually need modern versions.
- Some preview features from Java 12-16 changed before becoming final, so learn the final Java 17-era shape where possible.

Study these files:

- `src/main/java/net/jrodolfo/java_evolution/java14/SwitchExpressionExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java15/TextBlockExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java16/RecordExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java16/PatternMatchingInstanceofExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java17/SealedClassesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java17/StrongEncapsulationNotes.java`

Run:

```bash
mvn "-Dtest=SwitchExpressionExamplesTest,TextBlockExamplesTest" test
mvn "-Dtest=RecordExamplesTest,PatternMatchingInstanceofExamplesTest,SealedClassesExamplesTest" test
mvn -Dtest=StrongEncapsulationNotesTest test
```

Interview angle: describe Java 17 as a cleaner language for domain modeling. Records model data, sealed classes model closed alternatives, and pattern matching reduces manual casting.

## Java 17 To Java 21

Java 21 is another LTS release and is often the most important modern target after Java 17. The learning theme is production-relevant modern Java: better concurrency and better data-shape handling.

What changed conceptually:

- Virtual threads make thread-per-task code scale better for blocking I/O.
- Record patterns and pattern matching for `switch` make type and data-shape branching clearer.
- Sequenced collections add a common first/last/reversed vocabulary across ordered collections.
- Unnamed patterns and variables preview a way to make intentionally unused values explicit.
- Scoped values and structured concurrency appear as preview features, showing where Java concurrency is heading beyond Java 21.

Compatibility concerns:

- Virtual threads are not a replacement for CPU parallelism; they mainly help blocking I/O workloads.
- Libraries that pin platform threads or rely heavily on synchronized blocking sections should be evaluated before claiming virtual-thread scalability.
- Preview features require care. Do not present Java 21 preview APIs as stable production contracts without checking their later release status.

Study these files:

- `src/main/java/net/jrodolfo/java_evolution/java21/VirtualThreadsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java21/RecordPatternsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java21/PatternMatchingSwitchExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java21/SequencedCollectionsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java21/ScopedValuesPreviewNotes.java`
- `src/main/java/net/jrodolfo/java_evolution/java21/StructuredConcurrencyPreviewNotes.java`

Run:

```bash
mvn "-Dtest=VirtualThreadsExamplesTest,RecordPatternsExamplesTest" test
mvn "-Dtest=PatternMatchingSwitchExamplesTest,SequencedCollectionsExamplesTest" test
mvn "-Dtest=ScopedValuesPreviewNotesTest,StructuredConcurrencyPreviewNotesTest" test
```

Interview angle: explain Java 21 as the point where modern Java concurrency becomes practical for many server applications. Virtual threads let code keep a simple blocking style while supporting many concurrent I/O-bound tasks.

## Java 21 To Java 25

Java 25 is useful for current-release awareness. The learning theme is maturity tracking: some features become final, while others remain preview, incubator, runtime, security, or tooling topics.

What changed conceptually:

- Java 22 finalized unnamed variables and patterns and the Foreign Function and Memory API.
- Java 24 finalized Stream Gatherers and the Class-File API.
- Java 25 finalized Scoped Values, Flexible Constructor Bodies, Module Import Declarations, Compact Source Files and Instance Main Methods, and the Key Derivation Function API.
- Java 25 also continues preview/incubator work such as primitive patterns, stable values, PEM encodings, structured concurrency, and the Vector API.
- Runtime and operational improvements continue around AOT, JFR, object headers, and garbage collection.

Compatibility concerns:

- Final features can be discussed as stable Java features, but this repository may still document them as notes when they do not fit a small portable JUnit example.
- Preview and incubator features should be treated as learning material unless a project has explicitly chosen to adopt them with the required flags/modules.
- Runtime, GC, JFR, and AOT features usually require application-level measurement rather than small unit tests.

Study these files:

- `src/main/java/net/jrodolfo/java_evolution/java22/UnnamedVariablesPatternsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java24/StreamGatherersExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/ScopedValuesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/FlexibleConstructorBodiesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/ModuleImportDeclarationsNotes.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/CompactSourceFilesNotes.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/KeyDerivationFunctionNotes.java`

Run:

```bash
mvn "-Dtest=UnnamedVariablesPatternsExamplesTest,StreamGatherersExamplesTest" test
mvn "-Dtest=ScopedValuesExamplesTest,FlexibleConstructorBodiesExamplesTest" test
mvn "-Dtest=ModuleImportDeclarationsNotesTest,CompactSourceFilesNotesTest,KeyDerivationFunctionNotesTest" test
```

Interview angle: show maturity discipline. A strong answer distinguishes final language/API features from preview, incubator, runtime, tooling, and security topics, and explains why some final features are represented as notes in this repository.

## Practical Migration Checklist

Use this lightweight checklist before discussing or planning a Java version migration:

1. Confirm the target JDK and Maven versions with `make check`.
2. Identify whether the project depends on removed JDK-bundled modules or internal JDK APIs.
3. Upgrade build plugins, frameworks, and test libraries before changing language idioms.
4. Study the target baseline features in this guide.
5. Run the focused tests for the features you want to explain.
6. Use [status-matrix.md](status-matrix.md) to avoid confusing final, preview, incubator, runtime, tooling, and notes-only topics.
