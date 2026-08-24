# Console API

Java 6 added `java.io.Console`.

This module uses an executable boundary example. It calls `System.console()` only to detect whether this process has an attached console, then keeps the command-line behavior behind a small testable interface. Maven, IDEs, CI jobs, background processes, and redirected streams commonly have no attached console, so the tests do not require one.

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

## 3. What Does The Example Show?

`ConsoleApiExamples` demonstrates three practical lessons:

- `System.console()` may return `null`.
- Console logic should sit at the process boundary so application behavior remains testable.
- Passwords should be read as `char[]` and cleared after use.

The tests use a fake console session for line input, formatted output, and password handling. That keeps the example deterministic while still teaching the shape of the Java 6 API.

## 4. Remember This

Always check whether `System.console()` returned `null`. Use `readPassword(...)` for secrets when a real console is available, and clear the returned `char[]` after use.
