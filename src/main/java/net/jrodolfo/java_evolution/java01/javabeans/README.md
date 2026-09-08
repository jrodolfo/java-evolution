# JavaBeans

Java 1.1 introduced JavaBeans component conventions.

## 1. What Problem Does This Feature Solve?

Tools needed a standard way to discover component properties, events, and customization points.

## 2. What Did Java Introduce?

JavaBeans defined conventions such as convenient construction by tools,
getter/setter property names, event listener patterns, and introspection. A
no-argument constructor is a common convention because a tool can create a
bean without first knowing application-specific constructor arguments, but it
should not be read as a claim that every JavaBean is formally defined only by
having such a constructor.

## 3. What Does The Example Show?

`JavaBeansExamples` shows how tooling can discover bean shape from conventions:

- a no-argument constructor makes the component easy for tools to create
- `getName` and `setName` define a `name` property
- `isActive` and `setActive` define a boolean `active` property
- matching `addProjectListener(ProjectListener)` and
  `removeProjectListener(ProjectListener)` methods tell JavaBeans that the bean
  publishes events to `ProjectListener` objects
- an "event set" means this relationship between an event source and its
  listener type; it does not mean `java.util.Set`
- `Introspector` examines JavaBeans naming conventions and returns `BeanInfo`
- `BeanInfo` exposes the information through descriptors such as
  `PropertyDescriptor`, `MethodDescriptor`, and `EventSetDescriptor`

A descriptor is simply an object that describes one part of a bean, such as a
property, method, or event set.

The point is not the amount of code in the bean. The point is that tools can discover properties and events without each component implementing a custom metadata API.

## 4. Remember This

JavaBeans influenced many later Java frameworks because property naming and introspection became a common way to connect objects to tools.
