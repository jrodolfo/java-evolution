# Compiler API

Java 6 added JSR 199, the Java Compiler API.

This is an explanatory learning module. It does not compile generated source files inside the normal test suite because a faithful demo needs source files, diagnostics, class output management, and compiler availability checks.

## 1. What Problem Does This Feature Solve?

Before Java 6, tools that needed to compile Java source programmatically often launched `javac` as an external process or depended on implementation-specific compiler classes.

That was awkward for IDEs, build tools, application servers, template engines, and frameworks that generated Java source.

## 2. What Did Java Introduce?

Java 6 introduced `javax.tools.JavaCompiler`.

The API lets a program:

- locate a compiler through `ToolProvider`
- pass source files through a file manager
- collect diagnostics through a diagnostic listener
- run compilation without manually starting a separate process

## 3. Why This Repository Uses Notes

The compiler API is real and still available, but a meaningful example is more like a small tool than a single pure method.

A fake example that returns Java source code as a string would not teach JSR 199. The useful lesson is how the API fits tools that need compiler invocation and diagnostics.

## 4. Remember This

The Compiler API is for tools that need to invoke a Java compiler from Java code. It is not an AST construction API and it is not a replacement for Maven or Gradle in ordinary applications.
