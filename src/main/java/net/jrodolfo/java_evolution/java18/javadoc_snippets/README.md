# JavaDoc Code Snippets

Java 18 introduced the JavaDoc `@snippet` tag in JEP 413.

This feature is different from a language feature. It does not change how Java code runs. It changes how Java code examples can be written and rendered inside generated documentation.

## The Problem

Before Java 18, JavaDoc examples were often written with HTML preformatted blocks:

```html
<pre>
var name = "Java 18";
</pre>
```

That worked, but JavaDoc mostly treated the example as text. The tool had limited structure to work with, so examples could become inconsistent, hard to highlight, and easy to misformat.

For a learning project, this matters because documentation examples are part of the teaching surface. A reader should be able to scan a generated JavaDoc page and quickly find the code that demonstrates the point.

## What `@snippet` Adds

The `@snippet` tag gives JavaDoc a dedicated way to render code examples:

```java
{@snippet :
var name = "Java 18";
}
```

Snippet markup can also highlight important parts of an example or name a region inside a larger snippet.

## What This Example Does

`JavaDocSnippetExamples` keeps the runtime code intentionally small:

- `normalizeTitle(String)` prepares a title for display
- `previewLines(List<String>, int)` returns a short preview from a larger list
- `formatCommand(String, int)` formats a command shown in documentation

The important part is the JavaDoc on those methods. It shows inline and multi-line snippets that appear in the generated JavaDoc site.

## How To Study It

Read the source first:

```text
src/main/java/net/jrodolfo/java_evolution/java18/javadoc_snippets/JavaDocSnippetExamples.java
```

Then generate the JavaDoc site:

```bash
make docs
```

Open the generated page under:

```text
target/site/apidocs/net/jrodolfo/java_evolution/java18/javadoc_snippets/JavaDocSnippetExamples.html
```

Compare the source JavaDoc with the rendered page. That is the point of the feature: the documentation source stays readable, and the generated documentation presents code examples more clearly.
