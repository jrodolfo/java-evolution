# Java 12

Java 12 was a smaller release, but it started an important language direction: making `switch` usable as an expression. It also added several practical library improvements for streams, strings, files, and localized number formatting.

Some Java 12 features were preview features. A preview feature is available for experimentation, but it is not final yet and may change in later releases. This repository compiles with JDK 25, so preview-origin examples use current final syntax while explaining their original Java 12 status.

## Switch Expressions Preview

Before switch expressions, `switch` was a statement. Producing a value usually required a mutable local variable, `break` statements, and care to avoid accidental fall-through.

Java 12 previewed switch expressions, allowing `switch` to return a value directly:

```java
return switch (dayNumber) {
	case 1, 2, 3, 4, 5 -> "weekday";
	case 6, 7 -> "weekend";
	default -> "unknown";
};
```

The main problem solved was readability and safety. Arrow labels make fall-through impossible in normal branches, and the whole expression makes the returned value clear.

Example: `SwitchExpressionPreviewExamples`

Test: `SwitchExpressionPreviewExamplesTest`

## Teeing Collector

Before Java 12, computing two independent summaries from one stream often required either two passes or a custom collector. For example, calculating minimum and maximum together could become more code than the calculation deserved.

Java 12 added `Collectors.teeing`, which sends the same stream elements into two collectors and combines their results.

This is useful when a single pipeline should produce a result from two independent aggregations, such as minimum plus maximum, count plus average, or partitioned summaries.

Example: `TeeingCollectorExamples`

Test: `TeeingCollectorExamplesTest`

## String.indent

Before Java 12, adding or removing indentation from multi-line text required manual line splitting and joining. That was common in generated text, logs, templates, and tests.

Java 12 added `String.indent(int)`, which adjusts indentation line by line. Positive values add spaces, and negative values remove indentation.

Example: `StringIndentExamples`

Test: `StringIndentExamplesTest`

## Files.mismatch

Before Java 12, comparing two files and finding where they first differed required manual byte-by-byte reading or external tools.

Java 12 added `Files.mismatch(Path, Path)`. It returns `-1` when files match, or the first byte position where they differ.

This is useful for diagnostics, validation, tests, and file synchronization logic.

Example: `FilesMismatchExamples`

Test: `FilesMismatchExamplesTest`

## Compact Number Formatting

Before Java 12, formatting large numbers as user-friendly text such as `1K`, `1 million`, or locale-specific equivalents usually required custom code or third-party libraries.

Java 12 added compact number formatting through `NumberFormat.getCompactNumberInstance`. It supports short and long styles and respects locale-specific rules.

Example: `CompactNumberFormatExamples`

Test: `CompactNumberFormatExamplesTest`

## How To Read This Package

Start with `SwitchExpressionPreviewExamples` to see the first preview of switch expressions. Then read `TeeingCollectorExamples`, `StringIndentExamples`, `FilesMismatchExamples`, and `CompactNumberFormatExamples`.

Run the focused tests:

```bash
mvn -Dtest=SwitchExpressionPreviewExamplesTest,TeeingCollectorExamplesTest test
mvn -Dtest=StringIndentExamplesTest,FilesMismatchExamplesTest,CompactNumberFormatExamplesTest test
```

After this package, continue with Java 13 to see text blocks preview and the `yield` form that helped switch expressions move toward finalization.

## References

- [OpenJDK JDK 12 project](https://openjdk.org/projects/jdk/12/)
- [JEP 325: Switch Expressions](https://openjdk.org/jeps/325)
- [JEP 334: JVM Constants API](https://openjdk.org/jeps/334)
