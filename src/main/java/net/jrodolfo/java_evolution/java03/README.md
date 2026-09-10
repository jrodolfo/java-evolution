# Java 3

Released: May 2000 as J2SE 1.3.

J2SE 1.3 was not a large language release. Its most teachable additions for this repository are dynamic proxies, timer tasks, shutdown hooks, JNDI context, and legacy enterprise integration notes.

## Dynamic Proxy

Dynamic proxies let Java create an object at runtime that implements one or more interfaces and routes method calls to an invocation handler.

This is useful when every call needs an additional concern, such as logging,
security checks, transaction handling, or framework interception. The proxy is
the object the caller sees; the `InvocationHandler` receives each method call
and decides what should happen next, often forwarding the call to a real
object. You can picture the flow as:

```text
caller -> proxy -> invocation handler -> real object
```

Example: `DynamicProxyExamples`

Test: `DynamicProxyExamplesTest`

## Timer And TimerTask

Before scheduled executors, `Timer` and `TimerTask` provided a standard way to schedule delayed or repeated work.

`Timer` is the scheduler. `TimerTask` is the unit of work that the scheduler
will run. Separating those roles lets one timer coordinate tasks that should
run later or repeatedly without each task implementing its own waiting loop.

Example: `TimerExamples`

Test: `TimerExamplesTest`

## Shutdown Hooks

Shutdown hooks let code register cleanup work that runs during normal JVM shutdown.

Example module: [`shutdown_hooks`](shutdown_hooks/README.md)

Test: `ShutdownHookExamplesTest`

## JNDI

Java Naming and Directory Interface (JNDI) provides a standard API for naming and directory services.

Example module: [`jndi`](jndi/README.md)

Test: `JndiExamplesTest`

## Legacy Integration

J2SE 1.3 lived in an era of RMI/IIOP, CORBA, applet/plugin deployment, and enterprise integration APIs that are mostly legacy today.

Explanatory module: [`legacy_integration`](legacy_integration/README.md)

Test: `LegacyIntegrationNotesTest`

## How To Read This Package

Start with `DynamicProxyExamples`, then `TimerExamples`, then the executable modules for shutdown hooks and JNDI. Finish with the legacy integration notes.

Run the focused tests:

```bash
mvn -Dtest=DynamicProxyExamplesTest,TimerExamplesTest test
mvn -Dtest=ShutdownHookExamplesTest,JndiExamplesTest,LegacyIntegrationNotesTest test
```

After this package, continue with Java 4 for assertions, regex, NIO, logging, chained exceptions, preferences, XML, and integrated security APIs.

## References

- <a target="_blank" rel="noopener noreferrer" href="https://www.oracle.com/java/technologies/javase/jdk-relnotes-index.html">JDK release notes index</a>
