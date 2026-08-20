# Object Serialization

Java 1.1 added object serialization.

## 1. What Problem Does This Feature Solve?

Some early Java systems needed to write object graphs to streams for persistence, messaging, or RMI argument passing.

## 2. What Did Java Introduce?

Classes can opt in by implementing `Serializable`. `ObjectOutputStream` writes object data, and `ObjectInputStream` reconstructs it.

## 3. Why This Repository Uses Notes

Serialization is executable, but a cheerful tiny demo would be misleading today. Deserialization can be dangerous with untrusted data, and serialized forms become compatibility contracts.

## 4. Remember This

Treat Java serialization as legacy-sensitive infrastructure. Do not deserialize untrusted data without strong filtering and a clear reason.
