# Structured Concurrency Fourth Preview

Java 24 continued Structured Concurrency as a fourth preview feature in JEP 499.

This module is explanatory. It records the Java 24 step in the preview history without adding preview API compilation to the normal Maven build.

## What Problem Does This Feature Solve?

Concurrent code often starts several tasks that belong to one larger operation.

For example, a request handler might need to:

- load account data
- load permissions
- load recent activity

Those subtasks can run concurrently, but they still belong to the same parent request.

Without structure, the relationship can become hard to see:

```text
handleRequest()
    -> start account task
    -> start permissions task
    -> start activity task
    -> later, wait for results
    -> later, handle failure
    -> later, cancel work that is no longer useful
```

The hard part is not launching concurrent work. The hard part is keeping related work under one clear owner.

## How Was This Commonly Done Before?

Developers commonly used executors, futures, and manual coordination.

That style can work, but it leaves important questions for the developer to answer correctly:

- where are all subtasks joined?
- who cancels sibling tasks when one fails?
- what prevents a child task from outliving the parent operation?
- how do diagnostics show that the child tasks belong to one request?

If those rules are spread across separate methods and cleanup blocks, the concurrent code becomes harder to read and harder to debug.

## What Did Java Introduce?

Structured Concurrency treats related concurrent subtasks as one structured unit of work.

The mental model is:

```text
open a parent scope
    fork related subtasks
    join them as a group
    handle success or failure
close the parent scope
```

The scope owns the subtasks. Related work has a bounded lifetime instead of drifting away as detached background work.

## Important Terminology

**Parent operation**

The larger operation that starts related concurrent work. A server request is a typical example.

**Subtask**

A smaller task forked to help complete the parent operation.

**Scope**

The block of code that owns the subtasks. The subtasks are joined and cleaned up within this boundary.

**Join**

Waiting for subtasks to finish or reach a terminal state.

**Cancellation**

Stopping related work that no longer matters, often because another subtask failed or because the parent operation has ended.

**Preview feature**

A feature included in a JDK release for feedback before finalization. Java 24 continued Structured Concurrency as a fourth preview.

## Why This Module Has Notes Instead Of A Java 24 Example

This repository avoids compiling old preview API shapes as part of the normal build.

Structured Concurrency was still preview in Java 24 and continued into Java 25 as a fifth preview. The Java 24 package should teach the historical step, not make the build depend on a preview API shape that was still changing.

The fuller learning module is in Java 25:

```text
src/main/java/net/jrodolfo/java_evolution/java25/structured_concurrency/README.md
```

## What The Test Proves

`StructuredConcurrencyFourthPreviewNotesTest` does not test the preview API.

It protects the educational note by checking that the note explains:

- the parent scope problem
- the structured unit-of-work model
- the Java 24 fourth-preview status
- the link to the later Java 25 learning module

That is the useful test boundary for this explanatory module.

## Realistic Use Case

Imagine this request flow:

```text
GET /accounts/123/dashboard
    -> load account
    -> load permissions
    -> load recent activity
```

The subtasks are useful only as part of the dashboard request. If one important piece fails or the request is cancelled, the remaining subtasks should be handled as part of the same parent operation.

Structured Concurrency makes that relationship explicit.

## When Not To Use It

Do not use Structured Concurrency for unrelated background work that should keep running after the current operation finishes.

Use it when concurrent subtasks belong to one parent operation and should be joined, cancelled, and observed together.

## Remember This

Structured Concurrency gives related concurrent subtasks a parent scope. Java 24 was the fourth preview step; the Java 25 module is the better place in this repository to study the later API shape.
