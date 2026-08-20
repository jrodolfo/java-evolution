# Questions

## 1) What are Java agents?

A **Java agent** is a special Java component that can observe or modify the behavior of a JVM application, often by instrumenting bytecode.

Agents are commonly used by profilers, monitoring/APM tools, debuggers, coverage tools, and mocking/instrumentation frameworks.

A Java agent can be loaded in two main ways:

```text
Before application startup
        |
        +-- -javaagent:agent.jar
        |
        v
     premain()

After JVM has already started
        |
        +-- dynamic attachment
        |
        v
     agentmain()
```

An agent loaded at startup normally defines:

```java
public static void premain(String agentArgs, Instrumentation inst) {
    // agent initialization
}
```

A dynamically loaded agent normally defines:

```java
public static void agentmain(String agentArgs, Instrumentation inst) {
    // agent initialization
}
```

The important API here is `java.lang.instrument.Instrumentation`. It allows an agent to transform classes as they are loaded and, subject to JVM capabilities and restrictions, redefine or retransform already loaded classes.

---

## 2) What is dynamic loading of agents?

**Dynamic agent loading** means attaching an agent to a JVM that is **already running**, rather than specifying the agent when the JVM starts.

Conceptually:

```text
-javaagent
JVM starts -> agent loads -> application runs

dynamic loading
JVM starts -> application runs -> agent attaches later
```

This is useful for tools that need to attach to an application on demand.

However, Java has been moving toward restricting dynamic agent loading. **JEP 451, delivered in Java 21**, introduced warnings when agents are dynamically loaded, with the goal of eventually requiring explicit permission by default.

That is why Java 21 discussions about agents often mention `-XX:+EnableDynamicAgentLoading`.

---

## 3) What is the `-javaagent` flag?

`-javaagent` is a JVM command-line option that loads a Java instrumentation agent **at JVM startup**.

For example:

```bash
java -javaagent:monitor.jar -jar my-application.jar
```

You can also supply arguments:

```bash
java -javaagent:monitor.jar=config.properties -jar my-application.jar
```

The JVM loads the agent before the application's `main()` method and invokes the agent's `premain()` method.

This is important in the context of Java 21 because JEP 451 is primarily concerned with **agents being loaded dynamically**. An agent deliberately supplied at startup with `-javaagent` is different: the application operator has explicitly authorized it.

---

## 4) What is the `-XX:+EnableDynamicAgentLoading` flag?

This JVM option explicitly permits the **dynamic loading of agents**:

```bash
java -XX:+EnableDynamicAgentLoading -jar my-application.jar
```

The `+` means enable:

```text
-XX:+EnableDynamicAgentLoading
```

and `-` means disable:

```text
-XX:-EnableDynamicAgentLoading
```

In the Java 21/JEP 451 context, this option is significant because the Java platform is moving toward requiring explicit authorization for dynamically loaded agents.

The underlying philosophy is roughly:

```text
-javaagent:foo.jar
        ↓
"I intentionally started my application with this agent."

dynamic attachment
        ↓
"Something modified my already-running JVM."

-XX:+EnableDynamicAgentLoading
        ↓
"I explicitly permit that."
```

So this is partly about strengthening JVM integrity and making powerful runtime modification an explicit choice.

There is a nice connection among questions **1-4**:

```text
                    Java Agents
                         |
          +--------------+--------------+
          |                             |
    startup loading               dynamic loading
          |                             |
      -javaagent                 Attach mechanism
                                        |
                              Java 21 / JEP 451
                                        |
                         EnableDynamicAgentLoading
```

---

## 5) How does the Java Release Cycle work?

Since Java 10, the JDK has followed a **six-month feature-release cadence**.

Typically:

```text
March       September
  |             |
JDK N        JDK N+1
```

For example:

```text
Java 21   September 2023
Java 22   March 2024
Java 23   September 2024
Java 24   March 2025
Java 25   September 2025
```

The key idea is that Java no longer waits several years until a huge collection of features is ready.

