package net.jrodolfo.java_evolution.java26.structured_concurrency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class StructuredConcurrencySixthPreviewExamplesTest {

	private final StructuredConcurrencySixthPreviewExamples examples =
			new StructuredConcurrencySixthPreviewExamples();

	@Test
	void childProcessDemonstratesStructuredSuccessAndFailure(@TempDir Path workspace) throws Exception {
		assumeTrue(Runtime.version().feature() == 26,
				"Java 26 preview APIs require a matching JDK 26 preview compiler");
		StructuredConcurrencySixthPreviewExamples.StructuredConcurrencyWorkflowResult result =
				examples.runStructuredConcurrencyWorkflow(workspace);

		assertThat(result.compilation().exitCode())
				.as("the generated source should compile when Java 26 preview APIs are enabled")
				.isZero();
		assertThat(result.compilation().output())
				.as("javac should identify the child source as preview API usage")
				.contains("uses preview features of Java SE 26");
		assertThat(Files.exists(result.sourceFile()))
				.as("the example should write preview source into the temporary workspace")
				.isTrue();
		assertThat(Files.exists(result.classesDirectory()))
				.as("the child compilation should produce a classes directory")
				.isTrue();

		assertThat(result.execution().exitCode())
				.as("the child JVM should run successfully with --enable-preview")
				.isZero();
		assertThat(result.execution().output())
				.as("successful subtasks should join as one structured unit")
				.contains("joined-count=2")
				.contains("user-state=SUCCESS")
				.contains("order-state=SUCCESS")
				.contains("combined=user:ana,order:42")
				.contains("success-cancelled=false");
		assertThat(result.execution().output())
				.as("a failed subtask should make the structured join fail as a unit")
				.contains("join-result=failed-as-unit")
				.contains("failure-cause=IllegalStateException")
				.contains("ok-state=SUCCESS")
				.contains("failed-state=FAILED")
				.contains("failed-exception=IllegalStateException");
	}

	@Test
	void probeSourceUsesRealJava26StructuredConcurrencyApi() {
		assertThat(examples.probeSource())
				.as("the child source should use StructuredTaskScope directly")
				.contains("import java.util.concurrent.StructuredTaskScope;")
				.contains("StructuredTaskScope.open")
				.contains("scope.fork")
				.contains("scope.join")
				.contains("FailedException");
	}

	@Test
	void exampleExplainsScopeAndPreviewBoundary() {
		assertThat(examples.problem())
				.as("Structured concurrency should be motivated by task-lifetime and failure problems")
				.contains("outlive")
				.contains("failures")
				.contains("cancellation");
		assertThat(examples.idea())
				.as("Structured concurrency should be described as a scoped unit of work")
				.contains("scoped")
				.contains("unit of work")
				.contains("fork")
				.contains("join");
		assertThat(examples.status())
				.as("Structured concurrency should be marked as sixth preview in Java 26")
				.contains("sixth preview")
				.contains("Java 26");
		assertThat(examples.previewBoundary())
				.as("the preview API should be isolated from the main Maven build")
				.contains("child JVM")
				.contains("--enable-preview")
				.contains("main Maven build");
	}
}
