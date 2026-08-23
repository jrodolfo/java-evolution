# Feature Map

This document maps each Java release to the example and test classes that demonstrate or document its features.

Use this when you already know the Java version or feature name and want to jump directly to the code. For learning context, open the linked version README first. For release chronology, use [java-release-timeline.md](java-release-timeline.md). For feature maturity and JEP links, use [status-matrix.md](status-matrix.md). For a shorter list of hands-on demonstrations, use [practical-demos.md](practical-demos.md).

## Java 1

Learning notes: [java01 README](../src/main/java/net/jrodolfo/java_evolution/java01/README.md)

| Feature | Example | Test |
|---|---|---|
| Object-oriented basics | `ObjectOrientedBasicsExamples` | `ObjectOrientedBasicsExamplesTest` |
| Interfaces | `InterfaceExamples` | `InterfaceExamplesTest` |
| Checked exceptions | `ExceptionHandlingBasicsExamples` | `ExceptionHandlingBasicsExamplesTest` |
| Threads and Runnable | `ThreadBasicsExamples` | `ThreadBasicsExamplesTest` |
| `java.io` streams | `IoBasicsExamples` | `IoBasicsExamplesTest` |
| Inner classes | `InnerClassExamples` | `InnerClassExamplesTest` |
| Reflection | [`reflection/ReflectionExamples`](../src/main/java/net/jrodolfo/java_evolution/java01/reflection/ReflectionExamples.java) | [`ReflectionExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java01/reflection/ReflectionExamplesTest.java) |
| Serialization | [`serialization/SerializationExamples`](../src/main/java/net/jrodolfo/java_evolution/java01/serialization/SerializationExamples.java) | [`SerializationExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java01/serialization/SerializationExamplesTest.java) |
| JDBC | [`jdbc/JdbcNotes`](../src/main/java/net/jrodolfo/java_evolution/java01/jdbc/JdbcNotes.java) | [`JdbcNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java01/jdbc/JdbcNotesTest.java) |
| RMI | [`rmi/RmiNotes`](../src/main/java/net/jrodolfo/java_evolution/java01/rmi/RmiNotes.java) | [`RmiNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java01/rmi/RmiNotesTest.java) |
| JavaBeans | [`javabeans/JavaBeansExamples`](../src/main/java/net/jrodolfo/java_evolution/java01/javabeans/JavaBeansExamples.java) | [`JavaBeansExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java01/javabeans/JavaBeansExamplesTest.java) |

## Java 2

Learning notes: [java02 README](../src/main/java/net/jrodolfo/java_evolution/java02/README.md)

| Feature | Example | Test |
|---|---|---|
| Collections Framework | `CollectionsFrameworkExamples` | `CollectionsFrameworkExamplesTest` |
| Sorting with `Comparable` and `Comparator` | `SortingExamples` | `SortingExamplesTest` |
| `strictfp` | [`strict_floating_point/StrictFloatingPointNotes`](../src/main/java/net/jrodolfo/java_evolution/java02/strict_floating_point/StrictFloatingPointNotes.java) | [`StrictFloatingPointNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java02/strict_floating_point/StrictFloatingPointNotesTest.java) |
| Swing | [`swing/SwingNotes`](../src/main/java/net/jrodolfo/java_evolution/java02/swing/SwingNotes.java) | [`SwingNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java02/swing/SwingNotesTest.java) |
| Java 2D | [`java2d/Java2DExamples`](../src/main/java/net/jrodolfo/java_evolution/java02/java2d/Java2DExamples.java) | [`Java2DExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java02/java2d/Java2DExamplesTest.java) |
| Security policy | [`security/SecurityPolicyNotes`](../src/main/java/net/jrodolfo/java_evolution/java02/security/SecurityPolicyNotes.java) | [`SecurityPolicyNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java02/security/SecurityPolicyNotesTest.java) |

## Java 3

Learning notes: [java03 README](../src/main/java/net/jrodolfo/java_evolution/java03/README.md)

| Feature | Example | Test |
|---|---|---|
| Dynamic proxy | `DynamicProxyExamples` | `DynamicProxyExamplesTest` |
| Timer and TimerTask | `TimerExamples` | `TimerExamplesTest` |
| Shutdown hooks | [`shutdown_hooks/ShutdownHookExamples`](../src/main/java/net/jrodolfo/java_evolution/java03/shutdown_hooks/ShutdownHookExamples.java) | [`ShutdownHookExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java03/shutdown_hooks/ShutdownHookExamplesTest.java) |
| JNDI | [`jndi/JndiNotes`](../src/main/java/net/jrodolfo/java_evolution/java03/jndi/JndiNotes.java) | [`JndiNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java03/jndi/JndiNotesTest.java) |
| Legacy integration | [`legacy_integration/LegacyIntegrationNotes`](../src/main/java/net/jrodolfo/java_evolution/java03/legacy_integration/LegacyIntegrationNotes.java) | [`LegacyIntegrationNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java03/legacy_integration/LegacyIntegrationNotesTest.java) |

## Java 4

