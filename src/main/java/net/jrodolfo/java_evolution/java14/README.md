# Java 14

Java 14 finalized switch expressions and previewed two features that later became central to modern Java: records and pattern matching. It also improved runtime diagnostics with helpful `NullPointerException` messages.

The preview examples use JDK 25-compatible final syntax while explaining that Java 14 was the first preview release for those features.

## Switch Expressions Final

Before switch expressions, returning a value from `switch` usually required mutation and careful `break` handling. Java 12 and Java 13 previewed a better form, and Java 14 made it final.

Switch expressions solve two common problems:

- they let `switch` produce a value directly
- arrow branches avoid accidental fall-through

Example: `SwitchExpressionExamples`

Test: `SwitchExpressionExamplesTest`

## Helpful NullPointerExceptions

Before Java 14, a null pointer failure in chained code often produced a generic message. If this failed:

```java
customer.address().city().toUpperCase()
```

the developer still had to figure out which part was `null`.

Java 14 improved JVM diagnostics so the exception message can identify the null part of the expression. This does not prevent nulls, but it makes failures much faster to diagnose.

Example: `HelpfulNullPointerExceptionExamples`

Test: `HelpfulNullPointerExceptionExamplesTest`

## Records Preview

Before records, simple data carrier classes required constructors, fields, accessors, `equals`, `hashCode`, and `toString`. Most of that code was mechanical.

Java 14 previewed records, which let the developer declare the state and let the compiler generate the standard behavior:

```java
public record Feature(String name, boolean preview) {
}
```

Records are useful for immutable data carriers where identity is based on the component values.

Example: `RecordPreviewExamples`

Test: `RecordPreviewExamplesTest`

## Pattern Matching for instanceof Preview

Before pattern matching, using `instanceof` usually required a separate cast after the check:

```java
if (value instanceof String) {
	String text = (String) value;
}
```

Java 14 previewed pattern matching for `instanceof`, letting the type check and variable binding happen together:

```java
if (value instanceof String text) {
	return text.length();
}
```

This removes repetitive casts and keeps type-dependent logic easier to read.

Example: `PatternMatchingInstanceofPreviewExamples`

Test: `PatternMatchingInstanceofPreviewExamplesTest`

## References

- [OpenJDK JDK 14 project](https://openjdk.org/projects/jdk/14/)
- [JEP 361: Switch Expressions](https://openjdk.org/jeps/361)
- [JEP 358: Helpful NullPointerExceptions](https://openjdk.org/jeps/358)
- [JEP 359: Records](https://openjdk.org/jeps/359)
- [JEP 305: Pattern Matching for instanceof](https://openjdk.org/jeps/305)
