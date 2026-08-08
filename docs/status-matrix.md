# Status Matrix

This matrix gives one place to answer three practical questions:

- Is the feature final, preview, incubator, runtime, tooling, security, documentation, or notes-only in this repository?
- Which class should I open?
- Which test proves or documents the behavior?

Use this when you need to distinguish final, preview, incubator, notes-only, runtime, tooling, documentation, and security topics. Use [feature-map.md](feature-map.md) for class-by-class navigation and [jep-index.md](jep-index.md) for the release-level JEP list.

Status vocabulary:

- `final`: stable feature in the named release
- `preview`: available for feedback, may change later
- `incubator`: non-final incubating API
- `notes-only`: represented as a notes class in this repository; this does not imply the Java feature itself is non-final
- `runtime`: JVM, GC, diagnostics, startup, or migration behavior
- `tooling`: command-line, launcher, JavaDoc, or development support
- `security`: cryptography or security platform feature
- `documentation`: JavaDoc or documentation syntax feature

| Java | Feature | Status | Example or Notes Class | Test Class | Reference |
|---|---|---|---|---|---|
| 8 | Lambdas | final | `LambdaExamples` | `LambdaExamplesTest` | [JEP 126](https://openjdk.org/jeps/126) |
| 8 | Streams | final | `StreamExamples` | `StreamExamplesTest` | [JEP 107](https://openjdk.org/jeps/107) |
| 8 | Optional | final | `OptionalExamples` | `OptionalExamplesTest` | [java08 README](../src/main/java/net/jrodolfo/java_evolution/java08/README.md) |
| 8 | Method references | final | `MethodReferenceExamples` | `MethodReferenceExamplesTest` | [JEP 126](https://openjdk.org/jeps/126) |
| 8 | CompletableFuture | final | `CompletableFutureExamples` | `CompletableFutureExamplesTest` | [java08 README](../src/main/java/net/jrodolfo/java_evolution/java08/README.md) |
| 8 | Default methods | final | `DefaultMethodExamples` | `DefaultMethodExamplesTest` | [JEP 126](https://openjdk.org/jeps/126) |
| 8 | Date/Time API | final | `DateTimeApiExamples` | `DateTimeApiExamplesTest` | [JEP 150](https://openjdk.org/jeps/150) |
| 9 | Collection factories | final | `CollectionFactoryExamples` | `CollectionFactoryExamplesTest` | [JEP 269](https://openjdk.org/jeps/269) |
| 9 | Optional enhancements | final | `OptionalEnhancementExamples` | `OptionalEnhancementExamplesTest` | [java09 README](../src/main/java/net/jrodolfo/java_evolution/java09/README.md) |
| 9 | Stream enhancements | final | `StreamEnhancementExamples` | `StreamEnhancementExamplesTest` | [java09 README](../src/main/java/net/jrodolfo/java_evolution/java09/README.md) |
| 9 | Private interface methods | final | `PrivateInterfaceMethodExamples` | `PrivateInterfaceMethodExamplesTest` | [JEP 213](https://openjdk.org/jeps/213) |
| 9 | Try-with-resources improvement | final | `TryWithResourcesExamples` | `TryWithResourcesExamplesTest` | [JEP 213](https://openjdk.org/jeps/213) |
| 9 | Process API | final | `ProcessApiExamples` | `ProcessApiExamplesTest` | [JEP 102](https://openjdk.org/jeps/102) |
| 9 | StackWalker | final | `StackWalkerExamples` | `StackWalkerExamplesTest` | [JEP 259](https://openjdk.org/jeps/259) |
| 9 | Module system | final, notes-only | `ModuleSystemNotes` | `ModuleSystemNotesTest` | [JEP 261](https://openjdk.org/jeps/261) |
| 10 | Local variable type inference | final | `LocalVariableTypeInferenceExamples` | `LocalVariableTypeInferenceExamplesTest` | [JEP 286](https://openjdk.org/jeps/286) |
| 10 | Unmodifiable collectors | final | `UnmodifiableCollectorsExamples` | `UnmodifiableCollectorsExamplesTest` | [java10 README](../src/main/java/net/jrodolfo/java_evolution/java10/README.md) |
| 10 | `Optional.orElseThrow()` | final | `OptionalOrElseThrowExamples` | `OptionalOrElseThrowExamplesTest` | [java10 README](../src/main/java/net/jrodolfo/java_evolution/java10/README.md) |
| 11 | String API additions | final | `StringApiExamples` | `StringApiExamplesTest` | [java11 README](../src/main/java/net/jrodolfo/java_evolution/java11/README.md) |
| 11 | Files read/write string | final | `FilesApiExamples` | `FilesApiExamplesTest` | [java11 README](../src/main/java/net/jrodolfo/java_evolution/java11/README.md) |
| 11 | HTTP Client | final | `HttpClientExamples` | `HttpClientExamplesTest` | [JEP 321](https://openjdk.org/jeps/321) |
| 11 | `Predicate.not` | final | `PredicateNotExamples` | `PredicateNotExamplesTest` | [java11 README](../src/main/java/net/jrodolfo/java_evolution/java11/README.md) |
| 11 | Lambda `var` | final | `LambdaVarExamples` | `LambdaVarExamplesTest` | [JEP 323](https://openjdk.org/jeps/323) |
| 11 | `Optional.isEmpty` | final | `OptionalIsEmptyExamples` | `OptionalIsEmptyExamplesTest` | [java11 README](../src/main/java/net/jrodolfo/java_evolution/java11/README.md) |
| 12 | Switch expressions preview | preview | `SwitchExpressionPreviewExamples` | `SwitchExpressionPreviewExamplesTest` | [JEP 325](https://openjdk.org/jeps/325) |
| 12 | Teeing collector | final | `TeeingCollectorExamples` | `TeeingCollectorExamplesTest` | [java12 README](../src/main/java/net/jrodolfo/java_evolution/java12/README.md) |
| 12 | `String.indent` | final | `StringIndentExamples` | `StringIndentExamplesTest` | [java12 README](../src/main/java/net/jrodolfo/java_evolution/java12/README.md) |
| 12 | `Files.mismatch` | final | `FilesMismatchExamples` | `FilesMismatchExamplesTest` | [java12 README](../src/main/java/net/jrodolfo/java_evolution/java12/README.md) |
| 12 | Compact number formatting | final | `CompactNumberFormatExamples` | `CompactNumberFormatExamplesTest` | [java12 README](../src/main/java/net/jrodolfo/java_evolution/java12/README.md) |
| 13 | Text blocks preview | preview | `TextBlockPreviewExamples` | `TextBlockPreviewExamplesTest` | [JEP 355](https://openjdk.org/jeps/355) |
| 13 | Switch `yield` preview | preview | `SwitchYieldPreviewExamples` | `SwitchYieldPreviewExamplesTest` | [JEP 354](https://openjdk.org/jeps/354) |
| 13 | `FileSystems.newFileSystem(Path)` | final | `FileSystemsNewFileSystemExamples` | `FileSystemsNewFileSystemExamplesTest` | [java13 README](../src/main/java/net/jrodolfo/java_evolution/java13/README.md) |
| 14 | Switch expressions final | final | `SwitchExpressionExamples` | `SwitchExpressionExamplesTest` | [JEP 361](https://openjdk.org/jeps/361) |
| 14 | Helpful NullPointerExceptions | runtime | `HelpfulNullPointerExceptionExamples` | `HelpfulNullPointerExceptionExamplesTest` | [JEP 358](https://openjdk.org/jeps/358) |
| 14 | Records preview | preview | `RecordPreviewExamples` | `RecordPreviewExamplesTest` | [JEP 359](https://openjdk.org/jeps/359) |
| 14 | Pattern matching for `instanceof` preview | preview | `PatternMatchingInstanceofPreviewExamples` | `PatternMatchingInstanceofPreviewExamplesTest` | [JEP 305](https://openjdk.org/jeps/305) |
| 15 | Text blocks final | final | `TextBlockExamples` | `TextBlockExamplesTest` | [JEP 378](https://openjdk.org/jeps/378) |
| 15 | Sealed classes preview | preview | `SealedClassesPreviewExamples` | `SealedClassesPreviewExamplesTest` | [JEP 360](https://openjdk.org/jeps/360) |
| 15 | Hidden classes | final, notes-only | `HiddenClassesNotes` | `HiddenClassesNotesTest` | [JEP 371](https://openjdk.org/jeps/371) |
| 16 | Records final | final | `RecordExamples` | `RecordExamplesTest` | [JEP 395](https://openjdk.org/jeps/395) |
| 16 | Pattern matching for `instanceof` final | final | `PatternMatchingInstanceofExamples` | `PatternMatchingInstanceofExamplesTest` | [JEP 394](https://openjdk.org/jeps/394) |
| 16 | `Stream.toList()` | final | `StreamToListExamples` | `StreamToListExamplesTest` | [java16 README](../src/main/java/net/jrodolfo/java_evolution/java16/README.md) |
| 16 | Unix-domain socket channels | final, notes-only | `UnixDomainSocketChannelNotes` | `UnixDomainSocketChannelNotesTest` | [JEP 380](https://openjdk.org/jeps/380) |
| 17 | Sealed classes final | final | `SealedClassesExamples` | `SealedClassesExamplesTest` | [JEP 409](https://openjdk.org/jeps/409) |
| 17 | Pattern matching for switch preview | preview | `PatternMatchingSwitchPreviewExamples` | `PatternMatchingSwitchPreviewExamplesTest` | [JEP 406](https://openjdk.org/jeps/406) |
| 17 | Random Generator API | final | `RandomGeneratorExamples` | `RandomGeneratorExamplesTest` | [JEP 356](https://openjdk.org/jeps/356) |
| 17 | `HexFormat` | final | `HexFormatExamples` | `HexFormatExamplesTest` | [java17 README](../src/main/java/net/jrodolfo/java_evolution/java17/README.md) |
| 17 | Strong encapsulation | runtime, notes-only | `StrongEncapsulationNotes` | `StrongEncapsulationNotesTest` | [JEP 403](https://openjdk.org/jeps/403) |
| 18 | UTF-8 default charset | final | `Utf8DefaultCharsetExamples` | `Utf8DefaultCharsetExamplesTest` | [JEP 400](https://openjdk.org/jeps/400) |
| 18 | Simple Web Server | tooling, notes-only | `SimpleWebServerNotes` | `SimpleWebServerNotesTest` | [JEP 408](https://openjdk.org/jeps/408) |
| 18 | JavaDoc snippets | tooling, documentation, notes-only | `CodeSnippetJavaDocNotes` | `CodeSnippetJavaDocNotesTest` | [JEP 413](https://openjdk.org/jeps/413) |
| 18 | InetAddress resolver SPI | final, notes-only | `InetAddressResolutionNotes` | `InetAddressResolutionNotesTest` | [JEP 418](https://openjdk.org/jeps/418) |
| 19 | Virtual threads preview | preview | `VirtualThreadsPreviewExamples` | `VirtualThreadsPreviewExamplesTest` | [JEP 425](https://openjdk.org/jeps/425) |
| 19 | Record patterns preview | preview | `RecordPatternsPreviewExamples` | `RecordPatternsPreviewExamplesTest` | [JEP 405](https://openjdk.org/jeps/405) |
| 19 | Pattern matching for switch preview | preview | `PatternMatchingSwitchPreviewExamples` | `PatternMatchingSwitchPreviewExamplesTest` | [JEP 427](https://openjdk.org/jeps/427) |
| 19 | Structured concurrency incubator | incubator, notes-only | `StructuredConcurrencyPreviewNotes` | `StructuredConcurrencyPreviewNotesTest` | [JEP 428](https://openjdk.org/jeps/428) |
| 19 | Foreign Function and Memory API | preview, notes-only | `ForeignFunctionMemoryApiPreviewNotes` | `ForeignFunctionMemoryApiPreviewNotesTest` | [JEP 424](https://openjdk.org/jeps/424) |
| 20 | Record patterns second preview | preview | `RecordPatternsSecondPreviewExamples` | `RecordPatternsSecondPreviewExamplesTest` | [JEP 432](https://openjdk.org/jeps/432) |
| 20 | Pattern matching for switch fourth preview | preview | `PatternMatchingSwitchFourthPreviewExamples` | `PatternMatchingSwitchFourthPreviewExamplesTest` | [JEP 433](https://openjdk.org/jeps/433) |
| 20 | Virtual threads second preview | preview, notes-only | `VirtualThreadsSecondPreviewNotes` | `VirtualThreadsSecondPreviewNotesTest` | [JEP 436](https://openjdk.org/jeps/436) |
| 20 | Scoped values incubator | incubator, notes-only | `ScopedValuesIncubatorNotes` | `ScopedValuesIncubatorNotesTest` | [JEP 429](https://openjdk.org/jeps/429) |
| 20 | Structured concurrency second incubator | incubator, notes-only | `StructuredConcurrencySecondIncubatorNotes` | `StructuredConcurrencySecondIncubatorNotesTest` | [JEP 437](https://openjdk.org/jeps/437) |
| 20 | Foreign Function and Memory API second preview | preview, notes-only | `ForeignFunctionMemorySecondPreviewNotes` | `ForeignFunctionMemorySecondPreviewNotesTest` | [JEP 434](https://openjdk.org/jeps/434) |
| 20 | Vector API fifth incubator | incubator, notes-only | `VectorApiFifthIncubatorNotes` | `VectorApiFifthIncubatorNotesTest` | [JEP 438](https://openjdk.org/jeps/438) |
| 21 | Virtual threads final | final | `VirtualThreadsExamples` | `VirtualThreadsExamplesTest` | [JEP 444](https://openjdk.org/jeps/444) |
| 21 | Record patterns final | final | `RecordPatternsExamples` | `RecordPatternsExamplesTest` | [JEP 440](https://openjdk.org/jeps/440) |
| 21 | Pattern matching for switch final | final | [`PatternMatchingSwitchExamples`](../src/main/java/net/jrodolfo/java_evolution/java21/PatternMatchingSwitchExamples.java) | [`PatternMatchingSwitchExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java21/PatternMatchingSwitchExamplesTest.java) | [JEP 441](https://openjdk.org/jeps/441) |
| 21 | Sequenced collections | final | `SequencedCollectionsExamples` | `SequencedCollectionsExamplesTest` | [JEP 431](https://openjdk.org/jeps/431) |
| 21 | Unnamed patterns and variables preview | preview | `UnnamedPatternsVariablesPreviewExamples` | `UnnamedPatternsVariablesPreviewExamplesTest` | [JEP 443](https://openjdk.org/jeps/443) |
| 21 | Key Encapsulation Mechanism API | final, security, executable example | [`KeyEncapsulationExchange`](../src/main/java/net/jrodolfo/java_evolution/java21/key_encapsulation/KeyEncapsulationExchange.java), `KeyEncapsulationMechanismNotes` | [`KeyEncapsulationExchangeTest`](../src/test/java/net/jrodolfo/java_evolution/java21/key_encapsulation/KeyEncapsulationExchangeTest.java), `KeyEncapsulationMechanismNotesTest` | [JEP 452](https://openjdk.org/jeps/452) |
| 21 | Scoped values preview | preview, notes-only | `ScopedValuesPreviewNotes` | `ScopedValuesPreviewNotesTest` | [JEP 446](https://openjdk.org/jeps/446) |
| 21 | Structured concurrency preview | preview, notes-only | `StructuredConcurrencyPreviewNotes` | `StructuredConcurrencyPreviewNotesTest` | [JEP 453](https://openjdk.org/jeps/453) |
| 22 | Unnamed variables and patterns final | final | `UnnamedVariablesPatternsExamples` | `UnnamedVariablesPatternsExamplesTest` | [JEP 456](https://openjdk.org/jeps/456) |
| 22 | Statements before `super(...)` preview | preview, notes-only | `StatementsBeforeSuperPreviewNotes` | `StatementsBeforeSuperPreviewNotesTest` | [JEP 447](https://openjdk.org/jeps/447) |
| 22 | Stream Gatherers preview | preview, notes-only | `StreamGatherersPreviewNotes` | `StreamGatherersPreviewNotesTest` | [JEP 461](https://openjdk.org/jeps/461) |
| 22 | Foreign Function and Memory API final | final, executable native example | [`NativeStringParser`](../src/main/java/net/jrodolfo/java_evolution/java22/foreign_function/NativeStringParser.java), [`NativeStringLength`](../src/main/java/net/jrodolfo/java_evolution/java22/foreign_function/NativeStringLength.java), `ForeignFunctionMemoryApiNotes` | [`ForeignFunctionExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java22/foreign_function/ForeignFunctionExamplesTest.java), `ForeignFunctionMemoryApiNotesTest` | [JEP 454](https://openjdk.org/jeps/454) |
| 22 | Multi-file source launcher | tooling, notes-only | `LaunchMultiFileSourceProgramsNotes` | `LaunchMultiFileSourceProgramsNotesTest` | [JEP 458](https://openjdk.org/jeps/458) |
| 22 | Class-File API preview | preview, notes-only | `ClassFileApiPreviewNotes` | `ClassFileApiPreviewNotesTest` | [JEP 457](https://openjdk.org/jeps/457) |
| 22 | Scoped values second preview | preview, notes-only | `ScopedValuesSecondPreviewNotes` | `ScopedValuesSecondPreviewNotesTest` | [JEP 464](https://openjdk.org/jeps/464) |
| 22 | Structured concurrency second preview | preview, notes-only | `StructuredConcurrencySecondPreviewNotes` | `StructuredConcurrencySecondPreviewNotesTest` | [JEP 462](https://openjdk.org/jeps/462) |
| 23 | Markdown documentation comments | tooling, documentation, notes-only | `MarkdownDocumentationCommentsNotes` | `MarkdownDocumentationCommentsNotesTest` | [JEP 467](https://openjdk.org/jeps/467) |
| 23 | Primitive patterns preview | preview, notes-only | `PrimitivePatternsPreviewNotes` | `PrimitivePatternsPreviewNotesTest` | [JEP 455](https://openjdk.org/jeps/455) |
| 23 | Module import declarations preview | preview, notes-only | `ModuleImportDeclarationsPreviewNotes` | `ModuleImportDeclarationsPreviewNotesTest` | [JEP 476](https://openjdk.org/jeps/476) |
| 23 | Flexible constructor bodies second preview | preview, notes-only | `FlexibleConstructorBodiesSecondPreviewNotes` | `FlexibleConstructorBodiesSecondPreviewNotesTest` | [JEP 482](https://openjdk.org/jeps/482) |
| 23 | Stream Gatherers second preview | preview, notes-only | `StreamGatherersSecondPreviewNotes` | `StreamGatherersSecondPreviewNotesTest` | [JEP 473](https://openjdk.org/jeps/473) |
| 23 | Class-File API second preview | preview, notes-only | `ClassFileApiSecondPreviewNotes` | `ClassFileApiSecondPreviewNotesTest` | [JEP 466](https://openjdk.org/jeps/466) |
| 23 | Scoped values third preview | preview, notes-only | `ScopedValuesThirdPreviewNotes` | `ScopedValuesThirdPreviewNotesTest` | [JEP 481](https://openjdk.org/jeps/481) |
| 23 | Structured concurrency third preview | preview, notes-only | `StructuredConcurrencyThirdPreviewNotes` | `StructuredConcurrencyThirdPreviewNotesTest` | [JEP 480](https://openjdk.org/jeps/480) |
| 23 | Unsafe memory-access deprecation | runtime, notes-only | `UnsafeMemoryAccessDeprecationNotes` | `UnsafeMemoryAccessDeprecationNotesTest` | [JEP 471](https://openjdk.org/jeps/471) |
| 23 | ZGC generational mode | runtime, notes-only | `ZgcGenerationalModeNotes` | `ZgcGenerationalModeNotesTest` | [JEP 474](https://openjdk.org/jeps/474) |
| 24 | Stream Gatherers final | final | `StreamGatherersExamples` | `StreamGatherersExamplesTest` | [JEP 485](https://openjdk.org/jeps/485) |
| 24 | Class-File API final | final, notes-only | `ClassFileApiNotes` | `ClassFileApiNotesTest` | [JEP 484](https://openjdk.org/jeps/484) |
| 24 | Security Manager disabled | runtime, notes-only | `SecurityManagerDisabledNotes` | `SecurityManagerDisabledNotesTest` | [JEP 486](https://openjdk.org/jeps/486) |
| 24 | Virtual-thread synchronization | runtime, notes-only | `VirtualThreadSynchronizationNotes` | `VirtualThreadSynchronizationNotesTest` | [JEP 491](https://openjdk.org/jeps/491) |
| 24 | Quantum-resistant crypto | final, security, notes-only | `QuantumResistantCryptoNotes` | `QuantumResistantCryptoNotesTest` | [JEP 496](https://openjdk.org/jeps/496), [JEP 497](https://openjdk.org/jeps/497) |
| 24 | AOT class loading | runtime, notes-only | `AotClassLoadingNotes` | `AotClassLoadingNotesTest` | [JEP 483](https://openjdk.org/jeps/483) |
| 24 | KDF preview | security, preview, notes-only | `KeyDerivationFunctionPreviewNotes` | `KeyDerivationFunctionPreviewNotesTest` | [JEP 478](https://openjdk.org/jeps/478) |
| 24 | Flexible constructor bodies third preview | preview, notes-only | `FlexibleConstructorBodiesThirdPreviewNotes` | `FlexibleConstructorBodiesThirdPreviewNotesTest` | [JEP 492](https://openjdk.org/jeps/492) |
| 24 | Module import declarations second preview | preview, notes-only | `ModuleImportDeclarationsSecondPreviewNotes` | `ModuleImportDeclarationsSecondPreviewNotesTest` | [JEP 494](https://openjdk.org/jeps/494) |
| 24 | Primitive patterns second preview | preview, notes-only | `PrimitivePatternsSecondPreviewNotes` | `PrimitivePatternsSecondPreviewNotesTest` | [JEP 488](https://openjdk.org/jeps/488) |
| 24 | Scoped values fourth preview | preview, notes-only | `ScopedValuesFourthPreviewNotes` | `ScopedValuesFourthPreviewNotesTest` | [JEP 487](https://openjdk.org/jeps/487) |
| 24 | Structured concurrency fourth preview | preview, notes-only | `StructuredConcurrencyFourthPreviewNotes` | `StructuredConcurrencyFourthPreviewNotesTest` | [JEP 499](https://openjdk.org/jeps/499) |
| 25 | Scoped values final | final | `ScopedValuesExamples` | `ScopedValuesExamplesTest` | [JEP 506](https://openjdk.org/jeps/506) |
| 25 | Flexible constructor bodies final | final | `FlexibleConstructorBodiesExamples` | `FlexibleConstructorBodiesExamplesTest` | [JEP 513](https://openjdk.org/jeps/513) |
| 25 | Module import declarations final | final, notes-only | `ModuleImportDeclarationsNotes` | `ModuleImportDeclarationsNotesTest` | [JEP 511](https://openjdk.org/jeps/511) |
| 25 | Compact source files final | final, notes-only | `CompactSourceFilesNotes` | `CompactSourceFilesNotesTest` | [JEP 512](https://openjdk.org/jeps/512) |
| 25 | Key Derivation Function API final | final, security, notes-only | `KeyDerivationFunctionNotes` | `KeyDerivationFunctionNotesTest` | [JEP 510](https://openjdk.org/jeps/510) |
| 25 | Primitive patterns third preview | preview, notes-only | `PrimitivePatternsThirdPreviewNotes` | `PrimitivePatternsThirdPreviewNotesTest` | [JEP 507](https://openjdk.org/jeps/507) |
| 25 | Stable values preview | preview, notes-only | `StableValuesPreviewNotes` | `StableValuesPreviewNotesTest` | [JEP 502](https://openjdk.org/jeps/502) |
| 25 | PEM encodings preview | security, preview, notes-only | `PemEncodingsPreviewNotes` | `PemEncodingsPreviewNotesTest` | [JEP 470](https://openjdk.org/jeps/470) |
| 25 | Structured concurrency fifth preview | preview, notes-only | `StructuredConcurrencyFifthPreviewNotes` | `StructuredConcurrencyFifthPreviewNotesTest` | [JEP 505](https://openjdk.org/jeps/505) |
| 25 | Vector API tenth incubator | incubator, notes-only | `VectorApiTenthIncubatorNotes` | `VectorApiTenthIncubatorNotesTest` | [JEP 508](https://openjdk.org/jeps/508) |
| 25 | AOT command-line ergonomics | runtime, notes-only | `AotCommandLineErgonomicsNotes` | `AotCommandLineErgonomicsNotesTest` | [JEP 514](https://openjdk.org/jeps/514) |
| 25 | JFR enhancements | runtime, notes-only | `JfrEnhancementsNotes` | `JfrEnhancementsNotesTest` | [JEP 518](https://openjdk.org/jeps/518) |
| 25 | Compact object headers | runtime, notes-only | `CompactObjectHeadersNotes` | `CompactObjectHeadersNotesTest` | [JEP 519](https://openjdk.org/jeps/519) |
| 25 | Generational Shenandoah | runtime, notes-only | `GenerationalShenandoahNotes` | `GenerationalShenandoahNotesTest` | [JEP 521](https://openjdk.org/jeps/521) |
