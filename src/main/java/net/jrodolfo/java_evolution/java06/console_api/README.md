# Console API

Java 6 added `java.io.Console`.

This is an explanatory learning module. It does not call `System.console()` as a required test fixture because Maven, IDEs, CI jobs, background processes, and redirected streams commonly have no attached console.

## 1. What Problem Does This Feature Solve?

Before Java 6, command-line applications often used `System.in` and `System.out` directly. Reading passwords was especially awkward because normal input echoed characters to the terminal.

## 2. What Did Java Introduce?

Java 6 introduced `Console`, available through:

```java
Console console = System.console();
```

The API provides:

- line-oriented input
- formatted prompts
- console writers
- `readPassword(...)`, which disables echoing where a real console is available

## 3. Why This Repository Uses Notes

`System.console()` may return `null`.

That is normal when a Java program is launched without an interactive terminal. A Maven test that expects a console would fail for reasons unrelated to Java 6.

## 4. Remember This

Always check whether `System.console()` returned `null`. Use `readPassword(...)` for secrets when a real console is available, and clear the returned `char[]` after use.
