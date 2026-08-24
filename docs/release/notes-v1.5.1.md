# Release Notes: v1.5.1

`java-evolution` v1.5.1 is a portability patch release after the v1.5.0 executable-example expansion.

This release focuses on Windows validation stability for examples that launch child JVM or JDK tooling processes.

## Highlights

- Drained child-process output concurrently while waiting for selected child JVM and JDK tool executions.
- Prevented Windows validation from blocking when child processes produce enough output to fill their process pipe.
- Applied the fix to:
  - Java 7 `invokedynamic` bytecode inspection
  - Java 20 Vector API incubator workflow
  - Java 23 ZGC generational mode runtime probe
  - Java 24 AOT class loading workflow
  - Java 25 AOT command-line ergonomics workflow
  - Java 25 compact object headers runtime probe

## Why This Matters

Several executable examples intentionally launch child processes so preview flags, incubator modules, VM options, and bytecode inspection stay isolated from the main Maven build.

That approach is the right teaching model, but child processes need careful stream handling. Reading output only after `waitFor` can block on some platforms when a child process writes enough data to fill the pipe. This patch keeps those examples portable for Windows while preserving the JDK 25 baseline and the same learner-facing behavior.

## Validation

Before publishing this release, run:

```bash
mvn test
make docs
make links
node scripts/check-doc-navigation.mjs
git diff --check
```

Expected result:

- full Maven test suite passes on JDK 25
- JavaDoc generation succeeds
- Markdown link check passes
- documentation navigation audit passes
- whitespace diff check passes

Observed release-preparation context:

- Windows 11 build and test validation passed after the fix
- Ubuntu build and test validation passed

## Suggested GitHub Release Text

```text
java-evolution v1.5.1 is a Windows portability patch after the v1.5.0 executable-example expansion.

Highlights:
- drained child-process output concurrently while waiting for selected child JVM and JDK tool executions
- prevented Windows validation from blocking when verbose child processes fill their process pipe
- applied the fix to invokedynamic, Java 20 Vector API, Java 23 ZGC, Java 24 AOT class loading, Java 25 AOT command-line ergonomics, and Java 25 compact object headers examples

Validation:
- Windows 11 Maven build and test validation
- Ubuntu Maven build and test validation
- mvn test
- make docs
- make links
- node scripts/check-doc-navigation.mjs
- git diff --check
```
