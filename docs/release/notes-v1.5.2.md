# Release Notes: v1.5.2

`java-evolution` v1.5.2 is a learning-clarity patch release for Java 5 and Java 8 API-design examples.

This release improves the generated JavaDoc and README explanations for features that experienced Java developers often discuss as API-design tools, not only as syntax.

## Highlights

- Improved Java 5 enhanced-for examples to show that enhanced `for` works with arrays and any `Iterable`, not only `List`.
- Added a small custom `Iterable` example to demonstrate how application types can become enhanced-for friendly.
- Expanded Java 5 generics examples with:
  - upper-bounded wildcards such as `Iterable<? extends ReleaseFeature>`
  - multiple bounds such as `<T extends NamedFeature & PrioritizedFeature>`
- Expanded Java 5 varargs examples with:
  - `max(int first, int... rest)` to require at least one argument at compile time
  - an `Iterable` overload for callers that already have grouped values
- Expanded Java 8 lambda examples to show project-owned methods accepting `Predicate`, `Function`, `Supplier`, and `Consumer`.
- Standardized JavaDoc tag ordering for generic method type parameters.

## Why This Matters

Java 5 and Java 8 are not only historical syntax milestones. They changed how Java APIs can be shaped.

This patch makes those design lessons clearer:

- enhanced `for` depends on a lightweight traversal contract
- generics can make APIs flexible without losing type safety
- varargs are convenient for individual arguments but less natural for existing collections
- lambdas can be accepted by your own APIs through standard functional interfaces

The examples preserve the repository's historical rule: Java 5 examples use Java 5-era language and API features, and Java 8 examples avoid Java 9+ features.

## Validation

Before publishing this release, run:

```bash
mvn -Dtest=EnhancedForLoopExamplesTest,GenericsExamplesTest,VarargsExamplesTest,LambdaExamplesTest test
mvn "-Dtest=AnnotationExamplesTest,AutoboxingExamplesTest,ConcurrencyUtilitiesExamplesTest,CovariantReturnExamplesTest,EnhancedForLoopExamplesTest,EnumExamplesTest,FormattingExamplesTest,GenericsExamplesTest,StaticImportExamplesTest,VarargsExamplesTest,CompletableFutureExamplesTest,DateTimeApiExamplesTest,DefaultMethodExamplesTest,LambdaExamplesTest,MethodReferenceExamplesTest,OptionalExamplesTest,StreamExamplesTest" test
mvn test
make docs
make links
node scripts/check-doc-navigation.mjs
git diff --check
```

Expected result:

- focused Java 5 and Java 8 tests pass
- full Maven test suite passes on JDK 25
- JavaDoc generation succeeds
- Markdown link check passes
- documentation navigation audit passes
- whitespace diff check passes

Observed release-preparation context:

- focused Java 5 and Java 8 tests passed
- full Maven test suite passed on JDK 25
- JavaDoc generation passed
- documentation navigation audit passed
- whitespace diff check passed
- `make links` should be run locally before release publication because external link checks may fail in restricted network environments

## Suggested GitHub Release Text

```text
java-evolution v1.5.2 is a learning-clarity patch for Java 5 and Java 8 API-design examples.

Highlights:
- improved Java 5 enhanced-for examples to emphasize arrays, Iterable, Set, and custom Iterable types
- expanded Java 5 generics examples with upper-bounded wildcards and multiple bounds
- expanded Java 5 varargs examples with a mandatory first argument pattern and an Iterable overload
- expanded Java 8 lambda examples to show project-owned APIs accepting Predicate, Function, Supplier, and Consumer
- standardized JavaDoc tag ordering for generic method type parameters

Validation:
- Java 5 and Java 8 focused Maven test suites
- mvn test
- make docs
- node scripts/check-doc-navigation.mjs
- git diff --check
```
