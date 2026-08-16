# Key Derivation Function API Preview

Java 24 previewed the Key Derivation Function API in JEP 478.

A Key Derivation Function, or KDF, derives new key material from existing secret material and context information. Java 25 finalized the API and this repository demonstrates the final runnable form in the Java 25 [`key_derivation`](../../java25/key_derivation/README.md) module.

For recurring acronyms, see the [project glossary](../../../../../../../../docs/glossary.md).

## What Problem Does This Feature Solve?

Cryptographic protocols often start with shared secret material.

That shared secret might come from:

- a Key Encapsulation Mechanism (KEM)
- Module-Lattice-Based Key Encapsulation Mechanism (ML-KEM)
- another key exchange process

The shared secret is important, but it is usually not the exact key every part of the protocol should use directly.

A protocol may need different keys for different jobs:

- one key for encrypting messages
- one key for authenticating messages
- one key for client-to-server traffic
- another key for server-to-client traffic

Reusing the same raw secret everywhere is fragile. If one usage is weakened, other usages can be affected.

## How Was This Commonly Done Before?

Developers and libraries already used key derivation algorithms such as HMAC-based Key Derivation Function (HKDF).

The problem was not that key derivation was new.

The problem was that Java did not have one standard JDK API shape for deriving key material across providers. Applications often relied on provider-specific APIs, custom helper code, or library-specific abstractions.

Java 24 previewed a standard API so this operation could become part of the Java Cryptography Architecture.

## What Did Java 24 Preview?

Java 24 previewed a standard Key Derivation Function API.

At a high level, a KDF takes:

- input key material
- salt
- context information
- requested output length

and produces derived key material:

```text
input secret + salt + context + length
        |
        v
derived key material
```

The derived key material can then be used for a specific cryptographic purpose, such as an encryption key or authentication key.

## Important Terminology

**Input key material**

The starting secret material passed into the KDF. It might come from a KEM or another key exchange.

**Salt**

Extra non-secret input that helps separate derivations and strengthens the derivation process.

**Context information**

A label or byte sequence that ties the derived key to a purpose. In HKDF, this is often called `info`.

**Output length**

The number of bytes of derived key material requested from the KDF.

**Purpose-specific key**

A derived key intended for one job, such as message encryption or message authentication.

**Preview feature**

A feature included in a JDK release for feedback before finalization. Java 24 previewed the KDF API, and Java 25 finalized it.

## Connection To KEM And ML-KEM

KEM and ML-KEM establish shared secret material.

KDF turns shared secret material into purpose-specific keys.

The flow looks like:

```text
KEM or ML-KEM
  -> shared secret
  -> KDF with salt and context
  -> purpose-specific derived key
```

That is why KDF naturally follows the Java 21 KEM example and the Java 24 quantum-resistant cryptography examples.

## Why This Module Has Notes Instead Of A Java 24 Example

The KDF API became final in Java 25.

This repository keeps the runnable final example there:

```text
src/main/java/net/jrodolfo/java_evolution/java25/key_derivation/README.md
```

The Java 24 package exists to explain the preview step and prepare the learner for the final Java 25 HKDF example.

## What The Test Proves

`KeyDerivationFunctionPreviewNotesTest` does not run the Java 25 KDF API.

It protects the educational note by checking that the note explains:

- why protocols derive keys from secret material
- purpose-specific derived keys
- input key material, salt, context, and output length
- separation between encryption and authentication keys
- Java 24 preview status
- the Java 25 final runnable example

That is the useful test boundary for this explanatory module.

## Realistic Use Case

Imagine a client and server establish one shared secret.

They may derive separate keys like this:

```text
shared secret + salt + "client-to-server encryption" -> client encryption key
shared secret + salt + "server-to-client encryption" -> server encryption key
shared secret + salt + "message authentication"      -> authentication key
```

The context labels are not secret. Their job is to make each derived key belong to one purpose.

## When Not To Use It

Do not invent custom key-derivation logic.

Use standard algorithms and provider APIs. The Java 25 module demonstrates HKDF through the final JDK API.

## Remember This

A KDF does not create security from nothing. It takes existing secret material plus context and derives key material for a specific purpose. Java 24 previewed the API; Java 25 finalized it.
