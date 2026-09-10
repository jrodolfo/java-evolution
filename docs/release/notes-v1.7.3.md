# Release Notes: v1.7.3

`java-evolution` v1.7.3 is a patch release that corrects the Maven project
version and improves the presentation of generated JavaDoc pages.

## Highlights

- Updated the Maven project version from `0.0.1-SNAPSHOT` to `1.7.3`, ensuring
  the project metadata matches the release tag.
- Added explicit document and browser-window titles to generated JavaDoc.

## Validation

- `make show-versions` confirmed JDK 27 and Maven 3.9.16.
- `make check-build` passed with 478 tests, 0 failures, 0 errors, and 16 skips.
- `make audit-docs` passed.
- `./mvnw javadoc:javadoc` passed.
- `git diff --check` passed.

## Suggested GitHub Release Text

```text
java-evolution v1.7.3 corrects the Maven project version to 1.7.3 and improves generated JavaDoc page titles.

The release keeps the Java 27 build baseline and aligns the project metadata with the v1.7.3 release tag.
```
