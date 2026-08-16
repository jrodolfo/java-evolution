# Structured Concurrency Second Preview

Java 22 continued Structured Concurrency as a second preview feature.

This module is explanatory because the Java 22 API was still in preview. The fuller learning module belongs to Java 25, where Structured Concurrency was still previewed but documented in this repository with more complete teaching material.

## What Problem Does This Feature Solve?

Concurrent code often starts several related tasks to complete one larger operation.

For example, a request handler might need to:

- load customer data
- load order data
- load shipping options

Those subtasks are related because they all belong to the same parent request.

Without structure, concurrent tasks can become detached from the operation that started them:

```text
handleRequest()
    -> start customer task
    -> start order task
    -> start shipping task
    -> later, try to collect the results
```

The difficult questions are:

- who waits for every task?
- what happens if one task fails?
- should the other tasks be cancelled?
- how do we know all child work has finished?
- how do monitoring tools understand the relationship between the tasks?

## How Was This Commonly Done Before?

Developers often used executors, futures, and manual coordination.

That works, but the relationship between the parent operation and child tasks may be spread across several places in the code.

It is easy to accidentally create work that outlives the operation that needed it.

```text
parent request finishes
        |
        v
some child task is still running
```

That makes cancellation, cleanup, failure handling, and debugging harder.

## What Did Java Introduce?

Structured Concurrency treats related concurrent subtasks as one structured unit of work.

The mental model is:

```text
open a scope
    fork related subtasks
    join them as a group
    handle success or failure
close the scope
```

The parent operation owns the subtasks. When the parent scope ends, the related child work has a clear lifetime boundary.

## Important Terminology

**Parent operation**

The larger operation that starts related concurrent work. A web request handler is a common example.

**Subtask**

A smaller concurrent task started to help complete the parent operation.

**Structured unit of work**

A group of related subtasks managed together, with clear rules for waiting, failure, cancellation, and cleanup.

**Join**

Waiting for subtasks to reach a result or terminal state.

**Cancellation**

Stopping work that is no longer useful, often because another related task failed or the parent operation no longer needs the result.

**Preview feature**

A feature included in a JDK release so developers can try it and provide feedback before it becomes final. Java 22 continued Structured Concurrency as a second preview.

## Why This Module Has Notes Instead Of A Java 22 Example

This repository avoids keeping old preview API shapes active across the whole build.

The later learning module is in Java 25:

```text
src/main/java/net/jrodolfo/java_evolution/java25/structured_concurrency/README.md
```

That module explains the later preview API and the workflow in more detail.

The Java 22 module exists to show the second-preview step in the feature's evolution and to explain the problem that Structured Concurrency is trying to solve.

## Realistic Use Case

Imagine this request flow:

```text
GET /orders/123/summary
    -> load order
    -> load customer
    -> load shipment
```

The response is useful only if the related pieces can be assembled. If loading the order fails, the customer and shipment tasks may no longer matter.

Structured Concurrency gives that group of subtasks an explicit parent scope, making the lifetime and failure rules easier to see.

## When Not To Use It

Do not use Structured Concurrency for unrelated background work that should continue independently after the current operation finishes.

For example, a long-running scheduled cleanup job should not usually be a child of one web request.

Use Structured Concurrency when concurrent subtasks are part of one parent operation and should be waited for, cancelled, and observed together.

## Remember This

Structured Concurrency makes related concurrent subtasks follow the lifetime of a parent operation. Java 22 kept the idea in preview; the later Java 25 module is the place in this repository to study the API workflow in more detail.
