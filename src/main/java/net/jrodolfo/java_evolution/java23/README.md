# Java 23

Java 23 is mostly a preview and refinement release. That makes it valuable for learning because it shows how Java features mature: ideas appear as previews, receive feedback, and may become final in later releases.

This package uses notes classes for most Java 23 features because many of them were preview APIs, runtime behavior, documentation tooling, or VM features rather than small stable APIs that fit cleanly into unit tests.

## Markdown Documentation Comments

Traditional JavaDoc comments often require HTML tags for lists, code blocks, and formatting. That works, but it can make source comments noisy.

Java 23 introduced Markdown documentation comments so API docs can be written in a lighter format that is easier to read directly in source code.

Example: `MarkdownDocumentationCommentsNotes`

Test: `MarkdownDocumentationCommentsNotesTest`

## Primitive Patterns Preview

Pattern matching had been growing around reference types: `instanceof`, record patterns, and switch patterns. Primitive values were still less integrated with that model.

Java 23 previewed primitive types in patterns, `instanceof`, and `switch`, moving Java toward a more uniform pattern-matching model.

This feature continued as a second preview in Java 24 and a third preview in Java 25.

Example: `PrimitivePatternsPreviewNotes`

Test: `PrimitivePatternsPreviewNotesTest`

## Module Import Declarations Preview

Java has package imports, but a large API often spans many packages. After Java 9 introduced modules, it became natural to ask whether code could import from a module boundary instead of importing packages one by one.

Java 23 previewed module import declarations. The feature became final in Java 25.

Continue with `ModuleImportDeclarationsSecondPreviewNotes` in Java 24 and `ModuleImportDeclarationsNotes` in Java 25.

Example: `ModuleImportDeclarationsPreviewNotes`

Test: `ModuleImportDeclarationsPreviewNotesTest`

## Flexible Constructor Bodies Second Preview

Before this feature line, explicit constructor invocation had to appear first. That made argument validation before `super(...)` or `this(...)` awkward.

Java 23 refined flexible constructor bodies in a second preview. The goal is safer and clearer constructor code while still preventing use of the object before it is initialized.

Continue with `FlexibleConstructorBodiesThirdPreviewNotes` in Java 24 and `FlexibleConstructorBodiesExamples` in Java 25.

Example: `FlexibleConstructorBodiesSecondPreviewNotes`

Test: `FlexibleConstructorBodiesSecondPreviewNotesTest`

## Stream Gatherers Second Preview

Streams had many built-in operations, but custom intermediate operations were hard to express.

Java 23 continued Stream Gatherers as a second preview. Gatherers became final in Java 24.

Continue with `StreamGatherersExamples` in Java 24 for the final runnable example.

Example: `StreamGatherersSecondPreviewNotes`

Test: `StreamGatherersSecondPreviewNotesTest`

## Class-File API Second Preview

Bytecode tools need to read, generate, and transform `.class` files. Before the Class-File API, this usually required third-party libraries.

Java 23 continued the standard Class-File API as a second preview. The API became final in Java 24.

Continue with the Java 24 [`class_file`](../java24/class_file/README.md) module for the final executable example.

Example: `ClassFileApiSecondPreviewNotes`

Test: `ClassFileApiSecondPreviewNotesTest`

## Scoped Values Third Preview

Scoped values provide immutable context within a bounded execution scope, addressing many use cases where `ThreadLocal` can be too broad or too easy to leak.

Java 23 kept scoped values in preview. The feature became final in Java 25.

Continue with the Java 25 [`scoped_values`](../java25/scoped_values/README.md) module for the final learning example.

Example: `ScopedValuesThirdPreviewNotes`

Test: `ScopedValuesThirdPreviewNotesTest`

## Structured Concurrency Third Preview

Structured concurrency treats related concurrent subtasks as one unit of work, making cancellation and failure handling easier to reason about.

Java 23 continued this feature as a third preview.

Continue with the Java 25 [`structured_concurrency`](../java25/structured_concurrency/README.md) module because this feature is still preview there.

Example: `StructuredConcurrencyThirdPreviewNotes`

Test: `StructuredConcurrencyThirdPreviewNotesTest`

## Unsafe Memory-Access Deprecation

Some libraries historically used `sun.misc.Unsafe` for low-level memory access. Those APIs are unsupported and make Java upgrades harder.

Java 23 deprecated memory-access methods in `sun.misc.Unsafe`, continuing the move toward supported APIs such as `VarHandle` and the Foreign Function and Memory API.

Example: `UnsafeMemoryAccessDeprecationNotes`

Test: `UnsafeMemoryAccessDeprecationNotesTest`

## ZGC Generational Mode

Garbage collectors can often perform better when they treat young and old objects differently. Java 23 made ZGC use generational mode by default.

This is a runtime behavior topic rather than a small source-code feature, so this repository keeps it as notes.

Example: `ZgcGenerationalModeNotes`

Test: `ZgcGenerationalModeNotesTest`

## How To Read This Package

Start with `MarkdownDocumentationCommentsNotes`, then read the preview and runtime notes for primitive patterns, module imports, flexible constructor bodies, stream gatherers, the Class-File API, scoped values, structured concurrency, unsafe memory-access deprecation, and ZGC generational mode.

Run the focused notes tests:

```bash
mvn -Dtest=MarkdownDocumentationCommentsNotesTest,PrimitivePatternsPreviewNotesTest,ModuleImportDeclarationsPreviewNotesTest test
mvn -Dtest=FlexibleConstructorBodiesSecondPreviewNotesTest,StreamGatherersSecondPreviewNotesTest,ClassFileApiSecondPreviewNotesTest test
mvn -Dtest=ScopedValuesThirdPreviewNotesTest,StructuredConcurrencyThirdPreviewNotesTest,UnsafeMemoryAccessDeprecationNotesTest,ZgcGenerationalModeNotesTest test
```

This package is notes-heavy because many Java 23 topics are preview, runtime, or tooling features. After this package, continue with Java 24 to see stream gatherers and the Class-File API reach final status.

## References

- [OpenJDK JDK 23 project](https://openjdk.org/projects/jdk/23/)
- [JEP 467: Markdown Documentation Comments](https://openjdk.org/jeps/467)
- [JEP 455: Primitive Types in Patterns, instanceof, and switch](https://openjdk.org/jeps/455)
- [JEP 476: Module Import Declarations](https://openjdk.org/jeps/476)
- [JEP 482: Flexible Constructor Bodies](https://openjdk.org/jeps/482)
- [JEP 473: Stream Gatherers](https://openjdk.org/jeps/473)
- [JEP 466: Class-File API](https://openjdk.org/jeps/466)
- [JEP 481: Scoped Values](https://openjdk.org/jeps/481)
- [JEP 480: Structured Concurrency](https://openjdk.org/jeps/480)
- [JEP 471: Deprecate the Memory-Access Methods in sun.misc.Unsafe for Removal](https://openjdk.org/jeps/471)
- [JEP 474: ZGC: Generational Mode by Default](https://openjdk.org/jeps/474)
