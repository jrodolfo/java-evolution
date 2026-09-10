# Java 4

Released: February 2002 as J2SE 1.4.

J2SE 1.4 added several features that still matter in everyday Java: assertions, regular expressions, NIO, logging, chained exceptions, preferences, XML processing, and integrated security APIs.

## Assertions

Java 4 added the `assert` statement for checking programmer assumptions during development and testing.

Example module: [`assertions`](assertions/README.md)

Test: `AssertionExamplesTest`

## Regular Expressions

Java 4 added `java.util.regex`, bringing regular expression matching into the standard library.

`Pattern` is the compiled description of the text rule. A `Matcher` applies
that rule to a particular input. Together they support common tasks such as
validating an entire value, searching for matches, extracting captured text,
and replacing matching text.

For example, a pattern such as `"[0-9]+"` describes one or more digits; a
matcher can then search a string for portions that follow that rule.

Example: `RegexExamples`

Test: `RegexExamplesTest`

## NIO

Java 4 added New I/O (NIO), including buffers, channels, selectors, and charsets.

Older stream-oriented I/O presents data as a sequence that code reads from or
writes to one stream at a time. NIO separates the data from the connection: a
**buffer** is a region of memory that holds bytes or characters, and a
**channel** transfers data between that buffer and a file, socket, or other
source. A **selector** can then watch several selectable channels and report
which ones are ready, so one thread can coordinate multiple connections.

Example: `NioExamples`

Test: `NioExamplesTest`

## Logging

Java 4 added `java.util.logging` as a standard logging API.

Logging gives messages a destination, a logger name, and a severity instead of
mixing diagnostic text into normal output with `System.out.println`. Log
levels such as `SEVERE`, `WARNING`, `INFO`, and `FINE` let an operator choose
how much detail to see without changing the application's main logic. The
structure also makes it easier to route, filter, and understand messages in a
larger application.

Example: `LoggingExamples`

Test: `LoggingExamplesTest`

## Chained Exceptions

Java 4 standardized exception causes, making wrapper exceptions preserve the original failure.

For example, code can translate a low-level failure while keeping its cause:

```java
try {
    throw new IOException("disk failed");
} catch (IOException exception) {
    throw new ImportException("import failed", exception);
}
```

The caller receives a meaningful higher-level exception, while the original
`IOException` remains available for diagnosis. This is useful when an API
wants to expose its own vocabulary without hiding the underlying problem.

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

The executable module focuses on local JCA/JCE operations such as provider
discovery, message digests, secure random bytes, authenticated encryption,
HMAC, and digital signatures.

Example module: [`security`](security/README.md)

Test: `SecurityIntegrationExamplesTest`

## How To Read This Package

Start with `assertions/AssertionExamples`, `RegexExamples`, `NioExamples`, `LoggingExamples`, `ChainedExceptionExamples`, `xml/JaxpExamples`, and `security/SecurityIntegrationExamples`. Then read the Preferences notes module for runtime/configuration caveats.

Run the focused tests:

```bash
mvn -Dtest=RegexExamplesTest,NioExamplesTest,LoggingExamplesTest,ChainedExceptionExamplesTest test
mvn -Dtest=JaxpExamplesTest,AssertionExamplesTest,PreferencesNotesTest,SecurityIntegrationExamplesTest test
```

After this package, continue with Java 5 for generics, enums, annotations, enhanced loops, varargs, formatted output, and concurrency utilities.

## References

- <a target="_blank" rel="noopener noreferrer" href="https://docs.oracle.com/javase/8/docs/technotes/guides/language/enhancements.html">Java language enhancements history</a>
- <a target="_blank" rel="noopener noreferrer" href="https://download.oracle.com/otn_hosted_doc/jdeveloper/904preview/jdk14doc/docs/relnotes/features.html">J2SE 1.4 new features and enhancements</a>
