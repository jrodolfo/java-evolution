# Key Derivation Function API

Java 25 finalized the Key Derivation Function API in JEP 510.

A Key Derivation Function, or KDF, derives new key material from existing secret material and context information. This is an important piece of real cryptographic protocols because the first shared secret is rarely the exact key every part of the application should use directly.

For recurring acronyms, see the [project glossary](../../../../../../../../docs/glossary.md).

## The Problem

Imagine two parties completed a key exchange, such as the Java 21 KEM example or the Java 24 ML-KEM example.

They now share secret material:

```text
shared secret
```

That does not mean the application should use those bytes directly everywhere.

A protocol may need different keys for different purposes:

- one key for encrypting messages
- one key for authenticating messages
- one key for client-to-server traffic
- another key for server-to-client traffic

Reusing the same raw secret for every purpose is fragile. If one use is weakened or exposed, other uses can be affected.

## What HKDF Does

HMAC-based Key Derivation Function (HKDF) uses HMAC, a hash-based message authentication code, as the cryptographic building block for deriving key material.

At a high level, HKDF takes:

- input key material
- salt
- context information
- requested output length

and produces derived key bytes:

```text
input secret + salt + context -> derived key material
```

The context is sometimes called `info`. It binds the derived key to a specific purpose.

## Why Context Matters

The same input secret can safely produce different derived keys when the context changes.

For example:

```text
shared secret + salt + "message encryption" -> encryption key
shared secret + salt + "message authentication" -> authentication key
```

The context label is not secret. Its job is separation: the derived key should be clearly tied to one purpose.

## Java 25 API Flow

The example uses:

- `KDF.getInstance("HKDF-SHA256")`
- `HKDFParameterSpec.ofExtract()`
- `addIKM(...)` for input key material
- `addSalt(...)` for salt
- `thenExpand(...)` for context and output length
- `deriveData(...)` for derived bytes

The example derives 32 bytes, enough material for an AES-256 key.

## Connection To KEM

KEM establishes shared secret material.

KDF turns shared secret material into purpose-specific keys.

Together, the flow looks like:

```text
KEM
  -> shared secret
  -> KDF with salt and context
  -> encryption key or authentication key
```

That is why this feature fits naturally after the Java 21 KEM API and Java 24 module-lattice cryptography examples.
