# Compact Object Headers by Default

An object header is the JVM bookkeeping stored alongside a Java object's
application fields. A useful mental model is:

```text
Java object in memory
|-- object header: JVM bookkeeping
`-- object fields: application data
```

On supported 64-bit HotSpot configurations, Java 27 enables Compact Object
Headers by default. Reducing header bookkeeping can lower heap use and improve
data locality when an application creates very many objects, but the saving
depends on the object shapes and workload. The Java language specification does
not guarantee a particular object layout or a fixed saving for every object.

The example inspects `PrintFlagsFinal`; it does not measure object sizes,
memory savings, or cache behavior.

Example: `CompactObjectHeadersDefaultExamples`
Test: `CompactObjectHeadersDefaultExamplesTest`
