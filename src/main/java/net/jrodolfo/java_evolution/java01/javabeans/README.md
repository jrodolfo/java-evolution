# JavaBeans

Java 1.1 introduced JavaBeans component conventions.

## 1. What Problem Does This Feature Solve?

Tools needed a standard way to discover component properties, events, and customization points.

## 2. What Did Java Introduce?

JavaBeans defined conventions such as no-argument constructors, getter/setter property names, event listener patterns, and introspection.

## 3. What Does The Example Show?

`JavaBeansExamples` shows how tooling can discover bean shape from conventions:

- a no-argument constructor makes the component easy for tools to create
- `getName` and `setName` define a `name` property
- `isActive` and `setActive` define a boolean `active` property
- `addProjectListener` and `removeProjectListener` define an event-set convention
- `Introspector` exposes this metadata through descriptors

The point is not the amount of code in the bean. The point is that tools can discover properties and events without each component implementing a custom metadata API.

## 4. Remember This

JavaBeans influenced many later Java frameworks because property naming and introspection became a common way to connect objects to tools.
