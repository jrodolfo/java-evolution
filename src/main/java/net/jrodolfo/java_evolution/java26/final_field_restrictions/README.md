# Final Field Restrictions

Java developers read `final` as a promise: after construction, the value should not change.

For many years, deep reflection could still mutate final fields by making a `Field` accessible and calling `set`. That was useful for some serialization and framework code, but it also weakened immutability, made programs harder to reason about, and limited JVM assumptions.

Java 26 prepares for stronger integrity by default. It warns when deep reflection mutates final fields. A future release is expected to restrict that behavior more strongly unless final-field mutation is explicitly enabled.

This is a C2 explanatory module because the important behavior is a runtime warning and migration policy, not a normal source-level example.
