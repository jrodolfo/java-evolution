# Lazy Constants Third Preview

An eager `final` field can provide a stable value, but its computation happens
immediately. Hand-written lazy initialization defers the computation, but the
developer must implement caching and coordinate concurrent first access
correctly.

Lazy Constants continue as a Java 27 third preview. They defer computation,
establish the value at most once after successful initialization, and reuse the
established value on later reads. Here, “constant” means that established
stored value or reference, not a deeply immutable object graph: a referenced
object may still be mutable.

The child probe shows the first `get()` performing the computation, later
`get()` calls reusing the value, and a counter remaining at `1`. It uses the
matching Java 27 preview compiler and child JVM rather than pretending the
preview API is already final.

Example: `LazyConstantsThirdPreviewExamples`  
Test: `LazyConstantsThirdPreviewExamplesTest`
