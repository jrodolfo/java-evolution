# Flexible Constructor Bodies Second Preview

Java 23 continued flexible constructor bodies as a second preview feature.

This module is explanatory because the feature was still preview in Java 23. The final runnable example belongs to Java 25.

## What Problem Does This Feature Solve?

Constructors often need to validate or prepare arguments before delegating to another constructor.

For example, a public constructor might receive this value:

```text
" Rodolfo "
```

Before passing it to another constructor, the code may need to:

- reject `null`
- reject blank text
- trim surrounding whitespace
- normalize the value into the shape the object requires

The problem is that older Java constructor rules made this awkward when the constructor had an explicit `super(...)` or `this(...)` call.

## How Did Constructors Work Before?

Before this feature line, an explicit constructor invocation had to be the first statement in the constructor body.

That meant this shape was not allowed:

```java
public CustomerAccount(String owner) {
	String normalizedOwner = validate(owner);
	this(normalizedOwner, true);
}
```

The `this(...)` call had to appear first, so the validation could not be written as ordinary statements before delegation.

Developers often moved validation into helper methods:

```java
public CustomerAccount(String owner) {
	this(validate(owner), true);
}
```

That works for small cases. It becomes harder to read when preparation has several steps.

## Relationship To Java 22

Java 22 previewed this feature line as statements before `super(...)`.

Java 23 continued the work under the broader idea of flexible constructor bodies.

The theme is the same:

```text
let constructors prepare safe values before delegation
without allowing unsafe use of the partially constructed object
```

## What Did Java 23 Refine?

Java 23 continued the preview and refined the feature before finalization.

The goal was still practical:

```text
validate or prepare constructor arguments
then delegate to super(...) or this(...)
```

The important part is that this is not a permission to do arbitrary work before initialization.

It is a carefully limited relaxation of the old first-statement rule.

## Important Terminology

**Constructor delegation**

Calling another constructor from the current constructor, using either `this(...)` or `super(...)`.

**Explicit constructor invocation**

The explicit `this(...)` or `super(...)` call in a constructor.

**Initialization safety**

The rule that Java must prevent code from using an object before the superclass and object state have been initialized safely.

**Partially constructed object**

An object whose construction has started but whose superclass initialization or own initialization is not complete yet.

**Preview feature**

A feature included in a JDK release so developers can try it and provide feedback before it becomes final. Flexible constructor bodies were preview in Java 23 and Java 24, then finalized in Java 25.

## Why This Module Has Notes Instead Of A Java 23 Example

This repository avoids keeping old preview syntax active across the whole build.

The final runnable example is in Java 25:

```text
src/main/java/net/jrodolfo/java_evolution/java25/FlexibleConstructorBodiesExamples.java
```

That class demonstrates validation and normalization before constructor delegation using the final feature.

The Java 23 module explains the second-preview stage and connects the Java 22 preview to the Java 25 final example.

## Realistic Use Case

Imagine an account type with a public constructor that accepts user input:

```text
new Account(" Rodolfo ")
```

The public constructor wants to normalize the value and delegate to a more complete constructor:

```text
validate owner
trim owner
delegate to Account(owner, active)
```

Flexible constructor bodies make that flow readable while preserving initialization safety.

## When Not To Use It

If a constructor can assign fields directly, or if no validation is needed before delegation, keep the constructor simple.

For example:

```java
public Account(String owner) {
	this.owner = owner;
}
```

The feature matters when constructor arguments need clear validation or preparation before an explicit `super(...)` or `this(...)` call.

## Remember This

Flexible constructor bodies let constructors prepare safe argument values before constructor delegation. Java 23 refined the preview; Java 25 is where this repository shows the final runnable form.
