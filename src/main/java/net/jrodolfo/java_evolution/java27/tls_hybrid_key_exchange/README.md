# Post-Quantum Hybrid Key Exchange

TLS uses public-key cryptography during key establishment so two parties can
agree on secret key material without sending that secret directly. Current
public-key algorithms protect traffic against attacks that are practical
today, but a future cryptographically relevant quantum computer could threaten
some widely used algorithms. An attacker can also record encrypted traffic now
and hope to decrypt it later when better capabilities exist.

Post-quantum cryptography is designed to resist those quantum attacks. A hybrid
key exchange combines a traditional mechanism with a post-quantum mechanism,
so the result does not depend exclusively on either family. TLS calls each
negotiated option a named group. `X25519` is a widely used elliptic-curve key
agreement mechanism; `ML-KEM` is a post-quantum key-encapsulation mechanism;
`X25519MLKEM768` names a group that combines them, with 768 referring to the
ML-KEM parameter set used by that combination.

Java 27 exposes this hybrid named group for TLS 1.3. The example configures
`SSLParameters` and checks provider support without contacting a remote server.
That proves local API and configuration availability only; it does not prove
successful negotiation with a particular peer, interoperability with every
TLS implementation, or network performance and security behavior.

Example: `PostQuantumHybridKeyExchangeExamples`
Test: `PostQuantumHybridKeyExchangeExamplesTest`
