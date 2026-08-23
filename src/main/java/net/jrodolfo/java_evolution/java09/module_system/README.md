# Java Platform Module System

Java 9 introduced the Java Platform Module System (JPMS) in JEP 261.

This module uses an executable example without converting the whole Spring Boot project into a modular application. The test creates a temporary multi-module source layout, compiles it with `javac --module-source-path`, and runs it with `java --module-path`.

## 1. What Problem Does This Feature Solve?

Before Java 9, most applications were organized with the classpath.

The classpath is simple: put classes and Java Archive (JAR) files where the compiler and Java Virtual Machine (JVM) can find them.

That simplicity also created problems:

- dependencies were not declared in a strong, standard way
- one JAR could accidentally use packages that were meant to be internal to another JAR
- large applications could become hard to reason about
- the JDK itself was difficult to divide into smaller, well-defined pieces

The practical problem was not that the classpath never worked. It worked for years.

The problem was that the classpath did not express clear boundaries.

## 2. How Was This Commonly Handled Before?

Before JPMS, teams usually relied on conventions and build tools:

```text
do not use com.example.internal
do not depend on this JAR directly
keep these packages private by convention
```

Build tools such as Maven could declare project dependencies, but Java itself did not know which packages a library intended to expose.

If a class was visible on the classpath, code could often compile against it even if the library author considered it internal.

That made migration risky. A project could unknowingly depend on implementation details that changed later.

## 3. What Did Java Introduce?

Java 9 introduced named modules.

A module can declare:

- its name
- which other modules it depends on
- which packages it exports for other modules to use

That declaration lives in a file named:

```text
module-info.java
```

A small descriptor can look like this:

```java
module net.jrodolfo.java_evolution.examples {
    requires java.net.http;
    exports net.jrodolfo.java_evolution.examples;
}
```

This says:

- this module is named `net.jrodolfo.java_evolution.examples`
- it depends on the `java.net.http` module
- it exposes the package `net.jrodolfo.java_evolution.examples`

## 4. Terminology In Plain English

Module:

A named group of packages with explicit dependencies and exported packages.

Module descriptor:

The `module-info.java` file that describes a module.

`requires`:

A directive saying, "this module depends on another module."

`exports`:

A directive saying, "other modules may use the public types in this package."

Classpath:

The older mechanism where Java searches a flat list of classes and JAR files.

Module path:

The mechanism used for named modules. It lets the compiler and JVM resolve modules and check their declared dependencies.

Strong encapsulation:

The idea that code should not freely reach into internals that a module did not export.

## 5. How A Real Module Layout Looks

A real modular project usually has a source layout where `module-info.java` is at the root of a module's source tree.

Conceptually:

```text
src/
  net.jrodolfo.java_evolution.examples/
    module-info.java
    net/jrodolfo/java_evolution/examples/
      FeatureExample.java
```

The descriptor might say:

```java
module net.jrodolfo.java_evolution.examples {
    requires java.net.http;
    exports net.jrodolfo.java_evolution.examples;
}
```

The important point is that the descriptor is not just another helper class. It changes how the compiler and JVM understand dependencies and visibility.

## 6. What The Example Shows

This repository is a Spring Boot application that demonstrates many Java versions in one build.

Converting the whole project into a modular application would add build structure and framework considerations that distract from the Java 9 concept.

Creating a fake module descriptor inside a normal Java class would also be misleading. It would test a string, not the Java Platform Module System.

`ModuleSystemExamples` keeps the real JPMS workflow isolated in temporary files:

- create `module-info.java` files for `com.example.greetings` and `com.example.app`
- export only `com.example.greetings`
- keep `com.example.greetings.internal` unexported
- compile with `javac --module-source-path`
- run with `java --module-path`

The repository itself remains non-modular; only the temporary teaching source tree is modular.

## 7. What The Test Proves

`ModuleSystemExamplesTest` compiles and runs real named modules. It verifies that:

- `module-info.java` declares module metadata
- `requires` declares dependencies
- `exports` exposes selected packages
- the module path runs the modular application
- code in another module cannot import an unexported internal package

That is the right level of testing for this repository because the real feature belongs to project layout and compiler/runtime configuration.

## 8. Realistic Use Case

JPMS can be useful when a project needs explicit architectural boundaries.

Examples:

- a platform divided into independently maintained modules
- an application that wants to expose only selected packages
- a library that wants stronger protection for internal packages
- a runtime image that should include only required JDK modules

JPMS also matters when studying modern JDK internals, because the JDK itself was modularized in Java 9.

## 9. When Not To Use It

Do not modularize a project only because Java 9 introduced modules.

For many Spring Boot applications, Maven or Gradle dependency management plus normal package discipline may be enough.

JPMS is most useful when explicit module boundaries solve a real architecture, packaging, runtime-image, or encapsulation problem.

## 10. Remember This

JPMS lets Java code declare named modules, required dependencies, and exported packages. The main idea is explicit boundaries: a module says what it needs and what it makes available.
