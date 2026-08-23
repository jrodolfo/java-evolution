# Java 6

Released: December 2006 as Java SE 6.

Java 6 was more of a platform and tooling release than a language release. It added standard scripting integration, a compiler API, better monitoring and management support, console password prompting, web-service APIs, desktop improvements, and library refinements such as deques and navigable collections.

This repository keeps several Java 6 topics as explanatory modules because a faithful demonstration often depends on interactive terminals, optional engines, removed bundled APIs, or runtime attachment behavior. The executable examples focus on collection APIs, compiler invocation, and local MXBean monitoring APIs that still compile and run naturally on JDK 25.

## Navigable Collections And Deques

Before Java 6, sorted sets and maps existed, but APIs for nearest-neighbor navigation were limited. Code that needed "lower than this", "at least this", "first", "last", or descending views often had to combine multiple operations manually.

Java 6 added `NavigableSet`, `NavigableMap`, and `Deque`. These APIs made ordered collection navigation and double-ended queues part of the standard library.

Example: `NavigableCollectionExamples`

Test: `NavigableCollectionExamplesTest`

## Scripting Support

Java 6 added JSR 223, the Scripting API, so Java applications could host script engines through `javax.script`.

This is an explanatory module because JDK 25 still has the API, but no longer guarantees the old JavaScript engine that was bundled with Sun's Java 6 implementation.

Explanatory module: [`scripting`](scripting/README.md)

Test: `ScriptingSupportNotesTest`

## Compiler API

Java 6 added JSR 199, the Java Compiler API, which lets tools invoke a Java compiler programmatically and receive diagnostics.

This module uses a real file-based example: it locates the system compiler, compiles temporary `.java` files into a class-output directory, and collects structured diagnostics for invalid source.

Example module: [`compiler_api`](compiler_api/README.md)

Test: `CompilerApiExamplesTest`

## Console API

Java 6 added `java.io.Console`, including password-reading methods that avoid echoing sensitive input.

This is an explanatory module because `System.console()` is commonly `null` when code runs in Maven, IDEs, CI, or redirected processes.

Explanatory module: [`console_api`](console_api/README.md)

Test: `ConsoleApiNotesTest`

## Monitoring And Management

Java 6 improved Java Management Extensions (JMX), platform MXBeans, attach-on-demand diagnostics, JConsole support, and related monitoring tools.

This module uses executable examples for local platform MXBeans and documentation for the broader runtime-tooling story.

Example module: [`monitoring_management`](monitoring_management/README.md)

Test: `MonitoringManagementExamplesTest`

## Web-Service Support

Java 6 brought several XML and web-service APIs into Java SE, including JAX-WS and JAXB-era support.

This is an explanatory module because those APIs were later removed from the JDK and are normally handled as explicit dependencies in modern projects.

Explanatory module: [`web_services`](web_services/README.md)

Test: `WebServiceSupportNotesTest`

## How To Read This Package

Start with `NavigableCollectionExamples`, `compiler_api/CompilerApiExamples`, and `monitoring_management/MonitoringManagementExamples` for executable library, tooling, and runtime-observability features. Then read the explanatory modules to understand the Java 6 platform direction without forcing obsolete or environment-sensitive APIs into the Maven test suite.

Run the focused tests:

```bash
mvn -Dtest=NavigableCollectionExamplesTest test
mvn -Dtest=ScriptingSupportNotesTest,CompilerApiExamplesTest,ConsoleApiNotesTest test
mvn -Dtest=MonitoringManagementExamplesTest,WebServiceSupportNotesTest test
```

After this package, continue with Java 7 to see Project Coin language refinements, NIO.2, fork/join, and `invokedynamic`.

## References

- [Oracle Java SE 6 features and enhancements](https://www.oracle.com/java/technologies/javase/features.html)
- [Java SE 6 scripting guide](https://docs.oracle.com/javase/6/docs/technotes/guides/scripting/)
- [JavaCompiler API](https://docs.oracle.com/javase/6/docs/api/javax/tools/JavaCompiler.html)
- [Console API](https://docs.oracle.com/javase/6/docs/api/java/io/Console.html)
- [Java SE 6 monitoring and management](https://docs.oracle.com/javase/6/docs/technotes/guides/management/index.html)
