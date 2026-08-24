# Unsafe Memory-Access Deprecation

Java 23 deprecated the memory-access methods in `sun.misc.Unsafe` for removal.

This module is an executable migration-boundary example. It does not put `sun.misc.Unsafe` in the main compiled source tree. Instead, it generates a tiny child source file, compiles it with `javac -Xlint:removal`, and captures the warnings that learners need to recognize during migration work.

It also runs the generated class with `--sun-misc-unsafe-memory-access=deny` to show how a runtime can reject terminally deprecated memory-access operations. A small `VarHandle` example shows the supported replacement direction for ordinary variable access.

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

## What The Example Demonstrates

`UnsafeMemoryAccessDeprecationExamples` has three executable parts.

First, it generates child source containing selected `sun.misc.Unsafe` memory-access calls:

```text
objectFieldOffset(...)
getInt(...)
```

Those calls are intentionally isolated in generated source. They exist only to capture migration diagnostics.

Second, it compiles that source with:

```bash
javac -Xlint:removal ...
```

The test verifies the compiler warnings:

- `Unsafe is internal proprietary API`
- memory-access methods are `deprecated and marked for removal`

Third, it runs the compiled probe with:

```bash
java --sun-misc-unsafe-memory-access=deny ...
```

The test verifies that the runtime rejects the denied memory-access operation with `UnsupportedOperationException`.

## Replacement Directions

The right replacement depends on why the code used `Unsafe`.

- Use `VarHandle` for supported variable and array access patterns.
- Use the Foreign Function and Memory API for supported off-heap memory and native interoperation.
- Use ordinary JDK APIs when low-level memory access is unnecessary.

The replacement is not a mechanical rename. It is a design decision based on the original use case.

This module includes a tiny `VarHandle` example because it is a good replacement direction for supported variable access.

## Why Unsafe Code Is Isolated

This repository is educational. Showing `Unsafe` as ordinary application code would distract from the Java 23 lesson.

The generated child source is a negative example. It is present so the tests can show real compiler and runtime warnings, not so learners copy it into new code.

The goal is to understand why Java is steering code away from `Unsafe`, how the migration warnings look, and why supported APIs should be preferred.

## Remember This

`sun.misc.Unsafe` memory access was historically useful for some advanced libraries, but it depends on unsupported internals. Java 23 marks those memory-access methods for removal and pushes code toward supported APIs such as `VarHandle` and the Foreign Function and Memory API.
