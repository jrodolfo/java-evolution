# Stable Values

Java 25 previewed Stable Values in JEP 502.

This module is an executable preview example. The main Maven build does not compile `StableValue` directly. Instead, `StableValuesPreviewExamples` writes a small child source file, compiles it with `javac --enable-preview --release 25`, and runs it with `java --enable-preview`.

That keeps the repository build stable while still demonstrating the real Java 25 preview API.

## 1. What Problem Does This Feature Solve?

Applications often have values that are expensive to create and not always needed immediately:

- loggers
- configuration objects
- database clients
- parsers
- lookup tables
- service clients

Eager initialization creates the value as soon as the object or class is created. That is simple, but it can slow startup when many values are created before the application actually needs them.

Lazy initialization delays creation until the first use:

```text
first call -> create the value
later calls -> reuse the same value
```

The tricky part is that the value should become effectively immutable after it is created. You want delayed initialization, but you do not want ongoing mutability.

## 2. How Was This Commonly Done Before?

Before Stable Values, Java developers commonly used patterns such as:

- a nullable field initialized on first use
- `synchronized` access around initialization
- double-checked locking with `volatile`
- a `Supplier` that memoizes a value

For example, conceptually:

```java
private Logger logger;

Logger logger() {
    if (logger == null) {
        logger = Logger.create(OrderController.class);
    }
    return logger;
}
```

That shape has two problems for learning and maintenance:

- the field remains visibly mutable
- thread safety must be handled carefully if multiple threads can call the method

Double-checked locking can solve part of the problem, but it adds ceremony and is easy to get wrong.

## 3. What Did Java Introduce?

Java 25 previewed `StableValue`.

A stable value is a holder for content that can be initialized at most once. After the content is set, it is stable. The Java Virtual Machine (JVM) can treat it more like final data for optimization purposes, while the program still gets the flexibility of initializing it later.

The mental model is:

```text
created but unset
        |
        | first successful initialization
        v
set forever
```

This is deferred immutability: the value does not need to be ready at construction time, but after it is initialized it does not keep changing.

## 4. Terminology In Plain English

Stable value:

An object that holds content which can be initialized at most once.

Unset:

The stable value exists, but it does not hold content yet.

Set:

The stable value now holds content. Once set, the content does not change.

Deferred immutability:

The program delays initialization until the value is needed, but still gets an immutable result after initialization.

Content:

The object or primitive value stored inside the stable value.

Preview API:

An API included in Java for feedback and experimentation. It is not final yet, so using it requires preview flags and its shape may change in a later Java release.

## 5. Syntax Shape

A Stable Value example is naturally written with the preview API:

```java
private final StableValue<Logger> logger = StableValue.of();

Logger logger() {
    return logger.orElseSet(() -> Logger.create(OrderController.class));
}
```

The important part is `orElseSet(...)`:

```text
if content is already set:
    return the existing content

if content is not set:
    compute the content once
    store it
    return it
```

The supplier passed to `orElseSet(...)` is not supposed to run on every call. It provides the initial content only if the stable value has not already been set.

## 6. How This Repository Runs The Preview API

Stable Values are preview in Java 25.

To compile and run real `StableValue` code, Java requires preview options, such as:

```bash
javac --release 25 --enable-preview Main.java
java --enable-preview Main
```

This Maven project intentionally avoids enabling preview features globally. Enabling them for one feature would affect the whole build and make the repository harder to use as a stable study project.

That is why this module uses a child process. The repository class itself remains ordinary Java 25-compatible code, while the generated child program imports and executes `java.lang.StableValue`.

## 7. What The Test Proves

`StableValuesPreviewExamplesTest` tests the Java preview API through a child compiler and child JVM.

The test verifies that:

- `orElseSet(...)` computes content once and returns the same content later
- `trySet(...)` fails after content is already set
- `orElseThrow()` returns initialized content
- `StableValue.supplier(...)` memoizes a supplier result
- `StableValue.list(...)` computes each accessed element lazily and once
- `StableValue.map(...)` computes each accessed key lazily and once

The test does not prove JVM optimization behavior. It proves the API semantics that learners can observe directly.

## 8. Realistic Use Case

Imagine an application controller that may need a logger, parser, or service client, but not during startup.

With Stable Values, the controller can keep a stable holder:

```text
controller created
logger not created yet

first request that needs logging
logger is created once

later requests
same logger is reused
```

The value is not eagerly created, but after initialization it is stable.

## 9. When Not To Use Stable Values

Do not use Stable Values for ordinary data that is already available during construction. A normal `final` field is simpler and clearer.

Do not use Stable Values for data that is expected to change over time. Stable Values are about values initialized at most once, not general mutable state.

## 10. Remember This

Stable Values are about lazy initialization without ongoing mutability: create the holder now, initialize its content later, and then treat the content as stable.
