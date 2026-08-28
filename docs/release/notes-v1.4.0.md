# Release Notes: v1.4.0

`java-evolution` v1.4.0 expands the repository from Java 1 through Java 25 coverage to Java 1 through Java 26 coverage.

Java 26 is represented as C2 explanatory material while the project continues to build and test on JDK 25. This keeps the repository stable for learners while still documenting the newest Java platform changes.

## Highlights

- Added Java 26 package documentation and notes under `src/main/java/net/jrodolfo/java_evolution/java26/`.
- Documented all 10 Java 26 JEP areas:
  - JEP 500: Prepare to Make Final Mean Final
  - JEP 504: Remove the Applet API
  - JEP 516: Ahead-of-Time Object Caching with Any GC
  - JEP 517: HTTP/3 for the HTTP Client API
  - JEP 522: G1 GC throughput improvement
  - JEP 524: PEM Encodings of Cryptographic Objects, second preview
  - JEP 525: Structured Concurrency, sixth preview
  - JEP 526: Lazy Constants, second preview
  - JEP 529: Vector API, eleventh incubator
  - JEP 530: Primitive Types in Patterns, `instanceof`, and `switch`, fourth preview
- Added focused tests for each Java 26 notes class.
- Updated the root README, Java release timeline, feature map, status matrix, JEP index, learning path, study guide, migration guide, interview guide, and glossary for Java 26.
- Expanded the documentation navigation audit so it checks Java 1 through Java 26.

## Why This Matters

The repository is a historical learning path, not only a collection of runnable snippets.

Java 26 includes final API work, removal work, preview APIs, incubator APIs, and runtime behavior. Representing it as notes-only under the JDK 25 baseline teaches maturity discipline: learners can see what changed in Java 26 without confusing newer APIs or preview syntax with code that should compile in the current project build.

This release also preserves the practical value of JDK 25 as the project baseline. JDK 25 remains a strong long-term-support foundation, while Java 26 is documented as current-release awareness.

## Validation

Before publishing this release, run:

```bash
mvn "-Dtest=Http3ClientExamplesTest,PemEncodingsSecondPreviewNotesTest,PrimitivePatternsFourthPreviewNotesTest,FinalFieldRestrictionsExamplesTest,AppletApiRemovalExamplesTest,AotObjectCachingNotesTest,G1SynchronizationNotesTest,StructuredConcurrencySixthPreviewNotesTest,LazyConstantsSecondPreviewNotesTest,VectorApiEleventhIncubatorNotesTest" test
node scripts/check-doc-navigation.mjs
make docs
make links
git diff --check
```

Expected result outside restricted network and socket-binding environments:

- Java 26 focused tests pass
- documentation navigation audit passes
- JavaDoc generation succeeds
- Markdown link check passes
- whitespace diff check passes

Known local-environment note from release preparation:

- Full `mvn test` can fail in restricted environments if local socket binding is denied for the Java 18 Simple Web Server tests.

## Suggested GitHub Release Text

```text
java-evolution v1.4.0 expands the project from Java 1-25 coverage to Java 1-26 coverage.

Java 26 is represented as notes-only C2 explanatory material while the project remains on the JDK 25 build baseline.

Highlights:
- added Java 26 package documentation and notes
- documented all 10 Java 26 JEP areas
- added focused tests for every Java 26 notes class
- updated README, Java release timeline, feature map, status matrix, JEP index, learning path, study guide, migration guide, interview guide, and glossary
- expanded documentation navigation validation to include java26

Validation:
- Java 26 focused Maven test suite
- node scripts/check-doc-navigation.mjs
- make docs
- make links
- git diff --check
```
