# Release Checklist

Use this checklist before tagging and publishing a repository release.

## Local Checks

Confirm the working tree only contains intentional release changes:

```bash
git status --short
```

Run the local validation gate:

```bash
make release-check
```

Confirm generated files are not tracked:

```bash
git status --short
```

There should be no tracked `target/`, `.DS_Store`, `.idea/`, or generated JavaDoc files.

## Documentation Checks

- Confirm `README.md` links to the main learner docs.
- Confirm `CHANGELOG.md` has the release entry.
- Confirm `docs/release/notes-v1.0.0.md` matches the intended GitHub release text.
- Confirm `docs/status-matrix.md` distinguishes feature maturity from repository representation.
- Confirm `docs/jep-index.md` has official JEP links for the release content.
- Confirm the JavaDoc site URL is visible in the README.

## GitHub Checks

After pushing the release commit, confirm these workflows are green:

- `build`
- `links`
- `javadoc pages`

Confirm GitHub Pages serves the JavaDoc:

```text
https://jrodolfo.github.io/java-evolution/
```

Confirm repository metadata:

- About description is present.
- Website points to the GitHub Pages JavaDoc URL.
- Topics are present and relevant.
- License is detected as MIT.
- README badges render correctly.

## Tag And Publish

Create the release tag:

```bash
git tag -a v1.0.0 -m "v1.0.0"
git push origin v1.0.0
```

Create a GitHub release using `docs/release/notes-v1.0.0.md` as the source text.

## Post-Release Checks

- Confirm the GitHub release is visible.
- Confirm the tag points to the intended commit.
- Confirm the build workflow is green for the tag or latest main commit.
- Confirm GitHub Pages still loads after the release.