Learning notes: [java04 README](../src/main/java/net/jrodolfo/java_evolution/java04/README.md)

| Feature | Example | Test |
|---|---|---|
| Assertions | [`assertions/AssertionExamples`](../src/main/java/net/jrodolfo/java_evolution/java04/assertions/AssertionExamples.java) | [`AssertionExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java04/assertions/AssertionExamplesTest.java) |
| Regular expressions | `RegexExamples` | `RegexExamplesTest` |
| NIO | `NioExamples` | `NioExamplesTest` |
| Logging API | `LoggingExamples` | `LoggingExamplesTest` |
| Chained exceptions | `ChainedExceptionExamples` | `ChainedExceptionExamplesTest` |
| Preferences API | [`preferences/PreferencesNotes`](../src/main/java/net/jrodolfo/java_evolution/java04/preferences/PreferencesNotes.java) | [`PreferencesNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java04/preferences/PreferencesNotesTest.java) |
| XML/JAXP | [`xml/JaxpExamples`](../src/main/java/net/jrodolfo/java_evolution/java04/xml/JaxpExamples.java) | [`JaxpExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java04/xml/JaxpExamplesTest.java) |
| Integrated security APIs | [`security/SecurityIntegrationNotes`](../src/main/java/net/jrodolfo/java_evolution/java04/security/SecurityIntegrationNotes.java) | [`SecurityIntegrationNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java04/security/SecurityIntegrationNotesTest.java) |

## Java 5

Learning notes: [java05 README](../src/main/java/net/jrodolfo/java_evolution/java05/README.md)

| Feature | Example | Test |
|---|---|---|
| Generics | `GenericsExamples` | `GenericsExamplesTest` |
| Enhanced for loop | `EnhancedForLoopExamples` | `EnhancedForLoopExamplesTest` |
| Autoboxing and unboxing | `AutoboxingExamples` | `AutoboxingExamplesTest` |
| Typesafe enums | `EnumExamples` | `EnumExamplesTest` |
| Varargs | `VarargsExamples` | `VarargsExamplesTest` |
| Static import | `StaticImportExamples` | `StaticImportExamplesTest` |
| Annotations | `AnnotationExamples` | `AnnotationExamplesTest` |
| Covariant return types | `CovariantReturnExamples` | `CovariantReturnExamplesTest` |
| Formatted output | `FormattingExamples` | `FormattingExamplesTest` |
| Concurrency utilities | `ConcurrencyUtilitiesExamples` | `ConcurrencyUtilitiesExamplesTest` |

## Java 6

Learning notes: [java06 README](../src/main/java/net/jrodolfo/java_evolution/java06/README.md)

| Feature | Example | Test |
|---|---|---|
| Navigable collections and deques | `NavigableCollectionExamples` | `NavigableCollectionExamplesTest` |
| Scripting support | [`scripting/ScriptingSupportNotes`](../src/main/java/net/jrodolfo/java_evolution/java06/scripting/ScriptingSupportNotes.java) | [`ScriptingSupportNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java06/scripting/ScriptingSupportNotesTest.java) |
| Compiler API | [`compiler_api/CompilerApiExamples`](../src/main/java/net/jrodolfo/java_evolution/java06/compiler_api/CompilerApiExamples.java) | [`CompilerApiExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java06/compiler_api/CompilerApiExamplesTest.java) |
| Console API | [`console_api/ConsoleApiNotes`](../src/main/java/net/jrodolfo/java_evolution/java06/console_api/ConsoleApiNotes.java) | [`ConsoleApiNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java06/console_api/ConsoleApiNotesTest.java) |
| Monitoring and management | [`monitoring_management/MonitoringManagementExamples`](../src/main/java/net/jrodolfo/java_evolution/java06/monitoring_management/MonitoringManagementExamples.java) | [`MonitoringManagementExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java06/monitoring_management/MonitoringManagementExamplesTest.java) |
| Web-service support | [`web_services/WebServiceSupportNotes`](../src/main/java/net/jrodolfo/java_evolution/java06/web_services/WebServiceSupportNotes.java) | [`WebServiceSupportNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java06/web_services/WebServiceSupportNotesTest.java) |

## Java 7

Learning notes: [java07 README](../src/main/java/net/jrodolfo/java_evolution/java07/README.md)

| Feature | Example | Test |
|---|---|---|
| Try-with-resources | `TryWithResourcesStatementExamples` | `TryWithResourcesStatementExamplesTest` |
| Multi-catch and precise rethrow | `ExceptionHandlingExamples` | `ExceptionHandlingExamplesTest` |
| Diamond operator | `DiamondOperatorExamples` | `DiamondOperatorExamplesTest` |
| Strings in switch | `StringSwitchExamples` | `StringSwitchExamplesTest` |
| Binary literals and numeric underscores | `NumericLiteralExamples` | `NumericLiteralExamplesTest` |
| NIO.2 | `Nio2Examples` | `Nio2ExamplesTest` |
| Fork/join framework | `ForkJoinExamples` | `ForkJoinExamplesTest` |
| `invokedynamic` | [`invokedynamic/InvokeDynamicNotes`](../src/main/java/net/jrodolfo/java_evolution/java07/invokedynamic/InvokeDynamicNotes.java) | [`InvokeDynamicNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java07/invokedynamic/InvokeDynamicNotesTest.java) |

