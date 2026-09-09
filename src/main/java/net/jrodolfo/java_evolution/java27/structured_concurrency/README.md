# Structured Concurrency Seventh Preview

With unstructured concurrency, a parent operation can start independent tasks
whose lifetimes become disconnected from the parent. A task may finish after
the parent has failed or returned, and failure, cancellation, and completion
then require separate bookkeeping.

Structured concurrency continues as a Java 27 seventh preview. A lexical
scope is a bounded region of the program, such as the body of a block. The
structured model opens such a scope, makes related subtasks belong to it, and
joins them as a unit. Task lifetime, cancellation, failure handling, and
observability therefore follow the program structure rather than only the
thread that happened to start each task.

The child probe demonstrates this organization with two subtasks and a shared
join. Structured concurrency organizes concurrent work; it does not
automatically make arbitrary work execute faster.

Example: `StructuredConcurrencySeventhPreviewExamples`  
Test: `StructuredConcurrencySeventhPreviewExamplesTest`
