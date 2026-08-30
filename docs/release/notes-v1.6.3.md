# Release Notes: v1.6.3

`java-evolution` v1.6.3 is a maintenance release focused on completing selected Java 26 C1 migrations and aligning the learner documentation with the current feature coverage.

## Highlights

- Converted Java 26 Structured Concurrency, Lazy Constants, PEM Encodings, and Primitive Patterns from explanatory notes into executable child-JVM examples with focused tests.
- Updated Java 26 feature maps, learning guides, migration guidance, demo scripts, package documentation, and README content to identify eight C1 examples and two C2 explanatory modules.
- Corrected the PowerShell JDK 26 activation documentation so the helper is dot-sourced and environment changes persist in the current session.

## Validation

Run the following commands before publishing:

```bash
make java-version
make check-java-26
mvn test
make docs
make links
node scripts/check-doc-navigation.mjs
make demos
git diff --check
```

Observed validation during release preparation:

- JDK 26 Maven test suite passed with 459 tests, 0 failures, 0 errors, and 11 expected skips.
- JavaDoc generation passed.
- Documentation navigation audit passed.
- Markdown link validation passed with 1,092 links checked and 0 errors.
- `git diff --check` passed.

## Suggested GitHub Release Text

```text
java-evolution v1.6.3 is a maintenance release focused on completing selected Java 26 C1 migrations and aligning learner documentation with the current feature coverage.

Highlights:
- converted Java 26 Structured Concurrency, Lazy Constants, PEM Encodings, and Primitive Patterns into executable child-JVM examples
- updated Java 26 documentation to identify eight C1 examples and two C2 explanatory modules
- corrected PowerShell JDK 26 activation guidance so environment changes persist in the current session

Validation:
- JDK 26 Maven test suite: 459 tests, 0 failures, 0 errors, 11 expected skips
- JavaDoc generation
- documentation navigation audit
- Markdown link validation: 1,092 links checked, 0 errors
- git diff --check
```
