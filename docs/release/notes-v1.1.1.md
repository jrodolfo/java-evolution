# Release Notes: v1.1.1

`java-evolution` v1.1.1 is a cleanup release focused on making JavaDoc and documentation navigation clearer.

After v1.1.0 added executable packages for several previously notes-only topics, some duplicate `*Notes` classes remained visible in generated JavaDoc. This release removes those redundant notes classes so learners see the executable packages and examples as the primary entry point.

## Highlights

- Removed duplicate notes classes for features now covered by executable example packages.
- Removed matching redundant notes tests.
- Updated practical demo commands to run executable example tests directly.
- Updated feature maps, status matrix, learning path, migration guide, and version READMEs to point directly to executable examples.

## Removed Duplicate Notes

- Java 18 Simple Web Server notes.
- Java 18 JavaDoc snippets notes.
- Java 21 Key Encapsulation Mechanism notes.
- Java 22 Foreign Function and Memory API notes.
- Java 24 Class-File API notes.
- Java 24 ML-KEM / ML-DSA notes.
- Java 25 Key Derivation Function API notes.

Preview, incubator, runtime, and genuinely notes-only topics remain documented as notes.

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
java-evolution v1.1.1 is a cleanup release focused on JavaDoc and documentation navigation clarity.

This release removes duplicate notes classes for features that now have dedicated executable example packages, and updates docs and demo commands to point directly to those executable examples.

Validation:
- make release-check
```
