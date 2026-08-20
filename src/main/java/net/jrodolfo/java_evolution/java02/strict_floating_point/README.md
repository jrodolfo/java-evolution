# strictfp

Java 2 added the `strictfp` modifier.

## 1. What Problem Does This Feature Solve?

Floating-point calculations could vary across processors when intermediate values used wider precision. Java 2 allowed some non-strict behavior by default for performance on then-current hardware.

## 2. What Did Java Introduce?

`strictfp` let a class, interface, or method request strict floating-point semantics.

## 3. Why This Repository Uses Notes

Java 17 restored always-strict floating-point semantics, so a small JDK 25 unit test would not demonstrate the original Java 2 distinction.

## 4. Remember This

`strictfp` is most useful as historical context for Java's portability guarantees and the later Java 17 restoration of always-strict semantics.
