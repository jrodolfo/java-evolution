# Scoped Values

Java 25 finalized Scoped Values in JEP 506.

This package is a self-contained learning module for `ScopedValue`. It explains the problem first, then walks through the example and test code.

## 1. What Problem Does This Feature Solve?

Applications often have contextual information that describes the work currently being done:

- current user
- request ID
- tenant ID
- trace ID
- security context

This information is not always part of the main business calculation, but code deeper in the call chain may still need it.

Imagine a request flow like this:

```text
handleRequest(user)
    -> processOrder(user)
        -> calculatePrice(user)
            -> auditOperation(user)
```

Maybe `auditOperation` needs the user so it can write an audit log. The intermediate methods, `processOrder` and `calculatePrice`, may not use the user directly. They still have to accept the parameter and pass it along just so a deeper method can receive it.

That can become cumbersome when many pieces of contextual data are involved. It can also make method signatures noisy because every layer starts carrying values that are not part of its own job.

## 2. How Was This Commonly Done Before?

Java developers have often used `ThreadLocal` for this problem.

A `ThreadLocal` stores a value associated with the current thread. Code running later on the same thread can retrieve that value without receiving it as a method parameter.

At a high level:

```text
current thread -> current user
```

That can be convenient, but it has tradeoffs.

The value stored in a `ThreadLocal` is mutable thread-local state. Code can set it, replace it, and remove it. If the value is not removed correctly, it can remain associated with the thread longer than intended.

This matters because server applications often reuse threads. A thread that handled one request may later handle another request. If old contextual data remains attached to that reused thread, the next operation can accidentally observe stale data.

The issue is not that `ThreadLocal` is always wrong. The issue is that cleanup and ownership are easy to get wrong when contextual data is mutable and tied to a reusable thread.

## 3. What Did Java Introduce?

Scoped Values provide another way to share contextual data with code further down a call stack without explicitly passing the value through every method.

A scoped value lets code temporarily associate a value with a `ScopedValue` object while one operation runs. Code inside that operation can read the value through that object. When the operation finishes, the association disappears automatically.

Java 25 made Scoped Values final.

## 4. Terminology In Plain English

Scoped value:

A Java object that acts like a named access point for contextual data. In this example, `USER` is a scoped value.

Key (mental model):

It is useful to think of the `ScopedValue` object as a key used to retrieve a temporarily bound value. In this example, `USER` is not the username itself. During a scoped operation, a username such as `"Rodolfo"` is bound to `USER`.

Binding:

The temporary association between a `ScopedValue` object and a value. Using the key mental model, this example binds `USER -> "Rodolfo"`.

Scope:

The part of execution where the binding exists. In this example, the scope is the operation passed to `.call(...)`.

Dynamic scope:

The value is available to code that runs during the operation, including methods called by that operation. The source code does not have to pass the value as a parameter. The availability depends on the execution path at runtime.

Bounded lifetime:

The binding has a clear beginning and end. It begins before the scoped operation runs and ends when that operation finishes.

Immutable binding:

Inside one binding, the key points to one value. Code inside the scope can read that value, but it does not replace the binding with another value.

## 5. The Example Code Step By Step

The example starts with this field:

```java
private static final ScopedValue<String> USER = ScopedValue.newInstance();
```

`USER` is not the username itself.

It is better understood as a key or slot. Most of the time, this key has no value. During a scoped operation, Java can temporarily associate a value with it.

The method `userInsideScope` creates that temporary association:

```java
var binding = ScopedValue.where(USER, user);
return binding.call(this::currentUserMessage);
```

`ScopedValue.where(USER, user)` prepares a binding:

```text
USER -> user
```

The binding is not meant to live forever. It becomes active while `.call(...)` runs.

`.call(this::currentUserMessage)` runs the operation. During that operation, `USER` is bound, so code can call:

```java
USER.get()
```

The private method reads the value:

```java
private String currentUserMessage() {
    String currentUser = USER.get();
    return "current user=" + currentUser;
}
```

`USER.get()` works here because the method runs inside the scoped operation.

A useful mental model:

```text
Before call():
    USER is unbound

During call():
    USER -> "Rodolfo"
    USER.get() -> "Rodolfo"

After call():
    USER is unbound again
```

The method `isUserBound` asks whether the key currently has a value:

```java
public boolean isUserBound() {
    return USER.isBound();
}
```

It returns `true` only while code is running inside a scope where `USER` has been bound.

## 6. Walk Through The Unit Test

The test is written as teaching code. It uses intermediate variables so the lifecycle is easy to see.

First, the test checks the state before a binding exists:

```java
boolean userBoundBeforeScope = examples.isUserBound();

assertThat(userBoundBeforeScope)
        .isFalse();
```

This proves that creating the `ScopedValue` key does not store a user name by itself.

Next, the test runs the scoped operation:

```java
String valueInsideScope = examples.userInsideScope("Rodolfo");

assertThat(valueInsideScope)
        .isEqualTo("current user=Rodolfo");
```

This proves that code running inside `.call(...)` can retrieve the bound value with `USER.get()`.

Finally, the test checks the state after the scoped operation has finished:

```java
boolean userBoundAfterScope = examples.isUserBound();

assertThat(userBoundAfterScope)
        .isFalse();
```

This proves that the binding does not leak after the operation ends.

## 7. Lifecycle Diagram

```text
TEST
 |
 | userInsideScope("Rodolfo")
 v
ScopedValue.where(USER, "Rodolfo")
 |
 v
+------------------------------+
| scoped operation             |
|                              |
| USER -> "Rodolfo"            |
| USER.get() -> "Rodolfo"      |
+------------------------------+
 |
 | operation finishes
 v
USER is unbound again
```

The important detail is that the binding belongs to the operation. It is not global state that remains until someone remembers to clean it up.

## 8. Realistic Use Case

Scoped Values are useful when contextual data must be available across a deeper call chain.

For example:

```text
handleRequest()
    -> processOrder()
        -> calculatePrice()
            -> auditOperation()
```

The request handler may bind the current user or request ID once. Then `auditOperation` can read it later without forcing every intermediate method to accept and pass along parameters it does not use.

The example in this repository is intentionally small. Its job is to isolate the API and lifecycle, not to simulate a full web framework.

## 9. When Not To Use Scoped Values

This method by itself would not justify Scoped Values in real production code:

```java
public String userInsideScope(String user)
```

If all the code needs is one value in one method, passing a normal parameter is simpler and clearer.

Scoped Values make more sense when the value is contextual and multiple methods deeper in the execution path may need it.

## 10. Remember This

`ScopedValue.where(KEY, value).call(...)` temporarily associates a value with a key while an operation runs. Code inside that operation can retrieve the value without receiving it as a method parameter. When the operation finishes, the binding automatically disappears.
