# Launch Multi-File Source Programs

Java 22 improved the Java source launcher so small programs can span multiple source files.

This feature is documented as an explanatory module because it is command-line launcher behavior. A faithful demo belongs to a small source tree launched with `java Main.java`, not to ordinary Maven-compiled classes.

## What Problem Does This Feature Solve?

Sometimes you want to try a small Java program without creating a Maven or Gradle project first.

For a tiny example, this is useful:

```bash
java Main.java
```

The source launcher compiles and runs the source file for that execution.

The problem appears when the small example grows beyond one file. Before Java 22, the source-launcher model was mainly a single-file experience. That pushed learners back toward a build tool earlier than necessary.

## Single-File Mental Model

The older mental model was:

```text
Main.java
  -> java Main.java
  -> compile for this run
  -> execute
```

That is great for very small programs, scripts, experiments, and teaching examples.

## What Java 22 Changed

Java 22 made the launcher friendlier for multi-file source programs.

Conceptually:

```text
Main.java
Greeting.java
```

If `Main.java` refers to `Greeting`, the launcher can compile the small source set for that run:

```bash
java Main.java
```

The command still starts from the main source file. The difference is that the launched program can involve additional source files.

## How This Differs From Maven Or Gradle

Maven and Gradle are build tools. They manage project layout, dependencies, plugins, test execution, packaging, and repeatable builds.

The source launcher is lighter. It is useful before a program needs that project structure.

```text
source launcher:
  quick local source execution

Maven or Gradle:
  full project build lifecycle
```

The source launcher does not replace build tools for real applications. It reduces setup friction for small programs.

## Why This Module Has No Launcher Test

This repository is a Maven project. Its tests compile source files through the normal Maven lifecycle.

A faithful launcher test would need to create a temporary source tree and spawn a separate `java Main.java` process. That is possible, but it would test command-line process behavior rather than the Java 22 concept itself.

The module therefore documents the command shape and source layout, and the unit test checks the teaching notes.

## When To Use This Feature

Use the source launcher when:

- you are experimenting
- you are teaching or learning
- the program is small
- you do not need a full build lifecycle yet

Use Maven or Gradle when:

- you need dependencies
- you need repeatable builds
- you need packaging
- you need a structured test lifecycle
- the code has become a real project

## Remember This

Java 22 made source-launcher programs less restricted to one file. You can still start with `java Main.java`, but the small program can now involve helper source files before you need a build tool.
