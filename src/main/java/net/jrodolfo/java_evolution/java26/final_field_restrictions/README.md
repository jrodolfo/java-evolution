# Final Field Restrictions

Java developers read `final` as a promise: after construction, the value should not change.

For many years, deep reflection could still mutate final fields by making a `Field` accessible and calling `set`. That was useful for some serialization and framework code, but it also weakened immutability, made programs harder to reason about, and limited JVM assumptions.

Java 26 prepares for stronger integrity by default. It warns when deep reflection mutates final fields. A future release is expected to restrict that behavior more strongly unless final-field mutation is explicitly enabled.

This is a C1 executable runtime example. It runs the reflective mutation in a child JVM, captures the Java 26 warning, and also shows that `--enable-final-field-mutation=ALL-UNNAMED` is the explicit compatibility switch for unnamed-module code that still depends on this behavior.

The example deliberately avoids mutating final fields in the main Maven test JVM. The warning is the lesson, so the module treats the child process output as executable documentation.
