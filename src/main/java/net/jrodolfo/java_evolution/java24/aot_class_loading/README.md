# Ahead-of-Time Class Loading

Java 24 introduced ahead-of-time class loading and linking in JEP 483.

This feature is documented as an explanatory module because it is about JVM startup behavior and deployment preparation. A normal unit test can check the learning notes, but it cannot faithfully prove startup improvement without a separate runtime workflow and measurement setup.

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

The practical result is a runtime optimization path: deployments can prepare class metadata in advance so application startup has less work to do.

## Why This Is Notes-Only

This repository focuses on small, didactic examples. Ahead-of-time class loading is not naturally demonstrated by a plain method call.

A faithful demonstration would require a command-line workflow, an application run that records or creates cached data, and a second run that uses the prepared data. Measuring the benefit would also depend on machine, application shape, JVM options, and warm/cold startup conditions.

For that reason, this module teaches the mental model and keeps the test focused on the important concepts.

## Relation To Java 25

Java 25 continues the ahead-of-time story with command-line ergonomics. The Java 25 `aot_command_line` module explains how the workflow became easier to use from the command line.

Read this module first to understand the runtime problem. Then read the Java 25 module to see the tooling improvement.

## Remember This

Ahead-of-time class loading is about moving some class loading and linking work out of the critical startup path. It is a JVM startup feature, not a Java syntax feature.
