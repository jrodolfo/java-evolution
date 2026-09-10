# Release Notes: v1.7.2

`java-evolution` v1.7.2 is a patch release that makes the JDK 27 build
requirement explicit for direct Maven commands and keeps repository automation
aligned with the current `main` branch structure.

## Highlights

- Added Maven Enforcer validation in the `validate` phase with the exact Java
  version range `[27,28)`. Direct Maven commands now stop before compilation
  with Java 27 setup guidance when Maven uses another JDK.
- Updated Javadoc CI to use the temporary JDK 27 early-access setup and removed
  the obsolete `java-27` branch trigger.
- Added direct links for Maven, GNU Make, and Node.js in the root requirements
  documentation.

## Validation

- `make show-versions` confirmed JDK 27 and Maven 3.9.16.
- `./mvnw test` passed with 478 tests, 0 failures, 0 errors, and 16 skips.
- `make audit-docs` passed.
- `./mvnw javadoc:javadoc` passed.
- `make check-links` was attempted, but external requests were blocked in the
  local environment; rerun it in an unrestricted environment.
- `git diff --check` passed.

## Suggested GitHub Release Text

```text
java-evolution v1.7.2 makes the JDK 27 build requirement explicit for direct Maven commands and keeps CI aligned with the current main branch structure.

Maven now validates the active Java runtime before compilation and reports clear platform-specific instructions when Java 27 is not selected. Javadoc CI no longer targets the obsolete java-27 branch, and the root requirements documentation links the required build and documentation tools.
```
