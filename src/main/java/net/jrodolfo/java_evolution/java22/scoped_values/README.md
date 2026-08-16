# Scoped Values Second Preview

Java 22 continued Scoped Values as a second preview feature.

This module is explanatory because the Java 22 API was still in preview. The full runnable learning module belongs to Java 25, where Scoped Values became final.

## What Problem Does This Feature Solve?

Applications often have contextual data that belongs to the current operation:

- current user
- request ID
- tenant ID
- trace ID
- security context

Sometimes that data is needed by code several calls deeper than the method that first received it.

Without a contextual mechanism, the value must be passed through every intermediate method:

```text
handleRequest(requestId)
    -> processOrder(requestId)
        -> calculatePrice(requestId)
            -> auditOperation(requestId)
```

That can make method signatures noisy when intermediate methods do not use the value themselves.

## How Was This Commonly Done Before?

Java developers often used `ThreadLocal` for contextual data.

A `ThreadLocal` stores a value associated with the current thread. Code running later on the same thread can read that value without receiving it as a method parameter.

That can be useful, but it has risks:

- the value is mutable state attached to a thread
- the value must usually be cleaned up explicitly
- forgetting cleanup can leave stale context on a reused thread
- reasoning about where the value came from can become difficult

These problems matter more when applications use thread pools, request processing, and concurrent code.

## What Did Java Introduce?

Scoped Values provide a way to share immutable contextual data with code deeper in a call chain for a limited period of execution.

The important idea is not "global state." The important idea is:

```text
bind a value
run an operation
automatically remove the binding when the operation finishes
```

Java 22 kept this API in preview so the platform could continue refining it before finalization.

## Important Terminology

**Contextual data**

Data that belongs to the current operation rather than to one object. A request ID is contextual because many methods may need it while one request is being processed.

**Scoped value**

An object that can have a value temporarily bound while a scoped operation runs.

**Binding**

The temporary association between a scoped value and the value it provides.

**Scope**

The region of execution where the binding is visible.

**Bounded dynamic scope**

"Bounded" means the binding has a clear beginning and end. "Dynamic" means the value is visible to code called during the operation, not only to code written inside one lexical block.

**Immutable contextual data**

The value is meant to be read, not changed through the scoped binding.

## Why This Module Has Notes Instead Of A Java 22 Example

This repository avoids keeping old preview API shapes active across the whole build.

The faithful runnable example is in Java 25:

```text
src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/README.md
```

That module explains `ScopedValue.where(...)`, `.call(...)`, `get()`, `isBound()`, and the full binding lifecycle.

The Java 22 module exists to show where Scoped Values were in the release history and why the feature kept evolving.

## Realistic Use Case

Imagine a service that needs the current request ID during auditing:

```text
handleRequest()
    -> processOrder()
        -> calculatePrice()
            -> auditOperation()
```

`auditOperation()` may need the request ID, but `processOrder()` and `calculatePrice()` may not.

Scoped Values are designed for this kind of context: data that belongs to the current operation and should be visible only while that operation runs.

## When Not To Use It

Do not use Scoped Values just to avoid passing one ordinary parameter to one nearby method.

For simple code, a method parameter is clearer:

```java
auditOperation(requestId);
```

Scoped Values become useful when contextual data must be available across deeper call chains or child tasks while still having a clear lifetime.

## Remember This

Scoped Values provide temporary, immutable contextual bindings. Java 22 kept the idea in preview; Java 25 is the place in this repository to study the final runnable API.
