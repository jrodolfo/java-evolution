# AOT Object Caching

Ahead-of-time (AOT) work tries to reduce the amount of repeated startup work a Java application performs every time it launches.

Java 26 adds ahead-of-time object caching with any garbage collector. The idea is to preserve selected initialized object state so startup can reuse work that would otherwise be repeated.

This is part of the broader startup and warmup story around Project Leyden-style improvements. It is operationally important, but the learning artifact should explain the workflow and runtime behavior rather than pretend a normal unit test can prove startup benefits.

This is a C2 explanatory module because it depends on JVM startup behavior and command-line workflows.
