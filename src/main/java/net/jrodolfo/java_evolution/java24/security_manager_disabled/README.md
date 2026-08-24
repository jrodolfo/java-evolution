# Security Manager Disabled

Java 24 permanently disabled the Security Manager in Java Enhancement Proposal (JEP) 486.

This feature is not about learning a new expression, class, or library method. It is about understanding a major change in Java's old security model.

For recurring acronyms, see the [project glossary](../../../../../../../../docs/glossary.md).

## What Problem Did The Security Manager Try To Solve?

Some Java programs needed to run code that was not fully trusted.

Historically, examples included:

- applets downloaded from the web
- plugins loaded into an application
- scripts or extensions supplied by someone else
- application servers running many applications in one Java Virtual Machine (JVM)

The question was:

```text
How can one JVM run code while restricting what that code is allowed to do?
```

The Security Manager tried to answer this with an in-process sandbox.

## What Was An In-Process Sandbox?

An in-process sandbox means the restriction happens inside the same running process.

In this case, the process was the Java Virtual Machine.

Conceptually:

```text
same JVM
 |
 +-- trusted application code
 |
 +-- less-trusted code
        |
        v
     permission checks before sensitive actions
```

The less-trusted code might try to read a file, open a network connection, use reflection, or terminate the virtual machine. The Security Manager could check permissions before allowing those actions.

## How Was This Commonly Done Before?

Applications could install a Security Manager and configure permissions.

The idea was:

```text
code tries sensitive action
        |
        v
Security Manager checks permissions
        |
        +--> allowed
        |
        +--> rejected
```

This gave Java a way to restrict some code while it was still running in the same JVM as other code.

## Why Did This Become A Problem?

The model became difficult to maintain and difficult to rely on.

There are several reasons:

- modern applications rarely use applet-style sandboxing
- permission policies are hard to configure correctly
- libraries often were not designed with Security Manager checks as their main execution model
- running less-trusted code inside the same process is a fragile isolation boundary
- operating systems, containers, and deployment platforms provide stronger and clearer isolation tools

The important lesson is architectural:

```text
Do not treat code inside the same JVM as strongly isolated just because permission checks exist.
```

## What Did Java 24 Change?

Java 24 permanently disabled the Security Manager.

That means applications can no longer use it as a sandboxing mechanism.

This is different from simply discouraging its use. The Security Manager had already been deprecated for removal in earlier Java releases. Java 24 completed that direction by disabling it permanently.

## What Should Developers Use Instead?

Use isolation outside the JVM process.

Common approaches include:

- operating-system users and file permissions
- containers
- separate processes
- cloud runtime policy
- network policy
- deployment-level access controls

Those approaches make the isolation boundary easier to reason about:

```text
process A
  runs one trust boundary

process B
  runs another trust boundary

operating system / container / platform
  controls what each process may access
```

## What The Example Shows

`SecurityManagerDisabledExamples` keeps the obsolete API call inside a child Java Virtual Machine (JVM). The Maven test JVM never installs or changes a Security Manager.

- `oldModel()` explains the same-JVM sandbox model.
- `permissionExamples()` names actions that could be checked.
- `java24Impact()` states the Java 24 change.
- `modernIsolationAdvice()` points to the replacement mental model.
- `runSecurityManagerProbe(...)` source-launches a small Java program that tries to call `System.setSecurityManager(new SecurityManager())`.

The child process prints the actual runtime result:

```text
thrown=java.lang.UnsupportedOperationException
message=Setting a Security Manager is not supported
```

The source-launcher output also shows the removal warnings for `SecurityManager` and `System.setSecurityManager(...)`. That makes the migration path visible without pretending the old sandbox still works.

## What The Test Proves

`SecurityManagerDisabledExamplesTest` protects both the runtime behavior and the educational explanation.

It checks that the note still explains:

- less-trusted code
- same-JVM sandboxing
- permission checks
- sensitive actions such as file and network access
- permanent disablement in Java 24
- modern isolation through operating systems, containers, processes, and deployment boundaries
- child-JVM output showing `UnsupportedOperationException`
- source-launcher warnings that the old API is deprecated and marked for removal

The test does not install a Security Manager in the parent Maven JVM. That global mutation would be the wrong kind of example for a shared test suite.

## Realistic Use Case

Imagine a service that accepts user-provided plugins.

The old instinct might be:

```text
load plugin into this JVM
install Security Manager
restrict plugin permissions
```

The modern instinct should be:

```text
run plugin in a separate process or container
grant only the files, network access, and credentials it needs
communicate through a narrow interface
```

That design keeps the trust boundary outside the JVM, where the operating system and deployment platform can enforce it.

## Interview Angle

A strong answer is not only "the Security Manager was disabled."

A better answer is:

```text
The Security Manager was Java's old in-process sandboxing mechanism. It tried to restrict code inside the same JVM through permission checks. Java 24 permanently disabled it, so modern Java applications should rely on process, operating-system, container, and deployment-level isolation instead.
```

## When This Does Not Matter Much

Most ordinary applications never installed a Security Manager directly.

For those applications, this change may not affect day-to-day code. It matters most when maintaining old sandboxing designs, plugin systems, application servers, or security-sensitive runtime environments.

## Remember This

The Security Manager tried to make code inside one JVM trust boundary behave as if it were isolated. Java 24 permanently disabled that model. Treat strong isolation as an operating-system, process, container, or deployment responsibility.
