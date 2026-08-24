# Module Import Declarations Second Preview

Java 24 continued module import declarations as a second preview feature in JEP 494.

This module is explanatory. It records the Java 24 step in the feature's evolution and points to Java 25 for the final form.

## What Problem Does This Feature Solve?

Java source files often need imports before they can use types by simple name.

For example:

```java
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
```

This is clear, but it can become noisy in small programs, examples, scripts, and teaching code that use several types from the same area of the platform.

Java has also had modules since Java 9. A module is a named boundary that can export packages.

That creates a natural question:

```text
if these public types are already exported by one named module,
can a source file import from that module boundary?
```

## How Was This Commonly Done Before?

Before module import declarations, Java code normally used:

- single-type imports, such as `import java.util.List;`
- package-on-demand imports, such as `import java.util.*;`

Both forms are package-oriented or type-oriented.

They do not say:

```text
make the public exported API of this named module available
```

That module-oriented form is what this feature explores.

## What Did Java 24 Preview?

Java 24 continued the preview of module import declarations.

The syntax shape is:

```java
import module java.base;
```

Conceptually, this means:

```text
from the named module,
make public top-level types from exported packages available on demand
```

For example, `java.base` exports packages that contain common types such as `List`, `Map`, `Set`, and `Stream`.

The feature does not import private implementation classes. It works through the module's exported packages.

## Important Terminology

**Ordinary import**

An import that names a specific type or a package, such as `java.util.List` or `java.util.*`.

**Module**

A named unit introduced by the Java Platform Module System (JPMS). A module can export packages for other code to use.

**Exported package**

A package that a module makes available outside the module.

**Public top-level type**

A public class or interface declared directly in a package, not a nested type inside another class.

**Module import declaration**

An import declaration such as `import module java.base;` that imports public top-level types from packages exported by the named module.

**Preview feature**

A feature included in a JDK release for feedback before finalization. Java 24 continued module import declarations as a second preview.

## Why This Module Has Notes Instead Of A Java 24 Example

Module import declarations became final in Java 25.

This repository keeps the Java 24 package as a preview-history bridge and leaves the stable explanation to the Java 25 notes:

```text
src/main/java/net/jrodolfo/java_evolution/java25/ModuleImportDeclarationsExamples.java
```

That avoids keeping preview syntax from Java 24 in the normal Maven build.

## What The Test Proves

`ModuleImportDeclarationsSecondPreviewNotesTest` does not compile preview syntax.

It protects the educational note by checking that the note explains:

- ordinary imports
- module boundaries
- exported packages
- public top-level types
- Java 24 second-preview status
- Java 25 finalization

That is the useful test boundary for this explanatory module.

## Realistic Use Case

Module import declarations are useful in small source files that use several public types from one module.

For example, teaching code might use collections and streams together:

```text
List
Map
Set
Stream
```

Instead of listing several individual imports, a module import can express that the source file is using public API from the named module.

## When Not To Use It

Do not use module imports when a few explicit imports make the code clearer.

Explicit imports remain useful because they show exactly which types a source file depends on.

Module imports are most useful when reducing import ceremony helps the reader focus on the example or small program.

## Remember This

`import module ...` is a module-oriented import. It makes public top-level types from exported packages of a named module available on demand. Java 24 was the second preview step, and Java 25 finalized the feature.
