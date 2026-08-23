# Shutdown Hooks

J2SE 1.3 added shutdown hooks.

## 1. What Problem Does This Feature Solve?

Applications often need a last chance to release resources during normal JVM shutdown.

## 2. What Did Java Introduce?

`Runtime.addShutdownHook(Thread)` registers a thread to run during orderly shutdown.

## 3. What Does The Example Show?

`ShutdownHookExamples` registers a hook that writes a marker file during orderly JVM shutdown.

The test suite runs the example in a child JVM. That keeps the Maven test JVM alive while still exercising the real shutdown behavior. The child process returns normally from `main`, the JVM begins orderly shutdown, and the hook writes the marker.

## 4. Remember This

Shutdown hooks are best-effort cleanup, not a replacement for normal resource management. They are not guaranteed to run after forced termination, `Runtime.halt(...)`, operating-system process kills, or VM crashes.
