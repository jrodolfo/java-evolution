# Structured Concurrency Sixth Preview

Structured concurrency treats related concurrent subtasks as one scoped operation.

Without structure, child tasks can continue after the caller has moved on, failures can be reported far away from their cause, and cancellation can become scattered. Structured concurrency gives concurrent work a clearer lifetime.

Java 26 continues structured concurrency as a sixth preview. It is still not final, so this repository keeps the feature as explanatory material under the JDK 25 baseline.

This is a C2 explanatory module because compiling the current Java 26 preview API would require a Java 26 build with preview features enabled.
