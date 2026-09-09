# Release Notes: v1.7.0

`java-evolution` v1.7.0 is a minor release that extends the repository through
Java 27, moves the build baseline to JDK 27, and strengthens the examples,
tests, and documentation used to study Java's evolution from Java 1 onward.

JDK 27 is still pre-GA at the time of this project release. This release of
Java Evolution is stable as a repository release, but its Java 27 material
reflects the current pre-GA JDK 27 state and may require a focused follow-up
when the final JDK 27 build is available.

## Highlights

### Java 27 Baseline And Coverage

- Extended repository coverage from Java 1 through Java 26 to Java 27.
- Moved the Maven, Makefile, helper-script, and CI build baseline to JDK 27.
- Added Java 27 examples and explanatory modules for HotSpot defaults and
  object layout, hybrid post-quantum TLS, JFR redaction options, and the
  continuing Lazy Constants, Primitive Patterns, Structured Concurrency, PEM
  Encodings, and Vector API work.
- Kept workload-dependent runtime effects and incubator context explicitly
  bounded rather than presenting small probes as performance benchmarks.

### Examples And Tests

- Strengthened executable demonstrations for historical Java APIs and syntax,
  including Java 1 through Java 7 version-fidelity corrections.
- Preserved obsolete preview API and syntax shapes through focused historical
  source representations where current JDK compilation cannot reproduce them.
- Added concrete behavioral checks for Java 12 switch-preview history, Java 15
  sealed-class preview reflection, Java 19/20 FFM API evolution, and Java 21
  sequenced-collection view semantics.
- Improved Java 26 executable coverage, including the Vector API incubator and
  Generational Shenandoah product-status probe.
- Strengthened Java 27 tests for structured-concurrency failure propagation,
  Lazy Constants deferred initialization, G1 default selection, JFR redaction
  option availability, and cryptographic-object PEM round trips.

### Documentation And Workflow

- Reviewed feature READMEs across Java 1 through Java 27 for clearer learner
  explanations, historical context, and version fidelity.
- Synchronized repository-level indexes, learning guides, migration guidance,
  demo material, platform setup, and status references with the merged Java 27
  repository state.
- Added Maven Wrapper support and updated cross-platform JDK 27 activation and
  CI workflows.
- Kept the distinction clear between feature maturity, repository
  representation, and the pre-GA lifecycle of JDK 27.

## Validation

Validation results below were collected during this release preparation.

```bash
make show-versions
./mvnw test
./mvnw javadoc:javadoc
node scripts/check-doc-navigation.mjs
git diff --check
```

Observed local results:

- `make show-versions` confirmed JDK 27 and Maven 3.9.16.
- `./mvnw test` passed with 478 tests, 0 failures, 0 errors, and 16 skips.
- `./mvnw javadoc:javadoc` completed successfully.
- `node scripts/check-doc-navigation.mjs` passed.
- `make check-links` passed with 1,172 links checked, 0 errors, and 8 redirects.
- `git diff --check` passed.
- `make check-release` completed the navigation and JavaDoc checks, but its
  `lychee` step reported 95 external-link connection failures in the restricted
  environment before the successful standalone link check above.

## Suggested GitHub Release Text

```text
java-evolution v1.7.0 extends the repository through Java 27 and moves the build baseline to JDK 27.

This is a stable release of the Java Evolution project. JDK 27 remains pre-GA at the time of this release, so the Java 27 material reflects the current pre-GA JDK state and may receive a focused follow-up after the final JDK 27 build.

Highlights:
- added Java 27 feature examples and explanatory modules
- strengthened historical Java-version fidelity and preview evolution checks
- improved Java 26 executable Vector API and Generational Shenandoah coverage
- expanded behavioral tests for Java 27 runtime, preview, security, and API features
- synchronized learning guides, reference indexes, setup documentation, and CI for the JDK 27 baseline
```
