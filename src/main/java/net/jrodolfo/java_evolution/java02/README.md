# Java 2

Released: December 1998 as J2SE 1.2.

Java 2, also known as J2SE 1.2, was a major platform release. It introduced the Collections Framework, Swing, Java 2D, a stronger security-policy model, and the `strictfp` modifier.

This package focuses executable examples on the Collections Framework because it remains foundational and easy to demonstrate faithfully. GUI, graphics, floating-point-history, and security-policy topics are represented as explanatory modules.

## Collections Framework

Before Java 2, common containers included `Vector`, `Hashtable`, arrays, and custom structures. Java 2 introduced standard collection interfaces such as `List`, `Set`, and `Map`, plus implementations and algorithms.

Example: `CollectionsFrameworkExamples`

Test: `CollectionsFrameworkExamplesTest`

## Sorting

Java 2 collections made sorting and ordering a standard concern through `Comparable`, `Comparator`, and `Collections.sort`.

Example: `SortingExamples`

Test: `SortingExamplesTest`

## strictfp

Java 2 added `strictfp` for strict floating-point semantics. Java 17 later restored always-strict floating point, so the historical behavior is better explained than unit-tested.

Explanatory module: [`strict_floating_point`](strict_floating_point/README.md)

Test: `StrictFloatingPointNotesTest`

## Swing

Java 2 made Swing part of the standard platform for richer GUI development.

Explanatory module: [`swing`](swing/README.md)

Test: `SwingNotesTest`

## Java 2D

Java 2D improved graphics, shapes, text, images, and rendering control.

Explanatory module: [`java2d`](java2d/README.md)

Test: `Java2DNotesTest`

## Security Policy

Java 2 introduced a more flexible security architecture based on permissions and policy files.

Explanatory module: [`security`](security/README.md)

Test: `SecurityPolicyNotesTest`

## How To Read This Package

Start with `CollectionsFrameworkExamples` and `SortingExamples`, then read the explanatory modules for platform-level features.

Run the focused tests:

```bash
mvn -Dtest=CollectionsFrameworkExamplesTest,SortingExamplesTest test
mvn -Dtest=StrictFloatingPointNotesTest,SwingNotesTest,Java2DNotesTest,SecurityPolicyNotesTest test
```

After this package, continue with Java 3 for dynamic proxies, timers, shutdown hooks, JNDI, and legacy integration notes.

## References

- [Java language enhancements history](https://docs.oracle.com/javase/8/docs/technotes/guides/language/enhancements.html)
- [Java 2 platform documentation](https://docs.oracle.com/cd/E19683-01/806-7930/features-2d/index.html)
