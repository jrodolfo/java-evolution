# Java 1

Release context: JDK 1.0 arrived in January 1996; JDK 1.1 followed in February 1997.

This package covers early Java 1.0 and 1.1 foundations. It is intentionally a refresher on platform concepts that later releases evolved, not a complete introductory Java course.

Java 1.0 established the object-oriented language, checked exceptions, threads, `java.io`, AWT, applets, and the original standard-library shape. Java 1.1 expanded the platform with inner classes, reflection, object serialization, JDBC, RMI, JavaBeans, internationalization, JAR files, JNI, and the delegation event model.

The executable examples focus on concepts that still compile and behave faithfully on JDK 26. Environment-heavy topics use local, isolated fixtures where that still teaches the feature clearly.

## Object-Oriented Basics

Java code is organized around classes and objects. An object's **state** is the
data it currently holds, and its **behavior** is what it can do through its
methods. **Encapsulation** means keeping that data and behavior together while
controlling how other code can change the object's state. Inheritance lets a
subclass reuse and specialize behavior from a superclass.

Example: `ObjectOrientedBasicsExamples`

Test: `ObjectOrientedBasicsExamplesTest`

## Interfaces

Interfaces define contracts that unrelated classes can implement. Before Java 8 default methods, interfaces contained method signatures and constants, not reusable method bodies.

Example: `InterfaceExamples`

Test: `InterfaceExamplesTest`

## Checked Exceptions

For a checked exception, the compiler requires the calling code to catch the
exception or declare it in the method's `throws` clause. This makes the failure
part of the method's visible contract, but it does not mean that every checked
exception represents a recoverable situation. This baseline matters before
studying Java 7 multi-catch, precise rethrow, and try-with-resources.

Example: `ExceptionHandlingBasicsExamples`

Test: `ExceptionHandlingBasicsExamplesTest`

## Threads And Runnable

Early Java exposed concurrency through `Thread`, `Runnable`, synchronization,
`wait()`, and `notify()`. A `Thread` represents an independently running path
of execution, while a `Runnable` holds the work that path should perform.
Synchronization protects shared state when multiple threads access it. A
thread can call `wait()` to release a lock and pause until another thread calls
`notify()` after changing the shared state. Later releases added higher-level
concurrency utilities and virtual threads, but this original model explains
the foundation.

Example: `ThreadBasicsExamples`

Test: `ThreadBasicsExamplesTest`

## java.io

The original I/O model centered on streams and readers. `InputStream` and
`OutputStream` work with raw bytes, which is appropriate for binary data such as
images. `Reader` and `Writer` work with characters, which is appropriate for
text and involves character encoding. Later releases added NIO, NIO.2, and
convenience file APIs, but these byte-oriented and character-oriented types
remain important concepts.

Example: `IoBasicsExamples`

Test: `IoBasicsExamplesTest`

## Inner Classes

Java 1.1 added inner classes, making it easier to keep small helper
implementations near the code that uses them. Keeping a helper nearby makes
its purpose and access to the surrounding class easier to understand, while
also avoiding a separate top-level name for a type that has no wider use. This
history helps explain anonymous classes, which later became a common pre-lambda
pattern.

Example: `InnerClassExamples`

Test: `InnerClassExamplesTest`

## Reflection

Java 1.1 added reflection so code can inspect classes, methods, constructors, and fields at runtime.

Example module: [`reflection`](reflection/README.md)

Test: `ReflectionExamplesTest`

## Serialization

Java 1.1 added object serialization for writing object graphs to streams and reconstructing them later.

Example module: [`serialization`](serialization/README.md)

Test: `SerializationExamplesTest`

## JDBC

Java 1.1 introduced JDBC as a standard API for database access.

The executable module models driver registration, URL matching, driver-manager
dispatch, connection metadata, and cleanup without requiring a database server.
It uses small Java 1.1-era local types instead of modern compatibility adapters
for today's expanded JDBC interfaces.

Example module: [`jdbc`](jdbc/README.md)

Test: `JdbcExamplesTest`

## RMI

Java 1.1 introduced Remote Method Invocation for calling objects in another JVM.

Example module: [`rmi`](rmi/README.md)

Test: `RmiExamplesTest`

## JavaBeans

JavaBeans defined reusable component conventions around properties, events, and introspection.

Example module: [`javabeans`](javabeans/README.md)

Test: `JavaBeansExamplesTest`

## How To Read This Package

Start with the executable fundamentals, then read the focused platform modules
for reflection, serialization, JDBC, RMI, and JavaBeans.

Run the focused tests:

```bash
mvn -Dtest=ObjectOrientedBasicsExamplesTest,InterfaceExamplesTest,ExceptionHandlingBasicsExamplesTest test
mvn -Dtest=ThreadBasicsExamplesTest,IoBasicsExamplesTest,InnerClassExamplesTest test
mvn -Dtest=ReflectionExamplesTest,SerializationExamplesTest,JdbcExamplesTest,RmiExamplesTest,JavaBeansExamplesTest test
```

After this package, continue with Java 2 for the Collections Framework and the Java 2 platform shift.

## References

- [What's New in JDK 1.1](https://courses.cs.washington.edu/courses/cse341/99wi/java/tutorial/post1.0/whatsnew/index.html)
- [JavaBeans specification](https://www.oracle.com/java/technologies/javase/javabeans-spec.html)
- [Object Serialization FAQ](https://www.oracle.com/java/technologies/javase/serializationfaq-jsp.html)