## Java 8

Learning notes: [java08 README](../src/main/java/net/jrodolfo/java_evolution/java08/README.md)

| Feature | Example | Test |
|---|---|---|
| Lambdas | `LambdaExamples` | `LambdaExamplesTest` |
| Streams | `StreamExamples` | `StreamExamplesTest` |
| Optional | `OptionalExamples` | `OptionalExamplesTest` |
| Method references | `MethodReferenceExamples` | `MethodReferenceExamplesTest` |
| CompletableFuture | `CompletableFutureExamples` | `CompletableFutureExamplesTest` |
| Default methods | `DefaultMethodExamples` | `DefaultMethodExamplesTest` |
| Date/Time API | `DateTimeApiExamples` | `DateTimeApiExamplesTest` |

## Java 9

Learning notes: [java09 README](../src/main/java/net/jrodolfo/java_evolution/java09/README.md)

| Feature | Example | Test |
|---|---|---|
| Collection factories | `CollectionFactoryExamples` | `CollectionFactoryExamplesTest` |
| Optional enhancements | `OptionalEnhancementExamples` | `OptionalEnhancementExamplesTest` |
| Stream enhancements | `StreamEnhancementExamples` | `StreamEnhancementExamplesTest` |
| Private interface methods | `PrivateInterfaceMethodExamples` | `PrivateInterfaceMethodExamplesTest` |
| Try-with-resources improvement | `TryWithResourcesExamples` | `TryWithResourcesExamplesTest` |
| Process API | `ProcessApiExamples` | `ProcessApiExamplesTest` |
| StackWalker | `StackWalkerExamples` | `StackWalkerExamplesTest` |
| Module system | [`ModuleSystemNotes`](../src/main/java/net/jrodolfo/java_evolution/java09/module_system/ModuleSystemNotes.java) | [`ModuleSystemNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java09/module_system/ModuleSystemNotesTest.java) |

## Java 10

Learning notes: [java10 README](../src/main/java/net/jrodolfo/java_evolution/java10/README.md)

| Feature | Example | Test |
|---|---|---|
| Local variable type inference | `LocalVariableTypeInferenceExamples` | `LocalVariableTypeInferenceExamplesTest` |
| Unmodifiable collectors | `UnmodifiableCollectorsExamples` | `UnmodifiableCollectorsExamplesTest` |
| `Optional.orElseThrow()` | `OptionalOrElseThrowExamples` | `OptionalOrElseThrowExamplesTest` |

## Java 11

Learning notes: [java11 README](../src/main/java/net/jrodolfo/java_evolution/java11/README.md)

| Feature | Example | Test |
|---|---|---|
| String API additions | `StringApiExamples` | `StringApiExamplesTest` |
| Files read/write string | `FilesApiExamples` | `FilesApiExamplesTest` |
| HTTP Client | `HttpClientExamples` | `HttpClientExamplesTest` |
| `Predicate.not` | `PredicateNotExamples` | `PredicateNotExamplesTest` |
| Lambda `var` | `LambdaVarExamples` | `LambdaVarExamplesTest` |
| `Optional.isEmpty` | `OptionalIsEmptyExamples` | `OptionalIsEmptyExamplesTest` |

## Java 12

Learning notes: [java12 README](../src/main/java/net/jrodolfo/java_evolution/java12/README.md)

| Feature | Example | Test |
|---|---|---|
| Switch expressions preview | `SwitchExpressionPreviewExamples` | `SwitchExpressionPreviewExamplesTest` |
| Teeing collector | `TeeingCollectorExamples` | `TeeingCollectorExamplesTest` |
| `String.indent` | `StringIndentExamples` | `StringIndentExamplesTest` |
| `Files.mismatch` | `FilesMismatchExamples` | `FilesMismatchExamplesTest` |
| Compact number formatting | `CompactNumberFormatExamples` | `CompactNumberFormatExamplesTest` |

## Java 13

Learning notes: [java13 README](../src/main/java/net/jrodolfo/java_evolution/java13/README.md)

| Feature | Example | Test |
|---|---|---|
| Text blocks preview | `TextBlockPreviewExamples` | `TextBlockPreviewExamplesTest` |
| Switch `yield` preview | `SwitchYieldPreviewExamples` | `SwitchYieldPreviewExamplesTest` |
| `FileSystems.newFileSystem(Path)` | `FileSystemsNewFileSystemExamples` | `FileSystemsNewFileSystemExamplesTest` |

## Java 14

Learning notes: [java14 README](../src/main/java/net/jrodolfo/java_evolution/java14/README.md)

| Feature | Example | Test |
|---|---|---|
| Switch expressions final | `SwitchExpressionExamples` | `SwitchExpressionExamplesTest` |
| Helpful NullPointerExceptions | `HelpfulNullPointerExceptionExamples` | `HelpfulNullPointerExceptionExamplesTest` |
| Records preview | `RecordPreviewExamples` | `RecordPreviewExamplesTest` |
| Pattern matching for `instanceof` preview | `PatternMatchingInstanceofPreviewExamples` | `PatternMatchingInstanceofPreviewExamplesTest` |

## Java 15

Learning notes: [java15 README](../src/main/java/net/jrodolfo/java_evolution/java15/README.md)

| Feature | Example | Test |
|---|---|---|
| Text blocks final | `TextBlockExamples` | `TextBlockExamplesTest` |
| Sealed classes preview | `SealedClassesPreviewExamples` | `SealedClassesPreviewExamplesTest` |
| Hidden classes | [`HiddenClassesExamples`](../src/main/java/net/jrodolfo/java_evolution/java15/hidden_classes/HiddenClassesExamples.java) | [`HiddenClassesExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java15/hidden_classes/HiddenClassesExamplesTest.java) |

