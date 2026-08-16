# AOT Command-Line Ergonomics

Java 25 introduced Ahead-of-Time (AOT) Command-Line Ergonomics in JEP 514.

This is an explanatory learning module. It does not try to create an AOT cache during the Maven test suite because AOT cache creation is an operational workflow around launching an application, training it, and measuring startup behavior. A tiny unit test would not prove the feature in a useful way.

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
    starts faster
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

## 6. Why This Repository Uses Notes

This feature is about JVM startup behavior, command-line options, and operational measurement.

A meaningful demonstration would need:

- an application packaged for launch
- a training run
- an AOT cache file
- a production run using the cache
- startup measurements before and after

That is valuable in a deployment or performance lab, but it does not fit a tiny deterministic JUnit test. A unit test can protect the explanation, but it should not pretend to prove startup performance.

## 7. What The Test Proves

`AotCommandLineErgonomicsNotesTest` does not create an AOT cache.

Instead, it verifies that the notes preserve the important learning points:

- AOT is about startup work
- Java 25 simplifies cache creation for common workflows
- `-XX:AOTCacheOutput` is the key command-line option
- this is an operational feature, not ordinary Java source-code syntax

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

The intended benefit is faster startup because the JVM can reuse data prepared ahead of time.

## 9. When Not To Use It

Do not use AOT cache workflows blindly. If startup time is not a problem, the extra build/deployment step may not be worth it.

Do not assume it improves every workload equally. Measure startup behavior for the actual application and deployment environment.

Do not replace normal code clarity or good application design with runtime tuning. AOT is an operational optimization, not a substitute for understandable code.

## 10. Remember This

Java 25 did not make AOT a source-code feature. It made a common AOT cache workflow easier: use `-XX:AOTCacheOutput=...` to create the cache, then use `-XX:AOTCache=...` to run with it.
