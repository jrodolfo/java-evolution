# Release Notes: v1.1.3

`java-evolution` v1.1.3 is a patch release focused on the Java 21 virtual-thread naming example.

The Java 21 example now demonstrates the incrementing name sequence provided by a reused named virtual-thread builder. The corresponding test verifies the generated names directly, so the example better matches the behavior it is meant to teach.

## Highlights

- Reused one configured virtual-thread builder for the Java 21 naming example.
- Demonstrated the expected `worker-1`, `worker-2`, and `worker-3` sequence.
- Strengthened the virtual-thread naming test to verify the actual sequence instead of only the `worker-` prefix.

## Why This Matters

The repository is intended to be a didactic reference. Small examples should demonstrate the exact API behavior they describe.

`Thread.Builder.name(String, long)` increments the suffix as threads are created from the same builder. Reusing the builder makes that behavior visible in the example and prevents the test from passing when the counter is reset for every thread.

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
java-evolution v1.1.3 is a patch release focused on the Java 21 virtual-thread naming example.

This release fixes the example so it reuses one named virtual-thread builder and demonstrates the expected worker-1, worker-2, and worker-3 sequence. The corresponding test now verifies that sequence directly.

Validation:
- make release-check
```
