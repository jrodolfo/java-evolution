# Flexible Constructor Bodies Third Preview

Java 24 continued flexible constructor bodies as a third preview feature in JEP 492.

This module is explanatory. It records the Java 24 preview step and points to the Java 25 final runnable example.

## What Problem Does This Feature Solve?

Constructors often need to validate or normalize arguments.

For example, an account constructor may want to reject a blank owner and strip surrounding whitespace before delegating to another constructor.

Conceptually:

```text
validate owner
normalize owner
delegate to another constructor
```

Older Java made that pattern awkward when the constructor needed explicit delegation.

## How Was This Commonly Done Before?

Older Java required explicit constructor invocation to be the first statement in a constructor.

That means calls such as these had to come first:

```java
this(...);
super(...);
```

So this shape was not allowed:

```java
public Account(String owner) {
	String validatedOwner = validate(owner);
	this(validatedOwner, true);
}
```

The constructor wanted to validate before delegation, but the language required delegation first.

Developers worked around this with helper methods, factories, or duplicated validation logic.

## What Did Java 24 Preview?

Java 24 continued the preview of flexible constructor bodies.

The goal is to allow safe statements before explicit constructor invocation when those statements are used for preparation.

The mental model is:

```text
constructor starts
    perform safe validation or preparation
    delegate with this(...) or super(...)
constructor initialization continues
```

This makes constructor code easier to read when validation belongs directly beside construction.

## Important Terminology

**Constructor delegation**

A constructor calling another constructor with `this(...)`, or calling a superclass constructor with `super(...)`.

**Explicit constructor invocation**

The formal term for a `this(...)` or `super(...)` call inside a constructor.

**Validation**

Checking that an argument is acceptable before using it.

**Normalization**

Converting an acceptable argument into the form the object should store, such as trimming whitespace.

**Object under construction**

The object that is currently being initialized by the constructor.

**Preview feature**

A feature included in a JDK release for feedback before finalization. Java 24 continued flexible constructor bodies as a third preview.

## The Safety Rule Still Matters

Flexible constructor bodies do not mean arbitrary constructor code can run before the object is initialized.

The important safety idea is:

```text
safe preparation before delegation is allowed
unsafe use of the object under construction is still prevented
```

For example, validation that uses only constructor parameters is the kind of preparation this feature is designed to support.

Calling instance methods or reading instance fields before the object has been initialized would be a different safety problem.

## Why This Module Has Notes Instead Of A Java 24 Example

Flexible constructor bodies became final in Java 25.

This repository keeps the runnable final example there:

```text
src/main/java/net/jrodolfo/java_evolution/java25/FlexibleConstructorBodiesExamples.java
```

The Java 24 package exists to explain the third-preview step without keeping preview syntax from Java 24 in the normal Maven build.

## What The Test Proves

`FlexibleConstructorBodiesThirdPreviewNotesTest` does not compile preview syntax.

It protects the educational note by checking that the note explains:

- validation or normalization before delegation
- the older first-statement restriction
- `this(...)` and `super(...)`
- object-under-construction safety
- Java 24 third-preview status
- the Java 25 final runnable example

That is the useful test boundary for this explanatory module.

## Realistic Use Case

Imagine an account constructor:

```text
new Account(" Rodolfo ")
    -> reject null or blank owner
    -> strip whitespace
    -> delegate to constructor that sets the default active state
```

The validation belongs close to construction, and the final object should store the normalized value.

The Java 25 example demonstrates that final shape with runnable code and tests.

## When Not To Use It

Do not use constructor-body flexibility to hide complex work inside constructors.

If object creation requires many steps, a factory or builder may still be clearer.

This feature is most useful for small, safe preparation before constructor delegation.

## Remember This

Flexible constructor bodies relax the old "delegation must be first" rule so constructors can perform safe preparation before `this(...)` or `super(...)`. Java 24 was the third preview; Java 25 finalized the feature.
