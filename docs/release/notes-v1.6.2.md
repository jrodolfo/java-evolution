# Release Notes: v1.6.2

`java-evolution` v1.6.2 is a maintenance release for the JDK 26 build baseline.

## Highlights

- Fixed macOS GitHub Actions builds so the Makefile honors the JDK 26 selected by `actions/setup-java` through `JAVA_HOME`.
- Kept local macOS JDK discovery available when `JAVA_HOME` is not explicitly set.
- Removed repeated JDK 26 removal warnings from the intentional Java 2 `SocketPermission` example without hiding unrelated compiler diagnostics.

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

- macOS, Ubuntu, and Windows GitHub Actions build jobs passed with JDK 26 after the Makefile fix.
- Focused security test passed with 5 tests, 0 failures/errors.
- Full Maven suite passed with 451 tests, 0 failures/errors, and 11 expected skips.
- JavaDoc generation passed.
- Documentation navigation audit passed.
- `git diff --check` passed.
- No `SocketPermission` removal warnings remained in the JDK 26 Maven build output.
- `make links` should be run locally before publishing because external network access is restricted in the Codex sandbox.

## Suggested GitHub Release Text

```text
java-evolution v1.6.2 is a maintenance release for the JDK 26 build baseline.

Highlights:
- fixed macOS GitHub Actions JDK selection through JAVA_HOME
- preserved local macOS JDK discovery when JAVA_HOME is not explicitly set
- removed repeated expected SocketPermission removal warnings from the historical Java 2 example

Validation:
- JDK 26 GitHub Actions builds passed on Ubuntu, macOS, and Windows
- mvn test
- make docs
- node scripts/check-doc-navigation.mjs
- git diff --check
- make links locally before publishing
```
