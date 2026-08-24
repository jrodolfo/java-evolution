# Java 7

Released: July 2011 as Java SE 7.

Java 7 made everyday Java code cleaner and safer through Project Coin, while also adding major platform support for modern filesystem work, parallel decomposition, and dynamic language implementation on the JVM.

The examples in this package compile on JDK 25, but they intentionally use Java 7-era shapes where possible. For example, `StringSwitchExamples` uses a classic switch statement, not a later switch expression.

## Try-With-Resources

Before Java 7, resource cleanup commonly required `try`/`finally` blocks. That worked, but it was verbose and easy to get wrong, especially when both the main operation and the cleanup failed.

Try-with-resources closes resources automatically and records cleanup failures as suppressed exceptions.

Java 7 introduced `AutoCloseable` as the general contract for resources managed by try-with-resources. `java.io.Closeable` was retrofitted to extend `AutoCloseable`, but it keeps the older I/O-specific signature where `close()` throws `IOException` instead of the broader `Exception`. In interviews, this distinction explains why try-with-resources works for both classic I/O streams and non-I/O resources such as locks, cursors, or custom cleanup handles.

Example: `TryWithResourcesStatementExamples`

Test: `TryWithResourcesStatementExamplesTest`

## Multi-Catch And Precise Rethrow

Before Java 7, two exception types that needed the same handling usually required duplicated catch blocks or a broad common superclass.

Multi-catch lets one catch block handle multiple exception types. Java 7 also improved rethrow analysis so the compiler can preserve more precise thrown types in common wrapper methods.

Example: `ExceptionHandlingExamples`

Test: `ExceptionHandlingExamplesTest`

## Diamond Operator

Before Java 7, generic instance creation often repeated the same type arguments on both sides of an assignment:

```java
Map<String, List<Integer>> values = new HashMap<String, List<Integer>>();
```

The diamond operator lets the compiler infer the constructor type arguments from the target type:

```java
Map<String, List<Integer>> values = new HashMap<>();
```

Example: `DiamondOperatorExamples`

Test: `DiamondOperatorExamplesTest`

## Strings In switch

Before Java 7, branching on a `String` usually meant an `if`/`else if` chain.

Java 7 allowed `String` values in switch statements. The syntax was still the classic statement form, not the switch expressions introduced much later.

Example: `StringSwitchExamples`

Test: `StringSwitchExamplesTest`

## Binary Literals And Numeric Underscores

Java 7 made numeric literals easier to read by adding binary integer literals and underscores inside numeric literals.

These features improve source readability. They do not change the numeric value.

Example: `NumericLiteralExamples`

Test: `NumericLiteralExamplesTest`

## NIO.2

Before Java 7, file handling often used `java.io.File`, which had limited error reporting and weaker support for modern filesystem operations.

Java 7 added NIO.2, centered on `Path`, `Files`, file attributes, symbolic-link-aware APIs, directory walking, and filesystem providers.

Example: `Nio2Examples`

Test: `Nio2ExamplesTest`

## Fork/Join Framework

Before Java 7, Java 5 executors made task submission easier, but recursive parallel decomposition still required substantial manual coordination.

The fork/join framework added `ForkJoinPool`, `RecursiveTask`, and work stealing for computations that naturally split into subtasks and join results.

Example: `ForkJoinExamples`

Test: `ForkJoinExamplesTest`

## invokedynamic

Java 7 added the `invokedynamic` bytecode and method-handle linkage support for dynamic languages on the JVM.

The executable module demonstrates `java.lang.invoke` call-site linkage and inspects compiled lambda bytecode with `javap` to show real `invokedynamic` instructions. Ordinary Java source still does not directly spell an `invokedynamic` instruction.

Example module: [`invokedynamic`](invokedynamic/README.md)

Test: `InvokeDynamicExamplesTest`

## How To Read This Package

Start with the Project Coin examples: `TryWithResourcesStatementExamples`, `ExceptionHandlingExamples`, `DiamondOperatorExamples`, `StringSwitchExamples`, and `NumericLiteralExamples`. Then read `Nio2Examples`, `ForkJoinExamples`, and `invokedynamic/README.md`.

Run the focused tests:

```bash
mvn -Dtest=TryWithResourcesStatementExamplesTest,ExceptionHandlingExamplesTest,DiamondOperatorExamplesTest test
mvn -Dtest=StringSwitchExamplesTest,NumericLiteralExamplesTest,Nio2ExamplesTest test
mvn -Dtest=ForkJoinExamplesTest,InvokeDynamicExamplesTest test
```

After this package, continue with Java 8 to see lambdas, streams, `Optional`, default methods, `CompletableFuture`, and the Date/Time API.

## References

- [JDK 7 adoption guide](https://docs.oracle.com/javase/7/docs/webnotes/adoptionGuide/)
- [Project Coin / JSR 334 documentation](https://cr.openjdk.org/~darcy/ProjectCoin/ProjectCoin-Documentation-v0.9375.html)
- [Java SE 7 concurrency enhancements](https://docs.oracle.com/javase/7/docs/technotes/guides/concurrency/changes7.html)
- [java.lang.invoke package in Java 7](https://docs.oracle.com/javase/7/docs/api/java/lang/invoke/package-summary.html)
