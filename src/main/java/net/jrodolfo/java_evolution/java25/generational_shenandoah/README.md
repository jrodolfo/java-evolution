# Generational Shenandoah

Java 25 introduced Generational Shenandoah as a product feature in JEP 521.

This is an explanatory learning module. It does not try to prove garbage-collector behavior with a unit test because meaningful garbage-collector evaluation needs a real workload, JVM options, garbage-collection logs, and application measurements.

## 1. What Problem Does This Feature Solve?

Java programs create objects on the heap:

- request objects
- strings
- collections
- records
- framework objects
- temporary objects used during calculations

When those objects are no longer reachable, the Java Virtual Machine (JVM) can reclaim their memory. That process is garbage collection.

Garbage collection solves a memory-management problem: Java developers usually do not manually free objects, but the JVM still needs to find unused objects and reuse their memory.

The challenge is that garbage collection itself costs work. It can consume CPU time, memory bandwidth, and sometimes application pause time.

## 2. How Was This Commonly Handled Before?

Garbage collectors use different strategies.

Some collectors focus on throughput. Some focus on low pause times. Some divide the heap into generations. Some use concurrent work so the application can keep running while the collector does much of its job.

Shenandoah is a low-pause garbage collector. Its goal is to keep pause times short by doing much of the collection work concurrently with the application.

Before Java 25, Shenandoah already existed, but its generational mode was experimental.

That meant using the generational mode required an experimental unlock flag:

```bash
java -XX:+UseShenandoahGC \
     -XX:+UnlockExperimentalVMOptions \
     -XX:ShenandoahGCMode=generational ...
```

## 3. What Did Java Introduce?

Java 25 changed Shenandoah's generational mode from experimental to product.

In Java 25, the generational mode can be selected without unlocking experimental VM options:

```bash
java -XX:+UseShenandoahGC \
     -XX:ShenandoahGCMode=generational ...
```

This is important, but it is also limited:

- Java 25 made generational Shenandoah a product feature
- Java 25 removed the need for `-XX:+UnlockExperimentalVMOptions`
- Java 25 did not make generational mode the default Shenandoah mode

## 4. Terminology In Plain English

Garbage collection:

The JVM process of finding objects that are no longer reachable and reclaiming their memory.

Heap:

The memory area where Java objects live.

Pause time:

Time when application threads are stopped so the JVM can do some garbage-collection work safely.

Low-pause collector:

A garbage collector designed to keep pauses short, often by doing more work concurrently while the application continues running.

Shenandoah:

A low-pause garbage collector in the HotSpot JVM.

Generational garbage collection:

A strategy that divides heap management by object age.

Young generation:

The area or category for recently created objects.

Old generation:

The area or category for objects that have survived long enough to be considered longer-lived.

Product feature:

A supported JVM feature that can be used without unlocking experimental VM options.

Default:

The behavior used automatically when no special option is provided. In Java 25, generational Shenandoah is a product feature, but it is not the default Shenandoah mode.

## 5. Why Generations Help

Many Java objects die young.

For example:

```text
handle request
    create temporary strings
    create temporary DTOs
    create temporary collections
request finishes
    many temporary objects are no longer needed
```

Generational collectors use that observation.

The rough idea is:

```text
young objects
    collected more often

old objects
    collected less often
```

If most newly created objects die quickly, collecting young objects separately can be more efficient than treating every object the same way.

## 6. Why This Repository Uses Notes

Garbage collector behavior is not ordinary Java syntax.

A faithful demonstration would need:

- a realistic allocation workload
- JVM garbage-collector options
- before/after runs
- GC logs
- heap and pause-time measurements
- interpretation of workload-specific results

A small JUnit test can allocate objects, but that would not prove that Generational Shenandoah improved anything. It would only create artificial garbage.

## 7. What The Test Proves

`GenerationalShenandoahNotesTest` does not test the garbage collector.

Instead, it verifies that the notes preserve the important learning points:

- Shenandoah is a low-pause garbage collector
- generational collection uses the observation that many objects die young
- Java 25 made the generational mode a product feature
- the mode is selected with `-XX:ShenandoahGCMode=generational`
- it is not the default Shenandoah mode in Java 25

## 8. Realistic Use Case

Generational Shenandoah can matter for services that allocate many short-lived objects but also care about pause times.

Examples include:

- web services with many request-scoped temporary objects
- data-processing applications with large allocation rates
- latency-sensitive services where long pauses are problematic
- applications already using Shenandoah that want to evaluate the generational mode

The right way to evaluate it is to run the real workload with GC logging enabled and compare memory behavior, pause times, throughput, and CPU usage.

## 9. When Not To Use It

Do not switch garbage collectors or modes blindly. Garbage-collector tuning depends on workload behavior.

Do not judge a garbage collector from a tiny unit test. Use realistic application traffic or benchmark workloads.

Do not confuse "product feature" with "default behavior." Java 25 made generational Shenandoah easier to select, but it did not make it the default Shenandoah mode.

## 10. Remember This

Generational Shenandoah combines Shenandoah's low-pause design with the generational idea that many objects die young. Java 25 made this mode a product feature, but it remains a runtime option that should be evaluated with real measurements.
