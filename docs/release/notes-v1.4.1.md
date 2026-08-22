# Release Notes: v1.4.1

`java-evolution` v1.4.1 is a documentation-polish patch release after the Java 26 expansion in v1.4.0.

The release keeps the repository on the JDK 25 build baseline and focuses on making the learning guides easier to scan, especially around Java 22 through Java 26.

## Highlights

- Aligned `docs/study-guide.md` with the canonical learning stages used by `docs/learning-path.md`.
- Split current-release learning guidance into clearer Java 22-24 and Java 25-26 sections.
- Split `docs/migration-guide.md` into Java 21 to Java 25 LTS migration guidance plus Java 26 awareness.
- Updated `docs/interview-guide.md` with clearer Java 1-7 foundation context and Java 25/26 maturity talking points.
- Updated `docs/demo-script.md` to distinguish Java 25 LTS material from Java 26 notes-only current-release awareness.
- Split long Maven commands in `docs/learning-path.md` and standardized focused Maven command quoting in `docs/demo-script.md`.

## Why This Matters

Java 26 expanded the repository's scope, but the learning path still needs to feel approachable.

This patch keeps the Java 1 through Java 26 story organized around practical study stages: foundational Java, Java 8 style changes, LTS baselines, post-Java-21 platform expansion, and Java 25/26 maturity discipline.

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
java-evolution v1.4.1 is a documentation-polish patch release after the Java 26 expansion.

Highlights:
- aligned study-guide version ranges with the canonical learning path stages
- split current-release learning guidance into Java 22-24 and Java 25-26 sections
- split migration guidance into Java 21 to Java 25 LTS plus Java 26 awareness
- improved interview and demo guidance for Java 25 LTS vs Java 26 notes-only current-release awareness
- improved Maven command readability and consistency in learning walkthroughs

Validation:
- node scripts/check-doc-navigation.mjs
- make docs
- make links
- git diff --check
```
