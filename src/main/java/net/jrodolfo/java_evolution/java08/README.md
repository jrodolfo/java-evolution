# Java 8

Java 8 was one of the most important Java releases because it changed how everyday Java code is written. Before Java 8, Java was mostly object-oriented and imperative: you created classes, loops, and mutable collections to describe almost every operation. Java 8 added tools for passing behavior, processing data declaratively, representing missing values more explicitly, composing asynchronous work, and using a safer date/time model.

The examples in this package intentionally avoid later Java features such as records, `var`, switch expressions, and pattern matching. The goal is to show what Java 8 itself made possible.

## Lambdas

Before Java 8, passing behavior into a method usually meant writing an anonymous class. That was verbose, especially for simple operations such as filtering, sorting, mapping, or event handling.

Lambda expressions solved this by letting you pass small blocks of behavior directly:

```java
name -> name.length() >= 4
```

This made APIs such as `Comparator`, `Predicate`, `Function`, and `Consumer` much easier to use. Lambdas are the foundation for streams and many modern Java APIs.

Example: `LambdaExamples`

Test: `LambdaExamplesTest`

## Streams

Before Java 8, collection processing was usually written with loops, temporary lists, counters, and mutable state. That style works, but the intent can be buried inside the mechanics of iteration.

The Stream API solved this by making data processing read like a pipeline:

```java
users.stream()
		.filter(User::active)
		.map(User::name)
		.sorted()
		.collect(Collectors.toList());
```

Streams are useful when you want to transform, filter, group, aggregate, or search through data while keeping the code focused on the desired result.

Example: `StreamExamples`

Test: `StreamExamplesTest`

## Optional

Before Java 8, a method that might not find a value usually returned `null`. The caller had to remember to check for `null`, and missing checks often became `NullPointerException`s far away from the original source of the problem.

`Optional` solved part of this problem by making absence visible in the method return type. A method returning `Optional<String>` tells the caller: there may not be a value here, and you must decide what to do about that.

`Optional` is most useful as a return type. It is not meant to replace every nullable field or parameter.

Example: `OptionalExamples`

Test: `OptionalExamplesTest`

## Method References

After lambdas were added, many lambdas still had a repetitive shape:

```java
text -> Integer.parseInt(text)
```

Method references solved this by giving Java a shorter way to say “call this existing method”:

```java
Integer::parseInt
```

They are useful when a lambda would only delegate to an existing static method, instance method, or constructor.

Example: `MethodReferenceExamples`

Test: `MethodReferenceExamplesTest`

## CompletableFuture

Before Java 8, `Future` could represent a result that would arrive later, but composing futures was awkward. You often had to block with `get()`, manually coordinate threads, or write callback-heavy code.

`CompletableFuture` solved this by making asynchronous work composable. You can start a task, transform its result, combine it with another task, and recover from failure without immediately blocking the current thread.

This is useful for I/O-style work such as calling services, loading data, or combining independent operations.

Example: `CompletableFutureExamples`

Test: `CompletableFutureExamplesTest`

## Default Methods

Before Java 8, adding a method to an interface broke every class that implemented that interface. This made it difficult for the JDK to evolve old interfaces such as `Collection`, `Iterable`, and `Comparator`.

Default methods solved this by allowing interfaces to provide method bodies. Existing implementations could inherit the new behavior, while specific classes could override it when needed.

This feature helped Java evolve its core APIs without forcing every existing implementation to change immediately.

Example: `DefaultMethodExamples`

Test: `DefaultMethodExamplesTest`

## Date/Time API

Before Java 8, date and time code commonly used `java.util.Date`, `java.util.Calendar`, and `SimpleDateFormat`. Those APIs were mutable, confusing around time zones, and easy to misuse.

The `java.time` API solved this with immutable, clearer types:

- `LocalDate` for a date without time or zone
- `LocalDateTime` for a date and time without zone
- `ZonedDateTime` for a date and time with a time zone
- `Period` for date-based amounts
- `Duration` for time-based amounts
- `DateTimeFormatter` for parsing and formatting

The main improvement is that the type tells you what kind of time concept you are working with.

Example: `DateTimeApiExamples`

Test: `DateTimeApiExamplesTest`

## References

- [OpenJDK JDK 8 project](https://openjdk.org/projects/jdk8/)
- [JEP 126: Lambda Expressions and Virtual Extension Methods](https://openjdk.org/jeps/126)
- [JEP 107: Bulk Data Operations for Collections](https://openjdk.org/jeps/107)
- [JEP 150: Date & Time API](https://openjdk.org/jeps/150)
