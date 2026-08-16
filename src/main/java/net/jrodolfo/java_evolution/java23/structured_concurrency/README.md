# Structured Concurrency Third Preview

Java 23 continued Structured Concurrency as a third preview feature in JEP 480.

This module is explanatory. It exists to show the Java 23 step in the feature's evolution without forcing old preview API code into the normal Maven build.

## What Problem Does This Feature Solve?

Concurrent code often starts several tasks that belong to one larger operation.

For example, a request handler might need to:

- load customer data
- load order data
- load shipping options

Those tasks can run at the same time, but conceptually they are not independent background jobs. They are children of the same parent request.

Without structure, the code can become scattered:

```text
handleRequest()
    -> start customer task
    -> start order task
    -> start shipping task
    -> later, collect results
    -> somewhere else, handle failure
    -> somewhere else, cancel unfinished work
```

The hard part is not starting threads. The hard part is keeping the parent operation and child tasks tied together.

## How Was This Commonly Done Before?

Developers commonly used executors, futures, and manual coordination.

That approach can work, but the rules are easy to spread across the code:

- one place starts the work
- another place waits for results
- another place handles failure
- another place remembers to cancel unfinished tasks
- diagnostics may not show that the tasks belong together

If the parent operation finishes while a child task is still running, the program has work that outlived the operation that needed it.

## What Did Java Introduce?

Structured Concurrency treats related subtasks as one structured unit of work.

The mental model is:

```text
open a parent scope
    fork related subtasks
    join them as a group
    handle success or failure
close the parent scope
```

The parent scope gives the subtasks a bounded lifetime. Related work is started, joined, cancelled, and observed together.

## Important Terminology

**Parent operation**

The larger operation that needs several related subtasks. A web request handler is a common example.

**Subtask**

A smaller concurrent task started to help complete the parent operation.

**Scope**

The block of code that owns the subtasks. The subtasks should not outlive this block.

**Join**

Waiting for the related subtasks to finish or reach a terminal state.

**Cancellation**

Stopping work that is no longer useful, often because another related subtask failed or because the parent operation has ended.

**Preview feature**

A feature included in a JDK release so developers can try it and provide feedback before it becomes final. Java 23 continued Structured Concurrency as a third preview.

## Why This Module Has Notes Instead Of A Java 23 Example

This repository avoids compiling old preview API shapes as part of the normal build.

Structured Concurrency continued changing across preview rounds. The Java 23 package should teach the historical step, not freeze an old preview API into the main test suite.

The fuller learning module is in Java 25:

```text
src/main/java/net/jrodolfo/java_evolution/java25/structured_concurrency/README.md
```

## What The Test Proves

`StructuredConcurrencyThirdPreviewNotesTest` does not test the preview API.

It protects the educational note by checking that the note explains:

- the parent scope problem
- the structured unit-of-work model
- the Java 23 third-preview status
- the link to the later Java 25 learning module

That is the meaningful test boundary for this explanatory module.

## Realistic Use Case

Imagine this request flow:

```text
GET /orders/123/summary
    -> load order
    -> load customer
    -> load shipment
```

The response is useful only if the related pieces can be assembled. If loading the order fails, the customer and shipment tasks may no longer matter.

Structured Concurrency gives that group of subtasks one parent scope, making the lifetime and failure rules easier to see.

## When Not To Use It

Do not use Structured Concurrency for unrelated background work that should continue independently after the current operation finishes.

Use it when concurrent subtasks are part of one parent operation and should be waited for, cancelled, and observed together.

## Remember This

Structured Concurrency makes related concurrent subtasks follow the lifetime of a parent operation. Java 23 was the third preview step; the Java 25 module is the better place in this repository to study the later API shape.
