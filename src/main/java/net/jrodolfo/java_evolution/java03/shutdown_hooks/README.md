# Shutdown Hooks

J2SE 1.3 added shutdown hooks.

## 1. What Problem Does This Feature Solve?

Applications often need a last chance to release resources during normal JVM shutdown.

## 2. What Did Java Introduce?

`Runtime.addShutdownHook(Thread)` registers a thread to run during orderly shutdown.

## 3. Why This Repository Uses Notes

A faithful test would need to terminate a JVM process. Running hooks inside the main Maven test JVM would be disruptive and misleading.

## 4. Remember This

Shutdown hooks are best-effort cleanup, not a replacement for normal resource management.
