# Quantum-Resistant Crypto

Java 24 added standard support for module-lattice cryptographic algorithms:

- Module-Lattice-Based Key Encapsulation Mechanism (ML-KEM)
- Module-Lattice-Based Digital Signature Algorithm (ML-DSA)

These algorithms are intended for post-quantum security requirements.

For recurring acronyms, see the [project glossary](../../../../../../../../docs/glossary.md).

## The Problem

Much of modern secure communication depends on public-key cryptography. Public/private key systems let two parties establish secrets, verify identities, and sign data without sharing private keys.

The long-term concern is quantum computing. Large enough quantum computers could threaten some widely used public-key algorithms. Post-quantum cryptography prepares systems with algorithms designed to resist both classical and quantum attacks.

## Key Encapsulation And Signatures

ML-KEM and ML-DSA solve different problems.

ML-KEM helps two parties establish shared secret material:

```text
receiver public key
  -> sender encapsulates
  -> shared secret + encapsulation message
  -> receiver decapsulates with private key
  -> same shared secret
```

ML-DSA signs and verifies messages:

```text
private key + message -> signature
public key + message + signature -> valid or invalid
```

The KEM example relates directly to the Java 21 `javax.crypto.KEM` example. Java 21 standardized the KEM API shape. Java 24 added post-quantum KEM algorithms that can use that API.

## Java APIs Used Here

The examples use standard Java Cryptography Architecture APIs:

- `KeyPairGenerator.getInstance("ML-KEM")`
- `KEM.getInstance("ML-KEM")`
- `KeyPairGenerator.getInstance("ML-DSA")`
- `Signature.getInstance("ML-DSA")`

The classes are named with `ModuleLattice...` instead of `Ml...` to avoid confusing the lowercase letter `l` with the number `1`.

## What The Examples Show

`ModuleLatticeKemExample`:

- generates an ML-KEM key pair
- encapsulates a shared secret with the public key
- decapsulates with the private key
- verifies both sides derived the same key bytes

`ModuleLatticeDsaExample`:

- generates an ML-DSA key pair
- signs a message with the private key
- verifies the original message with the public key
- rejects a tampered message

## Provider Note

Java defines standard algorithm names, but concrete implementations come from security providers installed in the JDK or application runtime. These examples use the providers available in the JDK used by this project.
