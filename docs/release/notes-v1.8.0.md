# Release Notes: v1.8.0

`java-evolution` v1.8.0 is a patch release that reconciles the repository
with the Java 27 GA release on September 15, 2026.

## Highlights

- Updated current Java 27 documentation to distinguish the GA platform release
  from features that remain preview or incubator APIs.
- Updated GitHub Actions to use the official Oracle OpenJDK 27 distribution
  through `actions/setup-java@v6`, while preserving Maven dependency caching.
- Updated the Maven project version to `1.8.0`.

Historical release notes and changelog entries retain their original pre-GA
wording where it accurately described the state at the time of those releases.

## Validation

- `make check-build` passed with the GA JDK 27 runtime.
- `make audit-docs` passed.
- `node scripts/check-doc-navigation.mjs` passed.
- `./mvnw javadoc:javadoc` passed.
- `git diff --check` passed.

## Suggested GitHub Release Text

```text
java-evolution v1.8.0 reconciles the repository with the Java 27 GA release.

Current documentation now reflects the September 15, 2026 GA status while preserving the maturity labels of Java 27 preview and incubator features. GitHub Actions now use the official Oracle OpenJDK 27 distribution with Maven caching.
```
