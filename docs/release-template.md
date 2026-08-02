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
- Keep examples intentionally small and interview-readable.

## Tests

- Add a matching `*Test` class for every `*Examples` class.
- Add tests for `*Notes` classes when the notes expose structured summary methods.
- Make each test explain the expected behavior through method names, assertions, or short local comments.
- Run `mvn test`.

## Documentation

- Add the release to the `README.md` feature index.
- Add official OpenJDK and JEP links where useful.
- Add the release to `docs/feature-map.md`.
- Mention preview or incubator status explicitly when a feature was not final in that release.

## Final Check

- Confirm `mvn test` passes.
- Confirm `git status --short` only shows intentional changes.
- Use a lower-case commit message.
