# invokedynamic

Java 7 added the `invokedynamic` bytecode instruction and method-handle linkage support.

This is an explanatory learning module. It does not attempt to create an executable source-level demo because ordinary Java source code does not directly spell the `invokedynamic` instruction.

## 1. What Problem Does This Feature Solve?

The original JVM invocation instructions were designed mainly for statically typed Java method calls.

Dynamic language implementations on the JVM had to fit dynamic dispatch into bytecode instructions that expected more static target information. That made dynamic languages harder to implement efficiently.

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

## 4. Why This Repository Uses Notes

Java source can use APIs from `java.lang.invoke`, but that is not the same thing as demonstrating an actual `invokedynamic` instruction.

The feature lives at the bytecode and linkage level. A faithful teaching module should explain that layer directly instead of hiding bytecode concepts behind source strings.

## 5. Remember This

`invokedynamic` was added mainly for language implementers and JVM runtime machinery. Later Java features and runtimes could build on that infrastructure, but the Java 7 feature itself is a bytecode/linkage capability, not everyday application syntax.
