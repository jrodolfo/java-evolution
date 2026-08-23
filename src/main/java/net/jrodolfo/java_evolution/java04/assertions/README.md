# Assertions

J2SE 1.4 added the `assert` statement.

## 1. What Problem Does This Feature Solve?

Developers need a lightweight way to document and check assumptions that should be true if the program is internally correct.

## 2. What Did Java Introduce?

The `assert` statement checks a boolean condition when assertions are enabled.

```java
assert total >= 0 : "total should not be negative";
```

Assertions are disabled by default unless the JVM is launched with `-ea` or `-enableassertions`. Tests in this module enable assertions only for `AssertionExamples`, so the example remains explicit and does not depend on global Maven settings.

## 3. What Does The Example Show?

`AssertionExamples` demonstrates:

- checking whether assertions are enabled for a class
- using `assert` for an internal invariant that should be impossible to violate
- using ordinary exceptions for public input validation

That distinction matters. Assertions can be disabled, so they should not protect user input, security rules, or public API contracts.

## 4. Remember This

Use assertions for internal invariants, not for validating user input or enforcing public API contracts.
