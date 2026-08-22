# Release Notes: v1.3.1

`java-evolution` v1.3.1 is a documentation-focused patch release that adds historical release chronology to the Java 1 through Java 25 learning path.

The project already explains how Java changed. This release adds the missing time context: when each Java version arrived, how platform naming changed, and where the repository's own releases differ from Java platform releases.

## Highlights

- Added `docs/java-release-timeline.md` with release month/year and naming context from Java 1 through Java 25.
- Added release-date context to every Java version README.
- Added release chronology to the root README feature index and linked the timeline from study/navigation docs.
- Clarified the distinction between repository release history and Java platform release history.
- Clarified that Java 1.3 and Java 1.4 were marketed under the Java 2 Platform, Standard Edition brand even though this repository uses `java03` and `java04` module names.
- Added `VERBOSE=-v` and `VERBOSE=-vv` pass-through support for `make links`.
- Removed a timeline reference link that returned HTTP 403 during Markdown link checks.
- Cleaned up an unsupported JavaDoc tag in the Java 4 NIO example documentation.

## Why This Matters

The repository is a historical learning path, and history is easier to remember when features are attached to time.

Month/year release context helps learners connect language changes to eras: early Java foundations in the late 1990s, Java 5's major language modernization in 2004, Java 8's functional shift in 2014, the six-month release cadence starting in 2018, and the long-term-support releases that many teams still standardize on.

This release also keeps project release documentation separate from Java platform release documentation, so `CHANGELOG.md` remains about this repository while `docs/java-release-timeline.md` explains Java itself.

## Validation

Before publishing this release, run:

```bash
node scripts/check-doc-navigation.mjs
make docs
make links
git diff --check
```

Expected result:

- documentation navigation audit passes
- JavaDoc generation succeeds
- Markdown link check passes
- whitespace diff check passes

## Suggested GitHub Release Text

```text
java-evolution v1.3.1 is a documentation-focused patch release for Java release chronology.

Highlights:
- added a Java release timeline with month/year context from Java 1 through Java 25
- added release-date context to every Java version README
- linked release chronology from the root README and study/navigation docs
- clarified repository release history vs Java platform release history
- clarified historical naming for Java 1.3 and Java 1.4 under the Java 2 Platform, Standard Edition brand
- added VERBOSE=-v / VERBOSE=-vv support for make links
- removed a blocked timeline reference that returned HTTP 403 in link checks
- cleaned a JavaDoc warning in the Java 4 NIO documentation

Validation:
- node scripts/check-doc-navigation.mjs
- make docs
- make links
- git diff --check
```
