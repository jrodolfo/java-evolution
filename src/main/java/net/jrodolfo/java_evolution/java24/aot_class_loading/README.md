# Ahead-of-Time Class Loading

Java 24 introduced ahead-of-time class loading and linking in JEP 483.

This module is executable. It compiles a tiny Java application, packages it as a JAR, records AOT configuration, creates an AOT cache, and runs the application with that cache.

The example proves the workflow. It does not benchmark startup speed.

## What Problem Does This Feature Solve?

When a Java application starts, the JVM does work before the application can do useful work.

Part of that startup work is related to classes:

```text
start application
  -> find classes
  -> load class data
  -> link classes
  -> initialize classes when needed
  -> run application code
```

For small applications this may not matter much. For larger services, command-line tools, serverless functions, or short-lived processes, startup time can be important.

## What Is Class Loading?

Class loading is the process of finding class data and bringing it into the JVM.

For example, when code first needs `com.example.OrderService`, the JVM must locate the compiled class data for that type and load it.

## What Is Linking?

Linking prepares a loaded class so it can be used safely by the JVM.

At a high level, linking includes work such as verifying class-file structure, preparing storage for static fields, and resolving symbolic references when needed.

The details are JVM-level, but the learner-friendly idea is simple:

```text
loading finds the class
linking prepares the class for use
```

## What Does Ahead-of-Time Mean Here?

Ahead-of-time means some work that would normally happen while the application is starting can be prepared earlier.

Conceptually:

```text
usual startup:
  run application
    -> load and link many classes during startup

ahead-of-time preparation:
  prepare class-loading/linking data earlier
    -> run application with less startup work remaining
```

The goal is not to change Java source syntax. The goal is to reduce startup cost for suitable deployments.

## What Java 24 Introduced

Java 24 introduced a way for the JVM to prepare class loading and linking ahead of application execution.

The explicit workflow has three steps:

```bash
java -XX:AOTMode=record \
     -XX:AOTConfiguration=app.aotconf \
     -cp app.jar com.example.App

java -XX:AOTMode=create \
     -XX:AOTConfiguration=app.aotconf \
     -XX:AOTCache=app.aot \
     -cp app.jar

java -XX:AOTCache=app.aot \
     -cp app.jar com.example.App
```

The first run is a training run. The second command creates the cache. The third run uses the cache.

## What The Example Shows

[`AotClassLoadingExamples`](AotClassLoadingExamples.java) performs that workflow with child processes:

- writes a tiny Java application that uses `List` and streams
- compiles it with `javac`
- packages it with `jar`
- records AOT configuration with `-XX:AOTMode=record`
- creates an AOT cache with `-XX:AOTMode=create`
- runs with `-XX:AOTMode=on` and `-XX:AOTCache=...`

The final run uses `-XX:AOTMode=on` so cache problems fail loudly instead of silently falling back.

## What The Test Proves

`AotClassLoadingExamplesTest` verifies that:

- `javac` and `jar` complete successfully
- the training run creates a non-empty `.aotconf` file
- the create step creates a non-empty `.aot` cache file
- the final run opens the AOT cache
- the final run reports `Using AOT-linked classes: true`
- the application still runs normally

The test intentionally does not measure startup time. Timing depends on machine, storage, JVM configuration, cache state, and repeated-run methodology.

## Relation To Java 25

Java 25 continues the ahead-of-time story with command-line ergonomics.

Read this module first to understand the explicit Java 24 workflow:

```text
record configuration -> create cache -> run with cache
```

Then read the Java 25 `aot_command_line` module to see how `-XX:AOTCacheOutput=...` simplifies the common path.

## Remember This

Ahead-of-time class loading is about moving some class loading and linking work out of the critical startup path. It is a JVM startup feature, not a Java syntax feature.
