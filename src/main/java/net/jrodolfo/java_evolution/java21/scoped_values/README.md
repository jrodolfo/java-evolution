# Scoped Values Preview

Java 21 introduced Scoped Values as a first preview feature in Java Enhancement Proposal (JEP) 446.

This module is explanatory because the Java 21 API was still preview. The final runnable learning module belongs to Java 25, where Scoped Values became final.

For recurring acronyms, see the [project glossary](../../../../../../../../docs/glossary.md).

## What Problem Does This Feature Solve?

Applications often have contextual data that belongs to the current operation:

- current user
- request ID
- tenant ID
- trace ID
- security context

That data may be received near the top of a call chain but needed much deeper:

```text
handleRequest(requestId)
    -> processOrder(requestId)
        -> calculatePrice(requestId)
            -> auditOperation(requestId)
```

Some intermediate methods may not use the value themselves. They only carry it so a deeper method can use it.

That creates a design question:

```text
How can contextual data be available to deeper code without forcing every method to accept it as a parameter?
```

## How Was This Commonly Done Before?

Java developers often used `ThreadLocal`.

A `ThreadLocal` stores a value associated with the current thread. Code running later on that thread can read the value without receiving it as a parameter.

That can be useful, but it creates risks:

- the value is mutable thread-associated state
- cleanup is usually manual
- forgetting cleanup can leave stale context behind
- stale context is especially dangerous when threads are reused
- it can become difficult to see where the value came from

The issue is not that `ThreadLocal` is always wrong. The issue is that contextual data often needs a clearer lifetime than "whatever remains associated with this thread."

## What Did Java 21 Preview?

Java 21 previewed Scoped Values.

The preview idea was:

```text
bind immutable contextual data
run an operation
make the value visible to code called during that operation
remove the binding automatically when the operation finishes
```

This gives contextual data a bounded lifetime.

## Important Terminology

**Contextual data**

Data that belongs to the current operation rather than to one specific object. A request ID is contextual because many methods may need it while one request is being processed.

**Scoped value**

An object that can have a value temporarily associated with it while a scoped operation runs.

**Binding**

The temporary association between a scoped value and the value available through it.

**Scope**

The region of execution where the binding is visible.

**Dynamic scope**

The value is visible to code called during the operation, not only to code physically written inside one source-code block.

**Bounded lifetime**

The binding has a clear beginning and end. When the scoped operation finishes, the binding disappears.

**First preview**

Java 21 was the first release where developers could try Scoped Values as a preview feature and give feedback before finalization.

## Why This Module Has Notes Instead Of A Java 21 Example

This repository avoids keeping old preview API shapes active across the whole build.

Scoped Values evolved after Java 21 and became final in Java 25. The faithful runnable example is there:

```text
src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/README.md
```

The Java 21 module exists to explain why the feature entered the platform and what the first preview was trying to solve.

## Preview History In This Repository

```text
Java 21 -> first preview
Java 22 -> second preview
Java 23 -> third preview
Java 24 -> fourth preview
Java 25 -> final runnable example
```

The later preview modules explain the continuing refinement. The Java 25 module is the best place to study the final API workflow.

## What The Test Proves

`ScopedValuesPreviewNotesTest` protects this first-preview explanation.

It checks that the notes preserve:

- the contextual-data problem
- the `ThreadLocal` motivation
- cleanup and stale-context risks
- immutable contextual data
- bounded dynamic scope
- Java 21 first-preview status
- the Java 25 final runnable destination

The test does not execute the Java 21 preview API because this project compiles with a current JDK and keeps preview-history modules explanatory.

## Realistic Use Case

Imagine request processing with auditing:

```text
handleRequest()
    -> processOrder()
        -> calculatePrice()
            -> auditOperation()
```

`auditOperation()` may need the request ID, but the middle methods may not.

Scoped Values are designed for this kind of immutable context: data that belongs to one operation and should be visible only while that operation runs.

## When Not To Use It

Do not use Scoped Values just to avoid one ordinary method parameter.

For nearby calls, this is usually clearer:

```java
auditOperation(requestId);
```

Scoped Values become useful when contextual data must be available across a deeper call chain or related child tasks while still having a clear lifetime.

## Remember This

Scoped Values were previewed in Java 21 to provide immutable contextual data with a bounded lifetime. They are easier to reason about than mutable thread-associated context when the value belongs to one operation.
