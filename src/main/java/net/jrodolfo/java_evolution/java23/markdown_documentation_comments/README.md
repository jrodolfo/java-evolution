# Markdown Documentation Comments

Java 23 introduced Markdown documentation comments.

This module uses an executable tooling example. It creates a temporary Java source file with Markdown documentation comments, runs the JDK's `javadoc` tool, and checks the generated HTML output.

## What Problem Does This Feature Solve?

Java API documentation is usually written in documentation comments:

```java
/**
 * Returns the account owner.
 */
public String owner() {
	return owner;
}
```

That style is familiar and useful. The pain point appears when documentation needs structure, such as lists, code blocks, or examples.

Traditional JavaDoc comments often used HTML for that structure:

```java
/**
 * Validates an account.
 *
 * <ul>
 *   <li>The owner must not be blank.</li>
 *   <li>The account must be active.</li>
 * </ul>
 *
 * <pre>{@code
 * validate(account);
 * }</pre>
 */
```

That works, but it can make source comments noisy. The generated documentation may look fine, while the source itself becomes harder to scan.

## What Did Developers Do Before?

Developers mixed JavaDoc tags with HTML.

Common examples included:

- `<ul>` and `<li>` for lists
- `<pre>` for code blocks
- `<p>` for paragraphs
- HTML tables for tabular documentation

This was especially common in library or framework code, where comments are part of the public API.

## What Did Java 23 Introduce?

Java 23 allows documentation comments to be written with Markdown.

The goal is to make source documentation easier to read directly in the `.java` file while still allowing JavaDoc tooling to generate API documentation.

The same idea can be written more naturally:

```java
/// Validates an account.
///
/// Requirements:
///
/// - The owner must not be blank.
/// - The account must be active.
///
/// Example:
///
/// ```java
/// validate(account);
/// ```
```

The exact benefit is practical: documentation can be easier to write, review, and maintain in source form.

## Important Terminology

**JavaDoc**

The documentation tool and comment style used to generate API documentation from Java source code.

**Documentation comment**

A source comment that belongs to a class, method, field, or other API element and is used by documentation tooling.

**Markdown**

A lightweight text format for structure such as headings, lists, links, and code blocks.

**Generated API documentation**

The HTML documentation produced by tools from source comments.

## What The Example Shows

Markdown documentation comments affect source documentation and generated JavaDoc output.

They do not change how a method runs.

`MarkdownDocumentationCommentsExamples` demonstrates the feature through the documentation pipeline:

- create a `.java` source file that uses `///` Markdown documentation comments
- run the real `javadoc` executable in a child process
- inspect the generated HTML documentation

The example deliberately does not parse Markdown itself. The point is that JavaDoc tooling understands the new comment form.

## Realistic Use Case

This feature is useful when writing public APIs:

```text
library class
    -> method contract
    -> parameter rules
    -> usage example
    -> generated API documentation
```

Markdown helps keep that information readable in both places:

- in the source file during development
- in generated documentation for users

## When Not To Use It

Do not add long documentation comments just because Markdown exists.

If the code is internal, simple, and obvious, a short comment or no comment may be better.

Use Markdown documentation comments when structured API documentation helps the reader understand how to use the code.

## Remember This

Markdown documentation comments make Java API documentation easier to read and maintain in source code while still feeding the JavaDoc documentation pipeline.
