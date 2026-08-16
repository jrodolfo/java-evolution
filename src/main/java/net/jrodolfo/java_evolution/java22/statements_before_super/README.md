# Statements Before `super(...)` Preview

Java 22 previewed statements before `super(...)`.

This module is explanatory because the Java 22 feature was preview. The final runnable example belongs to Java 25, where the feature became part of Flexible Constructor Bodies.

## What Problem Does This Feature Solve?

Constructors often need to validate or prepare values before creating an object.

For example, imagine a subclass constructor that receives a user name:

```text
new CustomerAccount(" Rodolfo ")
```

Before passing that value to a superclass constructor, the subclass may want to:

- reject `null`
- reject blank text
- remove surrounding whitespace
- normalize the value into the shape required by the parent class

That sounds ordinary, but old Java constructor rules made this awkward when the constructor needed to call `super(...)` or `this(...)` explicitly.

## How Did Constructors Work Before?

Before this feature line, an explicit constructor invocation had to be the first statement in a constructor body.

That meant this shape was not allowed:

```java
public CustomerAccount(String owner) {
	String normalizedOwner = validate(owner);
	super(normalizedOwner);
}
```

The problem is not validation itself. The problem is where the validation can happen.

If `super(...)` must be first, then the constructor cannot compute `normalizedOwner` in ordinary statements before calling the parent constructor.

Developers often had to move logic into helper methods inside the `super(...)` argument list:

```java
public CustomerAccount(String owner) {
	super(validate(owner));
}
```

That can work, but it becomes harder to read when the preparation has several steps.

## What Did Java 22 Preview?

Java 22 previewed a relaxation of the constructor rule.

The idea was:

```text
allow safe statements before an explicit super(...) or this(...) call
```

This made constructor code easier to read when arguments needed validation or preparation before delegation.

## What Safety Rule Still Matters?

The constructor still cannot use the object being created before the superclass constructor has run.

In plain English:

```text
You may prepare constructor arguments.
You may not use `this` as if the object already exists.
```

That distinction matters because the superclass is responsible for initializing part of the object. Java must prevent code from observing or using a partially initialized object.

So the feature is not a general permission to do anything before `super(...)`.

It is a carefully limited permission to do work that does not depend on the object under construction.

## Why This Module Has Notes Instead Of A Java 22 Example

This repository compiles with a current JDK and avoids keeping old preview syntax active across the whole build.

The faithful runnable example is in Java 25:

```text
src/main/java/net/jrodolfo/java_evolution/java25/FlexibleConstructorBodiesExamples.java
```

That class demonstrates validation and normalization before constructor delegation using the final feature.

The Java 22 module explains the preview stage and the problem that motivated the feature.

## Realistic Use Case

Consider a subclass that must pass a validated value to its parent:

```text
CustomerAccount(String owner)
    -> validate owner
    -> trim owner
    -> call Account(owner, active)
```

The validation belongs close to the public constructor because it describes what that constructor accepts.

Flexible constructor bodies make that code easier to write directly instead of hiding preparation inside nested helper calls.

## When Not To Use It

If a constructor does not delegate explicitly, or if the argument can be passed directly without preparation, there is no need to make the constructor more complex.

For simple constructors, keep the code simple:

```java
public Account(String owner) {
	this.owner = owner;
}
```

Use the feature when constructor arguments need clear validation or preparation before an explicit `super(...)` or `this(...)` call.

## Remember This

Statements before `super(...)` let a constructor prepare safe argument values before constructor delegation. The constructor still cannot use the object being created before superclass initialization completes.