## Java 16

Learning notes: [java16 README](../src/main/java/net/jrodolfo/java_evolution/java16/README.md)

| Feature | Example | Test |
|---|---|---|
| Records final | `RecordExamples` | `RecordExamplesTest` |
| Pattern matching for `instanceof` final | `PatternMatchingInstanceofExamples` | `PatternMatchingInstanceofExamplesTest` |
| `Stream.toList()` | `StreamToListExamples` | `StreamToListExamplesTest` |
| Unix-domain socket channels | [`unix_domain_socket/UnixDomainSocketChannelExamples`](../src/main/java/net/jrodolfo/java_evolution/java16/unix_domain_socket/UnixDomainSocketChannelExamples.java) | [`UnixDomainSocketChannelExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java16/unix_domain_socket/UnixDomainSocketChannelExamplesTest.java) |
| Vector API first incubator | [java16 README](../src/main/java/net/jrodolfo/java_evolution/java16/README.md) | No dedicated test |
| Foreign Linker API incubator | [java16 README](../src/main/java/net/jrodolfo/java_evolution/java16/README.md) | No dedicated test |
| Foreign-Memory Access API third incubator | [java16 README](../src/main/java/net/jrodolfo/java_evolution/java16/README.md) | No dedicated test |

## Java 17

Learning notes: [java17 README](../src/main/java/net/jrodolfo/java_evolution/java17/README.md)

| Feature | Example | Test |
|---|---|---|
| Sealed classes final | `SealedClassesExamples` | `SealedClassesExamplesTest` |
| Pattern matching for switch preview | `PatternMatchingSwitchPreviewExamples` | `PatternMatchingSwitchPreviewExamplesTest` |
| Random Generator API | `RandomGeneratorExamples` | `RandomGeneratorExamplesTest` |
| `HexFormat` | `HexFormatExamples` | `HexFormatExamplesTest` |
| Strong encapsulation | [`strong_encapsulation`](../src/main/java/net/jrodolfo/java_evolution/java17/strong_encapsulation/README.md) | [`StrongEncapsulationNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java17/strong_encapsulation/StrongEncapsulationNotesTest.java) |

## Java 18

Learning notes: [java18 README](../src/main/java/net/jrodolfo/java_evolution/java18/README.md)

| Feature | Example | Test |
|---|---|---|
| UTF-8 default charset | `Utf8DefaultCharsetExamples` | `Utf8DefaultCharsetExamplesTest` |
| Simple Web Server | [`SimpleStaticFileServer`](../src/main/java/net/jrodolfo/java_evolution/java18/simple_web_server/SimpleStaticFileServer.java) | [`SimpleStaticFileServerTest`](../src/test/java/net/jrodolfo/java_evolution/java18/simple_web_server/SimpleStaticFileServerTest.java) |
| JavaDoc snippets | [`JavaDocSnippetExamples`](../src/main/java/net/jrodolfo/java_evolution/java18/javadoc_snippets/JavaDocSnippetExamples.java) | [`JavaDocSnippetExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java18/javadoc_snippets/JavaDocSnippetExamplesTest.java) |
| Internet-Address Resolution SPI | [`inet_address_resolution`](../src/main/java/net/jrodolfo/java_evolution/java18/inet_address_resolution/README.md) | [`InetAddressResolutionNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java18/inet_address_resolution/InetAddressResolutionNotesTest.java) |

## Java 19

Learning notes: [java19 README](../src/main/java/net/jrodolfo/java_evolution/java19/README.md)

| Feature | Example | Test |
|---|---|---|
| Virtual threads preview | `VirtualThreadsPreviewExamples` | `VirtualThreadsPreviewExamplesTest` |
| Record patterns preview | `RecordPatternsPreviewExamples` | `RecordPatternsPreviewExamplesTest` |
| Pattern matching for switch preview | `PatternMatchingSwitchPreviewExamples` | `PatternMatchingSwitchPreviewExamplesTest` |
| Structured concurrency incubator | [`StructuredConcurrencyPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java19/structured_concurrency/StructuredConcurrencyPreviewNotes.java) | [`StructuredConcurrencyPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java19/structured_concurrency/StructuredConcurrencyPreviewNotesTest.java) |
| Foreign Function and Memory API | `ForeignFunctionMemoryApiPreviewNotes` | `ForeignFunctionMemoryApiPreviewNotesTest` |

