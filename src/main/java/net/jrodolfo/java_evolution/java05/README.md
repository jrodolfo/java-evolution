# Java 5

Released: September 2004 as J2SE 5.0.

Java 5, originally named J2SE 5.0, was one of the largest changes in everyday Java programming. It added language features that made collections safer, loops clearer, primitive-wrapper conversions less noisy, constants more expressive, method calls more flexible, metadata available to tools, and concurrent code easier to build.

This package compiles on the repository's JDK 26 baseline. The syntax shown here is still valid today, but the examples explain the Java 5 problem each feature originally solved.

## Generics

Before Java 5, collection APIs commonly used raw `Object` values. Callers had to cast values back to the expected type, and many mistakes were found only at runtime.

Generics let classes and methods declare type parameters such as `List<String>`. The compiler can then reject many wrong-type operations before the program runs.

They also support API design. A method that only reads values can accept a producer such as `Iterable<? extends ReleaseFeature>`, allowing callers to pass iterables of more specific feature types without copying them. Java 5 type parameters can also have multiple bounds, such as `<T extends NamedFeature & PrioritizedFeature>`, when a method needs more than one capability.

Example: `GenericsExamples`

Test: `GenericsExamplesTest`

## Enhanced For Loop

Before Java 5, iterating over arrays and collections usually required an index variable or an explicit `Iterator`. That exposed mechanics that often distracted from the operation being performed.

The enhanced `for` loop makes simple traversal direct:

```java
for (String name : names) {
    total += name.length();
}
```

The loop works with arrays and any `Iterable`, not only `List`. That means an API can often accept `Iterable<T>` when it only needs traversal, and application classes can implement `Iterable<T>` to become enhanced-for friendly.

Example: `EnhancedForLoopExamples`

Test: `EnhancedForLoopExamplesTest`

## Autoboxing And Unboxing

Before Java 5, moving between primitives such as `int` and wrappers such as `Integer` required explicit calls like `Integer.valueOf(...)` and `intValue()`.

Autoboxing and unboxing let the compiler insert those conversions. The convenience is useful, but unboxing `null` still throws `NullPointerException`.

Example: `AutoboxingExamples`

Test: `AutoboxingExamplesTest`

## Typesafe Enums

Before Java 5, many APIs represented fixed choices with integer constants or hand-written typesafe-enum classes. Integer constants were easy to mix up because the compiler could not distinguish one group of constants from another.

Java 5 enums are real types. They can have fields, methods, constructors, and behavior while still limiting values to the declared constants.

Example: `EnumExamples`

Test: `EnumExamplesTest`

## Varargs

Before Java 5, methods that accepted a variable number of values usually required callers to manually create an array.

Varargs let a method declare `String... labels`, so callers can pass zero, one, or many arguments naturally. Inside the method, the parameter is still an array.

A method can put mandatory parameters before the varargs tail, such as `max(int first, int... rest)`, to make invalid empty calls impossible. Varargs are convenient for callers passing individual values; an `Iterable` or collection overload is often friendlier when callers already have grouped values.

Example: `VarargsExamples`

Test: `VarargsExamplesTest`

## Static Import

Before Java 5, using static helpers or constants required qualifying them with the declaring class name. Static import allows selected static members to be used directly.

This is useful for well-known constants and testing assertions, but overuse can make code harder to read because the declaring type becomes less visible.

Example: `StaticImportExamples`

Test: `StaticImportExamplesTest`

## Annotations

Before Java 5, source-level metadata often lived in naming conventions, marker interfaces, XML files, or JavaDoc tags. Those side channels could drift away from the code.

Annotations let metadata live directly on declarations. Tools and frameworks can inspect that metadata at compile time or runtime, depending on the annotation retention policy.

Example: `AnnotationExamples`

Test: `AnnotationExamplesTest`

## Covariant Return Types

Before Java 5, an overriding method generally had to declare the exact same return type as the method it overrode.

Covariant return types let an override return a more specific subtype. This keeps polymorphism while reducing casts for callers that know the concrete subtype.

Example: `CovariantReturnExamples`

Test: `CovariantReturnExamplesTest`

## Formatted Output

Before Java 5, formatted strings usually required manual concatenation, `NumberFormat`, `DateFormat`, or third-party helpers.

Java 5 added `Formatter`, `String.format`, and `printf`-style methods for consistent text, number, and date/time formatting.

Example: `FormattingExamples`

Test: `FormattingExamplesTest`

## Concurrency Utilities

Before Java 5, concurrent code often used low-level `Thread`, `wait`, `notify`, and synchronized blocks directly. Those primitives are powerful, but they are easy to coordinate incorrectly.

Java 5 added `java.util.concurrent`, including executors, futures, latches, locks, atomic variables, and thread-safe collections.

Example: `ConcurrencyUtilitiesExamples`

Test: `ConcurrencyUtilitiesExamplesTest`

## How To Read This Package

Start with `GenericsExamples`, `EnhancedForLoopExamples`, `AutoboxingExamples`, and `EnumExamples`. Then read `VarargsExamples`, `StaticImportExamples`, `AnnotationExamples`, `CovariantReturnExamples`, `FormattingExamples`, and `ConcurrencyUtilitiesExamples`.

Run the focused tests:

```bash
mvn -Dtest=GenericsExamplesTest,EnhancedForLoopExamplesTest,AutoboxingExamplesTest,EnumExamplesTest test
mvn -Dtest=VarargsExamplesTest,StaticImportExamplesTest,AnnotationExamplesTest,CovariantReturnExamplesTest test
mvn -Dtest=FormattingExamplesTest,ConcurrencyUtilitiesExamplesTest test
```

After this package, continue with Java 6 to see tooling, scripting, console, monitoring, web-service, and collections improvements.

## References

- [Oracle J2SE 5.0 new features](https://docs.oracle.com/javase/1.5.0/docs/relnotes/features.html)
- [J2SE 5.0 overview](https://www.oracle.com/java/technologies/javase/j2se-v50.html)
- [Formatter API in Java 5](https://docs.oracle.com/javase/1.5.0/docs/api/java/util/Formatter.html)
