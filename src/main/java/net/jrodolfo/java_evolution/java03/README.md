# Java 3

Released: May 2000 as J2SE 1.3.

J2SE 1.3 was not a large language release. Its most teachable additions for this repository are dynamic proxies, timer tasks, shutdown hooks, JNDI context, and legacy enterprise integration notes.

## Dynamic Proxy

Dynamic proxies let Java create an object at runtime that implements one or more interfaces and routes method calls to an invocation handler.

Example: `DynamicProxyExamples`

Test: `DynamicProxyExamplesTest`

## Timer And TimerTask

Before scheduled executors, `Timer` and `TimerTask` provided a standard way to schedule delayed or repeated work.

Example: `TimerExamples`

Test: `TimerExamplesTest`

## Shutdown Hooks

Shutdown hooks let code register cleanup work that runs during normal JVM shutdown.

Explanatory module: [`shutdown_hooks`](shutdown_hooks/README.md)

Test: `ShutdownHookNotesTest`

## JNDI

Java Naming and Directory Interface (JNDI) provides a standard API for naming and directory services.

Explanatory module: [`jndi`](jndi/README.md)

Test: `JndiNotesTest`

## Legacy Integration

J2SE 1.3 lived in an era of RMI/IIOP, CORBA, applet/plugin deployment, and enterprise integration APIs that are mostly legacy today.

Explanatory module: [`legacy_integration`](legacy_integration/README.md)

Test: `LegacyIntegrationNotesTest`

## How To Read This Package

Start with `DynamicProxyExamples`, then `TimerExamples`, then the notes modules.

Run the focused tests:

```bash
mvn -Dtest=DynamicProxyExamplesTest,TimerExamplesTest test
mvn -Dtest=ShutdownHookNotesTest,JndiNotesTest,LegacyIntegrationNotesTest test
```

After this package, continue with Java 4 for assertions, regex, NIO, logging, chained exceptions, preferences, XML, and integrated security APIs.

## References

- [JDK release notes index](https://www.oracle.com/java/technologies/javase/jdk-relnotes-index.html)
