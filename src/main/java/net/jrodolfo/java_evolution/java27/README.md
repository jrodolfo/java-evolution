# Java 27

Java 27 is scheduled for general availability in September 2026. This package
documents the repository's current pre-GA JDK 27 build.

Java 27 groups its changes into four themes:

- JVM ergonomics and memory layout: G1 becomes the default collector in more
  HotSpot environments, and Compact Object Headers are enabled by default.
- Security: post-quantum hybrid TLS key establishment, JFR data redaction, and
  PEM encodings.
- Language, API, and concurrency previews: Lazy Constants, primitive patterns,
  and Structured Concurrency.
- Performance-oriented incubation: the Vector API continues its incubator
  evolution.

The G1 and object-header changes are HotSpot JVM behavior, not Java-language or
Java-SE layout guarantees. The security and preview topics are described by
their dedicated examples below.

## Executable examples

- [`compact_object_headers`](compact_object_headers/README.md): verifies the
  default VM flag without measuring object layouts.
- [`tls_hybrid_key_exchange`](tls_hybrid_key_exchange/README.md): inspects and
  configures the standard TLS named-group API without a live network.
- [`jfr_data_redaction`](jfr_data_redaction/README.md): probes JDK 27 JFR
  redaction-option availability without creating a recording.
- [`lazy_constants`](lazy_constants/README.md): Java 27 third preview.
- [`primitive_patterns`](primitive_patterns/README.md): Java 27 fifth preview.
- [`structured_concurrency`](structured_concurrency/README.md): Java 27
  seventh preview.
- [`pem_encodings`](pem_encodings/README.md): Java 27 third preview.

Preview examples compile and run generated source in an isolated child JVM
with `--enable-preview` and `--release 27`. The main Maven build does not use
preview flags.

## Explanatory modules

- [`g1_default`](g1_default/README.md): explains the default-collector change;
  meaningful validation requires representative workloads.
- [`vector_api`](vector_api/README.md): records the twelfth incubator status
  without duplicating the earlier executable SIMD demonstrations.

## Focused tests

```bash
mvn -Dtest=CompactObjectHeadersDefaultExamplesTest,PostQuantumHybridKeyExchangeExamplesTest,JfrRedactionOptionExamplesTest test
mvn -Dtest=LazyConstantsThirdPreviewExamplesTest,PrimitivePatternsFifthPreviewExamplesTest,StructuredConcurrencySeventhPreviewExamplesTest,PemEncodingsThirdPreviewExamplesTest test
mvn -Dtest=G1DefaultNotesTest,VectorApiTwelfthIncubatorNotesTest test
```
