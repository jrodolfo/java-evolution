# Class-File API Preview

Java 22 introduced the Class-File API as a preview feature.

This module is explanatory because the Java 22 API was still being evaluated. The final runnable example belongs to Java 24, where the Class-File API became final.

## What Problem Does This Feature Solve?

Java source code is compiled into `.class` files.

A `.class` file is the binary format that the Java Virtual Machine reads. It contains information such as:

- the class name
- fields
- methods
- bytecode instructions
- constants
- annotations
- version information

Most application developers do not read class files directly. Tools often do.

Examples include:

- frameworks that inspect classes at build time or startup
- compilers that generate helper classes
- agents that instrument bytecode
- static-analysis tools
- build tools
- testing and mocking tools

Those tools need to understand the class-file format accurately.

## How Was This Commonly Done Before?

Before the Class-File API, Java projects usually depended on third-party bytecode libraries when they needed to read, generate, or transform class files.

Those libraries remain valuable. The problem is that the Java class-file format evolves with the platform. When the platform adds new class-file structures, tooling libraries must also catch up.

That created a gap:

```text
JDK evolves the class-file format
        |
        v
tools need to understand the new format
        |
        v
tools depend on external bytecode libraries or custom parsing
```

The JDK needed a standard API that could evolve together with the class-file format itself.

## What Did Java Introduce?

Java introduced a standard Class-File API for parsing, generating, and transforming Java class files.

The goal is not to make everyday business code manipulate bytecode. The goal is to give tool authors a supported JDK API for work that already exists in frameworks, agents, compilers, and analysis tools.

Conceptually, the API lets code work with class-file structure through Java objects instead of manually decoding raw bytes.

## Important Terminology

**Class file**

The compiled binary representation of a Java class or interface. A file named `OrderService.class` is a class file.

**Bytecode**

The instructions inside a class file that the Java Virtual Machine executes.

**Parsing**

Reading class-file bytes and turning them into a structured model that Java code can inspect.

**Generating**

Creating a new class file from structured information.

**Transforming**

Reading an existing class file and producing a modified version.

**Preview feature**

A feature included in a JDK release so developers can try it and provide feedback before it becomes final. Java 22 introduced the Class-File API as preview; Java 24 finalized it.

## Why This Module Has Notes Instead Of A Java 22 Example

This repository is compiled with a current JDK and avoids keeping old preview API shapes active across the whole build.

The faithful runnable example is in Java 24:

```text
src/main/java/net/jrodolfo/java_evolution/java24/class_file/README.md
```

That module parses a compiled project class and summarizes class-file metadata through the final API.

The Java 22 module explains why the API appeared and why it was important enough to preview before finalization.

## Realistic Use Case

Imagine a framework that wants to inspect compiled classes during startup.

It might need to answer questions such as:

```text
What is the class name?
Which methods exist?
Which annotations are present?
Which Java class-file version was used?
```

Without a standard API, the framework must rely on a third-party bytecode library or parse class-file bytes itself.

The Class-File API gives this kind of tool a JDK-supported model for class-file work.

## When Not To Use It

Most application code does not need the Class-File API.

If you are writing ordinary services, controllers, repositories, data models, or command-line tools, you probably do not need to inspect `.class` files directly.

Use this API when you are writing tooling that needs class-file structure.

## Remember This

The Class-File API gives Java tools a standard way to parse, generate, and transform `.class` files. Java 22 previewed the idea; Java 24 finalized it and is the right place in this repository to study runnable code.
