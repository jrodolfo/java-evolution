# Assertions

J2SE 1.4 added the `assert` statement.

## 1. What Problem Does This Feature Solve?

Developers need a lightweight way to document and check assumptions that should be true if the program is internally correct.

## 2. What Did Java Introduce?

The `assert` statement checks a boolean condition when assertions are enabled.

```java
assert total >= 0 : "total should not be negative";
```

## 3. Why This Repository Uses Notes

Assertions are disabled by default unless the JVM is launched with `-ea` or `-enableassertions`. This Maven build does not enable assertions globally, so a normal unit test would not faithfully show assertion behavior.

## 4. Remember This

Use assertions for internal invariants, not for validating user input or enforcing public API contracts.
