# Virtual Thread Synchronization

Java 24 improved how virtual threads behave when they block while executing synchronized code.

This feature is documented as an explanatory module because the important lesson is a runtime behavior change. A small unit test can check the notes, but it cannot faithfully prove scalability or carrier-thread pinning without turning the repository into a benchmark or runtime diagnostic tool.

## What Problem Does This Feature Solve?

Virtual threads, finalized in Java 21, let Java create a very large number of lightweight threads. They are useful when code spends a lot of time waiting, for example:

- waiting for a database query
- waiting for a network response
- waiting for a file operation
- waiting for another service

The goal is that a virtual thread can block without wasting an expensive operating-system thread.

Before the Java 24 improvement, one common situation could weaken that benefit: a virtual thread that blocked while inside a `synchronized` method or block could pin its carrier thread.

## Terminology

- Virtual thread: a lightweight Java thread managed by the JVM.
- Platform thread: a Java thread backed by an operating-system thread.
- Carrier thread: the platform thread that temporarily runs a virtual thread.
- Blocking: waiting for something to complete, such as input/output or a lock.
- `synchronized`: Java's built-in monitor-based locking mechanism.
- Pinning: a situation where a virtual thread cannot be detached from its carrier thread while it is blocked.

## Why Pinning Matters

A virtual thread normally does not need to keep a carrier thread occupied while it waits. The JVM can unmount the virtual thread from the carrier thread and let that carrier run something else.

Conceptually:

```text
virtual thread starts work
  -> reaches blocking operation
  -> JVM can free the carrier thread
  -> carrier thread runs other work
  -> virtual thread resumes later
```

Pinning changes that picture:

```text
virtual thread starts synchronized work
  -> blocks while pinned
  -> carrier thread stays occupied
  -> fewer carrier threads are available for other virtual threads
```

If many virtual threads are pinned at the same time, scalability suffers.

## Why `synchronized` Was Important

Many existing Java libraries use `synchronized`. That code may have been written years before virtual threads existed.

If virtual threads worked well only with code that avoided `synchronized`, then adopting virtual threads would be harder. Developers would have to audit and rewrite more existing code before getting good scalability.

## What Java 24 Improved

Java 24 changed the runtime so virtual threads blocked in synchronized code can avoid pinning their carrier threads in more cases.

The practical result is not a new syntax rule. Existing synchronized code can cooperate better with virtual threads, which makes virtual threads easier to use with older libraries and ordinary Java code.

## What This Notes Class Represents

`VirtualThreadSynchronizationNotes` keeps the learning points small and testable:

- what pinning means
- why synchronized code matters
- what Java 24 improved
- what practical benefit the change provides

The unit test verifies those teaching points. It does not benchmark the JVM or attempt to prove carrier-thread scheduling behavior.

## Remember This

Virtual threads are useful because waiting virtual threads should not waste platform threads. Java 24 made synchronized blocking less likely to pin a carrier thread, so existing synchronized code can scale better with virtual threads.
