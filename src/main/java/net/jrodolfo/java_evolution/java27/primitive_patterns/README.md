# Primitive Patterns Fifth Preview

Primitive patterns continue as a Java 27 fifth preview. A narrowing cast such
as `int` to `byte` can lose information, so code that casts first and checks
later can make an unsafe assumption. A primitive pattern tests whether the
value can match the requested primitive pattern before binding the narrowed
value.

For example, `42` is representable as a `byte`, so `value instanceof byte b`
can match it; `1000` is not, so that pattern does not match. This illustrates
the probe's range case. The broader preview conversion rules cover other
primitive conversions as well, so the feature is not merely a synonym for a
hand-written range check.

In `instanceof` and `switch`, this lets code express primitive classification
declaratively instead of combining potentially lossy casts with manual checks.
The child probe uses the matching Java 27 preview compiler and runtime.

Example: `PrimitivePatternsFifthPreviewExamples`
Test: `PrimitivePatternsFifthPreviewExamplesTest`
