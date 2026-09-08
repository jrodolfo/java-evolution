# Java 2

Released: December 1998 as J2SE 1.2.

Java 2, also known as J2SE 1.2, was a major platform release. It introduced the Collections Framework, Swing, Java 2D, a stronger security-policy model, and the `strictfp` modifier.

This package focuses executable examples on the Collections Framework, sorting, `strictfp`, headless Swing concepts, headless Java 2D rendering, and the security-policy permission model because they remain foundational and can be demonstrated faithfully. GUI windows and full Security Manager policy-file enforcement are treated as historical context rather than runtime fixtures.

## Collections Framework

Before Java 2, common containers included `Vector`, `Hashtable`, arrays, and custom structures. Java 2 introduced standard collection interfaces such as `List`, `Set`, and `Map`, plus implementations and algorithms.

A `List` is an ordered sequence that can contain duplicates. A `Set` represents
unique values, while a `Map` associates keys with values. Choose the interface
that describes what the code needs: for example, declare a parameter as
`List` when order and list operations matter, or as `Set` when
uniqueness matters. When the concrete implementation is not important, use
the interface rather than committing callers to an implementation such as
`ArrayList` or `HashSet`.

This separation lets the implementation change without changing the code that
uses it. The same idea applies to variables, parameters, and return types: the
interface describes the available operations, and the implementation supplies
the storage details.

Example: `CollectionsFrameworkExamples`

Test: `CollectionsFrameworkExamplesTest`

## Sorting

Java 2 collections made sorting and ordering a standard concern through `Comparable`, `Comparator`, and `Collections.sort`.

`Comparable` means that a type defines its own natural ordering through a
`compareTo` method, such as a value object ordering itself by an identifying
field. `Comparator` represents an ordering outside the type, which is useful
when callers need a different rule, such as sorting the same objects by name
in one place and by date in another.

Example: `SortingExamples`

Test: `SortingExamplesTest`

## strictfp

Java 2 added `strictfp` for strict floating-point semantics. Java 17 later restored always-strict floating point, so this module demonstrates the modifier and the modern compiler warning without pretending to reproduce the old hardware-dependent behavior.

Example module: [`strict_floating_point`](strict_floating_point/README.md)

Test: `StrictFloatingPointExamplesTest`

## Swing

Java 2 made Swing part of the standard platform for richer GUI development.

Example module: [`swing`](swing/README.md)

Test: `SwingExamplesTest`

## Java 2D

Java 2D improved graphics, shapes, text, images, and rendering control.

Example module: [`java2d`](java2d/README.md)

Test: `Java2DExamplesTest`

## Security Policy

Java 2 introduced a more flexible security architecture based on permissions and policy files.

Executable model module: [`security`](security/README.md)

Test: `SecurityPolicyExamplesTest`

## How To Read This Package

Start with `CollectionsFrameworkExamples`, `SortingExamples`, `swing/SwingExamples`, `java2d/Java2DExamples`, and `security/SecurityPolicyExamples`, then read the explanatory context for platform-level enforcement details.

Run the focused tests:

```bash
mvn -Dtest=CollectionsFrameworkExamplesTest,SortingExamplesTest test
mvn -Dtest=Java2DExamplesTest,StrictFloatingPointExamplesTest,SwingExamplesTest,SecurityPolicyExamplesTest test
```

After this package, continue with Java 3 for dynamic proxies, timers, shutdown hooks, JNDI, and legacy integration notes.

## References

- [Java language enhancements history](https://docs.oracle.com/javase/8/docs/technotes/guides/language/enhancements.html)
- [Java 2 platform documentation](https://docs.oracle.com/cd/E19683-01/806-7930/features-2d/index.html)
