# Java 13

Released: September 2019 as Java SE 13.

Java 13 continued preview work from Java 12 and refined features that later became part of everyday Java. Its most visible additions were text blocks and the `yield` keyword for switch expressions.

The examples use JDK 26-compatible final syntax while documenting where Java 13 was still previewing or refining the feature.

## Text Blocks Preview

Before text blocks, multi-line strings in Java required repeated quotes, newline escapes, and manual indentation management:

```java
"select name\n" +
"from java_features\n" +
"where version = 13\n"
```

Java 13 previewed text blocks, allowing readable multi-line string literals:

```java
"""
select name
from java_features
where version = 13
"""
```

This solved a practical readability problem for JSON, SQL, HTML, XML, and other embedded text formats.

Example: `TextBlockPreviewExamples`

Test: `TextBlockPreviewExamplesTest`

## Switch Expressions: `yield` Preview

Java 12 previewed switch expressions, but its design used a `break` statement with a value when a branch needed to produce the result. Feedback from that preview led Java 13 to replace that design with the `yield` statement.

This means `yield` is not an unrelated feature. It is part of the evolving switch-expression design. A branch with multiple statements still needs a clear way to say which value the branch returns.

Java 13 introduced `yield` for that purpose:

```java
case 7 -> {
	String base = "good";
	yield base + " progress";
}
```

The problem solved was clarity. `yield` makes it obvious which value leaves a switch branch, while keeping `break` associated with leaving a switch statement or loop. Java 14 later finalized the switch-expression feature with this `yield` design.

Example: `SwitchYieldPreviewExamples`

Test: `SwitchYieldPreviewExamplesTest`

## FileSystems.newFileSystem(Path)

Before Java 13, opening a file system for an archive path required more verbose overloads, often with a class loader or environment map.

Java 13 added a convenience overload: `FileSystems.newFileSystem(Path)`. This is useful when treating supported archive files, such as ZIP files, as file systems.

Example: `FileSystemsNewFileSystemExamples`

Test: `FileSystemsNewFileSystemExamplesTest`

## How To Read This Package

Start with `TextBlockPreviewExamples` because multiline strings are the clearest Java 13 language preview. Then read `SwitchYieldPreviewExamples` and `FileSystemsNewFileSystemExamples`.

Run the focused tests:

```bash
mvn -Dtest=TextBlockPreviewExamplesTest,SwitchYieldPreviewExamplesTest test
mvn -Dtest=FileSystemsNewFileSystemExamplesTest test
```

After this package, continue with Java 14 to see switch expressions become final and records/pattern matching begin their preview cycle.

## References

- [OpenJDK JDK 13 project](https://openjdk.org/projects/jdk/13/)
- [JEP 354: Switch Expressions](https://openjdk.org/jeps/354)
- [JEP 355: Text Blocks](https://openjdk.org/jeps/355)
