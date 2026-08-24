# Scripting Support

Java 6 added JSR 223, the Scripting API.

This module uses an executable Service Provider Interface (SPI) example. It does not depend on Nashorn, Rhino, GraalJS, or any external scripting engine because the repository runs on JDK 25, and modern JDKs no longer guarantee the JavaScript engine that was bundled with Sun's Java 6 implementation.

## 1. What Problem Does This Feature Solve?

Before Java 6, Java applications did not have a standard API for hosting scripting languages.

Applications that wanted user-defined formulas, dynamic configuration, or scripting extensions usually depended on a specific language integration library. That made the host application tightly coupled to one scripting implementation.

## 2. What Did Java Introduce?

Java 6 introduced `javax.script`, based on JSR 223.

The central idea is:

```text
Java application
    asks ScriptEngineManager for an engine
    evaluates script text through ScriptEngine
    exchanges values through bindings
```

The API standardized hosting. It did not make every JDK include every scripting language.

## 3. What Does The Example Show?

`ScriptingSupportExamples` demonstrates the Java 6 API shape with a tiny teaching engine:

- `ScriptEngineManager`
- `ScriptEngineFactory`
- `ScriptEngine`
- `Bindings`
- `ScriptEngine.eval(...)`
- service discovery through `META-INF/services/javax.script.ScriptEngineFactory`

The example engine only replaces `${name}` in a string. It is intentionally not a real scripting language. Its purpose is to show how Java code talks to a pluggable engine.

The tests also check that looking up `"javascript"` is optional. A modern JDK may return `null` unless an engine dependency has been provided.

## 4. Remember This

JSR 223 made scripting integration standard, but script engines are pluggable. Modern applications should treat the engine as an explicit dependency, not as something guaranteed by the JDK.