## Java 20

Learning notes: [java20 README](../src/main/java/net/jrodolfo/java_evolution/java20/README.md)

| Feature | Example | Test |
|---|---|---|
| Record patterns second preview | `RecordPatternsSecondPreviewExamples` | `RecordPatternsSecondPreviewExamplesTest` |
| Pattern matching for switch fourth preview | `PatternMatchingSwitchFourthPreviewExamples` | `PatternMatchingSwitchFourthPreviewExamplesTest` |
| Virtual threads second preview | `VirtualThreadsSecondPreviewNotes` | `VirtualThreadsSecondPreviewNotesTest` |
| Scoped values incubator | [`ScopedValuesIncubatorNotes`](../src/main/java/net/jrodolfo/java_evolution/java20/scoped_values/ScopedValuesIncubatorNotes.java) | [`ScopedValuesIncubatorNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java20/scoped_values/ScopedValuesIncubatorNotesTest.java) |
| Structured concurrency second incubator | [`StructuredConcurrencySecondIncubatorNotes`](../src/main/java/net/jrodolfo/java_evolution/java20/structured_concurrency/StructuredConcurrencySecondIncubatorNotes.java) | [`StructuredConcurrencySecondIncubatorNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java20/structured_concurrency/StructuredConcurrencySecondIncubatorNotesTest.java) |
| Foreign Function and Memory API second preview | `ForeignFunctionMemorySecondPreviewNotes` | `ForeignFunctionMemorySecondPreviewNotesTest` |
| Vector API fifth incubator | [`VectorApiFifthIncubatorNotes`](../src/main/java/net/jrodolfo/java_evolution/java20/vector_api/VectorApiFifthIncubatorNotes.java) | [`VectorApiFifthIncubatorNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java20/vector_api/VectorApiFifthIncubatorNotesTest.java) |

## Java 21

Learning notes: [java21 README](../src/main/java/net/jrodolfo/java_evolution/java21/README.md)

| Feature | Example | Test |
|---|---|---|
| Virtual threads final | `VirtualThreadsExamples` | `VirtualThreadsExamplesTest` |
| Record patterns final | `RecordPatternsExamples` | `RecordPatternsExamplesTest` |
| Pattern matching for switch final | [`PatternMatchingSwitchExamples`](../src/main/java/net/jrodolfo/java_evolution/java21/PatternMatchingSwitchExamples.java) | [`PatternMatchingSwitchExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java21/PatternMatchingSwitchExamplesTest.java) |
| Sequenced collections final | `SequencedCollectionsExamples` | `SequencedCollectionsExamplesTest` |
| Unnamed patterns and variables preview | `UnnamedPatternsVariablesPreviewExamples` | `UnnamedPatternsVariablesPreviewExamplesTest` |
| Key Encapsulation Mechanism API | [`KeyEncapsulationExchange`](../src/main/java/net/jrodolfo/java_evolution/java21/key_encapsulation/KeyEncapsulationExchange.java) | [`KeyEncapsulationExchangeTest`](../src/test/java/net/jrodolfo/java_evolution/java21/key_encapsulation/KeyEncapsulationExchangeTest.java) |
| Scoped values preview | [`ScopedValuesPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java21/scoped_values/ScopedValuesPreviewNotes.java) | [`ScopedValuesPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java21/scoped_values/ScopedValuesPreviewNotesTest.java) |
| Structured concurrency preview | [`StructuredConcurrencyPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java21/structured_concurrency/StructuredConcurrencyPreviewNotes.java) | [`StructuredConcurrencyPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java21/structured_concurrency/StructuredConcurrencyPreviewNotesTest.java) |

## Java 22

Learning notes: [java22 README](../src/main/java/net/jrodolfo/java_evolution/java22/README.md)

