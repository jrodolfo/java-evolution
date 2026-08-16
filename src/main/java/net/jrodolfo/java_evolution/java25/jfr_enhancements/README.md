# JFR Enhancements

Java 25 included three Java Flight Recorder (JFR) improvements:

- JEP 509: JFR CPU-Time Profiling (Experimental)
- JEP 518: JFR Cooperative Sampling
- JEP 520: JFR Method Timing & Tracing

This is an explanatory learning module. It does not try to prove profiling behavior inside the Maven test suite because JFR is an observability tool for running applications. A useful demonstration needs a real process, a recording, and analysis of the generated `.jfr` file.

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

### Cooperative Sampling

JEP 518 changed how JFR samples Java thread stacks.

The goal is stability. Instead of parsing stacks at unsafe arbitrary points, JFR can cooperate with the running thread and reconstruct stack traces at safepoints while reducing safepoint bias.

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

## 5. What The Workflow Looks Like

A JFR workflow usually has three steps:

```text
start recording
      |
      v
run the application workload
      |
      v
inspect the recording
```

Conceptually:

```bash
java -XX:StartFlightRecording:filename=app.jfr -jar app.jar

jfr view app.jfr
```

For method timing and tracing, the recording can select specific methods or groups of methods. For example, the filter syntax uses a shape similar to a method reference:

```text
com.example.OrderService::calculateTotal
```

The important idea is that JFR records runtime evidence. It is not just a logging library, and it is not a Java language feature.

## 6. Why This Repository Uses Notes

These features are about observing a running JVM.

A meaningful demonstration would need:

- a running application
- a workload that produces interesting behavior
- a JFR recording
- analysis using `jfr`, JDK Mission Control, or another tool
- interpretation of timing, tracing, or profiling results

That does not fit a small deterministic JUnit test. The test in this repository protects the explanation rather than pretending to verify profiling accuracy.

## 7. What The Test Proves

`JfrEnhancementsNotesTest` does not start a JFR recording.

Instead, it verifies that the notes preserve the important learning points:

- JFR is for runtime observability
- CPU-time profiling is experimental and Linux-specific
- cooperative sampling is about safer stack sampling
- method timing and tracing target selected methods
- this is operational documentation rather than ordinary executable example code

## 8. Realistic Use Case

Imagine a service whose startup has become slower after a dependency upgrade.

JFR method timing can help identify static initializers or selected framework methods that are taking unexpected time. JFR method tracing can show which call path reached a method. CPU-time profiling can help distinguish CPU-heavy work from time spent waiting.

That evidence helps the team decide what to optimize.

## 9. When Not To Use JFR Enhancements

Do not start with JFR for every tiny code question. A unit test or debugger may be simpler during local development.

Do not treat JFR output as automatically self-explanatory. Profiling data still needs interpretation.

Do not enable expensive method tracing broadly in production without care. Targeted tracing is useful, but tracing many methods can add overhead.

## 10. Remember This

JFR is about evidence. Java 25 improved how the JVM records CPU-time samples, how safely it samples stacks, and how directly it can time or trace selected methods.
