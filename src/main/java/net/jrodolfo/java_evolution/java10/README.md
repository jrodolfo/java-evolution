# Java 10

Released: March 2018 as Java SE 10.

Java 10 was a smaller release than Java 9, but it introduced one feature that appears often in modern Java code: local variable type inference with `var`.

The Java 10 examples focus on readability improvements. None of these features make Java dynamically typed or radically change the language. They remove small amounts of repetition when the compiler already has enough information.

## Local Variable Type Inference

Before Java 10, local variable declarations always repeated the type on the left side:

```java
Map<String, Integer> lengths = new HashMap<String, Integer>();
```

Java 10 added `var`, allowing the compiler to infer the local variable type from the initializer:

```java
var lengths = new HashMap<String, Integer>();
```

The variable still has a real static type. `var` is only a readability feature for local variables, loop variables, and try-with-resources variables. It cannot be used for fields, method parameters, or method return types in Java 10.

Example: `LocalVariableTypeInferenceExamples`

Test: `LocalVariableTypeInferenceExamplesTest`

## Unmodifiable Collectors

Java 8 streams made it easy to collect results into lists, sets, and maps, but the common collectors returned mutable collections. If a method wanted to return a read-only result, it needed an extra wrapping step or a later copy.

Java 10 added `Collectors.toUnmodifiableList`, `Collectors.toUnmodifiableSet`, and `Collectors.toUnmodifiableMap`. These collectors let the pipeline produce an unmodifiable result directly.

This is useful when a method should return a snapshot that callers cannot accidentally mutate.

Example: `UnmodifiableCollectorsExamples`

Test: `UnmodifiableCollectorsExamplesTest`

## Optional.orElseThrow()

Before Java 10, unwrapping a required `Optional` value often used either `get()` or `orElseThrow(Supplier)`. `get()` was short but vague, while `orElseThrow(Supplier)` was clearer but verbose when `NoSuchElementException` was acceptable.

Java 10 added no-argument `Optional.orElseThrow()`. It reads better than `get()` and keeps the common failure behavior concise.

Example: `OptionalOrElseThrowExamples`

Test: `OptionalOrElseThrowExamplesTest`

## How To Read This Package

Start with `LocalVariableTypeInferenceExamples` because `var` is the most visible Java 10 feature in everyday code. Then read `UnmodifiableCollectorsExamples` and `OptionalOrElseThrowExamples`.

Run the focused tests:

```bash
mvn -Dtest=LocalVariableTypeInferenceExamplesTest test
mvn -Dtest=UnmodifiableCollectorsExamplesTest,OptionalOrElseThrowExamplesTest test
```

After this package, continue with Java 11 to see the standard HTTP Client and small API additions that made common string, file, predicate, and optional code cleaner.

## References

- [OpenJDK JDK 10 project](https://openjdk.org/projects/jdk/10/)
- [JEP 286: Local-Variable Type Inference](https://openjdk.org/jeps/286)
