# Java 27

Released: September 2026 as Java SE 27.

Java 27 makes G1 the default collector in all environments, enables Compact
Object Headers by default, adds post-quantum hybrid key exchange for TLS 1.3,
and continues several preview and incubator features.

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
