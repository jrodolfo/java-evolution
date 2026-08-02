# Java 11

Java 11 is a long-term support release. It did not introduce one huge language feature, but it added several practical APIs that made everyday Java code cleaner.

The examples in this package focus on small improvements that remove boilerplate from string processing, file I/O, HTTP calls, predicates, optionals, and lambda parameters.

## String API Additions

Before Java 11, common string operations required helper code or less precise methods. Checking whether text contained only whitespace, splitting text into lines, stripping Unicode-aware whitespace, or repeating text was more verbose than necessary.

Java 11 added:

- `isBlank()`
- `lines()`
- `strip()`
- `stripLeading()`
- `stripTrailing()`
- `repeat(int)`

These methods make common text cleanup and formatting code easier to read.

Example: `StringApiExamples`

Test: `StringApiExamplesTest`

## Files.readString and Files.writeString

Before Java 11, reading or writing a whole text file required combining `Files.readAllBytes`, `Files.write`, byte arrays, and charset handling.

Java 11 added `Files.readString` and `Files.writeString`, which express the common text-file use case directly.

Example: `FilesApiExamples`

Test: `FilesApiExamplesTest`

## HTTP Client

Before Java 11, the JDK had `HttpURLConnection`, but it was old and awkward for modern HTTP usage. Many projects used third-party clients for a cleaner API, HTTP/2 support, and asynchronous requests.

Java 11 finalized the standard `java.net.http.HttpClient` API. It supports synchronous and asynchronous calls, HTTP/2, request builders, response body handlers, and a more fluent style.

The examples build requests without sending network traffic so the tests remain deterministic.

Example: `HttpClientExamples`

Test: `HttpClientExamplesTest`

## Predicate.not

Before Java 11, negating a predicate in a stream often required a lambda such as:

```java
value -> !value.isBlank()
```

Java 11 added `Predicate.not(...)`, which lets method references stay readable when filtering out values:

```java
Predicate.not(String::isBlank)
```

Example: `PredicateNotExamples`

Test: `PredicateNotExamplesTest`

## Lambda var

Java 10 introduced `var` for local variables. Java 11 extended the idea to lambda parameters.

The most important use case is not saving characters. It allows annotations on inferred lambda parameters:

```java
(@ExampleParameter var name) -> name.length()
```

Example: `LambdaVarExamples`

Test: `LambdaVarExamplesTest`

## Optional.isEmpty

Before Java 11, checking whether an `Optional` was empty usually meant writing:

```java
!value.isPresent()
```

Java 11 added `isEmpty()`, which says the intent directly.

Example: `OptionalIsEmptyExamples`

Test: `OptionalIsEmptyExamplesTest`

## How To Read This Package

Start with `StringApiExamples` and `FilesApiExamples` because they show small but practical library improvements. Then read `HttpClientExamples`, `PredicateNotExamples`, `LambdaVarExamples`, and `OptionalIsEmptyExamples`.

Run the focused tests:

```bash
mvn -Dtest=StringApiExamplesTest,FilesApiExamplesTest,HttpClientExamplesTest test
mvn -Dtest=PredicateNotExamplesTest,LambdaVarExamplesTest,OptionalIsEmptyExamplesTest test
```

After this package, continue with Java 12 to see the start of switch expressions and more collector/string/file refinements.

## References

- [OpenJDK JDK 11 project](https://openjdk.org/projects/jdk/11/)
- [JEP 321: HTTP Client](https://openjdk.org/jeps/321)
- [JEP 323: Local-Variable Syntax for Lambda Parameters](https://openjdk.org/jeps/323)
