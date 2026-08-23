# Java 4

Released: February 2002 as J2SE 1.4.

J2SE 1.4 added several features that still matter in everyday Java: assertions, regular expressions, NIO, logging, chained exceptions, preferences, XML processing, and integrated security APIs.

## Assertions

Java 4 added the `assert` statement for checking programmer assumptions during development and testing.

Example module: [`assertions`](assertions/README.md)

Test: `AssertionExamplesTest`

## Regular Expressions

Java 4 added `java.util.regex`, bringing regular expression matching into the standard library.

Example: `RegexExamples`

Test: `RegexExamplesTest`

## NIO

Java 4 added New I/O (NIO), including buffers, channels, selectors, and charsets.

Example: `NioExamples`

Test: `NioExamplesTest`

## Logging

Java 4 added `java.util.logging` as a standard logging API.

Example: `LoggingExamples`

Test: `LoggingExamplesTest`

## Chained Exceptions

Java 4 standardized exception causes, making wrapper exceptions preserve the original failure.

Example: `ChainedExceptionExamples`

Test: `ChainedExceptionExamplesTest`

## Preferences

Java 4 added the Preferences API for small configuration values.

Explanatory module: [`preferences`](preferences/README.md)

Test: `PreferencesNotesTest`

## XML Processing

Java 4 added standard XML processing support through JAXP.

Example module: [`xml`](xml/README.md)

Test: `JaxpExamplesTest`

## Integrated Security APIs

Java 4 integrated important security APIs such as JCE, JSSE, and JAAS into the standard platform.

Explanatory module: [`security`](security/README.md)

Test: `SecurityIntegrationNotesTest`

## How To Read This Package

Start with `assertions/AssertionExamples`, `RegexExamples`, `NioExamples`, `LoggingExamples`, `ChainedExceptionExamples`, and `xml/JaxpExamples`. Then read the notes modules for runtime/configuration/platform topics.

Run the focused tests:

```bash
mvn -Dtest=RegexExamplesTest,NioExamplesTest,LoggingExamplesTest,ChainedExceptionExamplesTest test
mvn -Dtest=JaxpExamplesTest,AssertionExamplesTest,PreferencesNotesTest,SecurityIntegrationNotesTest test
```

After this package, continue with Java 5 for generics, enums, annotations, enhanced loops, varargs, formatted output, and concurrency utilities.

## References

- [Java language enhancements history](https://docs.oracle.com/javase/8/docs/technotes/guides/language/enhancements.html)
- [J2SE 1.4 new features and enhancements](https://download.oracle.com/otn_hosted_doc/jdeveloper/904preview/jdk14doc/docs/relnotes/features.html)
