# Migration Guide

This guide explains what to study when moving from one common Java baseline to a newer one. It is not a full production migration manual. It focuses on the language, library, and platform changes that help a learner or interview candidate explain how Java evolved.

For exact feature status and JEP links, use [status-matrix.md](status-matrix.md) and [jep-index.md](jep-index.md). For release chronology, use [java-release-timeline.md](java-release-timeline.md). For class-by-class navigation, use [feature-map.md](feature-map.md).

For recurring acronyms such as JEP, LTS, AOT, JFR, and GC, see [glossary.md](glossary.md).

## Java 1-4 To Java 5

Java 1 through 4 established Java's original foundation: objects, interfaces, checked exceptions, threads, `java.io`, inner classes, reflection, serialization, collections, dynamic proxies, regex, NIO, logging, and chained exceptions. Java 5 then added major language and library improvements on top of that foundation.

Study these files:

- `src/main/java/net/jrodolfo/java_evolution/java01/ObjectOrientedBasicsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java01/ThreadBasicsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java02/CollectionsFrameworkExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java03/DynamicProxyExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java04/RegexExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java04/NioExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java05/GenericsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java05/ConcurrencyUtilitiesExamples.java`

Run:

```bash
mvn "-Dtest=ObjectOrientedBasicsExamplesTest,ThreadBasicsExamplesTest,CollectionsFrameworkExamplesTest" test
mvn "-Dtest=DynamicProxyExamplesTest,RegexExamplesTest,NioExamplesTest" test
mvn "-Dtest=GenericsExamplesTest,ConcurrencyUtilitiesExamplesTest" test
```

Interview angle: explain Java 5 as a major language modernization built on the Java 1-4 platform: generics made collections safer, annotations gave tools metadata, and concurrency utilities raised the level above raw threads.

## Java 5-7 To Java 8

Java 5 through 7 established many foundations of modern Java: generics, annotations, enums, `java.util.concurrent`, try-with-resources, NIO.2, and fork/join. Moving to Java 8 adds a functional programming layer on top of that baseline.

What changed conceptually:

- Java 5 made collections type-safe with generics and raised concurrency above raw `Thread` coordination.
- Java 6 improved tooling, scripting, console, monitoring, and web-service support.
- Java 7 made resource cleanup and exception handling safer through Project Coin and added NIO.2 filesystem APIs.
- Java 8 then added lambdas, method references, streams, `Optional`, default methods, and the Date/Time API.

Study these files:

- `src/main/java/net/jrodolfo/java_evolution/java05/GenericsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java05/ConcurrencyUtilitiesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java07/TryWithResourcesStatementExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java07/Nio2Examples.java`
- `src/main/java/net/jrodolfo/java_evolution/java08/LambdaExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java08/StreamExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java08/DateTimeApiExamples.java`

Run:

```bash
mvn "-Dtest=GenericsExamplesTest,ConcurrencyUtilitiesExamplesTest,TryWithResourcesStatementExamplesTest,Nio2ExamplesTest" test
mvn "-Dtest=LambdaExamplesTest,StreamExamplesTest,DateTimeApiExamplesTest" test
```

Interview angle: explain Java 8 as a major style shift, but not as the beginning of modern Java. Java 5-7 supplied much of the type-safety, concurrency, resource-management, and filesystem foundation.

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
- `src/main/java/net/jrodolfo/java_evolution/java09/module_system/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java10/LocalVariableTypeInferenceExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java11/StringApiExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java11/FilesApiExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java11/HttpClientExamples.java`

Run:

Multi-test Maven commands quote `-Dtest=...` so the examples work in Bash, Git Bash, and PowerShell.

```bash
mvn "-Dtest=CollectionFactoryExamplesTest,ModuleSystemExamplesTest" test
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
- `src/main/java/net/jrodolfo/java_evolution/java17/strong_encapsulation/README.md`

Run:

```bash
mvn "-Dtest=SwitchExpressionExamplesTest,TextBlockExamplesTest" test
mvn "-Dtest=RecordExamplesTest,PatternMatchingInstanceofExamplesTest,SealedClassesExamplesTest" test
mvn -Dtest=StrongEncapsulationExamplesTest test
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
- `src/main/java/net/jrodolfo/java_evolution/java21/scoped_values/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java21/structured_concurrency/README.md`

Run:

```bash
mvn "-Dtest=VirtualThreadsExamplesTest,RecordPatternsExamplesTest" test
mvn "-Dtest=PatternMatchingSwitchExamplesTest,SequencedCollectionsExamplesTest" test
mvn "-Dtest=ScopedValuesPreviewNotesTest,StructuredConcurrencyPreviewNotesTest" test
```

Interview angle: explain Java 21 as the point where modern Java concurrency becomes practical for many server applications. Virtual threads let code keep a simple blocking style while supporting many concurrent I/O-bound tasks.

## Java 21 To Java 25

Java 25 is the next LTS target after Java 21. The learning theme is maturity tracking: some features become final, while others remain preview, incubator, runtime, security, or tooling topics.

What changed conceptually:

