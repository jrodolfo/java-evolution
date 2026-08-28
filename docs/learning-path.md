# Learning Path

This path turns the repository into a sequence of study sessions. The goal is not to memorize release notes. The goal is to understand the problem each group of features solved, read the smallest useful examples, and run the tests that prove the behavior.

Use [feature-map.md](feature-map.md) when you want a complete class-by-class index. Use [java-release-timeline.md](java-release-timeline.md) when you want the release chronology behind the stages. Use [practical-demos.md](practical-demos.md) when you want a shorter tour of the most hands-on examples.

## Stage 1: Java 1-4 Early Foundations

Java 1 through 4 established the original object model, checked exceptions, threads, classic I/O, inner classes, reflection, serialization, JDBC, RMI, JavaBeans, the Collections Framework, dynamic proxies, regex, NIO, logging, and chained exceptions.

Read these first:

- `src/main/java/net/jrodolfo/java_evolution/java01/ObjectOrientedBasicsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java01/InterfaceExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java01/ExceptionHandlingBasicsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java01/ThreadBasicsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java01/InnerClassExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java01/jdbc/JdbcExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java02/CollectionsFrameworkExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java02/SortingExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java03/DynamicProxyExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java04/RegexExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java04/NioExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java04/ChainedExceptionExamples.java`

Run:

```bash
mvn "-Dtest=ObjectOrientedBasicsExamplesTest,InterfaceExamplesTest,ExceptionHandlingBasicsExamplesTest" test
mvn "-Dtest=ThreadBasicsExamplesTest,InnerClassExamplesTest,CollectionsFrameworkExamplesTest,SortingExamplesTest" test
mvn "-Dtest=ReflectionExamplesTest,SerializationExamplesTest,JdbcExamplesTest,RmiExamplesTest,JavaBeansExamplesTest" test
mvn "-Dtest=DynamicProxyExamplesTest,RegexExamplesTest,NioExamplesTest,ChainedExceptionExamplesTest" test
```

Interview angle: explain these releases as the foundation of Java's object model, standard collections, runtime reflection, classic I/O, and early platform libraries.

## Stage 2: Java 5-7 Foundations Before Modern Java

Java 5 through 7 created much of the baseline that modern Java developers take for granted. Java 5 added generics, enums, annotations, enhanced loops, varargs, formatted output, and concurrency utilities. Java 6 improved platform tooling and operational support. Java 7 added Project Coin, NIO.2, fork/join, and `invokedynamic`.

Read these first:

- `src/main/java/net/jrodolfo/java_evolution/java05/GenericsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java05/EnumExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java05/AnnotationExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java05/ConcurrencyUtilitiesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java06/NavigableCollectionExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java06/scripting/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java06/compiler_api/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java07/TryWithResourcesStatementExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java07/ExceptionHandlingExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java07/Nio2Examples.java`
- `src/main/java/net/jrodolfo/java_evolution/java07/ForkJoinExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java07/invokedynamic/README.md`

Run:

```bash
mvn "-Dtest=GenericsExamplesTest,EnumExamplesTest,AnnotationExamplesTest,ConcurrencyUtilitiesExamplesTest" test
mvn "-Dtest=NavigableCollectionExamplesTest,ScriptingSupportExamplesTest,CompilerApiExamplesTest" test
mvn "-Dtest=TryWithResourcesStatementExamplesTest,ExceptionHandlingExamplesTest,Nio2ExamplesTest,ForkJoinExamplesTest,InvokeDynamicExamplesTest" test
```

Interview angle: explain these releases as the foundation under later Java. Generics made collections type-safe, Java 5 concurrency utilities raised the abstraction above raw threads, Java 7 made resource and exception handling safer, and Java 6/7 platform work prepared Java for better tooling and JVM language support.

## Stage 3: Java 8 Foundations

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

## Stage 4: Java 9-11 Platform Modernization

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

## Stage 5: Java 12-16 Language Simplification

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

## Stage 6: Java 17-21 Modern Java Style

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
- `src/main/java/net/jrodolfo/java_evolution/java18/inet_address_resolution/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java18/inet_address_resolution/InetAddressResolutionExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java19/VirtualThreadsPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java19/RecordPatternsPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java21/VirtualThreadsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java21/RecordPatternsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java21/PatternMatchingSwitchExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java21/SequencedCollectionsExamples.java`

Then read the Java 21 specialized modules:

