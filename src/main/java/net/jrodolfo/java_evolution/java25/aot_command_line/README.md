# AOT Command-Line Ergonomics

Java 25 introduced Ahead-of-Time (AOT) Command-Line Ergonomics in JEP 514.

This module is executable. It creates a real AOT cache with `-XX:AOTCacheOutput=...`, then runs the same small program with `-XX:AOTCache=...`.

The example proves the command-line workflow. It does not claim a startup-speed result.

## 1. What Problem Does This Feature Solve?

Java applications normally do work during startup:

- discover classes
- load classes
- link classes
- initialize runtime structures
- warm up important execution paths

For long-running server applications, startup cost may not matter much. For command-line tools, serverless functions, short-lived jobs, and applications that scale up frequently, startup time can matter a lot.

Ahead-of-time work means doing some startup-related work before the production run, storing the result, and reusing it later.

The mental model is:

```text
training run
    observes application startup
    creates AOT data

production run
    reuses AOT data
    starts with less runtime preparation
```

## 2. How Was This Commonly Done Before?

Java 24 introduced AOT class loading and linking. The workflow was useful, but creating an AOT cache required more command-line ceremony.

Conceptually, the old workflow had two separate steps:

```bash
java -XX:AOTMode=record \
     -XX:AOTConfiguration=app.aotconf \
     -cp app.jar com.example.App

java -XX:AOTMode=create \
     -XX:AOTConfiguration=app.aotconf \
     -XX:AOTCache=app.aot
```

Then the production run used the cache:

```bash
java -XX:AOTCache=app.aot \
     -cp app.jar com.example.App
```

That workflow is explicit, but it asks the user to manage two AOT modes and a temporary configuration file.

## 3. What Did Java Introduce?

Java 25 added a simpler command-line path for common cases.

Instead of asking the user to run the record step and create step manually, the launcher can create the AOT cache output in one command:

```bash
java -XX:AOTCacheOutput=app.aot \
     -cp app.jar com.example.App
```

The production run still uses the cache:

```bash
java -XX:AOTCache=app.aot \
     -cp app.jar com.example.App
```

The important improvement is not a new Java language feature. The improvement is operational ergonomics: the common workflow becomes easier to use and harder to misconfigure.

## 4. Terminology In Plain English

AOT:

Ahead-of-time. Work done before the normal production run so startup can do less work later.

AOT cache:

A file containing runtime data that the JVM can reuse to accelerate startup.

Training run:

A run of the application used to observe what the application does during startup.

Production run:

The real run of the application, using the AOT cache created earlier.

Command-line ergonomics:

Making the command-line interface easier to use for common workflows without removing the more explicit advanced workflow.

## 5. How The Workflow Changes

Before Java 25, the common path looked like:

```text
record startup behavior
        |
        v
write AOT configuration
        |
        v
create AOT cache from configuration
        |
        v
run application with AOT cache
```

With Java 25, the common path becomes:

```text
run application with -XX:AOTCacheOutput=app.aot
        |
        v
JVM performs the training and cache-creation workflow
        |
        v
run application with -XX:AOTCache=app.aot
```

Advanced workflows can still use the explicit steps when they need more control.

## 6. What The Example Shows

[`AotCommandLineErgonomicsExamples`](AotCommandLineErgonomicsExamples.java) writes a tiny source-launched Java program into a temporary directory.

The test then launches it twice:

```bash
java -Xlog:aot -XX:AOTCacheOutput=probe.aot AotCacheProbe.java
java -Xlog:aot -XX:AOTCache=probe.aot AotCacheProbe.java
```

The first command runs the program and creates a real AOT cache file. The second command runs the program with that cache.

The example checks:

- the training run exits successfully
- the application code runs during training
- the AOT cache file exists and is non-empty
- the production run exits successfully
- the JVM reports that it opened the AOT cache
- the application code still runs during production

## 7. What The Test Does Not Prove

`AotCommandLineErgonomicsExamplesTest` does not benchmark startup time.

Startup measurements depend on the operating system, storage, CPU, JVM configuration, cache state, application shape, and repeated-run methodology. A tiny Maven test would make a poor performance lab.

The test proves the workflow: Java 25 can create and use an AOT cache through the simplified command-line options.

## 8. Realistic Use Case

Imagine a command-line application packaged as `app.jar`.

During build or deployment preparation, you create a cache:

```bash
java -XX:AOTCacheOutput=app.aot -cp app.jar com.example.App
```

Then production starts the application with:

```bash
java -XX:AOTCache=app.aot -cp app.jar com.example.App
```

The intended benefit is faster startup because the JVM can reuse data prepared ahead of time. Whether the benefit matters should be measured for the real application and deployment environment.

## 9. When Not To Use It

Do not use AOT cache workflows blindly. If startup time is not a problem, the extra build/deployment step may not be worth it.

Do not assume it improves every workload equally. Measure startup behavior for the actual application and deployment environment.

Do not replace normal code clarity or good application design with runtime tuning. AOT is an operational optimization, not a substitute for understandable code.

## 10. Remember This

Java 25 did not make AOT a source-code feature. It made a common AOT cache workflow easier: use `-XX:AOTCacheOutput=...` to create the cache, then use `-XX:AOTCache=...` to run with it.
