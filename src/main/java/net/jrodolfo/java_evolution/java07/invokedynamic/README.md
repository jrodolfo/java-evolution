# invokedynamic

Java 7 added the `invokedynamic` bytecode instruction and method-handle linkage support.

This is an executable linkage and bytecode-inspection module. It does not pretend that ordinary Java source code directly spells the `invokedynamic` instruction. Instead, it demonstrates the Java 7 `java.lang.invoke` building blocks and then inspects compiled bytecode that contains real `invokedynamic` instructions.

## 1. What Problem Does This Feature Solve?

The original JVM invocation instructions were designed mainly for statically typed Java method calls.

Dynamic language implementations on the JVM had to fit dynamic dispatch into bytecode instructions that expected more static target information. That made dynamic languages harder to implement efficiently.

The traditional instructions were designed around Java's known method and
receiver relationships. A dynamic language may decide the target only at
runtime based on the value, type, or call-site state, so it needs a runtime
linkage point rather than a fully predetermined method reference.

`invokedynamic` provides that linkage point. The JVM asks a bootstrap method to
choose a target, then can reuse the linked target until the call site changes.

## 2. What Did Java Introduce?

Java 7 introduced:

- the `invokedynamic` bytecode instruction
- bootstrap methods for linking dynamic call sites
- method handles
- call site types in `java.lang.invoke`

The basic lifecycle is:

```text
unlinked dynamic call site
        |
        | first execution asks bootstrap method to link the call site
        v
linked call site with a method-handle target
```

## 3. Terminology In Plain English

Dynamic call site:

An `invokedynamic` instruction location whose target is linked at runtime.

Bootstrap method:

Code that the JVM calls to decide how a dynamic call site should be linked.

Method handle:

A typed, directly executable reference to an underlying method, constructor, field, or similar operation.

Call site:

An object representing the target associated with a dynamic call site.

## 4. What Does The Example Show?

The executable example builds `ConstantCallSite` and `MutableCallSite`
examples with `MethodHandle`, `MethodType`, and `dynamicInvoker()`. These are
Java 7-era APIs and directly demonstrate the linkage machinery without using
later lambda syntax or APIs. Later Java features such as lambdas can use
`invokedynamic`, but that later source is outside this Java 7 example.

## 5. Remember This

`invokedynamic` was added mainly for language implementers and JVM runtime machinery. Later Java features and runtimes could build on that infrastructure, but the Java 7 feature itself is a bytecode/linkage capability, not everyday application syntax.
