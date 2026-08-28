# Class-File API

Java 24 finalized the Class-File API in JEP 484.

The API gives Java a standard way to parse, generate, and transform `.class` files. That matters because bytecode tools need to evolve with the Java class-file format.

## Mental Model

Java source code is not what the Java Virtual Machine runs directly.

```text
Example.java
  -> javac
  -> Example.class
  -> JVM loads and executes the class file
```

A `.class` file is structured binary data. It is not just a text copy of the source code. It contains names, flags, methods, fields, attributes, and bytecode instructions in the format defined by the Java Virtual Machine Specification.

The Class-File API lets Java code inspect and work with that structure through standard JDK types instead of treating class files as raw bytes.

## The Problem

Every Java source file that is compiled becomes one or more `.class` files.

Those class files are what the JVM loads and executes. They contain structured information such as:

- class name
- superclass and interfaces
- fields
- methods
- access flags
- attributes
- bytecode instructions

Frameworks, agents, compilers, static-analysis tools, and bytecode libraries often need to read or write this structure.

Before the Class-File API, Java projects usually depended on third-party bytecode libraries for that work. Those libraries remain useful, but the JDK now has a standard API that can evolve with the class-file format itself.

## What This Example Does

The example keeps the first step intentionally small:

```text
compiled .class bytes
  -> ClassFile.of().parse(...)
  -> ClassModel
  -> learner-friendly summary
```

`ClassFileInspector` parses class-file bytes and extracts:

- dotted class name
- class-file major version
- method names
- field names
- access flags

The test reads an already-compiled project class through the classloader, so it works both in Maven and in an IDE.

## Important Terms

- `ClassFile`: the entry point used to parse, generate, or transform class-file bytes.
- `ClassModel`: the parsed view of one class file. In this example, it lets the code ask for the class name, methods, fields, version, and flags.
- Access flags: metadata such as `public`, `final`, or `abstract` that describe how a class, method, or field is declared.
- Major version: the class-file format version produced by the compiler.

## What Major Version Means

Class files have a major version. The major version tells the JVM which class-file format the compiler used.

For example:

- Java 21 uses major version 65
- Java 22 uses major version 66
- Java 23 uses major version 67
- Java 24 uses major version 68
- Java 25 uses major version 69
- Java 26 uses major version 70

This project compiles with JDK 26, so the inspected class is expected to use a Java 26 class-file version.

## Why We Inspect Instead Of Generate

The API can also generate and transform class files. That is powerful, but it is not the best first learning step.

Parsing an existing class file shows the main idea without creating artificial bytecode. Once the reader understands `ClassFile`, `ClassModel`, methods, fields, and flags, generation becomes easier to approach later.
