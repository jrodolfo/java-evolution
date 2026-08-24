# Java 23

Released: September 2024 as Java SE 23.

Java 23 is mostly a preview and refinement release. That makes it valuable for learning because it shows how Java features mature: ideas appear as previews, receive feedback, and may become final in later releases.

This package uses notes classes for most Java 23 features because many of them were preview APIs or evolving APIs that do not fit cleanly into the JDK 25 build. Markdown documentation comments are represented with an executable tooling example, Unsafe memory-access deprecation is represented as an executable migration-boundary example, and ZGC generational mode is represented as an executable runtime-boundary example.

## Markdown Documentation Comments

Traditional JavaDoc comments often require HTML tags for lists, code blocks, and formatting. That works, but it can make source comments noisy.

Java 23 introduced Markdown documentation comments so API docs can be written in a lighter format that is easier to read directly in source code.

The mental model is simple: source comments can move from HTML-heavy shapes such as `<ul>` and `<pre>` toward Markdown lists and code fences, while JavaDoc tooling still produces the generated API documentation.

Example module: [`markdown_documentation_comments`](markdown_documentation_comments/README.md)

Test: `MarkdownDocumentationCommentsExamplesTest`

## Primitive Patterns Preview

Pattern matching had been growing around reference types: `instanceof`, record patterns, and switch patterns. Primitive values were still less integrated with that model.

Java 23 previewed primitive types in patterns, `instanceof`, and `switch`, moving Java toward a more uniform pattern-matching model.

This feature continued as a second preview in Java 24 and a third preview in Java 25.

Explanatory module: [`primitive_patterns`](primitive_patterns/README.md)

Test: `PrimitivePatternsPreviewNotesTest`

## Module Import Declarations Preview

Java has package imports, but a large API often spans many packages. After Java 9 introduced modules, it became natural to ask whether code could import from a module boundary instead of importing packages one by one.

Java 23 previewed module import declarations. The feature continued as a second preview in Java 24 and became final in Java 25.

Continue with the Java 24 [`module_import_declarations`](../java24/module_import_declarations/README.md) module and `ModuleImportDeclarationsExamples` in Java 25.

Explanatory module: [`module_import_declarations`](module_import_declarations/README.md)

Test: `ModuleImportDeclarationsPreviewNotesTest`

## Flexible Constructor Bodies Second Preview

Before this feature line, explicit constructor invocation had to appear first. That made argument validation before `super(...)` or `this(...)` awkward.

Java 23 refined flexible constructor bodies in a second preview. The goal is safer and clearer constructor code while still preventing use of the object before it is initialized.

Continue with the Java 24 [`flexible_constructor_bodies`](../java24/flexible_constructor_bodies/README.md) module and `FlexibleConstructorBodiesExamples` in Java 25.

Explanatory module: [`flexible_constructor_bodies`](flexible_constructor_bodies/README.md)

Test: `FlexibleConstructorBodiesSecondPreviewNotesTest`

## Stream Gatherers Second Preview

Streams had many built-in operations, but custom intermediate operations were hard to express.

Java 23 continued Stream Gatherers as a second preview. Gatherers became final in Java 24.

Continue with `StreamGatherersExamples` in Java 24 for the final runnable example.

Explanatory module: [`stream_gatherers`](stream_gatherers/README.md)

Test: `StreamGatherersSecondPreviewNotesTest`

## Class-File API Second Preview

Bytecode tools need to read, generate, and transform `.class` files. Before the Class-File API, this usually required third-party libraries.

Java 23 continued the standard Class-File API as a second preview. The API became final in Java 24.

Continue with the Java 24 [`class_file`](../java24/class_file/README.md) module for the final executable example.

Explanatory module: [`class_file_api`](class_file_api/README.md)

Test: `ClassFileApiSecondPreviewNotesTest`

## Scoped Values Third Preview

Scoped values provide immutable context within a bounded execution scope, addressing many use cases where `ThreadLocal` can be too broad or too easy to leak.

Java 23 kept scoped values in preview. The feature became final in Java 25.

Continue with the Java 25 [`scoped_values`](../java25/scoped_values/README.md) module for the final learning example.

Explanatory module: [`scoped_values`](scoped_values/README.md)

Test: `ScopedValuesThirdPreviewNotesTest`

## Structured Concurrency Third Preview

Structured concurrency treats related concurrent subtasks as one unit of work, making cancellation and failure handling easier to reason about.

Java 23 continued this feature as a third preview.

Continue with the Java 25 [`structured_concurrency`](../java25/structured_concurrency/README.md) module because this feature is still preview there.

Explanatory module: [`structured_concurrency`](structured_concurrency/README.md)

Test: `StructuredConcurrencyThirdPreviewNotesTest`

## Unsafe Memory-Access Deprecation

Some libraries historically used `sun.misc.Unsafe` for low-level memory access. Those APIs are unsupported and make Java upgrades harder.

Java 23 deprecated memory-access methods in `sun.misc.Unsafe`, continuing the move toward supported APIs such as `VarHandle` and the Foreign Function and Memory API. This repository demonstrates the migration boundary by compiling generated child source, capturing deprecation-for-removal warnings, and showing runtime denial mode without putting `Unsafe` into the main source tree.

Executable migration module: [`unsafe_memory_access_deprecation`](unsafe_memory_access_deprecation/README.md)

Test: `UnsafeMemoryAccessDeprecationExamplesTest`

## ZGC Generational Mode

Garbage collectors can often perform better when they treat young and old objects differently. Java 23 made ZGC use generational mode by default.

This repository demonstrates the runtime boundary by launching a child JVM with ZGC enabled and inspecting VM flag output plus GC initialization logs. It does not try to benchmark garbage-collector efficiency.

Executable runtime module: [`zgc_generational_mode`](zgc_generational_mode/README.md)

Test: `ZgcGenerationalModeExamplesTest`

## How To Read This Package

Start with `markdown_documentation_comments/README.md`, then read `primitive_patterns/README.md`, `module_import_declarations/README.md`, `flexible_constructor_bodies/README.md`, `stream_gatherers/README.md`, `class_file_api/README.md`, `scoped_values/README.md`, `structured_concurrency/README.md`, `unsafe_memory_access_deprecation/README.md`, and `zgc_generational_mode/README.md`.

Run the focused tests:

```bash
mvn -Dtest=MarkdownDocumentationCommentsExamplesTest,PrimitivePatternsPreviewNotesTest,ModuleImportDeclarationsPreviewNotesTest test
mvn -Dtest=FlexibleConstructorBodiesSecondPreviewNotesTest,StreamGatherersSecondPreviewNotesTest,ClassFileApiSecondPreviewNotesTest test
mvn -Dtest=ScopedValuesThirdPreviewNotesTest,StructuredConcurrencyThirdPreviewNotesTest,UnsafeMemoryAccessDeprecationExamplesTest,ZgcGenerationalModeExamplesTest test
```

After this package, continue with Java 24 to see Stream Gatherers and the Class-File API reach final status.

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