| Feature | Example | Test |
|---|---|---|
| Unnamed variables and patterns final | [`UnnamedVariablesPatternsExamples`](../src/main/java/net/jrodolfo/java_evolution/java22/UnnamedVariablesPatternsExamples.java) | [`UnnamedVariablesPatternsExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java22/UnnamedVariablesPatternsExamplesTest.java) |
| Statements before `super(...)` preview | [`StatementsBeforeSuperPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java22/statements_before_super/StatementsBeforeSuperPreviewNotes.java) | [`StatementsBeforeSuperPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java22/statements_before_super/StatementsBeforeSuperPreviewNotesTest.java) |
| Stream Gatherers preview | [`StreamGatherersPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java22/stream_gatherers/StreamGatherersPreviewNotes.java) | [`StreamGatherersPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java22/stream_gatherers/StreamGatherersPreviewNotesTest.java) |
| Foreign Function and Memory API final | [`NativeStringParser`](../src/main/java/net/jrodolfo/java_evolution/java22/foreign_function/NativeStringParser.java), [`NativeStringLength`](../src/main/java/net/jrodolfo/java_evolution/java22/foreign_function/NativeStringLength.java) | [`ForeignFunctionExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java22/foreign_function/ForeignFunctionExamplesTest.java) |
| Multi-file source launcher final | [`LaunchMultiFileSourceProgramsExamples`](../src/main/java/net/jrodolfo/java_evolution/java22/launch_multi_file_source_programs/LaunchMultiFileSourceProgramsExamples.java) | [`LaunchMultiFileSourceProgramsExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java22/launch_multi_file_source_programs/LaunchMultiFileSourceProgramsExamplesTest.java) |
| Class-File API preview | [`ClassFileApiPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java22/class_file_api/ClassFileApiPreviewNotes.java) | [`ClassFileApiPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java22/class_file_api/ClassFileApiPreviewNotesTest.java) |
| Scoped values second preview | [`ScopedValuesSecondPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java22/scoped_values/ScopedValuesSecondPreviewNotes.java) | [`ScopedValuesSecondPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java22/scoped_values/ScopedValuesSecondPreviewNotesTest.java) |
| Structured concurrency second preview | [`StructuredConcurrencySecondPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java22/structured_concurrency/StructuredConcurrencySecondPreviewNotes.java) | [`StructuredConcurrencySecondPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java22/structured_concurrency/StructuredConcurrencySecondPreviewNotesTest.java) |

## Java 23

Learning notes: [java23 README](../src/main/java/net/jrodolfo/java_evolution/java23/README.md)

| Feature | Example | Test |
|---|---|---|
| Markdown documentation comments | [`MarkdownDocumentationCommentsExamples`](../src/main/java/net/jrodolfo/java_evolution/java23/markdown_documentation_comments/MarkdownDocumentationCommentsExamples.java) | [`MarkdownDocumentationCommentsExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java23/markdown_documentation_comments/MarkdownDocumentationCommentsExamplesTest.java) |
| Primitive patterns preview | [`PrimitivePatternsPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java23/primitive_patterns/PrimitivePatternsPreviewNotes.java) | [`PrimitivePatternsPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java23/primitive_patterns/PrimitivePatternsPreviewNotesTest.java) |
| Module import declarations preview | [`ModuleImportDeclarationsPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java23/module_import_declarations/ModuleImportDeclarationsPreviewNotes.java) | [`ModuleImportDeclarationsPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java23/module_import_declarations/ModuleImportDeclarationsPreviewNotesTest.java) |
| Flexible constructor bodies second preview | [`FlexibleConstructorBodiesSecondPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java23/flexible_constructor_bodies/FlexibleConstructorBodiesSecondPreviewNotes.java) | [`FlexibleConstructorBodiesSecondPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java23/flexible_constructor_bodies/FlexibleConstructorBodiesSecondPreviewNotesTest.java) |
| Stream Gatherers second preview | [`StreamGatherersSecondPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java23/stream_gatherers/StreamGatherersSecondPreviewNotes.java) | [`StreamGatherersSecondPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java23/stream_gatherers/StreamGatherersSecondPreviewNotesTest.java) |
| Class-File API second preview | [`ClassFileApiSecondPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java23/class_file_api/ClassFileApiSecondPreviewNotes.java) | [`ClassFileApiSecondPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java23/class_file_api/ClassFileApiSecondPreviewNotesTest.java) |
| Scoped values third preview | [`ScopedValuesThirdPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java23/scoped_values/ScopedValuesThirdPreviewNotes.java) | [`ScopedValuesThirdPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java23/scoped_values/ScopedValuesThirdPreviewNotesTest.java) |
| Structured concurrency third preview | [`StructuredConcurrencyThirdPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java23/structured_concurrency/StructuredConcurrencyThirdPreviewNotes.java) | [`StructuredConcurrencyThirdPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java23/structured_concurrency/StructuredConcurrencyThirdPreviewNotesTest.java) |
| Unsafe memory-access deprecation | [`UnsafeMemoryAccessDeprecationNotes`](../src/main/java/net/jrodolfo/java_evolution/java23/unsafe_memory_access_deprecation/UnsafeMemoryAccessDeprecationNotes.java) | [`UnsafeMemoryAccessDeprecationNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java23/unsafe_memory_access_deprecation/UnsafeMemoryAccessDeprecationNotesTest.java) |
| ZGC generational mode | [`ZgcGenerationalModeNotes`](../src/main/java/net/jrodolfo/java_evolution/java23/zgc_generational_mode/ZgcGenerationalModeNotes.java) | [`ZgcGenerationalModeNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java23/zgc_generational_mode/ZgcGenerationalModeNotesTest.java) |

## Java 24

Learning notes: [java24 README](../src/main/java/net/jrodolfo/java_evolution/java24/README.md)

