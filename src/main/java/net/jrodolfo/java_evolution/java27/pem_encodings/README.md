# PEM Encodings Third Preview

PEM, or Privacy-Enhanced Mail, is a textual envelope around encoded binary
material. A typical public-key value looks like:

```text
-----BEGIN PUBLIC KEY-----
Base64-encoded data
-----END PUBLIC KEY-----
```

PEM is commonly used for keys, certificates, certificate requests, and related
security data. Before a standard API, applications often had to combine
cryptographic object APIs, binary encodings, Base64 handling, PEM boundary
text, and custom or third-party parsing and formatting. The Java 27 preview
works toward standardizing that conversion between cryptographic objects and
PEM text.

The child probe performs a deterministic in-memory round trip using a synthetic
`LEARNING OBJECT`. It proves encoder/decoder mechanics and boundary handling;
it does not validate a certificate, establish trust, or perform real PKI
processing. The example is compiled and run with matching Java 27 preview
flags.

Example: `PemEncodingsThirdPreviewExamples`  
Test: `PemEncodingsThirdPreviewExamplesTest`
