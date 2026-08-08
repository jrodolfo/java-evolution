# Foreign Function and Memory API

Java 22 finalized the Foreign Function and Memory API in JEP 454.

The API lets Java code call native functions and work with memory outside the Java heap using supported JDK APIs. It is one of the clearest examples of Java evolving as a platform, not only as a language.

## The Problem

Most Java code should stay in Java. The JVM gives us portability, safety, garbage collection, strong tooling, and a large standard library.

Sometimes, however, a Java application needs to cross the Java boundary:

- call an operating-system function
- reuse an existing C library
- talk to native hardware or performance libraries
- read or write memory layouts that are not Java objects

Before the Foreign Function and Memory API, the usual answer was JNI, the Java Native Interface.

JNI is powerful, but it is not beginner-friendly. It usually requires C headers, generated glue code, native compilation, careful deployment, and a lot of discipline around memory safety.

## This Is Not RPC Or RMI

Foreign function calls are local native calls. Java calls a function available to the same process on the same machine.

RPC, remote procedure call, and RMI, remote method invocation, are different. They involve another process, runtime, or machine, usually over a network.

The Foreign Function and Memory API solves a local interop problem:

```text
Java code -> native function in the same process
```

RPC and RMI solve a remote communication problem:

```text
program A -> network -> program B
```

## What Foreign Function Means

A foreign function is a function outside normal Java code. In these examples, the foreign functions are small C standard-library functions:

- `atoi`: parses an integer from a C string
- `strlen`: returns the length of a C string

The examples use standard library functions so the repository does not need to ship or compile a custom native library.

## What Foreign Memory Means

Native functions usually do not understand Java objects.

For example, C functions expect a string as a pointer to bytes ending with a null byte. Java `String` is not that representation.

The example uses an `Arena` to allocate native memory:

```java
try (var arena = Arena.ofConfined()) {
	var cString = arena.allocateFrom("25");
}
```

The arena controls the lifetime of that memory. When the try-with-resources block closes, the confined arena releases the native memory allocated inside it.

## How The Java API Fits Together

The examples use these Java 22 API pieces:

- `Linker.nativeLinker()`: gets a linker for native calls on the current platform
- `defaultLookup()`: searches for native symbols available by default
- `MemorySegment`: represents a native memory address or memory region
- `FunctionDescriptor`: describes the native function signature
- `downcallHandle(...)`: creates a method handle that Java can invoke

For `atoi`, the C shape is conceptually:

```text
int atoi(const char *text)
```

The Java descriptor maps that to:

```java
FunctionDescriptor.of(JAVA_INT, ADDRESS)
```

For `strlen`, the C shape is conceptually:

```text
size_t strlen(const char *text)
```

The Java descriptor maps that to:

```java
FunctionDescriptor.of(JAVA_LONG, ADDRESS)
```

## Why These Examples Are Small

Real native integration can become platform-specific quickly. Library names, symbol availability, memory layouts, and operating-system conventions can differ.

The examples here intentionally use tiny native calls to show the mechanics:

```text
Java string
  -> native memory segment
  -> native function
  -> Java result
```

That is enough to understand why the API matters without turning this repository into a native build project.

## Native Access

The Foreign Function and Memory API includes restricted operations. This project runs tests with:

```text
--enable-native-access=ALL-UNNAMED
```

That makes native access explicit for the unnamed module used by this Maven project.
