# Learning Path

This path turns the repository into a sequence of study sessions. The goal is not to memorize release notes. The goal is to understand the problem each group of features solved, read the smallest useful examples, and run the tests that prove the behavior.

Use [feature-map.md](feature-map.md) when you want a complete class-by-class index. Use [practical-demos.md](practical-demos.md) when you want a shorter tour of the most hands-on examples.

## Stage 1: Java 8 Foundations

Java 8 changed the way everyday Java code is written. Before Java 8, behavior was often passed around through anonymous classes, collection processing required explicit loops, dates were handled with mutable and confusing APIs, and asynchronous work was harder to compose.

Read these first:

- `src/main/java/net/jrodolfo/java_evolution/java08/LambdaExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java08/StreamExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java08/OptionalExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java08/MethodReferenceExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java08/CompletableFutureExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java08/DefaultMethodExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java08/DateTimeApiExamples.java`

Run:

```bash
mvn "-Dtest=LambdaExamplesTest,StreamExamplesTest,OptionalExamplesTest" test
mvn "-Dtest=CompletableFutureExamplesTest,DateTimeApiExamplesTest" test
```

Interview angle: explain how Java 8 made Java more expressive without abandoning static typing. Be ready to compare external iteration with streams, null checks with `Optional`, and callback-style asynchronous code with `CompletableFuture`.

## Stage 2: Java 9-11 Platform Modernization

Java 9 through 11 cleaned up many rough edges in the platform. These releases improved collection creation, stream and optional APIs, resource handling, process inspection, HTTP calls, files, strings, and local variable declarations.

Read these first:

- `src/main/java/net/jrodolfo/java_evolution/java09/CollectionFactoryExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java09/StreamEnhancementExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java09/OptionalEnhancementExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java09/ProcessApiExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java09/module_system/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java10/LocalVariableTypeInferenceExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java11/StringApiExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java11/FilesApiExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java11/HttpClientExamples.java`

Run:

```bash
mvn "-Dtest=CollectionFactoryExamplesTest,StreamEnhancementExamplesTest,OptionalEnhancementExamplesTest" test
mvn "-Dtest=LocalVariableTypeInferenceExamplesTest,StringApiExamplesTest,FilesApiExamplesTest" test
mvn "-Dtest=HttpClientExamplesTest,PredicateNotExamplesTest,OptionalIsEmptyExamplesTest" test
```

Interview angle: describe these releases as practical modernization. Java became easier to write and easier to package, while remaining compatible with existing code.

## Stage 3: Java 12-16 Language Simplification

Java 12 through 16 made common code shapes shorter and clearer. Switch expressions reduce assignment boilerplate, text blocks make embedded text readable, records remove repetitive data-carrier code, and pattern matching removes manual casts.

Read these first:

- `src/main/java/net/jrodolfo/java_evolution/java12/SwitchExpressionPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java12/TeeingCollectorExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java13/TextBlockPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java14/SwitchExpressionExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java14/RecordPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java15/TextBlockExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java15/SealedClassesPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java16/RecordExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java16/PatternMatchingInstanceofExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java16/StreamToListExamples.java`

Run:

```bash
mvn "-Dtest=SwitchExpressionPreviewExamplesTest,TeeingCollectorExamplesTest,TextBlockPreviewExamplesTest" test
mvn "-Dtest=SwitchExpressionExamplesTest,RecordPreviewExamplesTest,TextBlockExamplesTest" test
mvn "-Dtest=RecordExamplesTest,PatternMatchingInstanceofExamplesTest,StreamToListExamplesTest" test
```

Interview angle: focus on readability. These features are not about making Java clever; they reduce ceremony around common business-code patterns.

## Stage 4: Java 17-21 Modern Java Style

Java 17 and Java 21 are LTS releases, so this is the most important stage after Java 8. These releases make modern Java code more expressive through sealed classes, pattern matching, records, sequenced collections, and virtual threads.

Read these first:

- `src/main/java/net/jrodolfo/java_evolution/java17/SealedClassesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java17/PatternMatchingSwitchPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java17/RandomGeneratorExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java18/Utf8DefaultCharsetExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java18/simple_web_server/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java18/simple_web_server/SimpleStaticFileServer.java`
- `src/main/java/net/jrodolfo/java_evolution/java18/javadoc_snippets/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java18/javadoc_snippets/JavaDocSnippetExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java19/VirtualThreadsPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java19/RecordPatternsPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java21/VirtualThreadsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java21/RecordPatternsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java21/PatternMatchingSwitchExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java21/SequencedCollectionsExamples.java`