- `src/main/java/net/jrodolfo/java_evolution/java21/key_encapsulation/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java21/scoped_values/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java21/structured_concurrency/README.md`

Run:

```bash
mvn "-Dtest=SealedClassesExamplesTest,PatternMatchingSwitchPreviewExamplesTest,RandomGeneratorExamplesTest" test
mvn "-Dtest=Utf8DefaultCharsetExamplesTest,SimpleStaticFileServerTest,JavaDocSnippetExamplesTest,InetAddressResolutionExamplesTest" test
mvn "-Dtest=VirtualThreadsPreviewExamplesTest,RecordPatternsPreviewExamplesTest" test
mvn "-Dtest=VirtualThreadsExamplesTest,RecordPatternsExamplesTest,PatternMatchingSwitchExamplesTest,SequencedCollectionsExamplesTest" test
mvn "-Dtest=KeyEncapsulationExchangeTest,ScopedValuesPreviewNotesTest,StructuredConcurrencyPreviewNotesTest" test
```

Interview angle: treat Java 21 as the modern baseline. Explain virtual threads as a way to keep the simple blocking style while scaling I/O-bound work, and explain sealed classes plus pattern matching as a safer way to model known alternatives.

## Stage 7: Java 22-24 Post-Java-21 Expansion

Java 22 through 24 continue the work that became visible around Java 21: simpler source forms, stronger native interop, stream extension points, class-file work, security changes, and preview refinements.

Read these first by version:

Java 22:

- `src/main/java/net/jrodolfo/java_evolution/java22/UnnamedVariablesPatternsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java22/stream_gatherers/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java22/class_file_api/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java22/statements_before_super/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java22/scoped_values/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java22/structured_concurrency/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java22/foreign_function/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java22/foreign_function/NativeStringParser.java`
- `src/main/java/net/jrodolfo/java_evolution/java22/foreign_function/NativeStringLength.java`

Run:

```bash
mvn "-Dtest=UnnamedVariablesPatternsExamplesTest,StreamGatherersPreviewNotesTest,ForeignFunctionExamplesTest" test
mvn "-Dtest=ClassFileApiPreviewNotesTest,StatementsBeforeSuperPreviewNotesTest,LaunchMultiFileSourceProgramsExamplesTest" test
mvn "-Dtest=ScopedValuesSecondPreviewNotesTest,StructuredConcurrencySecondPreviewNotesTest" test
```

Java 23:

- `src/main/java/net/jrodolfo/java_evolution/java23/markdown_documentation_comments/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java23/primitive_patterns/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java23/module_import_declarations/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java23/flexible_constructor_bodies/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java23/stream_gatherers/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java23/class_file_api/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java23/scoped_values/README.md`

Run:

```bash
mvn "-Dtest=MarkdownDocumentationCommentsExamplesTest,PrimitivePatternsPreviewNotesTest,ModuleImportDeclarationsPreviewNotesTest" test
mvn "-Dtest=FlexibleConstructorBodiesSecondPreviewNotesTest,StreamGatherersSecondPreviewNotesTest,ClassFileApiSecondPreviewNotesTest" test
mvn "-Dtest=ScopedValuesThirdPreviewNotesTest" test
```

Java 24:

- `src/main/java/net/jrodolfo/java_evolution/java24/StreamGatherersExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java24/class_file/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java24/class_file/ClassFileInspector.java`
- `src/main/java/net/jrodolfo/java_evolution/java24/security_manager_disabled/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java24/virtual_thread_synchronization/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java24/aot_class_loading/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java24/key_derivation/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java24/quantum_resistant_crypto/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java24/quantum_resistant_crypto/ModuleLatticeKemExample.java`
- `src/main/java/net/jrodolfo/java_evolution/java24/quantum_resistant_crypto/ModuleLatticeDsaExample.java`
- `src/main/java/net/jrodolfo/java_evolution/java24/flexible_constructor_bodies/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java24/module_import_declarations/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java24/primitive_patterns/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java24/scoped_values/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java24/structured_concurrency/README.md`

Run:

