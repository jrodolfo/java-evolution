# Release Notes: v1.2.0

`java-evolution` v1.2.0 is a learning-focused release that substantially improves the Java 8 through Java 25 reference material.

The release strengthens the repository's central purpose: helping Java developers understand what changed, why it changed, and how the feature is used. The work covers the full Java 8 through Java 25 learning path rather than a single feature or release.

## Highlights

- Expanded problem-and-solution explanations across the Java version READMEs and feature modules.
- Added or expanded explanatory modules for complex features that would be misleading as tiny executable examples.
- Improved JavaDoc, glossary terminology, JEP references, feature maps, status tables, learning navigation, and release documentation.
- Strengthened tests as executable documentation with clearer scenarios and assertions.
- Corrected historical API usage and maturity labels, including the final tooling status of JEP 458.

## Why This Matters

The project is intended to be a study and interview reference, not merely a collection of syntax samples. This release makes the learning path more useful to developers who know Java already but want to understand the evolution from Java 8 through Java 25.

Simple features remain concise. More difficult topics now receive the additional explanation, terminology, lifecycle diagrams, historical context, and migration guidance they require.

## Validation

Before publishing this release, run:

```bash
make release-check
```

Expected result:

- documentation navigation audit passes
- JavaDoc generation succeeds
- Markdown link check passes
- Maven tests pass on JDK 25
- practical demo tests pass

## Suggested GitHub Release Text

```text
java-evolution v1.2.0 is a learning-focused release that substantially improves the Java 8 through Java 25 reference material.

Highlights:
- clearer problem-and-solution explanations across Java 8 through Java 25
- expanded explanatory modules for complex and preview features
- stronger executable documentation through clearer tests
- improved JavaDoc, glossary, JEP, navigation, and feature-status documentation
- corrected historical API usage and maturity labels, including JEP 458

Validation:
- make docs-audit
- make release-check
```
