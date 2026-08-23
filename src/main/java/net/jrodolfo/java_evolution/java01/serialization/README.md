# Object Serialization

Java 1.1 added object serialization so Java code could write an object graph to a stream and reconstruct it later.

## 1. What Problem Does This Feature Solve?

Some early Java systems needed to persist objects, send object graphs through messaging infrastructure, or pass arguments across RMI calls.

## 2. What Did Java Introduce?

Classes can opt in by implementing `Serializable`. `ObjectOutputStream` writes object data, and `ObjectInputStream` reconstructs it.

## 3. What Does The Example Show?

`SerializationExamples` uses trusted in-memory bytes to show the core mechanics:

- a class opts in with `Serializable`
- `ObjectOutputStream` writes an object to bytes
- `ObjectInputStream` reconstructs the object from bytes
- nested serializable objects are included in the object graph
- `transient` fields are excluded from the serialized form
- deserialization creates a new object with restored state, not the same identity

The example uses only bytes created inside the test. It does not read serialized data from files, sockets, HTTP requests, or other untrusted sources.

## 4. What Is The Modern Caution?

Java serialization is easy to demonstrate, but it is legacy-sensitive infrastructure. Deserializing untrusted data can be dangerous, and a serialized form can become a long-lived compatibility contract between versions of a class.

Modern systems often prefer explicit formats such as JSON, protocol buffers, or database rows for application data exchange. When Java serialization is used, the input source and filtering strategy matter.

## 5. Remember This

Treat Java serialization as legacy-sensitive infrastructure. Do not deserialize untrusted data without strong filtering and a clear reason.