Run:

```bash
mvn "-Dtest=SealedClassesExamplesTest,PatternMatchingSwitchPreviewExamplesTest,RandomGeneratorExamplesTest" test
mvn "-Dtest=Utf8DefaultCharsetExamplesTest,SimpleStaticFileServerTest,JavaDocSnippetExamplesTest" test
mvn "-Dtest=VirtualThreadsPreviewExamplesTest,RecordPatternsPreviewExamplesTest" test
mvn "-Dtest=VirtualThreadsExamplesTest,RecordPatternsExamplesTest,PatternMatchingSwitchExamplesTest,SequencedCollectionsExamplesTest" test
```

Interview angle: treat Java 21 as the modern baseline. Explain virtual threads as a way to keep the simple blocking style while scaling I/O-bound work, and explain sealed classes plus pattern matching as a safer way to model known alternatives.

## Stage 5: Java 22-25 Current Release Awareness

Java 22 through 25 are useful for showing that you can read current Java evolution carefully. Some features are final and usable directly. Others are preview, incubator, runtime, tooling, or notes-only topics because they require flags, external setup, or APIs that are not ideal for a small portable example.

Read these first by version:

Java 22:

- `src/main/java/net/jrodolfo/java_evolution/java22/UnnamedVariablesPatternsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java22/stream_gatherers/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java22/class_file_api/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java22/statements_before_super/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java22/scoped_values/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java22/foreign_function/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java22/foreign_function/NativeStringParser.java`
- `src/main/java/net/jrodolfo/java_evolution/java22/foreign_function/NativeStringLength.java`

Run:

```bash
mvn "-Dtest=UnnamedVariablesPatternsExamplesTest,StreamGatherersPreviewNotesTest,ForeignFunctionExamplesTest" test
```

Java 23:

- `src/main/java/net/jrodolfo/java_evolution/java23/MarkdownDocumentationCommentsNotes.java`
- `src/main/java/net/jrodolfo/java_evolution/java23/ModuleImportDeclarationsPreviewNotes.java`

Run:

```bash
mvn "-Dtest=MarkdownDocumentationCommentsNotesTest,ModuleImportDeclarationsPreviewNotesTest" test
```

Java 24:

- `src/main/java/net/jrodolfo/java_evolution/java24/StreamGatherersExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java24/class_file/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java24/class_file/ClassFileInspector.java`
- `src/main/java/net/jrodolfo/java_evolution/java24/SecurityManagerDisabledNotes.java`
- `src/main/java/net/jrodolfo/java_evolution/java24/quantum_resistant_crypto/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java24/quantum_resistant_crypto/ModuleLatticeKemExample.java`
- `src/main/java/net/jrodolfo/java_evolution/java24/quantum_resistant_crypto/ModuleLatticeDsaExample.java`

Run:

```bash
mvn "-Dtest=StreamGatherersExamplesTest,ClassFileInspectorTest,SecurityManagerDisabledNotesTest" test
mvn "-Dtest=ModuleLatticeCryptoExamplesTest" test
```

Java 25:

- `src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/ScopedValuesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/FlexibleConstructorBodiesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/ModuleImportDeclarationsNotes.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/CompactSourceFilesNotes.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/key_derivation/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java25/key_derivation/HkdfKeyDerivationExample.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/stable_values/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java25/structured_concurrency/README.md`

Run:

```bash
mvn "-Dtest=ScopedValuesExamplesTest,FlexibleConstructorBodiesExamplesTest" test
mvn "-Dtest=ModuleImportDeclarationsNotesTest,CompactSourceFilesNotesTest,HkdfKeyDerivationExampleTest" test
mvn "-Dtest=StableValuesPreviewNotesTest,StructuredConcurrencyFifthPreviewNotesTest" test
```

Interview angle: be explicit about maturity. A strong answer distinguishes final features from preview and incubator work, and explains why some topics are documented as notes instead of executable demos.

## Full Review Checklist

Use this checklist when preparing to show the repository:

1. Run `make check`.
2. Open the JavaDoc site or run `make docs` locally.
3. Open [practical-demos.md](practical-demos.md) and choose one hands-on demo.
4. Pick one Java 8 example and explain the problem it solved.
5. Pick one Java 21 example and explain why it matters for current production Java.
6. Pick one Java 25 example or notes class and explain whether the feature is final, preview, incubator, runtime, or tooling-related.
7. Use [docs/jep-index.md](jep-index.md) when you need the official JEP number or status.
