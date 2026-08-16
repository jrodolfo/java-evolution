# Module Import Declarations Preview

Java 23 previewed module import declarations.

This module is explanatory because the feature was preview in Java 23, continued as a second preview in Java 24, and became final in Java 25.

## What Problem Does This Feature Solve?

Java source files usually import one type or one package at a time.

For example:

```java
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
```

That is precise and works well for most application code.

The inconvenience appears in small programs, examples, scripts, and learning code that intentionally uses many types from a broad API surface.

The import section can become longer than the example itself.

## What Did Developers Do Before?

Developers usually used individual type imports:

```java
import java.util.List;
```

or package-on-demand imports:

```java
import java.util.*;
```

Package imports are limited to one package. They do not automatically cover related packages.

For example, `java.util.*` does not import `java.util.stream.Stream`.

That is one reason a source file using several related JDK APIs may still need several imports.

## What Did Modules Add To The Picture?

Java 9 introduced the Java Platform Module System.

A module groups packages and declares which packages it exports.

That gives Java a larger boundary than a package:

```text
module
    -> exported package
        -> public top-level type
    -> exported package
        -> public top-level type
```

Once Java had modules, it became natural to ask whether source code could import from a module boundary.

## What Did Java 23 Preview?

Java 23 previewed module import declarations.

The mental model is:

```java
import module java.base;
```

This does not mean "import every internal thing inside the module."

It means the source file can refer, on demand, to public top-level types from packages exported by the named module.

That is broader than a package import but still respects module boundaries.

## Important Terminology

**Package import**

An import that names one package, such as `import java.util.*;`.

**Type import**

An import that names one type, such as `import java.util.List;`.

**Module**

A named group of packages with explicit dependencies and exported packages.

**Exported package**

A package that a module makes available to other modules.

**Public top-level type**

A public class or interface declared directly in a package, not nested inside another type.

**Preview feature**

A feature included in a JDK release so developers can try it and provide feedback before it becomes final. Module import declarations were preview in Java 23 and Java 24, then finalized in Java 25.

## Why This Module Has Notes Instead Of A Java 23 Example

This repository is a Spring Boot Maven project with ordinary named packages.

Module import declarations are most natural in small source files, scripts, tutorials, and examples where reducing import noise helps the reader focus on the main idea.

The final Java 25 notes show the final feature shape:

```text
src/main/java/net/jrodolfo/java_evolution/java25/ModuleImportDeclarationsNotes.java
```

The Java 23 module exists to explain the first-preview step and the problem that motivated it.

## Realistic Use Case

Imagine a small teaching file that uses lists, maps, sets, streams, and optionals.

The traditional imports may distract from the example:

```text
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
```

A module import declaration can reduce that setup noise when the goal is to use a broad module surface intentionally.

## When Not To Use It

For production code where explicit imports improve readability, individual type imports may still be better.

Module imports are not a requirement. They are another option.

Use them when the broader import makes the source easier to read rather than less precise.

## Remember This

Module import declarations let source code import public top-level types from exported packages of a named module. Java 23 previewed the idea; Java 25 finalized it.
