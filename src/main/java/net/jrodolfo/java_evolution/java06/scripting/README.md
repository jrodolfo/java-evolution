# Scripting Support

Java 6 added JSR 223, the Scripting API.

This is an explanatory learning module. It does not run a script engine as part of the Maven build because the repository runs on JDK 25, and modern JDKs no longer guarantee the JavaScript engine that was bundled with Sun's Java 6 implementation.

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

## 3. Why This Repository Uses Notes

Sun's Java 6 implementation included a Rhino-based JavaScript engine, but JDK 25 does not provide that old bundled engine.

A test that assumes `new ScriptEngineManager().getEngineByName("JavaScript")` always returns an engine would be false on many modern JDKs.

The faithful lesson is the API shape and the optional engine model, so this module uses notes.

## 4. Remember This

JSR 223 made scripting integration standard, but script engines are pluggable. Modern applications should treat the engine as an explicit dependency, not as something guaranteed by the JDK.
