# Release Notes: v1.7.1

`java-evolution` v1.7.1 is a patch release that improves the Windows GitHub
Actions workflow and standardizes the repository build commands after the
v1.7.0 release.

## Highlights

- Updated the build workflow so Windows runs the existing Makefile check target
  through Bash, allowing the POSIX-style Maven Wrapper invocation to work on
  the hosted runner.
- Passed `JAVA_CMD=java` to the Makefile in CI so Java is resolved from the
  JDK 27 setup action's PATH rather than from a Windows-form `JAVA_HOME` path.
- Updated the Makefile to select `mvnw.cmd` on Windows and standardized the
  repository targets for showing versions, running tests, generating docs,
  auditing docs, checking links, and running the release gate.
- Updated current documentation and project templates to use the standardized
  targets and linked the JDK 27 download from the root requirements.
- Removed the obsolete Java 26 environment helpers and prerequisite checker now
  that JDK 27 is the repository baseline.
- No Java production or test source files changed after v1.7.0.

## Validation

The Windows GitHub Actions job passed after this correction. Local validation
results:

- `make show-versions` confirmed JDK 27 and Maven 3.9.16.
- `make check-build` passed with 478 tests, 0 failures, 0 errors, and 16 skips.
- `./mvnw test` passed with 478 tests, 0 failures, 0 errors, and 16 skips.
- `make generate-docs` completed successfully.
- `make audit-docs` passed.
- `make check-links` could not complete in the restricted environment because
  96 external-link requests failed; rerun it in an unrestricted environment.
- `git diff --check` passed.

## Suggested GitHub Release Text

```text
java-evolution v1.7.1 improves Windows GitHub Actions execution and standardizes the repository's Java 27 build commands.

The workflow now runs Makefile recipes through Bash and resolves Java from the configured PATH, allowing the existing Maven Wrapper command to work correctly on the Windows hosted runner.

The workflow now runs Makefile recipes through Bash and resolves Java from the configured PATH. The Makefile also selects the Windows Maven Wrapper command and uses consistent names for the repository validation targets.

No Java production or test source files changed after v1.7.0.
```
