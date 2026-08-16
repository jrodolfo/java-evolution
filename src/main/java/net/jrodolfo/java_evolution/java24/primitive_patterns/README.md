# Primitive Patterns Second Preview

Java 24 continued primitive types in patterns, `instanceof`, and `switch` as a second preview feature in JEP 488.

This module is explanatory. It records the Java 24 step in the preview history without adding preview syntax to the normal Maven build.

## What Problem Does This Feature Solve?

Java has reference types and primitive types.

Reference types are object-oriented types such as:

- `String`
- records
- arrays
- ordinary classes

Primitive types are built-in value types such as:

- `int`
- `long`
- `double`
- `char`
- `boolean`

Modern Java pattern matching had been growing around reference values. For example:

```java
if (value instanceof String text) {
	return text.toUpperCase();
}
```

That syntax tests the value and gives the programmer a useful variable in one step.

Primitive values were less integrated with this model. That left Java with a split mental model:

```text
reference values
    -> use richer pattern matching

primitive values
    -> use separate comparisons, conversions, and switch handling
```

Primitive patterns aim to reduce that split.

## How Was This Commonly Done Before?

Developers usually handled primitive values with ordinary comparisons, casts, parsing, and switch labels.

That is normal Java, and it is still often the clearest choice.

The limitation appears when code wants pattern matching to reason about primitive values and reference values in a consistent way.

For example, numeric conversions can be risky:

```text
long value -> int value
```

That conversion is safe only when the `long` value fits inside the `int` range.

Without primitive patterns, the programmer has to express that check separately.

## What Did Java 24 Preview?

Java 24 continued the work of letting primitive values participate in pattern contexts such as:

- pattern matching
- `instanceof`
- `switch`

The important idea is not just shorter syntax.

The feature moves Java toward a more uniform pattern model where the language can ask:

```text
does this value match the requested primitive type safely?
if yes, bind the converted value
```

That is useful when primitive values appear in broader pattern-matching logic.

## Important Terminology

**Primitive type**

A built-in value type such as `int`, `long`, `double`, `char`, or `boolean`.

**Reference type**

A type whose values are object references, such as `String`, records, arrays, or ordinary classes.

**Pattern matching**

A language feature that tests whether a value has a particular shape and, when the test succeeds, binds useful data.

**Binding**

Creating a variable from a successful pattern match.

**Safe conversion**

A conversion that can happen without losing information or changing the meaning of the value.

**Preview feature**

A feature included in a JDK release for feedback before finalization. Java 24 continued primitive patterns as a second preview.

## Why This Module Has Notes Instead Of A Java 24 Example

This repository avoids compiling preview syntax as part of the normal build.

The feature continued across multiple preview rounds:

```text
Java 23 -> first preview
Java 24 -> second preview
Java 25 -> third preview
```

Because the feature is still preview in this repository's current version range, this module focuses on the problem, terminology, and mental model.

## What The Test Proves

`PrimitivePatternsSecondPreviewNotesTest` does not test preview syntax.

It protects the educational note by checking that the note explains:

- primitive values and reference values
- pattern matching, `instanceof`, and `switch`
- safe primitive conversion before binding
- Java 24 second-preview status
- the Java 25 third-preview continuation

That is the useful test boundary for this explanatory module.

## Realistic Use Case

Imagine a generic rule engine that receives mixed values and needs to branch on their shape:

```text
value arrives
    -> is it safely usable as an int?
    -> is it a long that should stay a long?
    -> should switch handle this primitive value directly?
```

Primitive patterns aim to let those questions participate in Java's pattern-matching model instead of living as separate conversion code.

## When Not To Use It

Do not use primitive patterns just because a value is primitive.

For simple checks, ordinary Java remains clearer:

```java
if (age >= 18) {
	return "adult";
}
```

The feature matters when primitive values need to fit into broader pattern-matching logic.

## Remember This

Primitive patterns move Java toward one pattern-matching model for both reference and primitive values. Java 24 was the second preview step, continuing the Java 23 preview and leading to the Java 25 third preview.
