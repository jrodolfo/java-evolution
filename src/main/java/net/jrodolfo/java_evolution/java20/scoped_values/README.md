# Scoped Values Incubator

Java 20 introduced Scoped Values as an incubating API in Java Enhancement Proposal (JEP) 429.

This module is explanatory because the Java 20 API required an incubator module and changed before finalization. The final runnable learning module is in Java 25.

For recurring acronyms, see the [project glossary](../../../../../../../../docs/glossary.md).

## What Problem Does This Feature Solve?

Applications often have contextual data that belongs to the current operation:

- request ID
- current user
- tenant ID
- trace ID
- security context

That value may be known near the top of a call chain but needed much deeper:

```text
handleRequest(requestId)
    -> processOrder(requestId)
        -> calculatePrice(requestId)
            -> auditOperation(requestId)
```

Some intermediate methods do not use the value. They only receive it so they can pass it along.

That is sometimes fine. But in larger systems it can create noisy method signatures where every layer carries context that only one deeper layer needs.

## How Was This Commonly Done Before?

Java developers often used `ThreadLocal`.

A `ThreadLocal` stores a value associated with the current thread. Code later on that same thread can read the value without receiving it as a method parameter.

That solves the parameter-noise problem, but it introduces another problem: the context becomes mutable state attached to a thread.

The risks include:

- cleanup is manual
- forgetting cleanup can leave stale values behind
- reused threads can accidentally carry old context
- it can be hard to see where the value was originally set
- child-thread behavior can be surprising

The issue is not that `ThreadLocal` is useless. The issue is that contextual data often needs a clearer lifetime than "whatever is currently associated with this thread."

## What Did Java 20 Introduce?

Java 20 introduced Scoped Values as an incubating API.

The idea was:

```text
bind immutable contextual data
run an operation
make the value available to code called during that operation
remove the binding when the operation finishes
```

That gives the context a bounded lifetime.

## Important Terminology

**Contextual data**

Data that belongs to the current operation rather than to one specific object. A request ID is contextual because many methods may need it while one request is being processed.

**Scoped value**

An object that can have a value temporarily associated with it while a scoped operation runs.

**Binding**

The temporary association between a scoped value and the value available through it.

**Scope**

The region of execution where the binding is visible.

**Bounded lifetime**

The binding has a clear beginning and end. When the scoped operation finishes, the binding disappears.

**Incubator API**

An API placed in an incubator module so developers can try it and provide feedback before the Java platform commits to a final API shape.

## Why This Module Has Notes Instead Of Java 20 Code

The Java 20 API was incubating.

Using it directly would require enabling an incubator module and preserving an old API shape that changed later. That would make this repository harder to run and would teach learners an API form that is no longer the final one.

The faithful runnable example belongs here:

```text
src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/README.md
```

This Java 20 module explains the origin: why Scoped Values entered the platform and what problem the incubator API was exploring.

## Evolution In This Repository

```text
Java 20 -> incubator
Java 21 -> first preview
Java 22 -> second preview
Java 23 -> third preview
Java 24 -> fourth preview
Java 25 -> final runnable example
```

The Java 25 module is the best place to study the final API workflow.

## What The Test Proves

`ScopedValuesIncubatorNotesTest` protects the Java 20 explanation.

It checks that the notes preserve:

- the contextual-data problem
- why passing context through many parameters can become noisy
- `ThreadLocal` as the older common approach
- cleanup and stale-context risks
- immutable contextual data with bounded lifetime
- Java 20 incubator status
- the Java 25 final runnable destination

The test does not execute the Java 20 incubator API because this project compiles with a current JDK and avoids old incubator-module setup.

## Realistic Use Case

Imagine request processing with auditing:

```text
handleRequest()
    -> processOrder()
        -> calculatePrice()
            -> auditOperation()
```

`auditOperation()` may need a request ID, but the middle methods may not.

Scoped Values are designed for this kind of immutable operation context: data that should be available during one operation and disappear afterward.

## When Not To Use It

Do not use Scoped Values just to avoid one ordinary parameter.

For a nearby method call, this is usually clearer:

```java
auditOperation(requestId);
```

Scoped Values become useful when contextual data must cross a deeper call chain or related child tasks while still having a clear lifetime.

## Remember This

Java 20 introduced Scoped Values as an incubating attempt to replace many fragile `ThreadLocal` context patterns with immutable context that has a clear lifetime.
