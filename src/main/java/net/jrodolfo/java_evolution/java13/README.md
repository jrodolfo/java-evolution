# Java 13

Java 13 continued preview work from Java 12 and refined features that later became part of everyday Java. Its most visible additions were text blocks and the `yield` keyword for switch expressions.

The examples use JDK 25-compatible final syntax while documenting where Java 13 was still previewing or refining the feature.

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

## Switch yield Preview

Java 12 previewed switch expressions, but Java 13 refined how block branches produce a value. A branch with multiple statements needs a clear way to say which value the branch returns.

Java 13 introduced `yield` for that purpose:

```java
case 7 -> {
	String base = "good";
	yield base + " progress";
}
```

The problem solved was clarity. `yield` makes it obvious which value leaves a switch branch.

Example: `SwitchYieldPreviewExamples`

Test: `SwitchYieldPreviewExamplesTest`

## FileSystems.newFileSystem(Path)

Before Java 13, opening a file system for an archive path required more verbose overloads, often with a class loader or environment map.

Java 13 added a convenience overload: `FileSystems.newFileSystem(Path)`. This is useful when treating supported archive files, such as ZIP files, as file systems.

Example: `FileSystemsNewFileSystemExamples`

Test: `FileSystemsNewFileSystemExamplesTest`

## References

- [OpenJDK JDK 13 project](https://openjdk.org/projects/jdk/13/)
- [JEP 354: Switch Expressions](https://openjdk.org/jeps/354)
- [JEP 355: Text Blocks](https://openjdk.org/jeps/355)
