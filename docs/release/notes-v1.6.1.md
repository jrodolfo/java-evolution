# Release Notes: v1.6.1

`java-evolution` v1.6.1 is a maintenance release following the move to the JDK 26 build baseline.

## Highlights

- Hardened socket-binding skip handling for restricted test environments, including wrapped socket failures.
- Improved child-process lifecycle handling with asynchronous output draining, bounded waits, forced cleanup, and Windows executable resolution.
- Restored Java 1.1 through Java 4 examples to historically appropriate APIs for reflection, security, JNDI, and JAXP.
- Documented the distinction between Java 4-era XML processing APIs and later XML security hardening.
- Removed order dependence from the in-memory JNDI test.
- Updated documentation to reference v1.6.0 as the previous release and to include Java 26 AOT object caching consistently.

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

- Focused tests passed with 7 tests, 0 failures/errors, and 1 expected sandbox skip.
- Full Maven suite passed with 451 tests, 0 failures/errors, and 11 skips.
- JavaDoc generation passed.
- Documentation navigation audit passed.
- `git diff --check` passed.
- `make links` was not run in the restricted Codex sandbox; run it locally before publishing.

## Suggested GitHub Release Text

```text
java-evolution v1.6.1 is a maintenance release following the move to the JDK 26 build baseline.

Highlights:
- hardened socket-binding skip handling for restricted environments
- improved child-process output draining, timeouts, cleanup, and Windows executable resolution
- restored historically appropriate APIs in Java 1.1 through Java 4 examples
- documented the Java 4 JAXP boundary versus later XML security hardening
- updated documentation consistency for the v1.6.0 release and Java 26 AOT object caching

Validation:
- mvn test
- make docs
- node scripts/check-doc-navigation.mjs
- git diff --check
- make links locally before publishing
```
