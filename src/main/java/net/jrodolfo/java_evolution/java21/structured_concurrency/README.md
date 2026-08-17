# Structured Concurrency Preview

Java 21 introduced Structured Concurrency as a first preview feature in Java Enhancement Proposal (JEP) 453.

This module is explanatory because the Java 21 API was still preview. The later Java 25 module contains the fuller learning material for the current preview workflow.

For recurring acronyms, see the [project glossary](../../../../../../../../docs/glossary.md).

## What Problem Does This Feature Solve?

Concurrent code often starts several related tasks to complete one larger operation.

For example, a request handler may need to:

- load customer data
- load order data
- load shipping data

Those subtasks belong to one parent operation:

```text
handleRequest()
    -> load customer
    -> load order
    -> load shipping
```

The difficult part is not only starting the tasks. The difficult part is managing their lifetime together.

Important questions include:

- who waits for all subtasks?
- what happens if one subtask fails?
- should the other subtasks be cancelled?
- how do we know no child task is still running after the parent operation ends?
- how do logs, debuggers, and monitoring tools show that these tasks belong together?

## How Was This Commonly Done Before?

Java developers often used executors, futures, and manual coordination.

That can work, but the relationship between the parent operation and child tasks can become scattered:

```text
parent starts task A
parent starts task B
parent starts task C
later, maybe joins A
later, maybe handles B failure
later, maybe forgets to cancel C
```

The risk is that a child task outlives the operation that needed it:

```text
parent operation ends
        |
        v
child task still running
```

That makes cleanup, failure handling, cancellation, and debugging harder.

## What Did Java 21 Preview?

Java 21 previewed Structured Concurrency.

The preview idea was to treat related concurrent subtasks as one structured unit of work:

```text
open a scope
    fork related subtasks
    join them as a group
    handle success or failure
close the scope
```

The parent operation owns the subtasks. When the scope ends, the child work has a visible lifetime boundary.

## Important Terminology

**Parent operation**

The larger operation that starts related concurrent work. A web request handler is a common example.

**Subtask**

A smaller concurrent task started to help complete the parent operation.

**Scope**

The lifetime boundary for the related subtasks.

**Fork**

Start a subtask inside the structured scope.

**Join**

Wait for subtasks to finish or reach a terminal state.

**Cancellation**

Stop work that is no longer useful, often because another related task failed.

**Structured unit of work**

A group of related subtasks managed together, with clear rules for waiting, failure, cancellation, cleanup, and observation.

**First preview**

Java 21 was the first release where developers could try Structured Concurrency as a preview feature and give feedback.

## Why This Module Has Notes Instead Of A Java 21 Example

This repository avoids keeping old preview API shapes active across the whole build.

Structured Concurrency continued to evolve after Java 21. The later learning module is here:

```text
src/main/java/net/jrodolfo/java_evolution/java25/structured_concurrency/README.md
```

The Java 21 module exists to explain why the feature entered the platform and what the first preview was trying to solve.

## Preview History In This Repository

```text
Java 21 -> first preview
Java 22 -> second preview
Java 23 -> third preview
Java 24 -> fourth preview
Java 25 -> fifth preview module
```

The later modules explain the continuing refinement. The Java 25 module is the best place to study the later API workflow.

## What The Test Proves

`StructuredConcurrencyPreviewNotesTest` protects this first-preview explanation.

It checks that the notes preserve:

- related subtasks and parent operation lifetime
- the problem with executors, futures, and manual coordination
- the scope/fork/join/close mental model
- cancellation, failure handling, cleanup, and observability benefits
- Java 21 first-preview status
- the Java 25 continuation module

The test does not execute the Java 21 preview API because this project compiles with a current JDK and keeps preview-history modules explanatory.

## Realistic Use Case

Imagine a request for an order summary:

```text
GET /orders/123/summary
    -> load order
    -> load customer
    -> load shipment
```

If loading the order fails, the customer and shipment tasks may no longer be useful. If the request times out, all child work should stop.

Structured Concurrency gives that group of subtasks a parent scope so the lifetime and failure policy are explicit.

## When Not To Use It

Do not use Structured Concurrency for unrelated background work that should continue independently.

For example, a scheduled cleanup job should not usually be a child of one web request.

Use Structured Concurrency when subtasks are part of one parent operation and should be waited for, cancelled, and observed together.

## Remember This

Structured Concurrency was previewed in Java 21 to make related concurrent subtasks follow the lifetime of a parent operation. It is about making concurrency easier to reason about, not just starting tasks more conveniently.
