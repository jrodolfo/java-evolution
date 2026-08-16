# Scoped Values Fourth Preview

Java 24 continued Scoped Values as a fourth preview feature in JEP 487.

This module is explanatory. It records the Java 24 step in the feature's evolution and points to the Java 25 module for the final runnable example.

## What Problem Does This Feature Solve?

Applications often have contextual information that describes the current operation:

- current user
- request ID
- tenant ID
- trace ID
- security context

That information may be needed deep in the call chain even when intermediate methods do not use it directly.

For example:

```text
handleRequest(user, requestId)
    -> processOrder(user, requestId)
        -> calculatePrice(user, requestId)
            -> auditOperation(user, requestId)
```

`auditOperation` may need the user and request ID for logging or security checks. The intermediate methods may only pass those values along.

As the number of contextual values grows, method signatures can become noisy and harder to read.

## How Was This Commonly Done Before?

Java developers often used `ThreadLocal` for this kind of context.

A `ThreadLocal` stores a value associated with the current thread. Code running later on that same thread can read the value without receiving it as a method parameter.

That can be convenient, but it has a cost: the value is mutable thread-local state.

If code forgets to remove the value, it can remain attached to a reused server thread longer than intended.

```text
request A stores user A in ThreadLocal
        |
        v
thread returns to pool
        |
        v
request B accidentally observes stale context
```

The problem is not that `ThreadLocal` is always wrong. The problem is that ownership, cleanup, and mutation can become difficult to reason about.

## What Did Java Introduce?

Scoped Values provide another way to share contextual data with code further down the call stack.

The mental model is:

```text
bind a value for one operation
    code inside the operation can read it
operation finishes
    the binding disappears automatically
```

The value is immutable within the binding, and the binding has a bounded lifetime.

## Important Terminology

**Scoped value**

A Java object that code can use to access contextual data while a binding is active.

**Binding**

The temporary association between a scoped value and a concrete value.

**Scope**

The part of execution where the binding exists.

**Dynamic scope**

The value is available to code that runs during the operation, including methods called by that operation. The source code does not need to pass the value as a parameter.

**Bounded lifetime**

The binding has a clear start and end. It does not remain active after the scoped operation finishes.

**Immutable context**

Code inside the scope can read the bound value, but it does not replace that binding with another value.

**Preview feature**

A feature included in a JDK release for feedback before finalization. Java 24 continued Scoped Values as a fourth preview.

## Why This Module Has Notes Instead Of A Java 24 Example

Scoped Values became final in Java 25. This repository keeps the final runnable learning example there:

```text
src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/README.md
```

The Java 24 package exists to explain the fourth-preview step and help the learner follow the feature's evolution without duplicating the final Java 25 example.

## What The Test Proves

`ScopedValuesFourthPreviewNotesTest` does not test the final Java 25 API.

It protects the educational note by checking that the note explains:

- the contextual-data problem
- the older `ThreadLocal` approach
- immutable bounded scope
- Java 24 fourth-preview status
- the Java 25 final module as the next learning step

That is the useful test boundary for this bridge module.

## Realistic Use Case

Scoped Values are useful when request context needs to be available deep in the call chain:

```text
handleRequest()
    -> processOrder()
        -> calculatePrice()
            -> auditOperation()
```

The audit layer may need the current user or trace ID, even if the intermediate business methods do not.

Scoped Values let the contextual value be visible during the operation without forcing every method to carry extra parameters.

## When Not To Use It

Do not use Scoped Values just to avoid passing one ordinary argument to one nearby method.

For simple code, a normal method parameter is clearer.

Scoped Values are useful when contextual data naturally belongs to an operation and many deeper calls may need to read it.

## Remember This

Scoped Values temporarily bind immutable contextual data for one bounded operation. Java 24 was the fourth preview; Java 25 is where this repository demonstrates the final runnable API.