| Feature | Example | Test |
|---|---|---|
| Stream Gatherers final | [`StreamGatherersExamples`](../src/main/java/net/jrodolfo/java_evolution/java24/StreamGatherersExamples.java) | [`StreamGatherersExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java24/StreamGatherersExamplesTest.java) |
| Class-File API final | [`ClassFileInspector`](../src/main/java/net/jrodolfo/java_evolution/java24/class_file/ClassFileInspector.java) | [`ClassFileInspectorTest`](../src/test/java/net/jrodolfo/java_evolution/java24/class_file/ClassFileInspectorTest.java) |
| Security Manager disabled | [`SecurityManagerDisabledNotes`](../src/main/java/net/jrodolfo/java_evolution/java24/security_manager_disabled/SecurityManagerDisabledNotes.java) | [`SecurityManagerDisabledNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java24/security_manager_disabled/SecurityManagerDisabledNotesTest.java) |
| Virtual-thread synchronization | [`VirtualThreadSynchronizationNotes`](../src/main/java/net/jrodolfo/java_evolution/java24/virtual_thread_synchronization/VirtualThreadSynchronizationNotes.java) | [`VirtualThreadSynchronizationNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java24/virtual_thread_synchronization/VirtualThreadSynchronizationNotesTest.java) |
| Quantum-resistant crypto | [`ModuleLatticeKemExample`](../src/main/java/net/jrodolfo/java_evolution/java24/quantum_resistant_crypto/ModuleLatticeKemExample.java), [`ModuleLatticeDsaExample`](../src/main/java/net/jrodolfo/java_evolution/java24/quantum_resistant_crypto/ModuleLatticeDsaExample.java) | [`ModuleLatticeCryptoExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java24/quantum_resistant_crypto/ModuleLatticeCryptoExamplesTest.java) |
| AOT class loading | [`AotClassLoadingNotes`](../src/main/java/net/jrodolfo/java_evolution/java24/aot_class_loading/AotClassLoadingNotes.java) | [`AotClassLoadingNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java24/aot_class_loading/AotClassLoadingNotesTest.java) |
| KDF preview | [`KeyDerivationFunctionPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java24/key_derivation/KeyDerivationFunctionPreviewNotes.java) | [`KeyDerivationFunctionPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java24/key_derivation/KeyDerivationFunctionPreviewNotesTest.java) |
| Flexible constructor bodies third preview | [`FlexibleConstructorBodiesThirdPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java24/flexible_constructor_bodies/FlexibleConstructorBodiesThirdPreviewNotes.java) | [`FlexibleConstructorBodiesThirdPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java24/flexible_constructor_bodies/FlexibleConstructorBodiesThirdPreviewNotesTest.java) |
| Module import declarations second preview | [`ModuleImportDeclarationsSecondPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java24/module_import_declarations/ModuleImportDeclarationsSecondPreviewNotes.java) | [`ModuleImportDeclarationsSecondPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java24/module_import_declarations/ModuleImportDeclarationsSecondPreviewNotesTest.java) |
| Primitive patterns second preview | [`PrimitivePatternsSecondPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java24/primitive_patterns/PrimitivePatternsSecondPreviewNotes.java) | [`PrimitivePatternsSecondPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java24/primitive_patterns/PrimitivePatternsSecondPreviewNotesTest.java) |
| Scoped values fourth preview | [`ScopedValuesFourthPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java24/scoped_values/ScopedValuesFourthPreviewNotes.java) | [`ScopedValuesFourthPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java24/scoped_values/ScopedValuesFourthPreviewNotesTest.java) |
| Structured concurrency fourth preview | [`StructuredConcurrencyFourthPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java24/structured_concurrency/StructuredConcurrencyFourthPreviewNotes.java) | [`StructuredConcurrencyFourthPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java24/structured_concurrency/StructuredConcurrencyFourthPreviewNotesTest.java) |

## Java 25

Learning notes: [java25 README](../src/main/java/net/jrodolfo/java_evolution/java25/README.md)

| Feature | Example | Test |
|---|---|---|
| Scoped values final | [`ScopedValuesExamples`](../src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/ScopedValuesExamples.java) | [`ScopedValuesExamplesTest`](../src/test/java/net/jrodolfo/java_evolution/java25/scoped_values/ScopedValuesExamplesTest.java) |
| Flexible constructor bodies final | `FlexibleConstructorBodiesExamples` | `FlexibleConstructorBodiesExamplesTest` |
| Module import declarations final | `ModuleImportDeclarationsNotes` | `ModuleImportDeclarationsNotesTest` |
| Compact source files final | `CompactSourceFilesNotes` | `CompactSourceFilesNotesTest` |
| Key Derivation Function API final | [`HkdfKeyDerivationExample`](../src/main/java/net/jrodolfo/java_evolution/java25/key_derivation/HkdfKeyDerivationExample.java) | [`HkdfKeyDerivationExampleTest`](../src/test/java/net/jrodolfo/java_evolution/java25/key_derivation/HkdfKeyDerivationExampleTest.java) |
| Primitive patterns third preview | `PrimitivePatternsThirdPreviewNotes` | `PrimitivePatternsThirdPreviewNotesTest` |
| Stable values preview | [`StableValuesPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java25/stable_values/StableValuesPreviewNotes.java) | [`StableValuesPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java25/stable_values/StableValuesPreviewNotesTest.java) |
| PEM encodings preview | `PemEncodingsPreviewNotes` | `PemEncodingsPreviewNotesTest` |
| Structured concurrency fifth preview | [`StructuredConcurrencyFifthPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java25/structured_concurrency/StructuredConcurrencyFifthPreviewNotes.java) | [`StructuredConcurrencyFifthPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java25/structured_concurrency/StructuredConcurrencyFifthPreviewNotesTest.java) |
| Vector API tenth incubator | [`VectorApiTenthIncubatorNotes`](../src/main/java/net/jrodolfo/java_evolution/java25/vector_api/VectorApiTenthIncubatorNotes.java) | [`VectorApiTenthIncubatorNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java25/vector_api/VectorApiTenthIncubatorNotesTest.java) |
| AOT command-line ergonomics | [`AotCommandLineErgonomicsNotes`](../src/main/java/net/jrodolfo/java_evolution/java25/aot_command_line/AotCommandLineErgonomicsNotes.java) | [`AotCommandLineErgonomicsNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java25/aot_command_line/AotCommandLineErgonomicsNotesTest.java) |
| JFR enhancements | [`JfrEnhancementsNotes`](../src/main/java/net/jrodolfo/java_evolution/java25/jfr_enhancements/JfrEnhancementsNotes.java) | [`JfrEnhancementsNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java25/jfr_enhancements/JfrEnhancementsNotesTest.java) |
| Compact object headers | [`CompactObjectHeadersNotes`](../src/main/java/net/jrodolfo/java_evolution/java25/compact_object_headers/CompactObjectHeadersNotes.java) | [`CompactObjectHeadersNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java25/compact_object_headers/CompactObjectHeadersNotesTest.java) |
| Generational Shenandoah | [`GenerationalShenandoahNotes`](../src/main/java/net/jrodolfo/java_evolution/java25/generational_shenandoah/GenerationalShenandoahNotes.java) | [`GenerationalShenandoahNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java25/generational_shenandoah/GenerationalShenandoahNotesTest.java) |

