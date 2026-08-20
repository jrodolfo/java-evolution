# Internet-Address Resolution SPI

Java 18 introduced the Internet-Address Resolution SPI in JEP 418.

This is an explanatory module rather than a live networking implementation. A
resolver provider can affect how `InetAddress` turns host names into network
addresses, so installing one during ordinary tests would change the behavior of
the whole running process.

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

## Why this repository does not install one

The repository deliberately avoids a live provider example. Installing a
resolver provider can affect unrelated tests that resolve host names, and the
result can depend on service configuration, class-path or module-path layout,
and the environment in which Maven is running.

That would make a small Java 18 lesson responsible for changing process-wide
network behavior. It would also make failures difficult to attribute: a test
could fail because of the provider rather than because of the feature being
studied.

The notes class therefore records the problem, the extension point, and the
project decision. The test checks that those explanations remain present; it
does not pretend to test a provider that the project intentionally does not
install.

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
repository explains the design without installing a live provider.

## References

- [JEP 418: Internet-Address Resolution SPI](https://openjdk.org/jeps/418)
- [Java 18 package overview](../README.md)
