# JFR Enhancements

Java 25 included three Java Flight Recorder (JFR) improvements:

- JEP 509: JFR CPU-Time Profiling (Experimental)
- JEP 518: JFR Cooperative Sampling
- JEP 520: JFR Method Timing & Tracing

Method timing and tracing are executable because a small deterministic JUnit test can create a real `.jfr` recording and inspect it with the JFR consumer API. CPU-time profiling and cooperative sampling remain explanatory because they are profiling-quality/runtime-observability features whose useful behavior depends on platform support, workload shape, and profiling analysis.

## 1. What Problem Does This Feature Solve?

When an application is slow, it is tempting to guess:

```text
maybe the database is slow
maybe JSON parsing is slow
maybe startup is slow
maybe one method is called too often
```

Guessing is not enough. Developers need evidence about what the application was doing while it ran.

Java Flight Recorder helps collect that evidence. It records runtime events from the Java Virtual Machine (JVM) and the application with low overhead, so developers can investigate performance and behavior without filling the code with temporary logging.

## 2. How Was This Commonly Done Before?

Before relying on JFR-style observability, developers often used:

- log statements around suspected slow methods
- manual timers such as `System.nanoTime()`
- debuggers during development
- external profilers
- custom metrics added to application code

Those tools are useful, but each has tradeoffs.

Temporary logging and manual timers require source-code changes. Debuggers are not appropriate for production diagnosis. External profilers may need extra setup. Custom metrics only answer the questions the application was already instrumented to answer.

JFR gives the JVM a built-in way to record many runtime facts in a structured recording.

## 3. What Did Java Introduce?

Java 25 improved JFR in three related areas.

### CPU-Time Profiling

JEP 509 added experimental CPU-time profiling on Linux.

The goal is to sample based on CPU time instead of only elapsed wall-clock time. That helps distinguish code that is actually consuming CPU from code that is waiting, blocked, or sleeping.

This repository explains the feature but does not require it in unit tests. On some platforms the JVM can report that CPU-time method sampling is not supported, and a no-sample result would teach the environment more than the Java feature.

### Cooperative Sampling

JEP 518 changed how JFR samples Java thread stacks.

The goal is stability. Instead of parsing stacks at unsafe arbitrary points, JFR can cooperate with the running thread and reconstruct stack traces at safepoints while reducing safepoint bias.

This repository explains the feature but does not try to prove sampling quality in a tiny test. A meaningful lesson needs a real workload and profiling analysis.

### Method Timing And Tracing

JEP 520 added JFR facilities for method timing and tracing.

The goal is to ask targeted questions such as:

```text
how often did this method run?
how long did it take?
what stack led to this method?
```

This is useful when sampling is too broad and the developer wants to investigate specific methods without modifying source code.

## 4. Terminology In Plain English

JFR:

Java Flight Recorder. A JVM observability system that records runtime events for later analysis.

Recording:

A file, usually ending in `.jfr`, containing events captured while the application ran.

Profiling:

Measuring where an application spends resources such as CPU time or elapsed time.

Sampling:

Periodically observing what the program is doing instead of recording every operation.

CPU time:

Time spent actually using the CPU.

Wall-clock time:

Elapsed real-world time. A thread can consume wall-clock time while waiting for I/O, sleeping, or blocked on a lock.

Safepoint:

A JVM location where a Java thread can be safely inspected or coordinated with the runtime.

Method timing:

Recording how often selected methods run and how long they take.

Method tracing:

Recording selected method invocations with stack information.

## 5. What The Example Shows

[`JfrEnhancementsExamples`](JfrEnhancementsExamples.java) creates a real JFR recording for one selected method:

```text
JfrEnhancementsExamples::tracedWork
```

The example:

- enables the `jdk.MethodTrace` event for the selected method
- enables the `jdk.MethodTiming` event for the same method
- runs a small repeatable workload
- dumps a real `.jfr` file
- reads the recording back with `RecordingFile`
- reports method trace events, method timing events, and the method names found in the recording

That makes JEP 520 executable without pretending that every JFR enhancement is equally suited to a deterministic unit test.

## 6. What The Test Proves

`JfrEnhancementsExamplesTest` verifies that:

- the example writes a real `.jfr` recording
- method tracing records selected method invocations
- method timing reports selected method activity
- the recording identifies `JfrEnhancementsExamples::tracedWork`
- CPU-time profiling remains documented as experimental and platform-dependent
- cooperative sampling remains documented as a profiling-quality improvement

The test does not claim that timing values are performance benchmarks. It only verifies that Java 25's method timing and tracing events can be enabled, recorded, and inspected.

## 7. Realistic Use Case

Imagine a service whose startup has become slower after a dependency upgrade.

JFR method timing can help identify static initializers or selected framework methods that are taking unexpected time. JFR method tracing can show which call path reached a method. CPU-time profiling can help distinguish CPU-heavy work from time spent waiting.

That evidence helps the team decide what to optimize.

## 8. When Not To Use JFR Enhancements

Do not start with JFR for every tiny code question. A unit test or debugger may be simpler during local development.

Do not treat JFR output as automatically self-explanatory. Profiling data still needs interpretation.

Do not enable expensive method tracing broadly in production without care. Targeted tracing is useful, but tracing many methods can add overhead.

## 9. Remember This

JFR is about evidence. Java 25 improved how the JVM records CPU-time samples, how safely it samples stacks, and how directly it can time or trace selected methods. In this repository, method timing and tracing are executable; CPU-time profiling and cooperative sampling remain documented as runtime profiling context.
