# RMI

Java 1.1 introduced Remote Method Invocation (RMI).

## 1. What Problem Does This Feature Solve?

Distributed Java applications needed a standard way for one JVM to call objects hosted in another JVM.

## 2. What Did Java Introduce?

RMI introduced remote interfaces, remote objects, stubs, registries, remote exceptions, and serialization-based argument passing.

## 3. What Does The Example Show?

The example starts a local RMI registry on an ephemeral loopback port, exports a small remote object, binds it under a service name, looks it up through the registry, and invokes it through the remote interface.

It also passes a serializable request object so the test can show that RMI copies remote arguments by value. The example uses modern dynamic stubs because generated `rmic` stubs were part of older RMI workflows, not the normal JDK 26 teaching path.

Because RMI binds local sockets, the focused test skips only when the execution environment blocks loopback socket binding.

## 4. Remember This

RMI is important historically because it made distributed objects part of early Java's enterprise story, even though modern systems often use HTTP, messaging, or RPC frameworks instead.
