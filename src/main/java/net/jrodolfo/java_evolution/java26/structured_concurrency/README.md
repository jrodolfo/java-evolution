# Structured Concurrency Sixth Preview

Structured concurrency treats related concurrent subtasks as one scoped operation.

Without structure, child tasks can continue after the caller has moved on, failures can be reported far away from their cause, and cancellation can become scattered. Structured concurrency gives concurrent work a clearer lifetime.

Java 26 continues structured concurrency as a sixth preview. It is still not final, so this repository runs the preview API only in an isolated child JVM.

This is a C1 executable preview example because the current API exposes deterministic scope, join, and failure semantics that improve the lesson without relying on scheduler timing or cancellation races.

Example: `StructuredConcurrencySixthPreviewExamples`

Test: `StructuredConcurrencySixthPreviewExamplesTest`