## Java 26

Learning notes: [java26 README](../src/main/java/net/jrodolfo/java_evolution/java26/README.md)

Java 26 is represented as C2 explanatory material while the repository keeps JDK 25 as its build baseline.

| Feature | Example | Test |
|---|---|---|
| HTTP/3 for the HTTP Client API | [`Http3ClientNotes`](../src/main/java/net/jrodolfo/java_evolution/java26/Http3ClientNotes.java) | [`Http3ClientNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java26/Http3ClientNotesTest.java) |
| Final field restrictions | [`FinalFieldRestrictionsNotes`](../src/main/java/net/jrodolfo/java_evolution/java26/final_field_restrictions/FinalFieldRestrictionsNotes.java) | [`FinalFieldRestrictionsNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java26/final_field_restrictions/FinalFieldRestrictionsNotesTest.java) |
| Applet API removal | [`AppletApiRemovalNotes`](../src/main/java/net/jrodolfo/java_evolution/java26/applet_api_removal/AppletApiRemovalNotes.java) | [`AppletApiRemovalNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java26/applet_api_removal/AppletApiRemovalNotesTest.java) |
| AOT object caching | [`AotObjectCachingNotes`](../src/main/java/net/jrodolfo/java_evolution/java26/aot_object_caching/AotObjectCachingNotes.java) | [`AotObjectCachingNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java26/aot_object_caching/AotObjectCachingNotesTest.java) |
| G1 synchronization reduction | [`G1SynchronizationNotes`](../src/main/java/net/jrodolfo/java_evolution/java26/g1_synchronization/G1SynchronizationNotes.java) | [`G1SynchronizationNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java26/g1_synchronization/G1SynchronizationNotesTest.java) |
| PEM encodings second preview | [`PemEncodingsSecondPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java26/PemEncodingsSecondPreviewNotes.java) | [`PemEncodingsSecondPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java26/PemEncodingsSecondPreviewNotesTest.java) |
| Structured concurrency sixth preview | [`StructuredConcurrencySixthPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java26/structured_concurrency/StructuredConcurrencySixthPreviewNotes.java) | [`StructuredConcurrencySixthPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java26/structured_concurrency/StructuredConcurrencySixthPreviewNotesTest.java) |
| Lazy Constants second preview | [`LazyConstantsSecondPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java26/lazy_constants/LazyConstantsSecondPreviewNotes.java) | [`LazyConstantsSecondPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java26/lazy_constants/LazyConstantsSecondPreviewNotesTest.java) |
| Vector API eleventh incubator | [`VectorApiEleventhIncubatorNotes`](../src/main/java/net/jrodolfo/java_evolution/java26/vector_api/VectorApiEleventhIncubatorNotes.java) | [`VectorApiEleventhIncubatorNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java26/vector_api/VectorApiEleventhIncubatorNotesTest.java) |
| Primitive patterns fourth preview | [`PrimitivePatternsFourthPreviewNotes`](../src/main/java/net/jrodolfo/java_evolution/java26/PrimitivePatternsFourthPreviewNotes.java) | [`PrimitivePatternsFourthPreviewNotesTest`](../src/test/java/net/jrodolfo/java_evolution/java26/PrimitivePatternsFourthPreviewNotesTest.java) |
