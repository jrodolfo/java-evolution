# G1 Synchronization Reduction

G1 means Garbage-First. It is a general-purpose garbage collector designed to balance throughput and pause-time goals.

Java 26 improves G1 throughput by reducing synchronization in garbage-collector internals. The practical idea is simple: less coordination overhead inside the collector can leave more time for application work.

This is a runtime improvement, not a Java language or library feature. It is best understood through release notes, JVM documentation, and workload measurements.

This is a C2 explanatory module because a small JUnit test would not faithfully demonstrate garbage-collector throughput.
