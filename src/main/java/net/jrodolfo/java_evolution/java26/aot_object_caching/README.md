# AOT Object Caching

Ahead-of-time (AOT) work tries to reduce the amount of repeated startup work a Java application performs every time it launches.

Java 26 adds ahead-of-time object caching with any garbage collector. The idea is to preserve selected initialized object state so startup can reuse work that would otherwise be repeated.

This is part of the broader startup and warmup story around Project Leyden-style improvements. The example demonstrates the cache workflow and runtime evidence rather than pretending a normal unit test can prove startup benefits.

This is a C1 executable runtime example. It launches a tiny source file in a child JVM with `-XX:AOTCacheOutput`, then launches it again with `-XX:AOTCache`. The test verifies that a cache is created and that the second JVM opens and uses it.

The example does not measure startup time. Cache size and startup speed depend on the JDK build, garbage collector, heap configuration, object-header settings, operating system, and machine. Temporary source, cache, and configuration artifacts stay inside the test workspace.
