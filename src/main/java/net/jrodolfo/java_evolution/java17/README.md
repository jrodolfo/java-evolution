# Java 17

Java 17 is a long-term support release. It finalized sealed classes and added several library/runtime improvements that matter for maintainability, diagnostics, security, and low-level data handling.

## Sealed Classes Final

Java 15 previewed sealed classes, and Java 17 made them final.

Before sealed classes, Java had no direct way to say “this interface can only be implemented by these specific types.” Developers relied on package visibility, documentation, or runtime checks.

Sealed classes solve this by making the permitted subtypes part of the type declaration:

```java
public sealed interface Shape permits Circle, Rectangle, Square {
}
```

This is useful for domain hierarchies that should be closed and well understood.

Example: `SealedClassesExamples`

Test: `SealedClassesExamplesTest`

## Pattern Matching for switch Preview

Before pattern matching for switch, type-based dispatch usually required chains of `if` and `instanceof` checks.

Java 17 previewed pattern matching for `switch`, allowing a switch expression to branch by type:

```java
return switch (value) {
	case String text -> "string length=" + text.length();
	case Integer number -> "integer doubled=" + number * 2;
	default -> "unknown";
};
```

The feature became final later in Java 21. This repository uses current syntax while documenting Java 17 as the preview origin.

Example: `PatternMatchingSwitchPreviewExamples`

Test: `PatternMatchingSwitchPreviewExamplesTest`

## Random Generator API

Before Java 17, random number generation APIs were less unified. Choosing an algorithm or discovering available algorithms was not as consistent across the JDK.

Java 17 introduced the `RandomGenerator` interface and `RandomGeneratorFactory`, creating a common abstraction for multiple pseudo-random number generator algorithms.

Example: `RandomGeneratorExamples`

Test: `RandomGeneratorExamplesTest`

## HexFormat

Before Java 17, formatting bytes as hexadecimal text or parsing hexadecimal text back into bytes often required custom utility code or third-party libraries.

Java 17 added `HexFormat`, a small standard API for byte-to-hex and hex-to-byte conversion, including delimiter support.

Example: `HexFormatExamples`

Test: `HexFormatExamplesTest`

## Strong Encapsulation

Before Java 17, some applications and libraries relied on internal JDK APIs through reflection or unsupported packages. That made upgrades fragile because internal APIs were never meant to be stable public contracts.

Java 17 strongly encapsulated JDK internals by default. The practical lesson is clear: production code should depend on supported public APIs or maintained libraries, not internal JDK implementation details.

This repository keeps the topic as notes because it is a runtime compatibility and migration topic rather than a small API demonstration.

Example: `StrongEncapsulationNotes`

Test: `StrongEncapsulationNotesTest`

## How To Read This Package

Start with `SealedClassesExamples` because sealed classes are final in Java 17. Then read `PatternMatchingSwitchPreviewExamples`, `RandomGeneratorExamples`, `HexFormatExamples`, and `StrongEncapsulationNotes`.

Run the focused tests:

```bash
mvn -Dtest=SealedClassesExamplesTest,PatternMatchingSwitchPreviewExamplesTest test
mvn -Dtest=RandomGeneratorExamplesTest,HexFormatExamplesTest,StrongEncapsulationNotesTest test
```

`StrongEncapsulationNotes` is notes-based because the behavior is mostly visible through module boundaries, reflective access, and migration warnings. After this package, continue with Java 18 for default charset and tooling/documentation improvements.

## References

- [OpenJDK JDK 17 project](https://openjdk.org/projects/jdk/17/)
- [JEP 409: Sealed Classes](https://openjdk.org/jeps/409)
- [JEP 406: Pattern Matching for switch](https://openjdk.org/jeps/406)
- [JEP 356: Enhanced Pseudo-Random Number Generators](https://openjdk.org/jeps/356)
- [JEP 403: Strongly Encapsulate JDK Internals](https://openjdk.org/jeps/403)
