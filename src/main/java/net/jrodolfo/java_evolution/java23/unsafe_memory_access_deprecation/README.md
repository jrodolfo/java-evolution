# Unsafe Memory-Access Deprecation

Java 23 deprecated the memory-access methods in `sun.misc.Unsafe` for removal.

This feature is documented as an explanatory module because the important lesson is migration away from unsupported low-level APIs. A runnable example that uses `Unsafe` would teach the wrong habit for this repository.

## What Problem Does This Feature Solve?

Some Java libraries historically needed operations that ordinary Java APIs did not expose directly.

Examples included:

- reading or writing memory with very low-level control
- working around allocation or field-access limitations
- implementing high-performance concurrency or serialization internals
- interoperating with memory outside the normal Java object model

For years, some libraries reached for `sun.misc.Unsafe` to do this kind of work.

## What Is `sun.misc.Unsafe`?

`sun.misc.Unsafe` is an internal JDK class that exposes low-level operations. Its name is intentional: it can bypass normal Java safety checks.

That power made it useful for some advanced libraries, but it also made code harder to maintain and easier to break when the JDK evolved.

## Why Unsupported Internals Are Risky

Code that depends on unsupported JDK internals has a weaker compatibility story than code using standard APIs.

The risk is not only that a method disappears. The larger problem is that the code is coupled to implementation details that ordinary Java applications are not supposed to depend on.

That can make upgrades harder:

```text
library depends on internal memory-access behavior
  -> JDK changes internals
  -> library may need special fixes
  -> application upgrade becomes harder
```

## What Does Deprecated For Removal Mean?

Deprecated means the API should no longer be used.

Deprecated for removal is stronger. It means the API is not only discouraged, but is also a candidate to be removed in a future release.

For learners, the practical meaning is:

```text
do not start new code on this API
plan migration for existing code
prefer supported replacements
```

## What Java 23 Changed

Java 23 deprecated the memory-access methods in `sun.misc.Unsafe` for removal. This continued Java's long-term move away from unsupported internal APIs and toward standard replacement APIs with clearer compatibility contracts.

## Replacement Directions

The right replacement depends on why the code used `Unsafe`.

- Use `VarHandle` for supported variable and array access patterns.
- Use the Foreign Function and Memory API for supported off-heap memory and native interoperation.
- Use ordinary JDK APIs when low-level memory access is unnecessary.

The replacement is not a mechanical rename. It is a design decision based on the original use case.

## Why This Module Has No Unsafe Demo

This repository is educational. Showing learners how to call deprecated low-level memory-access methods would distract from the Java 23 lesson.

The goal is to understand why Java is steering code away from `Unsafe`, not to normalize new usage of it.

## Remember This

`sun.misc.Unsafe` memory access was historically useful for some advanced libraries, but it depends on unsupported internals. Java 23 marks those memory-access methods for removal and pushes code toward supported APIs such as `VarHandle` and the Foreign Function and Memory API.
