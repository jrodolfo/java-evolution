# Java 12

Released: March 2019 as Java SE 12.

Java 12 was a smaller release, but it started an important language direction: making `switch` usable as an expression. It also added several practical library improvements for streams, strings, files, and localized number formatting.

Some Java 12 features were preview features. A preview feature is available for experimentation, but it is not final yet and may change in later releases. Java 12's first switch-expression preview used value-bearing `break`; Java 13 replaced it with `yield`, and Java 14 finalized the later design. The runnable class uses the current equivalent on JDK 26, while its `firstPreviewSource()` method preserves the Java 12 form as source text.

## Switch Expressions Preview

Before switch expressions, `switch` was a statement. Producing a value usually required a mutable local variable, `break` statements, and care to avoid accidental fall-through.

Java 12 previewed switch expressions, allowing `switch` to return a value directly. Its first-preview statement form used `break` with a value:

```java
String result = switch (dayNumber) {
	case 1, 2, 3, 4, 5: break "weekday";
	case 6, 7: break "weekend";
	default: break "unknown";
};
```

Java 13 changed value-bearing `break` to `yield`, and Java 14 finalized switch expressions with the later syntax. The repository keeps the historical Java 12 form in `firstPreviewSource()` because JDK 26 cannot compile an older JDK's preview syntax, while `dayType` provides runnable equivalent behavior. The main problem solved was readability and safety: a switch expression makes the produced value explicit, and the later arrow rules make fall-through impossible in normal branches.

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

## JVM Constants API

In simple terms, this API lets a tool describe a Java type, method, or constant
as data. The tool can inspect or manipulate that description without first
turning the referenced class into a live runtime object.

Before Java 12, bytecode libraries could already represent class names,
descriptors, constant-pool entries, and symbolic references without loading the
referenced classes. The missing piece was a standard JDK API for doing this
nominal description work.

Java 12 introduced the JVM Constants API through JEP 334. The
`java.lang.constant` API lets compilers, bytecode tools, and other platform
tools describe constant-pool entries by name and structure without requiring
the referenced class to be loaded first. A live `Class`, `MethodType`, or
`MethodHandle` is different from a nominal descriptor: the descriptor is a
symbolic recipe that can later be resolved when a live runtime object is
actually needed. This standardizes a useful representation for bytecode tools,
compiler infrastructure, bootstrap/linkage logic, and APIs that manipulate
symbolic constants.

This repository keeps JEP 334 as a reference topic because the API is most
useful in compiler and bytecode tooling rather than in a small application
example.

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
