# Java 15

Released: September 2020 as Java SE 15.

Java 15 finalized text blocks and continued the preview path for sealed classes. It also introduced hidden classes, a feature aimed mostly at frameworks, language runtimes, and dynamic code generation.

The examples use current JDK 25-compatible syntax while documenting which Java 15 features were final and which were still preview.

## Text Blocks Final

Text blocks were previewed in Java 13 and Java 14, then finalized in Java 15.

Before text blocks, multi-line strings required newline escapes, concatenation, and manual formatting. That made embedded JSON, SQL, HTML, XML, and shell snippets hard to read inside Java source code.

Text blocks solve this by making multi-line strings a first-class syntax:

```java
"""
{
  "version": 15,
  "feature": "text blocks"
}
"""
```

They are especially useful when the shape of the text matters.

Example: `TextBlockExamples`

Test: `TextBlockExamplesTest`

## Sealed Classes Preview

Before sealed classes, a type hierarchy was usually either fully open or closed only by convention. If an interface represented a small known domain, such as shapes or commands, the compiler could not enforce which implementations were allowed.

Java 15 previewed sealed classes and interfaces. A sealed type explicitly lists its permitted subtypes:

```java
public sealed interface Shape permits Circle, Rectangle {
}
```

This makes domain modeling clearer and prepares the language for safer exhaustive pattern matching.

Example: `SealedClassesPreviewExamples`

Test: `SealedClassesPreviewExamplesTest`

## Hidden Classes

Frameworks and language runtimes sometimes generate classes at runtime. Before hidden classes, generated implementation classes were more visible and discoverable than they needed to be.

Java 15 introduced hidden classes for generated implementation details that should not be used directly by application code. They are useful for frameworks, proxies, expression engines, and dynamic language runtimes.

Example module: [`hidden_classes`](hidden_classes/README.md)

Test: `HiddenClassesExamplesTest`

## How To Read This Package

Start with `TextBlockExamples` because Java 15 finalized text blocks. Then read `SealedClassesPreviewExamples` and `hidden_classes/README.md`.

Run the focused tests:

```bash
mvn -Dtest=TextBlockExamplesTest,SealedClassesPreviewExamplesTest test
mvn -Dtest=HiddenClassesExamplesTest test
```

`hidden_classes/README.md` explains hidden classes through a compiled-template example because they are mostly useful to frameworks and language runtimes that generate classes dynamically. After this package, continue with Java 16 to see records and pattern matching for `instanceof` become final.

## References

- [OpenJDK JDK 15 project](https://openjdk.org/projects/jdk/15/)
- [JEP 378: Text Blocks](https://openjdk.org/jeps/378)
- [JEP 360: Sealed Classes](https://openjdk.org/jeps/360)
- [JEP 371: Hidden Classes](https://openjdk.org/jeps/371)