- Java 22 finalized unnamed variables and patterns and the Foreign Function and Memory API.
- Java 24 finalized Stream Gatherers and the Class-File API.
- Java 25 finalized Scoped Values, Flexible Constructor Bodies, Module Import Declarations, Compact Source Files and Instance Main Methods, and the Key Derivation Function API.
- Java 25 also continues preview/incubator work such as primitive patterns, stable values, PEM encodings, structured concurrency, and the Vector API.
- Runtime and operational improvements continue around AOT, JFR, object headers, and garbage collection.
- Java 25 preview child-compilation workflows need a JDK 25 preview compiler for full execution. Under the JDK 26 repository baseline, those tests keep source and documentation checks while skipping the old-preview compiler step.

Compatibility concerns:

- Final features can be discussed as stable Java features, but this repository may still document them as notes when they do not fit a small portable JUnit example.
- Preview and incubator features should be treated as learning material unless a project has explicitly chosen to adopt them with the required flags/modules.
- Runtime, GC, JFR, and AOT features usually require application-level measurement rather than small unit tests.

Study these files:

- `src/main/java/net/jrodolfo/java_evolution/java22/UnnamedVariablesPatternsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java24/StreamGatherersExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/ScopedValuesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/FlexibleConstructorBodiesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/ModuleImportDeclarationsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/CompactSourceFilesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/key_derivation/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java25/key_derivation/HkdfKeyDerivationExample.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/PrimitivePatternsThirdPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/stable_values/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java25/stable_values/StableValuesPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/structured_concurrency/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java25/structured_concurrency/StructuredConcurrencyFifthPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/vector_api/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java25/vector_api/VectorApiTenthIncubatorExamples.java`

Run:

```bash
mvn "-Dtest=UnnamedVariablesPatternsExamplesTest,StreamGatherersExamplesTest" test
mvn "-Dtest=ScopedValuesExamplesTest,FlexibleConstructorBodiesExamplesTest" test
mvn "-Dtest=ModuleImportDeclarationsExamplesTest,CompactSourceFilesExamplesTest,HkdfKeyDerivationExampleTest" test
mvn "-Dtest=PrimitivePatternsThirdPreviewExamplesTest,StableValuesPreviewExamplesTest,StructuredConcurrencyFifthPreviewExamplesTest,VectorApiTenthIncubatorExamplesTest" test
```

Interview angle: explain Java 25 as an LTS baseline where several features become final, while preview, incubator, runtime, tooling, and security topics still require maturity discipline.

## Java 26 Awareness

Java 26 is useful for current-release awareness and is now the repository build baseline. Some Java 26 feature modules remain notes-only until each topic is evaluated for a faithful executable example; HTTP/3, final-field restrictions, Applet API removal, AOT object caching, PEM encodings, Lazy Constants, and primitive patterns already have focused executable examples.

What changed conceptually:

- Java 26 adds HTTP/3 support for the standard HTTP Client API.
- Java 26 removes the Applet API after a long deprecation path, and this repository demonstrates the removal with a child-compiler example.
- Java 26 continues preview/incubator work such as structured concurrency, Lazy Constants, and the Vector API. PEM encodings and primitive patterns are also preview work, but this repository demonstrates their behavior in isolated JDK 26 child-JVM workflows.
- Runtime and operational improvements continue around final-field restrictions, AOT object caching, and G1 garbage collection.

Compatibility concerns:

- Java 26 topics should not be treated as executable examples merely because the repository now builds on JDK 26.
- Final, preview, incubator, runtime, removal, and notes-only labels matter more than the release number alone.
- Converting Java 26 notes into executable examples should be a separate feature-by-feature validation decision. HTTP/3 is executable because its final API can be demonstrated without live network dependencies. Final-field restrictions are executable because the runtime warning can be captured in an isolated child JVM. Applet API removal is executable because the removed package can be verified through a deterministic compiler failure. AOT object caching is executable because cache creation and reuse can be verified in isolated child JVMs without measuring startup performance.

Study these files:

- `src/main/java/net/jrodolfo/java_evolution/java26/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java26/Http3ClientExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java26/final_field_restrictions/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java26/applet_api_removal/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java26/applet_api_removal/AppletApiRemovalExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java26/aot_object_caching/AotObjectCachingExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java26/lazy_constants/README.md`

Run:

```bash
mvn "-Dtest=Http3ClientExamplesTest,FinalFieldRestrictionsExamplesTest,AppletApiRemovalExamplesTest" test
mvn "-Dtest=AotObjectCachingExamplesTest,PemEncodingsSecondPreviewExamplesTest,LazyConstantsSecondPreviewExamplesTest,StructuredConcurrencySixthPreviewNotesTest,PrimitivePatternsFourthPreviewExamplesTest" test
```

Interview angle: show baseline discipline. A strong answer explains why moving the repository baseline to JDK 26 is separate from deciding whether each Java 26 feature should be executable or notes-only.

## Practical Migration Checklist

Use this lightweight checklist before discussing or planning a Java version migration:

1. Confirm the target JDK and Maven versions with `make check`.
2. Identify whether the project depends on removed JDK-bundled modules or internal JDK APIs.
3. Upgrade build plugins, frameworks, and test libraries before changing language idioms.
4. Study the target baseline features in this guide.
5. Run the focused tests for the features you want to explain.
6. Use [status-matrix.md](status-matrix.md) to avoid confusing final, preview, incubator, runtime, tooling, and notes-only topics.
