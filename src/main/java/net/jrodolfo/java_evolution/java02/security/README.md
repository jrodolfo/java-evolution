# Security Policy

Java 2 introduced a more flexible security architecture.

This module is an executable example of the Java 2 permission model. It uses `CodeSource`, `Permissions`, `SocketPermission`, and `ProtectionDomain` directly so learners can see how code origin and granted permissions produce an access-check answer.

It deliberately does not install a `SecurityManager` or load a real policy file. That enforcement mechanism is now deprecated and disabled in modern Java, so this repository treats it as migration history rather than current application practice.

## 1. What Problem Does This Feature Solve?

Early Java security was strongly associated with applets and sandboxing. Java 2 generalized the model around permissions and policy files.

Instead of a single idea of "trusted application" versus "untrusted applet," Java 2 made security decisions more explicit:

```text
where code came from
        +
which permissions were granted
        =
whether an access check should succeed
```

## 2. What Did Java Introduce?

The Java 2 security model used code sources, protection domains, permissions, policy files, and access checks.

Important terms:

Code source:

Where code came from, such as a file URL or signed JAR location.

Permission:

A value object that describes an allowed action, such as connecting to a host.

Protection domain:

The combination of a code source and the permissions granted to that code.

Policy file:

A configuration file that historically mapped code sources to permission grants.

Access check:

The runtime question: "does this protection domain imply the requested permission?"

## 3. What The Example Shows

`SecurityPolicyExamples` creates a synthetic code source:

```text
file:/trusted/app/
```

It then grants one permission:

```text
connect to api.example.test:443
```

The test verifies that the protection domain:

- remembers the code source
- implies the granted `connect` permission
- does not imply an unrelated `listen` permission
- uses a read-only in-memory permission collection

No socket is opened. `SocketPermission` is used only as a permission object so the example remains deterministic and portable.

## 4. What The Example Does Not Do

This module does not enable the Security Manager.

It also does not parse or install a real policy file. Real policy-file enforcement depends on launcher options, runtime configuration, and APIs that are no longer the recommended direction for new Java code.

The goal is to teach the Java 2 model clearly:

```text
CodeSource -> ProtectionDomain -> Permission -> implies(...)
```

That model helps learners read older code and understand migration discussions without encouraging new Security Manager based designs.

## 5. Remember This

Java 2 security policy is essential migration history, especially when reading older code that assumes a Security Manager or policy file.
