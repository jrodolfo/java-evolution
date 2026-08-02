# Java 18

Java 18 was not a large language-feature release, but it made important quality-of-life improvements. The common theme is portability and better tooling: text files became more predictable across operating systems, the JDK gained a tiny static web server, JavaDoc gained better code examples, and advanced networking code gained a supported name-resolution extension point.

## UTF-8 by Default

Before Java 18, the default charset depended on the operating system, locale, and machine configuration. The same code could write a text file on one machine and read it incorrectly on another if both sides relied on the platform default charset.

That was especially confusing for beginners because methods like this look harmless:

```java
byte[] bytes = text.getBytes();
```

Before Java 18, that line did not necessarily mean UTF-8. Java 18 standardized the default charset as UTF-8, which makes text handling more predictable across platforms.

The lesson is still important: when a protocol, file format, or external system requires a specific charset, say it explicitly. Java 18 makes the default safer, but explicit charsets remain clearer at integration boundaries.

Example: `Utf8DefaultCharsetExamples`

Test: `Utf8DefaultCharsetExamplesTest`

## Simple Web Server

Before Java 18, serving a folder of static files usually required installing another tool or writing a few lines of server code. That is unnecessary when the goal is only to preview generated HTML, share a local demo, or inspect files in a browser.

Java 18 added the `jwebserver` command:

```bash
jwebserver --port 8000 --directory public
```

This is not a replacement for Spring Boot, Tomcat, nginx, or production web infrastructure. It solves the small local-development problem: "I have static files and want to serve them quickly with only the JDK installed."

Example: `SimpleWebServerNotes`

Test: `SimpleWebServerNotesTest`

## JavaDoc Code Snippets

Before Java 18, JavaDoc examples often used `<pre>` blocks. They worked, but they were just formatted text. They did not give the documentation tool much structure for highlighting, regions, or clearer example presentation.

Java 18 introduced the `@snippet` tag so documentation can include code examples as first-class documentation content:

```java
{@snippet :
var name = "Java 18";
}
```

For a teaching repository, this matters because examples in documentation should be easy to read and hard to misformat.

Example: `CodeSnippetJavaDocNotes`

Test: `CodeSnippetJavaDocNotesTest`

## InetAddress Resolver SPI

Before Java 18, applications that needed custom host-name resolution had limited standard options. Most programs were fine with the operating system resolver, but advanced environments sometimes need controlled resolution behavior, such as custom DNS, service discovery, or testing infrastructure.

Java 18 introduced a service-provider interface for `InetAddress` resolution. This is mostly useful for libraries, platforms, and specialized runtime environments.

This repository keeps the feature as notes because installing a resolver provider changes process-wide networking behavior. That would be too heavy for a simple educational unit test.

Example: `InetAddressResolutionNotes`

Test: `InetAddressResolutionNotesTest`

## References

- [OpenJDK JDK 18 project](https://openjdk.org/projects/jdk/18/)
- [JEP 400: UTF-8 by Default](https://openjdk.org/jeps/400)
- [JEP 408: Simple Web Server](https://openjdk.org/jeps/408)
- [JEP 413: Code Snippets in Java API Documentation](https://openjdk.org/jeps/413)
- [JEP 418: Internet-Address Resolution SPI](https://openjdk.org/jeps/418)
