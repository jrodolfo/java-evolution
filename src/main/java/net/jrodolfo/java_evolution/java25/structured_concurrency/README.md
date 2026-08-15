# Structured Concurrency

Java 25 continued Structured Concurrency as a fifth preview in JEP 505.

This is an explanatory learning module. It does not compile a `StructuredTaskScope` example as part of the Maven build because the API is preview in Java 25 and requires preview compiler/runtime options.

## 1. What Problem Does This Feature Solve?

Applications often split one task into several related subtasks.

For example, a request handler may need to fetch:

- user details
- order details
- shipping options

Those operations can run concurrently because they are independent. Conceptually, though, they still belong to one parent operation:

```text
handleRequest()
    -> findUser()
    -> fetchOrder()
    -> findShippingOptions()
```

The problem is not starting concurrent work. Java has had concurrency APIs for a long time.

The problem is coordinating related concurrent work so that:

- all subtasks are joined in one place
- failure is handled consistently
- cancellation is not forgotten
- unfinished work does not leak
- observability tools can show the parent-child relationship

## 2. How Was This Commonly Done Before?

Developers commonly used `ExecutorService` and `Future`:

```java
Future<User> user = executor.submit(this::findUser);
Future<Order> order = executor.submit(this::fetchOrder);

User foundUser = user.get();
Order foundOrder = order.get();
```

That works, but the structure is easy to lose as code grows.

Questions become harder:

- If `findUser()` fails, who cancels `fetchOrder()`?
- If the request times out, who stops the subtasks?
- Where are all the subtasks joined?
- How do diagnostics know those threads belong to one request?

Without a structure, related concurrent work can become scattered across separate variables, callbacks, cleanup blocks, and exception handlers.

## 3. What Did Java Introduce?

Structured Concurrency introduces `StructuredTaskScope`.

The idea is to make concurrent code look more like ordinary structured code:

```text
enter scope
    fork related subtasks
    join them as one group
    handle success or failure
leave scope
```

The scope is the owner of the subtasks. The subtasks should not outlive the scope that created them.

In Java 25, the API is still preview. JEP 505 also changed the API shape so a scope is opened with static factory methods such as `StructuredTaskScope.open()`.

## 4. Terminology In Plain English

Structured concurrency:

A style of concurrent programming where related subtasks have a clear parent scope and are managed as one unit.

Task:

The parent operation. In a server application, this might be handling one request.

Subtask:

A piece of work forked by the parent task, such as fetching a user or loading an order.

Scope:

The block of code that owns the subtasks. The subtasks are forked, joined, and cleaned up inside this block.

Fork:

Start a subtask.

Join:

Wait for the subtasks in the scope to finish and apply the scope's completion policy.

Cancellation:

Stopping related subtasks when they are no longer needed, usually because one failed or the parent task is no longer useful.

Preview API:

An API included for feedback and experimentation. It requires preview flags and may change before becoming final.

## 5. Syntax Shape

A Java 25 structured-concurrency example has this shape:

```java
try (var scope = StructuredTaskScope.open()) {
    StructuredTaskScope.Subtask<User> user = scope.fork(this::findUser);
    StructuredTaskScope.Subtask<Order> order = scope.fork(this::fetchOrder);

    scope.join();

    return new Response(user.get(), order.get());
}
```

The important lifecycle is:

```text
open scope
    fork user subtask
    fork order subtask
    join subtasks as one group
    read successful results
close scope
```

The `try` block matters because the scope is closed at the end. The scope gives the subtasks a bounded lifetime.

## 6. Why This Repository Uses Notes

Structured Concurrency is preview in Java 25.

To compile and run real `StructuredTaskScope` code, Java requires preview options:

```bash
javac --release 25 --enable-preview Main.java
java --enable-preview Main
```

This repository keeps the normal Maven build free of preview compilation. That makes the project easier to run consistently while still documenting the preview feature accurately.

## 7. What The Test Proves

`StructuredConcurrencyFifthPreviewNotesTest` does not test the preview API.

Instead, it protects the learning note. The test verifies that the note explains:

- the scattered-subtask problem
- the common `ExecutorService`/`Future` alternative
- the Java 25 `StructuredTaskScope` idea
- the fifth-preview status and `--enable-preview` requirement

That is the meaningful test boundary for this C2 explanatory module.

## 8. Realistic Use Case

Structured Concurrency is useful in request handling:

```text
handleProductPage()
    -> loadProduct()
    -> loadInventory()
    -> loadRecommendations()
```

Those subtasks can run at the same time. But they belong to one request. If one fails or the request is cancelled, the other subtasks should be handled as part of the same unit of work.

Structured Concurrency makes that relationship explicit in code.

## 9. When Not To Use Structured Concurrency

Do not use Structured Concurrency for unrelated background work that should outlive the current operation.

Do not use it merely because code uses threads. It is most useful when multiple subtasks belong to one parent task and should be joined, cancelled, and observed together.

## 10. Remember This

Structured Concurrency makes concurrent subtasks follow a structured lifetime: open a scope, fork related work, join it as a group, handle the result, and close the scope.
