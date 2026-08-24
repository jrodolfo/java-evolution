# strictfp

Java 2 added the `strictfp` modifier.

## 1. What Problem Does This Feature Solve?

Floating-point calculations could vary across processors when intermediate values used wider precision. Java 2 allowed some non-strict behavior by default for performance on then-current hardware.

## 2. What Did Java Introduce?

`strictfp` let a class, interface, or method request strict floating-point semantics.

## 3. What Does The Example Show?

`StrictFloatingPointExamples` shows the actual `strictfp` modifier and runs a small calculation through a `strictfp` helper type.

The test also compiles a tiny unsuppressed `strictfp` source file with the current JDK. On JDK 25, `javac` accepts the source but warns that, as of Java 17, all floating-point expressions are evaluated strictly and `strictfp` is no longer required.

That warning is the important modern lesson. The original Java 2 distinction between strict and non-strict evaluation cannot be reproduced faithfully in this JDK 25 project.

## 4. Remember This

`strictfp` is most useful as historical context for Java's portability guarantees and the later Java 17 restoration of always-strict semantics.
