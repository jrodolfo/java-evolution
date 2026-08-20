# Java 1

This package covers early Java 1.0 and 1.1 foundations. It is intentionally a refresher on platform concepts that later releases evolved, not a complete introductory Java course.

Java 1.0 established the object-oriented language, checked exceptions, threads, `java.io`, AWT, applets, and the original standard-library shape. Java 1.1 expanded the platform with inner classes, reflection, object serialization, JDBC, RMI, JavaBeans, internationalization, JAR files, JNI, and the delegation event model.

The executable examples focus on concepts that still compile and behave faithfully on JDK 25. Environment-heavy or obsolete topics use notes modules.

## Object-Oriented Basics

Java code is organized around classes and objects. Classes can encapsulate state and behavior, and inheritance lets a subclass reuse and specialize behavior from a superclass.

Example: `ObjectOrientedBasicsExamples`

Test: `ObjectOrientedBasicsExamplesTest`

## Interfaces

Interfaces define contracts that unrelated classes can implement. Before Java 8 default methods, interfaces contained method signatures and constants, not reusable method bodies.

Example: `InterfaceExamples`

Test: `InterfaceExamplesTest`

## Checked Exceptions

Checked exceptions force callers to handle or declare recoverable failure. This baseline matters before studying Java 7 multi-catch, precise rethrow, and try-with-resources.

Example: `ExceptionHandlingBasicsExamples`

Test: `ExceptionHandlingBasicsExamplesTest`

## Threads And Runnable

Early Java exposed concurrency through `Thread`, `Runnable`, synchronization, `wait`, and `notify`. Later releases added higher-level concurrency utilities and virtual threads, but the original model explains the foundation.

Example: `ThreadBasicsExamples`

Test: `ThreadBasicsExamplesTest`

## java.io

The original I/O model centered on streams and readers. Later releases added NIO, NIO.2, and convenience file APIs, but `InputStream`, `OutputStream`, `Reader`, and `Writer` remain important concepts.

Example: `IoBasicsExamples`

Test: `IoBasicsExamplesTest`

## Inner Classes

Java 1.1 added inner classes, making it easier to keep small helper implementations near the code that uses them. This history helps explain anonymous classes, which later became a common pre-lambda pattern.

Example: `InnerClassExamples`

Test: `InnerClassExamplesTest`

## Reflection

Java 1.1 added reflection so code can inspect classes, methods, constructors, and fields at runtime.

Explanatory module: [`reflection`](reflection/README.md)

Test: `ReflectionNotesTest`

## Serialization

Java 1.1 added object serialization for writing object graphs to streams and reconstructing them later.

Explanatory module: [`serialization`](serialization/README.md)

Test: `SerializationNotesTest`

## JDBC

Java 1.1 introduced JDBC as a standard API for database access.

Explanatory module: [`jdbc`](jdbc/README.md)

Test: `JdbcNotesTest`

## RMI

Java 1.1 introduced Remote Method Invocation for calling objects in another JVM.

Explanatory module: [`rmi`](rmi/README.md)

Test: `RmiNotesTest`

## JavaBeans

JavaBeans defined reusable component conventions around properties, events, and introspection.

Explanatory module: [`javabeans`](javabeans/README.md)

Test: `JavaBeansNotesTest`

## How To Read This Package

Start with the executable fundamentals, then read the notes modules to understand early platform facilities that are not a good fit for small portable tests.

Run the focused tests:

```bash
mvn -Dtest=ObjectOrientedBasicsExamplesTest,InterfaceExamplesTest,ExceptionHandlingBasicsExamplesTest test
mvn -Dtest=ThreadBasicsExamplesTest,IoBasicsExamplesTest,InnerClassExamplesTest test
mvn -Dtest=ReflectionNotesTest,SerializationNotesTest,JdbcNotesTest,RmiNotesTest,JavaBeansNotesTest test
```

After this package, continue with Java 2 for the Collections Framework and the Java 2 platform shift.

## References

- [What's New in JDK 1.1](https://courses.cs.washington.edu/courses/cse341/99wi/java/tutorial/post1.0/whatsnew/index.html)
- [JavaBeans specification](https://www.oracle.com/java/technologies/javase/javabeans-spec.html)
- [Object Serialization FAQ](https://www.oracle.com/java/technologies/javase/serializationfaq-jsp.html)
