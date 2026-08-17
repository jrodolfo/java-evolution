# Structured Concurrency Incubator

Java 19 introduced Structured Concurrency as an incubating API in Java Enhancement Proposal (JEP) 428.

The class in this module is still named `StructuredConcurrencyPreviewNotes` to preserve existing repository navigation, but the official Java 19 status was incubator, not preview.

This module is explanatory because the Java 19 API required an incubator module and changed in later releases. The later Java 25 module contains the current preview learning material.

For recurring acronyms, see the [project glossary](../../../../../../../../docs/glossary.md).

## What Problem Does This Feature Solve?

Concurrent code often starts several related tasks to complete one larger operation.

For example, a request handler may need to:

- load customer data
- load order data
- load shipping data

Those tasks are separate, but they belong to one parent operation:

```text
handleRequest()
    -> load customer
    -> load order
    -> load shipping
```

The parent operation needs clear answers to practical questions:

- who waits for all child tasks?
- what happens if one child task fails?
- should the other child tasks be cancelled?
- how do we know no child task is still running after the parent operation ends?
- how do logs and debuggers show that these tasks are related?

## How Was This Commonly Done Before?

Java developers commonly used `ExecutorService`, `Future`, and manual coordination.

That can work, but the coordination can become scattered:

```text
start task A
start task B
start task C
later, wait for A
later, handle B failure
maybe remember to cancel C
maybe remember to shut down the executor
```

The risk is that child work outlives the operation that needed it:

```text
parent operation ends
        |
        v
child task still running
```

That makes failure handling, cancellation, cleanup, and observability harder to reason about.

## What Did Java 19 Introduce?

Java 19 introduced Structured Concurrency as an incubating API.

The idea was:

```text
open a scope for one parent operation
    fork related subtasks inside that scope
    join them as a group
    handle success or failure as one unit
close the scope
```

The parent owns the child tasks. The scope gives the child tasks a visible lifetime boundary.

## Important Terminology

**Parent operation**

The larger operation that starts related concurrent work. A web request handler is a common example.

**Child task**

A smaller concurrent task started to help complete the parent operation.

**Scope**

The lifetime boundary for the related child tasks.

**Fork**

Start a child task inside the structured scope.

**Join**

Wait for the child tasks to finish or reach a terminal state.

**Cancellation**

Stop work that is no longer useful, often because another related task failed.

**Structured unit of work**

A group of related child tasks managed together, with clear rules for waiting, failure, cancellation, cleanup, and observation.

**Incubator API**

An API placed in an incubator module so developers can try it and provide feedback before the Java platform commits to a final API shape.

## Why This Module Has Notes Instead Of Java 19 Code

The Java 19 API was incubating.

Using it directly would require enabling an incubator module and preserving an old API shape. That would make this repository harder to run and would distract from the learning goal.

The next historical module is here:

```text
src/main/java/net/jrodolfo/java_evolution/java20/structured_concurrency/README.md
```

The later learning module is here:

```text
src/main/java/net/jrodolfo/java_evolution/java25/structured_concurrency/README.md
```

This Java 19 module explains the first incubator step: what problem the feature was addressing and why the platform started exploring it.

## Evolution In This Repository

```text
Java 19 -> first incubator
Java 20 -> second incubator
Java 21 -> first preview
Java 22 -> second preview
Java 23 -> third preview
Java 24 -> fourth preview
Java 25 -> fifth preview module
```

The Java 25 module is the best place to study the later API workflow.

## What The Test Proves

`StructuredConcurrencyPreviewNotesTest` protects the Java 19 explanation.

It checks that the notes preserve:

- the parent-child task lifetime problem
- the older `ExecutorService` and `Future` coordination model
- the structured unit of work idea
- cancellation, failure handling, cleanup, and observability benefits
- official Java 19 incubator status
- why this project avoids old incubator APIs
- the later Java 20 and Java 25 modules

The test does not execute the Java 19 incubator API because this project compiles with a current JDK and avoids old incubator-module setup.

## Realistic Use Case

Imagine a request for an order summary:

```text
GET /orders/123/summary
    -> load order
    -> load customer
    -> load shipment
```

If loading the order fails, the customer and shipment tasks may no longer be useful. If the request times out, all child tasks should stop.

Structured Concurrency gives that group of child tasks a parent scope so lifetime and failure policy are explicit.

## When Not To Use It

Do not use Structured Concurrency for unrelated background work that should continue independently.

For example, a scheduled cleanup job should not usually be a child of one web request.

Use Structured Concurrency when concurrent subtasks are part of one parent operation and should be waited for, cancelled, and observed together.

## Remember This

Java 19 introduced Structured Concurrency as an incubating API so Java could explore a clearer parent-child structure for related concurrent tasks before moving the idea through later incubator and preview rounds.
