# Class-File API Second Preview

Java 23 continued the Class-File API as a second preview feature.

This module is explanatory because the API was still preview in Java 23. The final runnable example belongs to Java 24.

## What Problem Does This Feature Solve?

Java source code is compiled into `.class` files.

Tools often need to inspect, generate, or transform those files:

- frameworks inspect metadata
- agents instrument bytecode
- compilers generate helper classes
- static-analysis tools inspect compiled structures
- testing and mocking tools may generate or transform classes

That work needs an accurate understanding of the class-file format.

## How Was This Commonly Done Before?

Before the standard Class-File API, tools usually depended on third-party bytecode libraries or custom byte parsing.

Those libraries remain useful, but the class-file format evolves with the JDK.

When the platform changes the format, tooling has to keep up:

```text
JDK evolves class-file format
        |
        v
tools need to understand new structures
        |
        v
bytecode libraries and custom parsers must adapt
```

A standard JDK API can evolve together with the format it represents.

## Relationship To Java 22

Java 22 introduced the Class-File API as a first preview.

For the fuller first-preview explanation, read:

```text
src/main/java/net/jrodolfo/java_evolution/java22/class_file_api/README.md
```

Java 23 continued the API as a second preview while the platform refined the design.

## What Did Java 23 Continue?

Java 23 continued the same goal:

```text
give Java tools a standard model for parsing, generating, and transforming class files
```

The API was still preview, so developers could try it and provide feedback before finalization.

## Important Terminology

**Class file**

The compiled binary representation of a Java class or interface.

**Bytecode**

The instructions inside a class file that the Java Virtual Machine executes.

**Parsing**

Reading class-file bytes into a structured model.

**Generating**

Creating a new class file.

**Transforming**

Reading an existing class file and producing a modified version.

**Preview feature**

A feature included in a JDK release so developers can try it and provide feedback before it becomes final. The Class-File API was preview in Java 22 and Java 23, then finalized in Java 24.

## Why This Module Has Notes Instead Of A Java 23 Example

This repository avoids keeping old preview API shapes active across the whole build.

The final runnable example is in Java 24:

```text
src/main/java/net/jrodolfo/java_evolution/java24/class_file/README.md
```

That module parses a compiled project class and summarizes class-file metadata through the final API.

The Java 23 module records the second-preview step and points learners to the final code.

## Realistic Use Case

Imagine a framework that wants to inspect compiled classes during startup.

It may need to answer:

```text
which class is this?
which methods exist?
which annotations are present?
which Java class-file version was used?
```

The Class-File API gives tools a standard JDK model for that kind of work.

## When Not To Use It

Most application code does not need to inspect `.class` files.

If you are writing ordinary services, controllers, repositories, or domain classes, this API is probably not part of your daily code.

Use it when you are writing tooling that works with compiled Java class structure.

## Remember This

Java 23 kept the Class-File API in preview. The feature gives Java tooling a standard way to parse, generate, and transform `.class` files, and Java 24 is where this repository shows the final runnable API.
