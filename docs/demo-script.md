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
mvn "-Dtest=StreamExamplesTest" test
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
mvn "-Dtest=VirtualThreadsExamplesTest" test
```

Expected point: the example shows the API shape without turning the repository into a full web application.

### 5. Show Java 25 LTS, Java 26, And Java 27 Preparation

Open:

```text
src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/README.md
src/main/java/net/jrodolfo/java_evolution/java25/scoped_values/ScopedValuesExamples.java
src/test/java/net/jrodolfo/java_evolution/java25/scoped_values/ScopedValuesExamplesTest.java
src/main/java/net/jrodolfo/java_evolution/java26/README.md
```

Say:

```text
For newer releases, I separate final features from preview, incubator, runtime, tooling, security, and removal topics. Scoped values are represented as a final executable Java 25 LTS feature. Java 26 contains executable examples for HTTP/3, final-field restrictions, Applet API removal, AOT object caching, PEM encodings, Lazy Constants, primitive patterns, and Structured Concurrency, while G1 synchronization reduction and the Vector API remain explanatory notes. The `java-27` branch prepares the next baseline with executable examples for compact headers, hybrid TLS, JFR redaction, and continuing previews, while keeping G1 default selection and Vector API evolution as notes.
```

Run:

```bash
mvn "-Dtest=ScopedValuesExamplesTest" test
```

Expected point: current-release awareness is useful only if you can also explain feature maturity and the project's build baseline.

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

Expected point: the whole repository is tested with JDK 26.

## Two Minute Version

Use this when time is short:

1. Show `README.md` and explain the version-based structure.
2. Open `java08/StreamExamples.java` to show Java 8 functional style.
3. Open `java21/VirtualThreadsExamples.java` to show modern Java concurrency.
4. Open `java25/scoped_values/README.md`, `java26/README.md`, and `java27/README.md` to show LTS/current-release awareness.
5. Run one focused command:

```bash
mvn "-Dtest=VirtualThreadsExamplesTest" test
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

Run the practical demo validation:

```bash
make demos
```

Use this before a live walkthrough to verify that the hands-on demos still match your local environment.

Run one example:

```bash
mvn "-Dtest=StreamExamplesTest" test
mvn "-Dtest=VirtualThreadsExamplesTest" test
mvn "-Dtest=ScopedValuesExamplesTest" test
```

Generate JavaDoc:

```bash
make docs
```

Open the generated JavaDoc:

```text
target/site/apidocs/index.html
```

## Java 27 Preparation

On the `java-27` branch, show `src/main/java/net/jrodolfo/java_evolution/java27/README.md` and run the focused Java 27 tests. Explain that preview APIs use an isolated child JVM with matching Java 27 preview flags, while G1 default selection and the Vector API remain notes because their useful validation depends on runtime workloads or incubator evolution.
```
