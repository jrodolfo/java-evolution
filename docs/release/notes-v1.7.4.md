# Release Notes: v1.7.4

`java-evolution` v1.7.4 is a patch release that restores package descriptions
for Java 27 in the generated JavaDoc and aligns the Maven project version with
the release.

## Highlights

- Added `package-info.java` descriptions to all nine Java 27 feature packages,
  so their descriptions appear in the generated Javadoc package overview.
- Updated the Maven project version to `1.7.4`.

## Validation

- `make show-versions` confirmed JDK 27 and Maven 3.9.16.
- `make check-build` passed with 478 tests, 0 failures, 0 errors, and 16 skips.
- `make audit-docs` passed.
- `./mvnw javadoc:javadoc` passed.
- `git diff --check` passed.

## Suggested GitHub Release Text

```text
java-evolution v1.7.4 restores Java 27 package descriptions in the generated JavaDoc and updates the Maven project version to 1.7.4.

All nine Java 27 feature packages now use the repository's established package-info.java convention, so their descriptions appear in the generated package overview.
```
