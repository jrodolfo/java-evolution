# Demo Script

Use this script when you want to walk through the repository live in an interview or technical conversation. The goal is to show that the project is organized, runnable, and tied to real Java evolution instead of being a folder of disconnected snippets.

## Five To Ten Minute Walkthrough

### 1. Open With The Repository Goal

Say:

```text
I built this repository to study Java release by release. Each package focuses on features introduced in that Java version, and each example has tests that document the expected behavior.
```

Show:

```text
README.md
docs/feature-map.md
src/main/java/net/jrodolfo/java_evolution
src/test/java/net/jrodolfo/java_evolution
```

Point out that Spring Boot is only the project shell. The examples are plain Java classes.

### 2. Show The Version-Based Structure

Show:

```text
src/main/java/net/jrodolfo/java_evolution/java08
src/main/java/net/jrodolfo/java_evolution/java21
src/main/java/net/jrodolfo/java_evolution/java25
```

Say:

```text
The package name tells you the Java release. Inside each package, the class names tell you the feature. That keeps the examples small enough to study independently.
```

### 3. Show A Java 8 Foundation Example

Open:

```text
src/main/java/net/jrodolfo/java_evolution/java08/StreamExamples.java
src/test/java/net/jrodolfo/java_evolution/java08/StreamExamplesTest.java
```

Say:

```text
Java 8 changed everyday Java style. Streams let me describe collection transformations directly instead of writing manual loops for filtering, mapping, and grouping.
```

Run:

```bash
mvn -Dtest=StreamExamplesTest test
```

Expected point: the test proves the behavior and acts as executable documentation.

### 4. Show A Java 21 Modern Java Example

Open:

```text
src/main/java/net/jrodolfo/java_evolution/java21/VirtualThreadsExamples.java
src/test/java/net/jrodolfo/java_evolution/java21/VirtualThreadsExamplesTest.java
```

Say:

```text
Java 21 is important because it is an LTS release. Virtual threads let blocking, thread-per-task code scale much better for I/O-heavy workloads without forcing every codebase into a reactive style.
```

Run:

```bash
mvn -Dtest=VirtualThreadsExamplesTest test
```

Expected point: the example shows the API shape without turning the repository into a full web application.

### 5. Show A Java 25 Current-Release Example

Open:

```text
src/main/java/net/jrodolfo/java_evolution/java25/ScopedValuesExamples.java
src/test/java/net/jrodolfo/java_evolution/java25/ScopedValuesExamplesTest.java
```

Say:

```text
For newer releases, I separate final features from preview, incubator, runtime, and tooling features. Scoped values are represented as a final Java 25 feature, while other Java 25 topics are documented as notes when they need special flags or setup.
```

Run:

```bash
mvn -Dtest=ScopedValuesExamplesTest test
```

Expected point: current-release awareness is useful only if you can also explain feature maturity.

### 6. Close With The Supporting Docs

Show:

```text
docs/learning-path.md
docs/interview-guide.md
docs/jep-index.md
docs/feature-map.md
```

Say:

```text
The feature map helps me find examples quickly, the JEP index links features to official proposals, and the learning path gives me an order for studying the project.
```

Then run:

```bash
make check
```

Expected point: the whole repository is tested with JDK 25.

## Two Minute Version

Use this when time is short:

1. Show `README.md` and explain the version-based structure.
2. Open `java08/StreamExamples.java` to show Java 8 functional style.
3. Open `java21/VirtualThreadsExamples.java` to show modern Java concurrency.
4. Open `java25/ScopedValuesExamples.java` or `java25/CompactSourceFilesNotesTest.java` to show current-release awareness.
5. Run one focused command:

```bash
mvn -Dtest=VirtualThreadsExamplesTest test
```

Close with:

```text
The important part is not only that I read about these features. I wrote small examples, documented the problem each feature solves, and backed the examples with tests.
```

## Commands To Keep Ready

Run the full validation:

```bash
make check
```

Run one example:

```bash
mvn -Dtest=StreamExamplesTest test
mvn -Dtest=VirtualThreadsExamplesTest test
mvn -Dtest=ScopedValuesExamplesTest test
```

Generate JavaDoc:

```bash
make docs
```

Open the generated JavaDoc:

```text
target/site/apidocs/index.html
```