```bash
mvn "-Dtest=StreamGatherersExamplesTest,ClassFileInspectorTest,SecurityManagerDisabledExamplesTest" test
mvn "-Dtest=ModuleLatticeCryptoExamplesTest" test
mvn "-Dtest=VirtualThreadSynchronizationExamplesTest,AotClassLoadingExamplesTest,KeyDerivationFunctionPreviewNotesTest" test
mvn "-Dtest=FlexibleConstructorBodiesThirdPreviewNotesTest,ModuleImportDeclarationsSecondPreviewNotesTest,PrimitivePatternsSecondPreviewNotesTest" test
mvn "-Dtest=ScopedValuesFourthPreviewNotesTest,StructuredConcurrencyFourthPreviewNotesTest" test
```

Interview angle: explain Java 22-24 as a transition from Java 21's modern baseline into newer platform capabilities. Some features become final, such as unnamed variables and patterns, the Foreign Function and Memory API, Stream Gatherers, and the Class-File API. Others remain preview, runtime, security, or notes-only topics.

## Stage 8: Java 25-26 LTS And Current-Release Maturity

Java 25 and Java 26 are useful for showing that you can track feature maturity carefully. Java 25 is a long-term support release with several final features. Java 26 is the repository build baseline. HTTP/3 and Applet API removal are executable because they can be demonstrated deterministically, while the remaining Java 26 feature modules stay notes-only until selected topics are evaluated for faithful executable examples. Java 25 preview child-compilation workflows still require a JDK 25 preview compiler for full execution; on JDK 26 their tests keep the source and documentation checks and skip only that old-preview compiler step.

Read these first by version:

Java 25:

- `src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/ScopedValuesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/FlexibleConstructorBodiesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/ModuleImportDeclarationsExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/CompactSourceFilesExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/key_derivation/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java25/key_derivation/HkdfKeyDerivationExample.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/PrimitivePatternsThirdPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/PemEncodingsPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/stable_values/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java25/stable_values/StableValuesPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/structured_concurrency/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java25/structured_concurrency/StructuredConcurrencyFifthPreviewExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java25/vector_api/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java25/vector_api/VectorApiTenthIncubatorExamples.java`

Run:

```bash
mvn "-Dtest=ScopedValuesExamplesTest,FlexibleConstructorBodiesExamplesTest" test
mvn "-Dtest=ModuleImportDeclarationsExamplesTest,CompactSourceFilesExamplesTest,HkdfKeyDerivationExampleTest" test
mvn "-Dtest=PrimitivePatternsThirdPreviewExamplesTest,PemEncodingsPreviewExamplesTest,StableValuesPreviewExamplesTest" test
mvn "-Dtest=StructuredConcurrencyFifthPreviewExamplesTest,VectorApiTenthIncubatorExamplesTest" test
```

Java 26:

- `src/main/java/net/jrodolfo/java_evolution/java26/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java26/Http3ClientExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java26/final_field_restrictions/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java26/applet_api_removal/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java26/applet_api_removal/AppletApiRemovalExamples.java`
- `src/main/java/net/jrodolfo/java_evolution/java26/aot_object_caching/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java26/g1_synchronization/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java26/lazy_constants/README.md`
- `src/main/java/net/jrodolfo/java_evolution/java26/structured_concurrency/README.md`

Run:

```bash
mvn "-Dtest=Http3ClientExamplesTest,FinalFieldRestrictionsNotesTest,AppletApiRemovalExamplesTest" test
mvn "-Dtest=AotObjectCachingNotesTest,G1SynchronizationNotesTest,LazyConstantsSecondPreviewNotesTest" test
mvn "-Dtest=PemEncodingsSecondPreviewNotesTest,StructuredConcurrencySixthPreviewNotesTest" test
mvn "-Dtest=VectorApiEleventhIncubatorNotesTest,PrimitivePatternsFourthPreviewNotesTest" test
```

Interview angle: be explicit about maturity. A strong answer distinguishes final, preview, incubator, runtime, tooling, security, and removal topics. It should also explain why moving the build baseline to JDK 26 does not automatically mean every Java 26 topic should become an executable example.

## Full Review Checklist

Use this checklist when preparing to show the repository:

1. Run `make check`.
2. Open the JavaDoc site or run `make docs` locally.
3. Open [practical-demos.md](practical-demos.md) and choose one hands-on demo.
4. Pick one Java 8 example and explain the problem it solved.
5. Pick one Java 21 example and explain why it matters for current production Java.
6. Pick one Java 25 or Java 26 example or notes class and explain whether the feature is final, preview, incubator, runtime, tooling-related, security-related, or removal-related.
7. Use [jep-index.md](jep-index.md) when you need the official JEP number or status.
