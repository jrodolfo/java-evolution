# Lazy Constants

Lazy Constants address a familiar initialization problem: a value should be created only when needed, but after creation it should behave like a constant.

Before this kind of API, developers often used suppliers, nullable fields, synchronization, or double-checked locking. Those approaches work, but they mix application intent with concurrency and initialization mechanics.

Java 26 previews Lazy Constants after Java 25 previewed Stable Values. The naming change is useful for learners: the feature is about constant-like data whose initialization can be delayed.

This is a C2 explanatory module because Lazy Constants are a Java 26 preview API and still need focused executable-example evaluation.
