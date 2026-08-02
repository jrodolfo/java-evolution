# Java 16

Java 16 finalized records and pattern matching for `instanceof`, two features that make modern Java code noticeably less repetitive. It also added `Stream.toList()` and Unix-domain socket channel support.

## Records Final

Before records, simple immutable data carriers required a lot of mechanical code: fields, constructors, accessors, `equals`, `hashCode`, and `toString`.

Records solve that by letting the developer declare the state and letting the compiler generate the standard value-based behavior:

```java
public record Feature(String name, int version) {
}
```

Records are best for transparent data carriers where the components define the value.

Example: `RecordExamples`

Test: `RecordExamplesTest`

## Pattern Matching for instanceof Final

Before pattern matching, `instanceof` checks usually required a separate cast:

```java
if (value instanceof String) {
	String text = (String) value;
}
```

Java 16 finalized pattern matching for `instanceof`, allowing the type check and variable binding together:

```java
if (value instanceof String text) {
	return text.length();
}
```

This removes repetitive casts and makes type-based logic easier to scan.

Example: `PatternMatchingInstanceofExamples`

Test: `PatternMatchingInstanceofExamplesTest`

## Stream.toList

Before Java 16, collecting a stream into a list usually used:

```java
stream.collect(Collectors.toList())
```

That works, but it is verbose for the most common terminal collection operation. Java 16 added `Stream.toList()`, which is shorter and returns an unmodifiable list.

Example: `StreamToListExamples`

Test: `StreamToListExamplesTest`

## Unix-Domain Socket Channels

Before Java 16, local inter-process communication through Unix-domain sockets was not covered by the standard Java NIO socket channel API.

Java 16 added Unix-domain socket channel support. This is useful when two processes on the same machine need to communicate without opening TCP ports.

This repository keeps the feature as notes because support depends on operating-system behavior and should not make the unit test suite platform-sensitive.

Example: `UnixDomainSocketChannelNotes`

Test: `UnixDomainSocketChannelNotesTest`

## References

- [OpenJDK JDK 16 project](https://openjdk.org/projects/jdk/16/)
- [JEP 395: Records](https://openjdk.org/jeps/395)
- [JEP 394: Pattern Matching for instanceof](https://openjdk.org/jeps/394)
- [JEP 380: Unix-Domain Socket Channels](https://openjdk.org/jeps/380)
