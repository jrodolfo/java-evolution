# Scoped Values Third Preview

Java 23 continued Scoped Values as a third preview feature.

This module is explanatory because the API was still preview in Java 23. The full runnable learning module belongs to Java 25, where Scoped Values became final.

## What Problem Does This Feature Solve?

Applications often need contextual data during one operation:

- current user
- request ID
- tenant ID
- trace ID
- security context

That data may be needed by code deeper in a call chain or by child tasks.

Passing the value through every method can make signatures noisy when intermediate methods only forward the value.

## How Was This Commonly Done Before?

Java developers often used `ThreadLocal`.

`ThreadLocal` attaches a value to the current thread. Code running later on that thread can read the value without receiving it as an explicit method parameter.

That approach can be useful, but it can also be difficult to reason about:

- the value is mutable thread-associated state
- cleanup is easy to forget
- reused threads can accidentally keep stale context
- the value's lifetime may be less obvious than the operation that needed it

Scoped Values are designed to handle many contextual-data cases with clearer lifetime boundaries.

## Relationship To Java 22

Java 22 continued Scoped Values as a second preview.

For that bridge explanation, read:

```text
src/main/java/net/jrodolfo/java_evolution/java22/scoped_values/README.md
```

Java 23 kept the feature in preview as the platform continued refining the API before finalization.

## What Did Java 23 Continue?

Java 23 continued the same core idea:

```text
bind immutable contextual data
make it visible during a bounded operation
automatically remove the binding afterward
```

The goal is to make contextual data available where it is needed without turning it into broad mutable global-like state.

## Important Terminology

**Contextual data**

Data that belongs to the current operation, such as a request ID or current user.

**Scoped value**

An object that can have a value temporarily bound while an operation runs.

**Binding**

The temporary association between a scoped value and the value it provides.

**Bounded execution scope**

A region of execution with a clear beginning and end. The scoped binding exists only during that region.

**ThreadLocal**

A mechanism that stores a value associated with the current thread.

**Preview feature**

A feature included in a JDK release so developers can try it and provide feedback before it becomes final. Scoped Values continued as a third preview in Java 23 and became final in Java 25.

## Why This Module Has Notes Instead Of A Java 23 Example

This repository avoids keeping old preview API shapes active across the whole build.

The final runnable example is in Java 25:

```text
src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/README.md
```

That module explains `ScopedValue.where(...)`, `.call(...)`, `get()`, `isBound()`, and the binding lifecycle.

The Java 23 module records the third-preview step and points learners to the final API.

## Realistic Use Case

Imagine request handling:

```text
handleRequest()
    -> processOrder()
        -> calculatePrice()
            -> auditOperation()
```

`auditOperation()` may need the request ID, while the intermediate methods may not.

Scoped Values are designed for context that belongs to one operation and should disappear when that operation finishes.

## When Not To Use It

Do not use Scoped Values just to avoid passing a simple parameter to one nearby method.

If a parameter is clear, use the parameter.

Scoped Values are useful when contextual data needs to be available across a deeper execution path with a clear lifetime.

## Remember This

Java 23 kept Scoped Values in preview. The feature provides immutable contextual bindings with bounded lifetime, and Java 25 is where this repository shows the final runnable API.
