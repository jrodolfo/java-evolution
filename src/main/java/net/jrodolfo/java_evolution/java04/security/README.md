# Integrated Security APIs

J2SE 1.4 integrated important security APIs into the standard platform.

This is an executable local-security example. It demonstrates the provider
model, message digests, secure random bytes, key generation, HMAC
authentication, and RSA signatures using APIs that belong to the Java 4-era
JCA/JCE platform.

## 1. What Problem Does This Feature Solve?

Security features such as cryptography, secure sockets, and authentication needed to be standard platform capabilities rather than separate optional packages.

## 2. What Did Java Introduce?

Java 4 integrated JCE, JSSE, and JAAS into J2SE.

JCE (Java Cryptography Extension) covers cryptographic operations such as
encryption, key generation, and message authentication. JSSE (Java Secure
Socket Extension) covers secure socket protocols such as TLS. JAAS (Java
Authentication and Authorization Service) covers authentication subjects and
authorization decisions.

## 3. What Does The Example Show?

The executable example focuses on local JCA/JCE operations because they are
portable and deterministic:

- provider and service discovery through `java.security.Security`
- SHA-256 message digests
- `SecureRandom` byte generation
- AES key generation through the JCE provider model
- HMAC-SHA1 message authentication, retained here only as a historical
  Java 4-era provider capability; modern systems should follow current
  cryptographic guidance
- SHA256withRSA signing and verification

It intentionally does not open TLS sockets, configure JAAS login modules, or
teach a historical cipher mode as modern application guidance. Modern
authenticated-encryption choices belong in current security guidance; the
Java 4 lesson here is that the security APIs and provider model became standard
platform facilities.

## 4. Remember This

Java 4 helped make security APIs part of the normal Java platform, but modern
security examples should follow current algorithms, providers, and operational
guidance. Historical weak algorithms such as DES, MD5, and SHA-1 are not used
as examples here.
