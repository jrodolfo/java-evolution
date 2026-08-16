# Primitive Patterns Preview

Java 23 previewed primitive types in patterns, `instanceof`, and `switch`.

This module is explanatory because the feature was preview in Java 23 and continued to evolve in later releases.

## What Problem Does This Feature Solve?

Java has two broad families of values:

- reference values, such as `String`, `List`, records, and ordinary objects
- primitive values, such as `int`, `long`, `double`, and `boolean`

Pattern matching had been improving the way Java works with reference values.

For example, modern Java can test a value and bind it at the same time:

```java
if (value instanceof String text) {
	return text.toUpperCase();
}
```

Records and `switch` also became part of the pattern-matching story.

Primitive values were still less integrated. That meant pattern matching was becoming more expressive for objects, while primitives still followed older special-case rules.

## What Did Developers Do Before?

Developers usually handled primitive checks through ordinary comparisons, casts, parsing, or switch labels.

That is normal Java, but it means the language has two mental models:

```text
reference values
    -> pattern matching keeps getting more expressive

primitive values
    -> use separate conversion and checking rules
```

The feature aims to reduce that split.

## What Did Java 23 Preview?

Java 23 previewed primitive types in pattern contexts.

The long-term goal is a more uniform model where primitive values can participate naturally in:

- pattern matching
- `instanceof`
- `switch`

The important idea is not only convenience. It is also safety.

Primitive conversions can lose information. For example, converting a large `long` to an `int` may not be safe if the value does not fit.

A primitive pattern can help express:

```text
does this value safely match the requested primitive shape?
if yes, bind it
```

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

A feature included in a JDK release so developers can try it and provide feedback before it becomes final. Primitive patterns were preview in Java 23 and continued as previews in Java 24 and Java 25.

## Why This Module Has Notes Instead Of A Java 23 Example

This repository avoids keeping old preview syntax active across the whole build.

The feature continued changing after Java 23:

```text
Java 23 -> first preview
Java 24 -> second preview
Java 25 -> third preview
```

Because the feature is still preview in this repository's current version range, this module focuses on the problem and the mental model rather than forcing preview syntax into the main build.

## Realistic Use Case

Imagine code that receives numeric values from a generic pipeline.

The code may need to ask:

```text
can this value safely be treated as an int?
can this switch branch handle this primitive value directly?
can the language bind the converted value only if the conversion is safe?
```

Primitive patterns aim to make these questions part of the pattern-matching model instead of scattered conversion code.

## When Not To Use It

Do not reach for preview primitive patterns when ordinary comparisons are clearer.

For simple checks, this is still fine:

```java
if (age >= 18) {
	return "adult";
}
```

The feature matters when primitive values need to participate in broader pattern-matching logic.

## Remember This

Primitive patterns move Java toward one pattern-matching model for both reference values and primitive values. Java 23 introduced the idea as a preview, and Java 24 and Java 25 continued refining it.

Continue with the Java 24 [`primitive_patterns`](../../java24/primitive_patterns/README.md) module for the second-preview step.
