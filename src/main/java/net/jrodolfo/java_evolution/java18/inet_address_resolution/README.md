# Internet-Address Resolution SPI

Java 18 introduced the Internet-Address Resolution SPI in JEP 418.

This module uses an executable child-JVM example. A resolver provider can affect
how `InetAddress` turns host names into network addresses for the whole running
process, so the Maven test JVM does not install one. Instead, the example
generates a tiny provider, registers it through `META-INF/services`, and runs a
separate Java process to observe the behavior safely.

## The problem

Most Java applications need to resolve a host name such as `example.com` into
an IP address. Normally, `InetAddress` delegates that work to the operating
system and its configured DNS or name-resolution services.

That default is appropriate for most applications. Some environments need
different behavior, however:

- a service-discovery system may know addresses that are not in public DNS
- a controlled DNS environment may need custom resolution rules
- a platform may want to route names through its own resolver
- tests may need predictable addresses without depending on external DNS

Before Java 18, replacing the resolver used by the standard networking APIs was
not exposed as a clean, supported Java extension point.

## What Java introduced

Java 18 added a Service Provider Interface (SPI) for Internet-address
resolution. An SPI is an extension point: the JDK defines the contract, and a
separate provider supplies an implementation that follows that contract.

The main provider type is `InetAddressResolverProvider`. Conceptually, the
relationship is:

```text
application code
      |
      v
InetAddress
      |
      v
configured resolver provider
      |
      v
IP address or resolution failure
```

The application can continue to use the familiar `InetAddress` API. The
provider changes how resolution is supplied underneath that API.

## How provider discovery works

A provider is packaged as a service implementation and made available through
the Java service-provider mechanism. The runtime can discover providers that
advertise the resolver service, subject to the provider's configuration and
the JDK's selection rules.

This is different from calling a helper method such as
`InetAddress.getByName(...)` with a resolver object. The provider is an
integration point for the networking runtime, which is why its scope and
lifecycle matter.

## Why this repository isolates the provider

The repository deliberately avoids installing a resolver provider in the Maven
test JVM. Installing a provider can affect unrelated tests that resolve host
names, and the result can depend on service configuration, class-path or
module-path layout, and the environment in which Maven is running.

That would make a small Java 18 lesson responsible for changing process-wide
network behavior. It would also make failures difficult to attribute: a test
could fail because of the provider rather than because of the feature being
studied.

`InetAddressResolutionExamples` solves this by creating the provider in a
temporary classpath and launching a child JVM. The child process resolves
`demo.internal` to `10.0.0.42` and rejects `outside.internal` without using real
DNS.

## What the example shows

The executable example demonstrates:

- an `InetAddressResolverProvider` implementation
- an `InetAddressResolver` with forward and reverse lookup methods
- service discovery through `META-INF/services/java.net.spi.InetAddressResolverProvider`
- `InetAddress.getByName("demo.internal")` using the custom provider
- deterministic rejection of an unsupported host name

## Realistic use cases

This SPI is most useful in software that owns or integrates with its network
environment, such as:

- a platform runtime with service discovery
- an application that must resolve names through a controlled DNS policy
- a testing environment that supplies deterministic address mappings
- infrastructure software that needs to combine standard Java networking with
  a specialized resolver

For an ordinary application that only needs the operating system's resolver,
using `InetAddress` directly remains simpler. The SPI is an extension point for
specialized environments, not a requirement for normal host-name lookups.

## Remember this

The Internet-Address Resolution SPI lets a specialized provider participate in
the name-resolution work used by `InetAddress`. It solves a customization
problem, but because that customization can affect the whole process, this
repository demonstrates it only in an isolated child JVM.

## References

- [JEP 418: Internet-Address Resolution SPI](https://openjdk.org/jeps/418)
- [Java 18 package overview](../README.md)
