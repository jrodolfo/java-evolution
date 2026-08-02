# Java Release Template

Use this checklist when adding examples for a new Java release.

## Release Setup

- Create `src/main/java/net/jrodolfo/java_evolution/javaXX`.
- Create `src/test/java/net/jrodolfo/java_evolution/javaXX`.
- Review the OpenJDK release page and final JEP list.
- Separate final features from preview, incubator, experimental, tool, JVM, and platform-specific features.

## Example Classes

- Add one focused `*Examples` class for each feature that can be demonstrated with normal JDK 25-compatible source code.
- Add one focused `*Notes` class for features that cannot be demonstrated cleanly in normal tests.
- Add JavaDoc to each class and public method.
- Generate JavaDoc with `make docs`.
- Keep examples intentionally small and interview-readable.

## Tests

- Add a matching `*Test` class for every `*Examples` class.
- Add tests for `*Notes` classes when the notes expose structured summary methods.
- Make each test explain the expected behavior through method names, assertions, or short local comments.
- Run `make check`.

## Documentation

- Add a version README using the same shape as the existing packages: release overview, feature sections, how to read this package, and references.
- Add the release to the `README.md` feature index.
- Add official OpenJDK and JEP links where useful.
- Add the release to `docs/feature-map.md`.
- Add the release to `docs/status-matrix.md`.
- Add the release or key features to `docs/learning-path.md` and `docs/demo-script.md` when they change the suggested study flow.
- Mention preview or incubator status explicitly when a feature was not final in that release.
- Run `make docs-audit` to catch stale grouped-test names, wildcard test references, and missing version READMEs.
- Confirm generated JavaDoc is readable for the new classes.

## Final Check

- Confirm `make docs-audit` passes.
- Confirm `make check` passes.
- Confirm `make docs` passes.
- Confirm `git status --short` only shows intentional changes.
- Use a lower-case commit message.
