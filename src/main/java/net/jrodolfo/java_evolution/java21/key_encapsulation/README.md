# Key Encapsulation Mechanism API

Java 21 introduced the Key Encapsulation Mechanism (KEM) API through JEP 452. The API lives in `javax.crypto.KEM` and gives Java a standard shape for KEM algorithms.

This topic deserves more explanation than a short class comment because it sits at the boundary between Java, networking, and modern cryptography. For recurring acronyms, see the [project glossary](../../../../../../../../docs/glossary.md).

## The Problem

Imagine Alice wants to send encrypted data to Bob.

The data may be large: a file, a stream of API traffic, or a long application session. For that kind of data, symmetric encryption is usually the practical choice.

Symmetric encryption means the same secret key is used for encryption and decryption:

```text
message + secret key -> encrypted message
encrypted message + same secret key -> original message
```

Advanced Encryption Standard (AES) is the most common example. It is fast and widely used for protecting application data.

The problem is not AES itself. The problem is key agreement:

```text
How does Alice give Bob the AES key without sending the AES key directly?
```

If Alice simply sends the key over the network, anyone listening can copy it.

## Public And Private Keys

The traditional answer uses asymmetric cryptography.

Bob creates a key pair:

```text
public key  -> safe to share
private key -> kept secret by Bob
```

Alice can use Bob's public key as part of a secure setup process. Bob uses his private key to complete the other side of that process.

Older designs often transported a symmetric key by encrypting that key with public-key cryptography. That can work, but it requires careful choices around algorithms, key sizes, modes, and padding.

## What Padding Means

Padding means adding extra bytes so data fits the size or structure required by a cryptographic algorithm.

For example, a block cipher may require data to fit an exact block size. If the message is shorter than the block, extra bytes are added and later removed.

Padding sounds simple, but bad padding design or error handling can leak information. A padding oracle attack happens when a system reveals different behavior for valid and invalid padding, giving attackers clues they can use without knowing the secret key.

KEM does not magically remove every cryptographic concern, but it gives key establishment a cleaner API boundary than directly encrypting a symmetric key yourself.

## What KEM Changes

A Key Encapsulation Mechanism separates the problem into two operations:

- encapsulation
- decapsulation

The sender does not send the shared secret.

Instead, the sender uses the receiver's public key to produce two things:

```text
shared secret
encapsulation message
```

The sender keeps the shared secret locally and sends only the encapsulation message.

The receiver combines the encapsulation message with the private key:

```text
private key + encapsulation message -> same shared secret
```

Now both sides have matching secret key material, but the secret itself was not transmitted.

## Where AES Fits

KEM is usually not the algorithm that encrypts all application data.

A common high-level design is:

```text
KEM
  -> shared secret
  -> key derivation
  -> AES key
  -> encrypted application data
```

KEM helps establish the secret. Symmetric encryption algorithms such as AES protect the larger data.

## Java 21 API Flow

This package demonstrates that flow with:

- `KemReceiver`
- `KemSender`
- `EncapsulatedSecret`
- `KeyEncapsulationExchange`

The example uses:

- `KeyPairGenerator.getInstance("X25519")`
- `KEM.getInstance("DHKEM")`
- `KEM.Encapsulator`
- `KEM.Decapsulator`

The important point is that Java 21 standardized the API. Specific algorithms, such as Diffie-Hellman Key Encapsulation Mechanism (DHKEM), are still supplied by Java security providers.

## Sender And Receiver Flow

Receiver:

```text
create X25519 key pair
share public key
keep private key secret
```

Sender:

```text
receive public key
create KEM encapsulator
encapsulate
keep shared secret
send encapsulation message
```

Receiver:

```text
receive encapsulation message
create KEM decapsulator with private key
decapsulate
recover same shared secret
```

The test verifies that the sender and receiver secret bytes match.

## Why Java Added This

KEM is important for modern secure protocols and post-quantum cryptography work. Many newer cryptographic designs use key encapsulation concepts, and Java needed a standard Java Cryptography Architecture API for that family of algorithms.

This is a good example of Java evolving as a platform, not only as a language. Java 21 is famous for virtual threads and pattern matching, but JEP 452 shows the platform also adapting to long-term security needs.