A feature that is not ready for a particular release generally waits for a later release rather than delaying the entire JDK.

This is one reason features can appear through several releases as previews:

```text
JDK 17 -> first preview
JDK 18 -> second preview
JDK 19 -> third preview
JDK 20 -> fourth preview
JDK 21 -> final
```

Pattern Matching for `switch` is a good example of that progression.

---

## 6) What is a long-term release? Give me examples.

Usually this is called an **LTS release**, meaning **Long-Term Support** release.

An LTS JDK is a release for which a JDK vendor commits to providing support and updates for an extended period.

Recent LTS releases include:

```text
Java 8
Java 11
Java 17
Java 21
Java 25
```

There is an important nuance: **LTS is primarily a vendor/support designation rather than meaning that the Java language itself has a fundamentally different kind of release.**

For example, Java 22 was a perfectly normal Java feature release even though organizations commonly standardized on Java 21 because vendors offered Java 21 as an LTS release.

So:

```text
Java 21 -> feature release + commonly supported as LTS
Java 22 -> feature release
Java 23 -> feature release
Java 24 -> feature release
Java 25 -> feature release + commonly supported as LTS
```

LTS matters especially to enterprises that want several years of security patches and maintenance without upgrading their production JDK every six months.

---

## 7) What are "delivered", "preview" and "incubator" features?

These terms describe different things, and **"delivered" is not the opposite of preview/incubator**.

### Delivered

If a JEP is **delivered** in Java 21, it means the JEP was incorporated into that JDK release.

But what was delivered might itself be final, preview, or incubating.

For example:

```text
JEP delivered in Java 21
          |
          +-- final feature
          |
          +-- preview feature
          |
          +-- incubator API
```

So "delivered" answers:

> **In which release did this JEP land?**

It does not necessarily answer:

> **Is this feature final?**

### Preview

A **preview feature** is a fully specified and implemented feature of the Java SE Platform that is made available to developers for feedback before becoming permanent.

Preview language features generally require:

```bash
javac --enable-preview --release 21 ...
java --enable-preview ...
```

A feature can be previewed multiple times before becoming final.

For example, Pattern Matching for `switch` went through:

```text
Java 17   JEP 406   first preview
Java 18   JEP 420   second preview
Java 19   JEP 427   third preview
Java 20   JEP 433   fourth preview
Java 21   JEP 441   final
```

Preview features may change or even disappear, so production code should use them with awareness of that risk.

### Incubator

An **incubator API** is an experimental API distributed with the JDK to gather feedback while its design is still evolving.

Incubator APIs normally live in modules prefixed with:

```text
jdk.incubator.*
```

For example, the Vector API has gone through multiple incubator iterations.

A useful simplified distinction is:

```text
Incubator
    ↓
experimental API, design still evolving

Preview
    ↓
feature substantially designed and implemented,
but feedback is still wanted

Final
    ↓
permanent part of the Java platform
```

Do not assume that every feature necessarily travels through exactly:

```text
incubator -> preview -> final
```

Those mechanisms serve different purposes.

---

## 8) What was the Java 21 Foreign Function and Memory API preview feature? Do we have this feature in this code base?

The Foreign Function and Memory (FFM) API allows Java programs to interact with code and memory outside the JVM more safely and conveniently than traditional approaches such as JNI.

Its progression was:

```text
Java 19   JEP 424   first preview
Java 20   JEP 434   second preview
Java 21   JEP 442   third preview
Java 22   JEP 454   final
```

The API provides mechanisms for things such as accessing off-heap memory and calling functions in native libraries.

For example, rather than writing JNI glue code merely to invoke a native C function, FFM provides Java APIs for describing and invoking native functions.

The Java 21 Foreign Function and Memory API preview feature was **the third preview of FFM, JEP 442**.

Do we have a Java 21 FFM example in this code base? Not as an executable Java 21-specific example. The repository documents the Java 19 and Java 20 preview stages as notes and provides an executable example of the finalized FFM API in the Java 22 `foreign_function` module.
