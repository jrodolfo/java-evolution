# Strong Encapsulation

Java 17 strongly encapsulated JDK internals through JEP 403.

This is an executable child-JVM module. The example deliberately attempts deep
reflection into a JDK class from an isolated child process, then compares the
result with targeted migration flags. That keeps the Maven test JVM clean while
showing the real runtime behavior.

## The problem

The JDK contains implementation code that supports the public Java platform.
Some of that code lives in packages such as `sun.*`, `com.sun.*`, `jdk.*`, or
other packages that are not public API contracts.

Before strong encapsulation, applications and libraries sometimes reached into
those internals because they offered useful capabilities that were not yet
available through a standard API. Code might compile against an internal class
or use reflection to access a non-public field.

That created a hidden dependency:

```text
application
    |
    v
JDK implementation detail
    |
    v
application breaks when the JDK implementation changes
```

Internal APIs were never promised to remain stable. Depending on them made
upgrades harder for application developers and forced the JDK to preserve
implementation details for code that was never supposed to use them.

## What Java modules protect

Java's module system gives a module control over which packages it exports.
An exported package makes its supported public types available to other
modules. A package that is not exported is not part of the module's normal
external API.

There are two different ideas to keep separate:

- **Export:** permits ordinary access to public types in a package.
- **Open:** permits reflective access to members of classes in a package,
  including access that ordinary Java source code could not perform.

For example, a package may expose a public class for normal use without being
open to deep reflection. Strong encapsulation applies to both ordinary access
rules and reflective access rules.

The simplified model is:

```text
exported package
    -> public API can be used according to the module contract

opened package
    -> reflection can inspect or access members with the necessary permission

internal or unexported package
    -> external code should not depend on it
```

The exact result also depends on whether the code is running from a named
module or the class path, but the design goal is the same: implementation
details should remain behind the module boundary.

## The migration history

Strong encapsulation was introduced gradually:

```text
Java 9
    modules limited access to many internal APIs

Java 9-15
    older JDK internals could still receive relaxed reflective access

Java 16
    strong encapsulation became the default

Java 17
    the broad --illegal-access escape hatch was no longer effective
```

This gradual transition gave library and application maintainers time to find
internal API dependencies and migrate to supported alternatives.

## What changed in Java 17

Java 17 strongly encapsulated almost all internal JDK elements. It also removed
the general mechanism that allowed users to restore the older relaxed behavior
with `--illegal-access=permit`, `warn`, or `debug`.

This does not mean that every possible access can never be opened. Targeted
options such as `--add-opens` still exist for particular packages and migration
scenarios. They are an explicit exception, not evidence that the internal API
has become supported.

## `--add-exports` and `--add-opens`

These options are useful to understand when diagnosing a migration, but they
should not be treated as a design solution.

`--add-exports` makes a package's public types accessible to a specified target
module even though the package is not exported to that target in the module's
declaration.

`--add-opens` opens a package for deep reflection to a specified target module.
It is relevant when a framework needs reflective access to non-public members.

Conceptually:

```text
--add-exports
    ordinary access to public types

--add-opens
    reflective access to members
```

Both options create a command-line dependency on JDK internals. They can be
useful as temporary migration bridges, but they do not make those internals
stable public APIs.

## How to investigate a migration problem

The `jdeps` tool can help identify dependencies on JDK internal APIs:

```bash
jdeps --jdk-internals application.jar
```

The result is a starting point, not a complete proof that an application is
safe. Reflection, generated code, and dependencies loaded indirectly may need
additional investigation.

A practical migration process is:

1. Identify the internal API or reflective access that fails.
2. Check whether a supported Java API now provides the same capability.
3. Upgrade the library if the dependency belongs to a third party.
4. Use a maintained external library when the JDK has no replacement.
5. Treat `--add-exports` or `--add-opens` as temporary, explicitly documented
   compatibility measures.

## What The Example Shows

The executable example launches a small Java source file in a child JVM. The
probe inspects `String.class.getDeclaredFields()` and calls
`setAccessible(true)` on a non-public field.

The child JVM is run four ways:

- with no module-opening flags, where strong encapsulation rejects the access
- with `--add-opens java.base/java.lang=ALL-UNNAMED`, where the targeted
  reflective access bridge succeeds
- with `--add-exports java.base/java.lang=ALL-UNNAMED`, which still fails
  because exporting a package is not the same as opening it for deep reflection
- with `--illegal-access=permit`, which modern JDKs ignore because broad
  relaxed illegal access was removed after Java 17

The reflective access is intentionally bad production practice. It exists here
only to make the migration behavior observable.

## When this matters in real software

Strong encapsulation matters most when upgrading older applications, frameworks,
build tools, serializers, mocking libraries, and instrumentation tools. A
failure may appear only at runtime because reflective access is involved, or it
may appear during compilation when an internal package is no longer accessible.

For new application code, the practical rule is simpler: use documented public
APIs and treat `sun.*`, most `com.sun.*`, and `jdk.*` packages as implementation
details unless the JDK documentation explicitly identifies a supported API.

## Remember this

Java 17 made the boundary between supported Java APIs and JDK implementation
details harder to ignore. Migrate to public APIs or maintained libraries; use
targeted access flags only as explicit, temporary compatibility bridges.

## References

- [JEP 403: Strongly Encapsulate JDK Internals](https://openjdk.org/jeps/403)
- [JEP 261: Module System](https://openjdk.org/jeps/261)
- [Java 17 package overview](../README.md)
