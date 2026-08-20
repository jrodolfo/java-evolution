# RMI

Java 1.1 introduced Remote Method Invocation (RMI).

## 1. What Problem Does This Feature Solve?

Distributed Java applications needed a standard way for one JVM to call objects hosted in another JVM.

## 2. What Did Java Introduce?

RMI introduced remote interfaces, remote objects, stubs, registries, remote exceptions, and serialization-based argument passing.

## 3. Why This Repository Uses Notes

A faithful RMI demo requires multiple runtime pieces, a registry, network binding, exported objects, and remote failure handling. That is too environment-sensitive for a compact unit example.

## 4. Remember This

RMI is important historically because it made distributed objects part of early Java's enterprise story, even though modern systems often use HTTP, messaging, or RPC frameworks instead.
