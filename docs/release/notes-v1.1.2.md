# Release Notes: v1.1.2

`java-evolution` v1.1.2 is a documentation polish release focused on glossary and acronym clarity.

The project explains many Java platform, cryptography, tooling, and runtime topics. This release makes acronym definitions easier to find and makes the first use of important acronyms more explicit in the feature documentation.

## Highlights

- Expanded `docs/glossary.md` with recurring acronyms used across the repository.
- Added explicit first-use definitions in cryptography-focused feature READMEs.
- Added glossary links from deeper feature documentation.

## Updated Acronym Coverage

The glossary now covers terms such as:

- KEM
- ML-KEM
- ML-DSA
- HKDF
- HMAC
- AES
- DHKEM
- SPI
- NIO
- JNI
- CI
- ARM64
- SDKMAN
- ZGC

## Why This Matters

The repository is meant for learning, not only for quick reference. Acronyms can make technical writing feel compact but opaque. This release makes the association between full terms and shortened forms explicit so learners can move through the documentation with less friction.

## Validation

Before publishing this release, run:

```bash
make release-check
```

Expected result:

- documentation navigation audit passes
- JavaDoc generation succeeds
- Markdown link check passes
- Maven tests pass on JDK 25
- practical demo tests pass

## Suggested GitHub Release Text

```text
java-evolution v1.1.2 is a documentation polish release focused on glossary and acronym clarity.

This release expands glossary coverage and makes first-use acronym definitions explicit in cryptography-focused feature documentation, including KEM, ML-KEM, ML-DSA, HKDF, HMAC, AES, KDF, and related terms.

Validation:
- make release-check
```
