# Reflection

Java 1.1 added reflection so Java code could inspect classes, constructors, methods, and fields at runtime.

## 1. What Problem Does This Feature Solve?

Frameworks and tools often need to work with types they did not know at compile time. Without reflection, they need generated code, naming conventions, or hand-written adapters.

## 2. What Did Java Introduce?

Reflection added APIs in `java.lang.Class` and `java.lang.reflect` for inspecting type metadata and invoking members dynamically.

## 3. What Does The Example Show?

`ReflectionExamples` shows four core reflection operations:

- reading the runtime class behind an object reference
- discovering field metadata by name
- loading a class by name and calling a constructor
- invoking a method selected by name at runtime

The example intentionally avoids annotations, because annotations were added later in Java 5. It also avoids making private members accessible, because the core Java 1.1 lesson is runtime discovery; access-bypassing belongs with later encapsulation and security discussions.

## 4. What Is The Tradeoff?

Reflection moves some checks from compile time to runtime. A normal method call fails to compile when the method name is wrong. A reflective method call can compile and then fail later with exceptions such as `NoSuchMethodException`.

That tradeoff is why reflection is common in frameworks, tools, serializers, dependency injection containers, test libraries, and application servers, but ordinary application code should prefer direct calls when the type is already known.

## 5. Remember This

Reflection is powerful infrastructure. Use direct calls when the type is known; use reflection when a tool or framework genuinely needs runtime discovery.
