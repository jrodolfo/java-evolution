# Reflection

Java 1.1 added reflection so Java code could inspect classes, constructors, methods, and fields at runtime.

## 1. What Problem Does This Feature Solve?

Frameworks and tools often need to work with types they did not know at compile time. Without reflection, they need generated code, naming conventions, or hand-written adapters.

## 2. What Did Java Introduce?

Reflection added APIs in `java.lang.Class` and `java.lang.reflect` for inspecting type metadata and invoking members dynamically.

## 3. Why This Repository Uses Notes

Small reflection calls are executable, but the important lesson is the tradeoff: reflection enables frameworks and tools while weakening compile-time checking and sometimes bypassing encapsulation.

## 4. Remember This

Reflection is powerful infrastructure. Use direct calls when the type is known; use reflection when a tool or framework genuinely needs runtime discovery.
