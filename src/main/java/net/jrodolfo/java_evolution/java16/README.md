# Java 16

Released: March 2021 as Java SE 16.

Java 16 finalized records and pattern matching for `instanceof`, two features that make modern Java code noticeably less repetitive. It also added `Stream.toList()` and Unix-domain socket channel support.

Java 16 also introduced two important incubator APIs. The Vector API began its
incubation journey in this release, while the Foreign Linker API and the
third-incubator version of the Foreign-Memory Access API explored safer ways to
work with native code and memory. These APIs were still experimental, so the
repository explains their history through later, more stable stages instead of
compiling their Java 16 shapes in the main Maven build.

## Vector API First Incubator

Many numeric, image-processing, audio, compression, and machine-learning
operations repeat the same calculation over many values. Ordinary Java loops
usually describe one scalar value at a time, and the just-in-time compiler may
or may not recognize a form that can use the processor's vector instructions.

Java 16 introduced the Vector API as an incubator API through
[JEP 338](https://openjdk.org/jeps/338). It gave Java code a more direct way to
describe lane-wise operations, such as adding corresponding values from two
groups of numbers. The API lived in the `jdk.incubator.vector` module and was
intentionally expected to change as the design gathered feedback.

This repository explains later points in that evolution in the [Java 20 Vector
API module](../java20/vector_api/README.md) and the [Java 25 Vector API
module](../java25/vector_api/README.md).

## Foreign Linker And Foreign-Memory Access APIs

Java applications sometimes need to call a native function or work with memory
outside the Java heap. Before these APIs, common choices included the Java
Native Interface (JNI), direct buffers, or unsafe implementation-specific
APIs. Those choices can require native glue code or make memory lifetime and
access rules harder to express safely.

Java 16 explored these needs through two related but separate incubator APIs:

- [JEP 389: Foreign Linker API](https://openjdk.org/jeps/389) explored calling
  foreign functions from Java.
- [JEP 393: Foreign-Memory Access API, third incubator](https://openjdk.org/jeps/393)
  explored safe and efficient access to memory outside the Java heap.

The two efforts were later combined into the Foreign Function and Memory API.
Read the [Java 19 notes](../java19/ForeignFunctionMemoryApiPreviewNotes.java)
and [Java 20 notes](../java20/ForeignFunctionMemorySecondPreviewNotes.java) for
the preview progression, followed by the [Java 22 executable module](../java22/foreign_function/README.md)
for the final API.

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

Example module: [`unix_domain_socket`](unix_domain_socket/README.md)

Test: `UnixDomainSocketChannelExamplesTest`

## How To Read This Package

Start with `RecordExamples` and `PatternMatchingInstanceofExamples` because they represent final versions of important language simplifications. Then read `StreamToListExamples` and the `unix_domain_socket/README.md` example module. Finally, use the Vector API and Foreign Function/Memory sections to understand the incubator work that continued into later releases.

Run the focused tests:

```bash
mvn -Dtest=RecordExamplesTest,PatternMatchingInstanceofExamplesTest test
mvn -Dtest=StreamToListExamplesTest,UnixDomainSocketChannelExamplesTest test
```

The Unix-domain socket message-exchange test skips when local socket binding is blocked by a restricted environment. After this package, continue with Java 17, an LTS release that finalizes sealed classes.

## References

- [OpenJDK JDK 16 project](https://openjdk.org/projects/jdk/16/)
- [JEP 395: Records](https://openjdk.org/jeps/395)
- [JEP 394: Pattern Matching for instanceof](https://openjdk.org/jeps/394)
- [JEP 380: Unix-Domain Socket Channels](https://openjdk.org/jeps/380)
- [JEP 338: Vector API](https://openjdk.org/jeps/338)
- [JEP 389: Foreign Linker API](https://openjdk.org/jeps/389)
- [JEP 393: Foreign-Memory Access API](https://openjdk.org/jeps/393)
